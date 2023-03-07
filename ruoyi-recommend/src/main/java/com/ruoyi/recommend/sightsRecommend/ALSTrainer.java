package com.ruoyi.recommend.sightsRecommend;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.sights.domain.SightsRecordScore;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import scala.Tuple2;
import scala.Tuple3;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ruoyi.recommend.util.SparkUtil.buildSparkSession;
import static com.ruoyi.recommend.util.SparkUtil.readFromMysql;

/**
 * 参数训练器
 * @author Chas
 * @date 2023-2
 */
public class ALSTrainer implements Serializable {

    @Autowired
    private RedisCache redisCache;

    /**
     * 模拟训练哈
     */
    public void TrainParam(){
        SparkSession spark = buildSparkSession("local[*]", "ALSTrainer", true);
        Dataset<SightsRecordScore> sightsRecordScore = (Dataset<SightsRecordScore>) readFromMysql(spark, "sights_record_score");
        JavaRDD<Rating> ratingRDD = sightsRecordScore
                .toJavaRDD()
                .map((Function<SightsRecordScore, Rating>)
                        f -> new Rating(
                                f.getUserId().intValue(),
                                f.getSightsId().intValue(),
                                f.getScore()))
                .cache();
        double[] doubles ={0.8,0.2};
        JavaRDD<Rating>[] split = ratingRDD.randomSplit(doubles);
        JavaRDD<Rating> trainingRDD = split[0];
        JavaRDD<Rating> testingRDD = split[1];
        adjustALSParams(trainingRDD,testingRDD);
        spark.stop();
    }

    /**
     * 获取较优参数，提供给离线使用
     * @param trainData 训练数据
     * @param testData 测试数据
     * 最终数据应该存 redis
     */
    public  void adjustALSParams(JavaRDD<Rating> trainData,JavaRDD<Rating> testData){
        // 应该变成动态
        int[] rank = {5,10,20,50};
        double[] lambda= {1,0.1,0.01};
        List<Tuple3<Integer,Double,Double>> result = new LinkedList<>();

        Arrays.stream(rank).forEach(f1->{
            Arrays.stream(lambda).forEach(f2->{
                MatrixFactorizationModel model = ALS.train(trainData.rdd(), f1, 10, f2);
                Double rmse = getRMSE(model, testData);
                result.add(new Tuple3<>(f1, f2, rmse));
            });
        });
        Tuple3<Integer, Double, Double> params = result
                .stream()
                .sorted((o1, o2) -> o1._3() - o2._3() > 0 ? 1 : 0)
                .findFirst()
                .get();
        redisCache.setCacheObject("TrainParam",params);

    }

    /**
     * 计算RMSE
     * @param model
     * @param data
     * @return Double
     */
    public static Double getRMSE(MatrixFactorizationModel model,JavaRDD<Rating> data){
        JavaPairRDD<Integer, Integer> userSights = data
                .mapToPair((PairFunction<Rating, Integer, Integer>) rating ->
                        new Tuple2<>(rating.user(), rating.product()));
        JavaRDD<Rating> predictRating = model.predict(userSights);
        // 按照公式计算rmse，首先把预测评分和实际评分表按照(userId, productId)做一个连接
        JavaPairRDD<Tuple2<Integer, Integer>, Double> observed = data.mapToPair((PairFunction<Rating, Tuple2<Integer, Integer>, Double>) rating ->
                new Tuple2<>(new Tuple2<>(rating.user(), rating.product()), rating.rating()));
        JavaPairRDD<Tuple2<Integer, Integer>, Double> predict = predictRating.mapToPair((PairFunction<Rating, Tuple2<Integer, Integer>, Double>) rating ->
                new Tuple2<>(new Tuple2<>(rating.user(), rating.product()), rating.rating()));
        JavaRDD<Double> err2 = observed
                .join(predict)
                .map((Function<Tuple2<Tuple2<Integer, Integer>, Tuple2<Double, Double>>, Double>)
                        f -> (f._2._1 - f._2._2) * (f._2._1 - f._2._2));
        Double sum = err2.reduce((Function2<Double, Double, Double>) (a, b) -> a + b);
        return Math.sqrt(sum/err2.count());
    }

}
