package com.ruoyi.recommend.sightsRecommend;

import com.ruoyi.recommend.domain.SightsRecs;
import com.ruoyi.recommend.domain.UserRecs;
import com.ruoyi.sights.domain.SightsRecordScore;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.jblas.DoubleMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;
import scala.Tuple3;

import java.util.*;
import java.util.stream.Collectors;

import static com.ruoyi.recommend.util.SparkUtil.*;

/**
 * 离线推荐
 * @author Chas
 * @date 2023-2
 */
public class OfflineRecommend {
    public final static Logger logger = LoggerFactory.getLogger(OfflineRecommend.class);
    public void offlineService(){
        SparkSession spark = buildSparkSession("local[*]", "OffLineRecommend", true);
        Dataset<SightsRecordScore> sightsRecordScore = (Dataset<SightsRecordScore>) readFromMysql(spark, "sights_record_score");
        JavaRDD<Tuple3<Long, Long, Double>> ratingRDD = sightsRecordScore.toJavaRDD()
                .map((Function<SightsRecordScore, Tuple3<Long, Long, Double>>)
                        sightsRecordScore1 ->
                        new Tuple3<>(sightsRecordScore1.getUserId(), sightsRecordScore1.getSightsId(), sightsRecordScore1.getScore()))
                .cache();
        // 提取出所有用户和景点的数据集
        JavaRDD<Integer> userRDD = ratingRDD.map((Function<Tuple3<Long, Long, Double>, Integer>)
                f -> Math.toIntExact(f._1().intValue())).distinct();
        JavaRDD<Integer> sightsRDD = ratingRDD.map((Function<Tuple3<Long, Long, Double>, Integer>)
                f -> Math.toIntExact(f._2().intValue())).distinct();

        // 核心计算过程
        // 1. 训练隐语义模型
        JavaRDD<Rating> dataTrain = ratingRDD.map(x -> new Rating(x._1().intValue(),x._2().intValue(), x._3()));

        int rank = 5;
        int iterations = 10;
        double lambda = 0.01;
        // 定义模型训练的参数，rank隐特征个数，iterations迭代词数，lambda正则化系数
        MatrixFactorizationModel model = ALS.train(dataTrain.rdd(),rank,iterations,lambda);

        // 2. 获得预测评分矩阵，得到用户的推荐列表
        // 用userRDD和productRDD做一个笛卡尔积，得到空的userProductsRDD表示的评分矩阵
        JavaPairRDD<Integer, Integer> userSights = userRDD.cartesian(sightsRDD);
        JavaRDD<Rating> predictRating = model.predict(userSights);
        Map<Integer, Iterable<Tuple2<Integer, Double>>> iterableMap = predictRating
                .filter((Function<Rating, Boolean>) rating -> rating.rating() > 0)
                .mapToPair((PairFunction<Rating, Integer, Tuple2<Integer, Double>>)
                        rating -> new Tuple2<>(rating.user(),
                                new Tuple2<>(rating.product(), rating.rating())))
                .groupByKey().collectAsMap();
        List<UserRecs> all = new LinkedList<>();
        // 从预测评分矩阵中提取得到用户推荐列表
        Set<Integer> integers = iterableMap.keySet();
        integers.forEach(f->{
            Iterable<Tuple2<Integer, Double>> s = iterableMap.get(f);
            List<UserRecs> temp = new LinkedList<>();
            while (s.iterator().hasNext()){
                Tuple2<Integer, Double> next = s.iterator().next();
                temp.add(new UserRecs(f.longValue(), next._1.longValue(),next._2.doubleValue()));
            }
            List<UserRecs> collect = temp
                    .stream()
                    .sorted(Comparator.comparingDouble(UserRecs::getSightsScore).reversed())
                    .limit(10)
                    .collect(Collectors.toList());
            all.addAll(collect);
        });

        Dataset<Row> frame = spark.createDataFrame(all, UserRecs.class);
        writeToMysql(frame,"UserRecs","overwrite");

        // 3. 利用景点的特征向量，计算景点的相似度列表
        JavaRDD<Tuple2<Long, DoubleMatrix>> sightsFeatures = model
                .productFeatures()
                .toJavaRDD()
                .map((Function<Tuple2<Object, double[]>, Tuple2<Long, DoubleMatrix>>)
                        f -> new Tuple2<Long, DoubleMatrix>((Long) f._1, new DoubleMatrix(f._2)));
        Map<Long, Iterable<Tuple2<Long, Double>>> asMap = sightsFeatures
                .cartesian(sightsFeatures)
                .filter((Function<Tuple2<Tuple2<Long, DoubleMatrix>, Tuple2<Long, DoubleMatrix>>, Boolean>)
                        f -> !Objects.equals(f._1._1, f._2._1))
                .mapToPair((PairFunction<Tuple2<Tuple2<Long, DoubleMatrix>, Tuple2<Long, DoubleMatrix>>, Long, Tuple2<Long, Double>>) f -> {
                    double sim = consinSim(f._1._2, f._2._2);
                    return new Tuple2<>(f._1._1, new Tuple2<>(f._2._1, sim));
                })
                .filter(f -> f._2._2 > 0.4)
                .groupByKey()
                .collectAsMap();
        List<SightsRecs> allSights = new LinkedList<>();
        Set<Long> sights = asMap.keySet();
        sights.forEach(f->{
            Iterable<Tuple2<Long, Double>> s = asMap.get(f);
            List<SightsRecs> temp = new LinkedList<>();
            while (s.iterator().hasNext()){
                Tuple2<Long, Double> next = s.iterator().next();
                temp.add(new SightsRecs(f, next._1, next._2));
            }
            List<SightsRecs> collect = temp
                    .stream()
                    .sorted(Comparator.comparingDouble(SightsRecs::getSightsScore).reversed())
                    .limit(10)
                    .collect(Collectors.toList());
            allSights.addAll(collect);
        });
        Dataset<Row> dataFrame = spark.createDataFrame(allSights, SightsRecs.class);
        writeToMysql(dataFrame,"SightsRecs","overwrite");
        spark.stop();
    }

}
