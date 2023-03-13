package com.ruoyi.recommend.sightsRecommend;

import com.ruoyi.sights.domain.SightsRecordScore;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.ruoyi.recommend.util.SparkUtil.*;

public class StatisticRecommend {

    public static void  StatisticService(){
        SparkSession spark = buildSparkSession("local[*]", "StatisticRecommend", true);
        Dataset<SightsRecordScore> recordScore = (Dataset<SightsRecordScore>) readFromMysql(spark, "sights_record_score");
        Dataset<Row> ratingDF = recordScore.toDF();

        ratingDF.createOrReplaceTempView("ratings");
        // TODO: 用spark sql去做不同的统计推荐
        // 1. 历史热门景点，按照评分个数统计，sightsId，count
        Dataset<Row> rateMoreSightsIdDF = spark.sql("select sights_id ,count(sights_id) as count from ratings " +
                "group by sights_id order by count desc");
        writeToMysql(rateMoreSightsIdDF,"RateMoreSights","overwrite");

        // 2. 近期热门景点，把时间戳转换成yyyyMM格式进行评分个数统计，sightsId, count, yearmonth
        // 创建一个日期格式化工具
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        // 注册UDF，将timestamp转化为年月格式yyyyMM
        spark.udf().register("changeData",
                (UDF1<Date, Date>) a ->
                        new Date(format.format(a)),
                DataTypes.DateType);
        // 把原始ratings数据转换成想要的结构sightsId, score, yearmonth
        Dataset<Row> ratingOfYearMonthDF = spark.sql("select sights_id,score, " +
                "changeData(create_time) as yearmonth from ratings");
        ratingOfYearMonthDF.createOrReplaceTempView("ratingOfMonth");
        Dataset<Row> rateMoreRecentlySights = spark.sql("select sights_id, count(sights_id) as count, " +
                "yearmonth from ratingOfMonth group by yearmonth, " +
                "sights_id order by yearmonth desc, count desc");
        writeToMysql(rateMoreRecentlySights,"RateMoreRecentlySights","overwrite");
        //3.优质景点统计
        Dataset<Row> averageSightsDF = spark.sql("select sights_d, avg(score) as avg from ratings group by sights_id order by avg desc");
        writeToMysql(averageSightsDF,"AverageSights","overwrite");
        spark.stop();
    }

}
