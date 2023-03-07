package com.ruoyi.recommend.sightsRecommend;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.recommend.domain.SightsRecs;
import com.ruoyi.recommend.domain.SightsUserStream;
import com.ruoyi.sights.domain.SightsRecordScore;
import com.ruoyi.sights.mapper.SightsRecordScoreMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import scala.Tuple2;


import java.util.*;
import java.util.stream.Collectors;

import static com.ruoyi.recommend.util.SparkUtil.*;

/**
 * 实时推荐
 * @author Chas
 * @date 2023-2
 */
public class OnLineRecommend {
    public final static Logger logger = LoggerFactory.getLogger(OnLineRecommend.class);
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SightsRecordScoreMapper sightsRecordScoreMapper;

    /**
     * 实时推荐核心代码
     */
    public void OnlineService(){

        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "localhost:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class.getName());
        kafkaParams.put("value.deserializer", StringDeserializer.class.getName());
        kafkaParams.put("group.id", "OnLineRecommendStreaming");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", "false");

        // 接收数据的主题  (消费者)
        Collection<String> topics = Arrays.asList("OnLineRecommend");

        SparkSession spark = buildSparkSession("local[*]", "OnLineRecommend", true);
        SparkContext sc = spark.sparkContext();
        JavaSparkContext javaSparkContext = JavaSparkContext.fromSparkContext(sc);
        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(javaSparkContext, new Duration(20000));

        // 从数据库中读取信息
        Dataset<SightsRecs> dataset = (Dataset<SightsRecs>) readFromMysql(spark, "");
        // 加载数据，相似度矩阵，广播出去
        Map<Long, Iterable<Tuple2<Long, Double>>> longIterableMap = dataset
                .toJavaRDD()
                .mapToPair((PairFunction<SightsRecs, Long, Tuple2<Long, Double>>) sightsRecs ->
                        new Tuple2<Long, Tuple2<Long, Double>>(sightsRecs.getSightsIdOne(),
                                new Tuple2<Long, Double>(sightsRecs.getSightsIdTwo(),
                                        sightsRecs.getSightsScore())))
                .groupByKey()
                .collectAsMap();
        Set<Long> keySet = longIterableMap.keySet();
        Map<Long,Map<Long,Double>> simMatrix = new HashMap<>();
        keySet.forEach(f->{
            Iterable<Tuple2<Long, Double>> tuple = longIterableMap.get(f);
            Map<Long,Double> longDoubleMap = new HashMap<>();
            while (tuple.iterator().hasNext()){
                Tuple2<Long, Double> next = tuple.iterator().next();
                longDoubleMap.put(next._1,next._2);
            }
            simMatrix.put(f,longDoubleMap);
        });

        // 加载数据 并将数据放入广播变量
        Broadcast<Map<Long, Map<Long, Double>>> broadcast = javaSparkContext.broadcast(simMatrix);

        JavaInputDStream<ConsumerRecord<Object, Object>> dStream = KafkaUtils.createDirectStream(
                javaStreamingContext,
                LocationStrategies.PreferConsistent(),
                ConsumerStrategies.Subscribe(topics, kafkaParams));

        JavaDStream<SightsUserStream> map = dStream.map((Function<ConsumerRecord<Object, Object>, SightsUserStream>) objectStringConsumerRecord -> {
            String value = (String) objectStringConsumerRecord.value();
            String[] split = value.split("\\|");
            return new SightsUserStream(Long.parseLong(split[0].trim()),
                    Long.parseLong(split[1].trim()),
                    Double.parseDouble(split[2].trim()),
                    Long.parseLong(split[4].trim()));
        });
        List<SightsUserStream> SightsUserStream= new LinkedList<>();
        map.foreachRDD((VoidFunction<JavaRDD<SightsUserStream>>) sightsUserStreamJavaRDD ->
                sightsUserStreamJavaRDD.foreach(f->{
                    // TODO: 核心算法流程
                    // 1. 从redis里取出当前用户的最近评分，保存成一个Map[(productId, score)]
                    Map<Long, Double> ratings = getUserRecentlyRatings(10, f.getUserId());
                    // 2. 从相似度矩阵中获取当前商品最相似的商品列表，作为备选列表，保存成一个List[productId]
                    List<Long> candidateSights  = getTopSimSights(10, f.getSightsId(), f.getUserId(), broadcast.value());
                    // 3. 计算每个备选商品的推荐优先级，得到当前用户的实时推荐列表，保存成 Array[(productId, score)]
                    Map<Long, Double> StreamRecs = computeSightsScore(candidateSights, ratings, broadcast.value());
                    //todo 将 StreamRecs 存入数据库
                    Set<Long> longKey = StreamRecs.keySet();
                    longKey.forEach(key->{
                        Double score = StreamRecs.get(key);
                        SightsUserStream.add(new SightsUserStream(f.getUserId(),key,score,new Date().getTime()));
                    });
                })
        );
        Dataset<Row> dataFrame = spark.createDataFrame(SightsUserStream, SightsUserStream.getClass());
        writeToMysql(dataFrame,"onlineRecs","overwrite");
        javaStreamingContext.start();
        try {
            javaStreamingContext.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户最近评分
     * @param num
     * @param userId
     * @return map
     */
    public  Map<Long,Double> getUserRecentlyRatings(int num,Long userId){
        Map map = new HashMap();
        List<String> list = redisCache.getCacheListRange("userId:" + userId.toString(), 0, num);
        list.forEach(f->{
           String[] split = f.split(":");
           map.put(Long.parseLong(split[0]),Double.parseDouble(split[1]));
        });
        return map;
  }

    /**
     * 获取当前景点的相似列表，并过滤掉用户已经评分过的，作为备选列表
     * @param num
     * @param sightsId
     * @param userId
     * @param simSightsMatrixBC
     * @return 景点id列表
     */
    public List<Long> getTopSimSights(int num,Long sightsId,Long userId,
                                      Map<Long,Map<Long,Double>> simSightsMatrixBC ){
      Map<Long, Double> doubleMap = simSightsMatrixBC.get(sightsId);
      List<SightsRecordScore> ratingList = sightsRecordScoreMapper.getRatingListByUserId(userId);
      List<Long> ratingExist = ratingList.stream().map(SightsRecordScore::getSightsId).collect(Collectors.toList());
      ratingExist.forEach(f->{
          // 如果有这个key 那么把这个删了
          if(doubleMap.containsValue(f)){
              doubleMap.remove(f);
          }
      });
      Map<Long, Double> sortedMap = new LinkedHashMap<Long, Double>();
      List<Map.Entry<Long, Double>> entryList = new ArrayList<>(doubleMap.entrySet());
      // 排序
      entryList.sort(Comparator.comparingDouble(Map.Entry::getValue));
      // 放入新的集合
      entryList.forEach(f-> sortedMap.put(f.getKey(),f.getValue()));
      List<Long> collect = sortedMap.keySet().stream().limit(num).collect(Collectors.toList());
      return collect;
  }

    /**
     *  计算每个备选商品的推荐得分
     * @param candidateSights
     * @param userRecentlyRatings
     * @param simProducts
     * @return 推荐列表 按照得分排序
     */
    public Map<Long,Double> computeSightsScore(List<Long> candidateSights,
                                                Map<Long,Double> userRecentlyRatings,
                                                Map<Long,Map<Long,Double>> simProducts){
        //定义一个长度可变数组Map，用于保存每一个备选景点的基础得分，(productId, score)
        ArrayList<Map<Long,List<Double>>> scores  = new ArrayList<>();
        // 定义两个map，用于保存每个景点的高分和低分的计数器，productId -> count
        Map<Long, Double> inMap = new HashMap<>();
        Map<Long, Double> deMap = new HashMap<>();
        Set<Long> userRecentlyRating = userRecentlyRatings.keySet();
        candidateSights.stream().forEach(candidateSight->{
            Map<Long,List<Double>> buffMap = new HashMap<>();
            List<Double> list = new LinkedList<>();
            userRecentlyRating.forEach(f1->{
                // 从相似度矩阵中获取当前备选景点和当前已评分景点间的相似度
                Double simScore = getSightsSimScore(candidateSight, f1, simProducts);
                if (simScore>0.4){
                    // 按照公式进行加权计算，得到基础评分
                    list.add(simScore * f1.doubleValue());
                    if (f1.doubleValue()>3.0){
                        inMap.put(candidateSight,inMap.getOrDefault(candidateSight,0.0) + 1 );
                    }else {
                        deMap.put(candidateSight,deMap.getOrDefault(candidateSight,0.0) + 1 );
                    }
                }
            });
            buffMap.put(candidateSight,list);
            list.clear();
            scores.add(buffMap);
        });
        Map<Long,Double> finalScore = new HashMap<>();
        scores.stream().forEach(countNum->{
            // 这里的set 里面固定就一个元素
            Set<Long> sightsId = countNum.keySet();
            // 这里只能遍历一次哦
            sightsId.forEach(f1->{
                List<Double> list = countNum.get(f1);
                double sum = list.stream().mapToDouble(value -> value).sum();
                int size = list.size();
                double score = sum / size +
                        log(inMap.getOrDefault(f1, 1.0), 10) +
                        log(deMap.getOrDefault(f1, 1.0), 10);
                finalScore.put(f1,score);
            });
        });
        // 对 finalScore 进行排序
        Map<Long,Double> sortedMap = new LinkedHashMap<>();
        ArrayList<Map.Entry<Long, Double>> arrayList = new ArrayList<>(finalScore.entrySet());
        arrayList.sort(Comparator.comparingDouble(Map.Entry::getValue));
        arrayList.stream().forEach(f-> sortedMap.put(f.getKey(),f.getValue()));
        return sortedMap;
    }

    /**
     *  相似度
     * @param sightsIdOne 第一个景点
     * @param sightsIdTwo 第二个景点
     * @param simSightsId 相似度矩阵
     * @return 两者相似度
     */
    public static Double getSightsSimScore(Long sightsIdOne,Long sightsIdTwo,
                                           Map<Long,Map<Long,Double>> simSightsId){
        boolean key = simSightsId.containsKey(sightsIdOne);
        if(key){
            Map<Long, Double> doubleMap = simSightsId.get(sightsIdOne);
            boolean key1 = doubleMap.containsKey(sightsIdTwo);
            if (key1){
                return doubleMap.get(sightsIdTwo);
            }else {
                return 0.0;
            }

        }else {
            return 0.0;
        }

    }

    /**
     * 自定义log函数，以N为底
     * @param num
     * @param N
     * @return 值
     */
    public static Double log(Double num,int N){
        return Math.log(num)/Math.log(N);
    }
}
