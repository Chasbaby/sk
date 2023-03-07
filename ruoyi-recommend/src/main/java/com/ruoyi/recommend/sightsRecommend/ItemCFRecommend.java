package com.ruoyi.recommend.sightsRecommend;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.ruoyi.recommend.domain.SightsRecs;
import com.ruoyi.sights.domain.SightsRecordScore;
import org.apache.commons.collections.IteratorUtils;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.*;
import java.util.stream.Collectors;

import static com.ruoyi.recommend.util.SparkUtil.*;
import static org.apache.commons.collections4.IteratorUtils.getIterator;

public class ItemCFRecommend {

    public void ItemCFService(){
        SparkSession spark = buildSparkSession("local[*]", "ItemCFRecommend", true);
        Dataset<SightsRecordScore> sightsRecordScore = (Dataset<SightsRecordScore>) readFromMysql(spark, "sights_record_score");
        Dataset<Row> ratingDF = sightsRecordScore
                .toDF("userId", "sightsId", "score")
                .cache();
        // TODO: 核心算法，计算同现相似度，得到商品的相似列表
        // 统计每个景点的评分个数，按照productId来做group by
        Dataset<Row> sightsRatingCountDF = ratingDF
                .groupBy("sightsId")
                .count();
        // 在原有的评分表上rating添加count
        Dataset<Row> ratingWithCountDF = ratingDF
                .join(sightsRatingCountDF, "sightsId");
        // 将评分按照用户id两两配对，统计两个景点被同一个用户评分过的次数
        Dataset<Row> joinDF = ratingWithCountDF
                .join(ratingWithCountDF, "userId")
                .toDF("userId"
                        , "sightsIdOne", "scoreOne", "countOne"
                        , "sightsIdTwo", "scoreTwo", "countTwo")
                .select("userId", "sightsIdOne", "countOne",
                        "sightsIdTwo", "countTwo");
        // 创建一张临时表，用于写sql查询
        joinDF.createOrReplaceTempView("joined");
        // sightsIdOne,sightsIdTwo 做group by，统计userId的数量，就是对两个商品同时评分的人数
        Dataset<Row> cooccurrenceDF  = spark.sql("select sightsIdOne,sightsIdTwo," +
                "count(userId) as cocount," +
                "first(countOne) as countOne," +
                "first(countTwo) as countTwo from joined " +
                "group by sightsIdOne,sightsIdTwo").cache();
        // 提取需要的数据，包装成( sightsId1, (sightsId2, score) )
        Map<Long, Iterable<Tuple2<Long, Double>>> asMap = cooccurrenceDF.toJavaRDD().mapToPair((PairFunction<Row, Long, Tuple2<Long, Double>>) row -> {
            double sim = cooccurrenceSim(row.getAs("cocount"),
                    row.getAs("countOne"),
                    row.getAs("countTwo"));
            return new Tuple2<>(row.getLong(0), new Tuple2<>(row.getLong(1), sim));
        }).groupByKey().collectAsMap();
        Set<Long> keySet = asMap.keySet();
        List<SightsRecs> all = new LinkedList<>();
        keySet.forEach(f->{
            Iterable<Tuple2<Long, Double>> iterable = asMap.get(f);
            List<Tuple2<Long, Double>> list = IteratorUtils.toList(getIterator(iterable));
            List<SightsRecs> temp = list
                    .stream()
                    .filter(data -> !data._1.equals(f))
                    .map((SFunction<Tuple2<Long, Double>, SightsRecs>) data ->
                            new SightsRecs(f, data._1, data._2))
                    .sorted(Comparator.comparingDouble(SightsRecs::getSightsScore).reversed())
                    .limit(10)
                    .collect(Collectors.toList());
            all.addAll(temp);
        });
        writeToMysql(spark.createDataFrame(all,SightsRecs.class),
                "ItemCFSights","overwrite");
        spark.stop();
    }
}
