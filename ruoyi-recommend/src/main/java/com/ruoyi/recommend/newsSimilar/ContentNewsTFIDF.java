package com.ruoyi.recommend.newsSimilar;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.home.domain.dto.NewsRecs;
import com.ruoyi.home.domain.dto.newsKeyDTO;
import com.ruoyi.home.service.IHomeNewsService;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.jblas.DoubleMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scala.Serializable;
import scala.Tuple2;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.ruoyi.recommend.sightsRecommend.ContentRecommend.getTokenizerResult;
import static com.ruoyi.recommend.util.SparkUtil.*;

/**
 * @author chas
 * @introduction  新闻相似度
 * @date 2023-5-26
 */
public class ContentNewsTFIDF implements Serializable {


    public void startTFIDFNews(){
        List<newsKeyDTO> news = SpringUtils.getBean(IHomeNewsService.class).convertToKey();
        SparkSession spark = buildSparkSession("local[1]", "NEWS_TF_IDF", true);
        Dataset<Row> dataset = spark.createDataFrame(news, newsKeyDTO.class);
        JavaRDD<newsKeyDTO> newsKeyDTOJavaRDD = dataset
                .toJavaRDD().map((Function<Row, newsKeyDTO>) v1 -> {
                    String keys = v1.getAs("newsKey");
                    String tags = keys.replace(",", " ");
                    return new newsKeyDTO(v1.getAs("newsId"),
                            v1.getAs("newsTitle"), tags);
                });

        Dataset<Row> cache = spark.createDataFrame(newsKeyDTOJavaRDD, newsKeyDTO.class).cache();
        Dataset<Row> newsKey = getTokenizerResult(cache, "newsKey");

        JavaRDD<Tuple2<Long, DoubleMatrix>> map = newsKey.toJavaRDD().map((Function<Row, Tuple2<Long, double[]>>) f -> {
            SparseVector vector = f.getAs("features");
            double[] array = vector.toArray();
            return new Tuple2<>(f.getAs("newsId"), array);
        }).map((Function<Tuple2<Long, double[]>, Tuple2<Long, DoubleMatrix>>) f ->
                new Tuple2<>(f._1, new DoubleMatrix(f._2)));

        List<NewsRecs> collect = map.cartesian(map).filter((Function<Tuple2<Tuple2<Long, DoubleMatrix>,
                        Tuple2<Long, DoubleMatrix>>, Boolean>) f -> !f._1._1.equals(f._2._1))
                .mapToPair((PairFunction<Tuple2<Tuple2<Long, DoubleMatrix>, Tuple2<Long, DoubleMatrix>>, Long, Tuple2<Long, Double>>)
                        f -> new Tuple2<>(f._1._1, new Tuple2<>(f._2._1, consinSim(f._1._2(), f._2._2())))).filter(f -> f._2._2 > 0.01)
                .map((Function<Tuple2<Long, Tuple2<Long, Double>>, NewsRecs>)
                        f -> new NewsRecs(f._1, f._2._1, f._2._2)).collect();
        writeToMysql(spark.createDataFrame(collect,NewsRecs.class),"ContentBasedNewsRecs","overwrite");
        spark.close();
    }
}
