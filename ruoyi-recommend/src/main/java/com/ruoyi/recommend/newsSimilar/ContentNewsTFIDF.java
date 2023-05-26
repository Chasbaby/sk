package com.ruoyi.recommend.newsSimilar;

import com.ruoyi.home.domain.dto.newsKeyDTO;
import com.ruoyi.home.service.IHomeNewsService;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.ruoyi.recommend.util.SparkUtil.buildSparkSession;

/**
 * @author chas
 * @introduction  新闻相似度
 * @date 2023-5-26
 */
public class ContentNewsTFIDF {

    @Autowired
    private IHomeNewsService newsService;

    public void startTFIDFNews(){
        List<newsKeyDTO> news = newsService.convertToKey();
        SparkSession spark = buildSparkSession("local[*]", "NEWS_TF_IDF", true);
        Dataset<Row> dataset = spark.createDataFrame(news, newsKeyDTO.class);
        dataset
                .toJavaRDD().map((Function<Row, newsKeyDTO>) v1 -> {
                    String keys = v1.getAs("newsKey");
                    String tags = keys.replace(",", " ");
                    return new newsKeyDTO(v1.getAs(""),v1.getAs(""),tags);
                });

    }
}
