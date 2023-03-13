package com.ruoyi.recommend.util;


import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.jblas.DoubleMatrix;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @author Chas
 * @content Spark工具类
 */
public class SparkUtil {

    /**
     * 景点评分前缀
     */
    public static String SIGHTS_RATING_PREFIX = "SIGHTS_RATING_PREFIX";

    /**
     * redis 景点评分队列长度
     */
    public static int REDIS_SIGHTS_RATING_QUEUE_SIZE = 40;


    /**
     * 创建 SparkSession 环境 附带 隐式转换
     * @param master
     * @param appName
     * @param implicits
     * @return SparkSession
     */
    public static SparkSession buildSparkSession(@NotNull String master, @NotNull String appName, boolean implicits){
        SparkConf conf = new SparkConf().setMaster(master).setAppName(appName);
        SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();
        if (implicits){
            sparkSession.implicits();
        }
        return sparkSession;
    }

    /**
     * 创建 SparkSession 环境
     * @param master
     * @param appName
     * @return
     */
    public static SparkSession buildSparkSession(@NotNull String master,@NotNull String appName){
        SparkConf conf = new SparkConf().setMaster(master).setAppName(appName);
        SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();
        return  sparkSession;
    }

    /**
     * 关闭 Spark 服务
     * @param sparkSession
     */
    public static void closeSparkSession( @NotNull SparkSession sparkSession){
        sparkSession.close();
    }


    /**
     * 将数据直接写进Mysql数据库
     * @param map 数据集
     * @param table 表
     * @param mode 存储模式
     *
     *             mode的参数
     * overwrite:表示如果目标文件目录中数据已经存在了，则用需要保存的数据覆盖掉已经存在的数据
     * append:表示如果目标文件目录中数据已经存在了,则将数据追加到目标文件中。数据追加方式是：先将表中的所有索引删除，再追加数据
     * ignore:表示如果目标文件目录中数据已经存在了,则不做任何操作
     * error:表示如果目标文件目录中数据已经存在了，则抛异常(这个是默认的配置)
     *
     */
    public static void writeToMysql(@NotNull Dataset<?> map, @NotNull String table, @Nullable String mode){
        map.write()
                .format("jdbc")
                .option("url","jdbc:mysql://localhost:3306/ruoyi?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8")
                .option("driver","com.mysql.cj.jdbc.Driver")
                .option("username","root")
                .option("user","root")
                .option("password","2412798007")
                .option("dbtable",table)
                .mode(mode)
                .save();
    }

    /**
     * 从数据库中读取 表 数据
     * @param spark SparkSession
     * @param table 表
     * @return Dataset<>
     */
    public static Dataset<?> readFromMysql(@NotNull SparkSession spark,@NotNull String table){
        return spark.read()
                .format("jdbc")
                .option("url","jdbc:mysql://localhost:3306/ruoyi?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8")
                .option("driver","com.mysql.cj.jdbc.Driver")
                .option("username","root")
                .option("user","root")
                .option("password","2412798007")
                .option("dbtable",table)
                .load();
    }

    /**
     * 相似度公式  我们可以在这里 改变 评判标准
     * @param product1
     * @param product2
     * @return double
     */
    public static double consinSim(@NotNull DoubleMatrix product1, @NotNull DoubleMatrix product2){
        return product1.dot(product2)/(product1.norm2()*product2.norm2());
    }

    public static double cooccurrenceSim(@NotNull Long coCount,@NotNull Long count1,@NotNull Long count2){
        return coCount/Math.sqrt(count1*count2);
    }









}
