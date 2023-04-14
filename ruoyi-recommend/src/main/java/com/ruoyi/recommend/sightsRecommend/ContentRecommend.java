package com.ruoyi.recommend.sightsRecommend;

import com.ruoyi.recommend.domain.SightsRecs;
import com.ruoyi.sights.domain.SightsTags;
import com.ruoyi.sights.mapper.SightsTagsMapper;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.ml.feature.HashingTF;
import org.apache.spark.ml.feature.IDF;
import org.apache.spark.ml.feature.IDFModel;
import org.apache.spark.ml.feature.Tokenizer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.jblas.DoubleMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import scala.Tuple2;


import java.util.List;

import static com.ruoyi.recommend.util.SparkUtil.*;

/**
 * 内容相似度 待完成
 */

public class ContentRecommend {

    @Autowired
    private SightsTagsMapper sightsTagsMapper;

    public void ContentService(){
        List<SightsTags> sights = sightsTagsMapper.selectAllSightsAndTags();
        SparkSession spark = buildSparkSession("local[*]", "ContentRecommend", true);
        Dataset<Row> sightsTags = spark.createDataFrame(sights, SightsTags.class);
        JavaRDD<SightsTags> temp = sightsTags
                .toJavaRDD()
                .map((Function<Row, SightsTags>) row -> {
            String tag = row.getAs("tags");
            String tags = tag.replace("|", " ");
            return new SightsTags(row.getAs("sightsId"),
                    row.getAs("sightsName"), tags);
        });
        Dataset<Row> sightsTagsDF = spark.createDataFrame(temp, SightsTags.class).cache();

        // TODO: 用TF-IDF提取商品特征向量
        Dataset<Row> rescaledDataDF = getTokenizerResult(sightsTagsDF);

        JavaRDD<Tuple2<Long, DoubleMatrix>> sightsFeatures = rescaledDataDF
                .toJavaRDD()
                .map((Function<Row, Tuple2<Long, double[]>>) row ->
                        new Tuple2<>(row.getAs("sightsId"),
                                row.getAs("features")))
                .map((Function<Tuple2<Long, double[]>, Tuple2<Long, DoubleMatrix>>) f ->
                        new Tuple2<>(f._1, new DoubleMatrix(f._2)));
        // 两两配对商品，计算余弦相似度
        List<SightsRecs> sightsRecs = sightsFeatures
                .cartesian(sightsFeatures)
                .filter((Function<Tuple2<Tuple2<Long, DoubleMatrix>, Tuple2<Long, DoubleMatrix>>, Boolean>)
                        f -> !f._1._1.equals(f._2._2))
                .mapToPair((PairFunction<Tuple2<Tuple2<Long, DoubleMatrix>, Tuple2<Long, DoubleMatrix>>, Long, Tuple2<Long, Double>>)
                        f -> new Tuple2<>(f._1._1, new Tuple2<>(f._2._1, consinSim(f._1._2, f._2._2))))
                .filter(f -> f._2._2 > 0.4)
                .map((Function<Tuple2<Long, Tuple2<Long, Double>>, SightsRecs>) f ->
                        new SightsRecs(f._1, f._2._1, f._2._2))
                .collect();
        writeToMysql(spark.createDataFrame(sightsRecs,SightsRecs.class),"ContentBasedSightsRecs","overwrite");
        spark.stop();
    }

    private Dataset<Row> getTokenizerResult(Dataset<Row> sightsTagsDF) {
        // 1. 实例化一个分词器，用来做分词，默认按照空格分
        Tokenizer tokenizer = new Tokenizer()
                .setInputCol("tags")
                .setOutputCol("words");
        // 用分词器做转换，得到增加一个新列words的DF
        Dataset<Row> wordsDataDF = tokenizer.transform(sightsTagsDF);
        // 2. 定义一个HashingTF工具，计算频次
        HashingTF hashingTF = new HashingTF()
                .setInputCol("words")
                .setOutputCol("rawFeatures").setNumFeatures(500);
        Dataset<Row> featurizedDataDF = hashingTF.transform(wordsDataDF);
        // 3. 定义一个IDF工具，计算TF-IDF
        IDF idf = new IDF().setInputCol("rawFeatures").setOutputCol("features");
        // 训练一个idf模型
        IDFModel idfModel = idf.fit(featurizedDataDF);
        // 得到增加新列features的DF
        Dataset<Row> rescaledDataDF = idfModel.transform(featurizedDataDF);
        return rescaledDataDF;
    }

}
