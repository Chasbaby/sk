package com.ruoyi.recommend.sightsRecommend;

import com.ruoyi.recommend.domain.*;
import com.ruoyi.recommend.domain.Product;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.feature.*;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.jblas.DoubleMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import scala.*;
import scala.reflect.ClassTag;

import java.lang.Boolean;
import java.lang.Double;
import java.lang.Long;
import java.text.SimpleDateFormat;
import java.util.*;


import static com.ruoyi.common.config.RuoYiConfig.getLogPath;
import static com.ruoyi.recommend.util.SparkUtil.*;

public class DataLoad implements Serializable {
    public final static Logger logger = LoggerFactory.getLogger(DataLoad.class);
    public static String SIGHTS_DATA_PATH =getLogPath()+"/products.txt";
    public static String SIGHTS_RATING_PATH=getLogPath()+"/ratings.csv";
    public static Integer MaxNum = 10;
    public static Double standardNum = 0.4;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * ???????????????????????????
     */
    public static void test111(){

        SparkSession spark = buildSparkSession("local[*]", "DataLoader",true);
        Dataset<Row> dataset = spark.read().text(SIGHTS_DATA_PATH);
        Dataset<Product> map = dataset.map((MapFunction<Row, Product>) item -> {
            String[] s = item.getString(0).split("\\^");
            return new Product(Integer.parseInt(s[0].trim()), s[1].trim(), s[5].trim(), s[6].trim());
        }, Encoders.bean(Product.class));
        Dataset<?> product1 = readFromMysql(spark, "product");


        Dataset<Product> product = (Dataset<Product>) readFromMysql(spark, "product");
        Dataset<Row> map1 = product
                .as(Encoders.bean(Product.class))
                .map((MapFunction<Product, Tuple3<Integer, String, String>>) item -> {
                    String s = item.getTags().trim().replace('|', ' ');
                    String productName = item.getProductName();
                    Integer productId = item.getProductId();
                    return new Tuple3(productId, productName, s);
                }
                , Encoders.tuple(Encoders.INT(), Encoders.STRING(), Encoders.STRING()))
                .toDF("productId", "productName", "tags")
                .cache();

        //  ???TF-IDF????????????????????????
        //  1.???????????????????????? ????????????  ???????????????
        Tokenizer tokenizer = new Tokenizer().setInputCol("tags").setOutputCol("words");
        //  ????????????????????? ????????????????????????words???DS
        Dataset<Row> rowDataset = tokenizer.transform(map1);

        // 2.????????????HashingTF?????????????????????
        HashingTF hashingTF = new HashingTF().setInputCol("words").setOutputCol("rawFeatures").setNumFeatures(500);
        Dataset<Row> rowDataset1 = hashingTF.transform(rowDataset);

        //????????????IDF?????? ??????TF-IDF
        IDF idf = new IDF().setInputCol("rawFeatures").setOutputCol("features");
        //????????????idf ??????
        IDFModel idfModel = idf.fit(rowDataset1);
        //??????????????????features???DF
        Dataset<Row> dataDF = idfModel.transform(rowDataset1);
        dataDF.show();

        // ????????????????????? ??????RDD ?????????features
        Dataset<Tuple2<Integer, double[]>> map2 = dataDF.map((MapFunction<Row, Tuple2<Integer, double[]>>) (row) -> {
            int productId = row.getAs("productId");
            SparseVector features = row.getAs("features");
            double[] doubles = features.toArray();
            return new Tuple2(productId, doubles);
        }, Encoders.tuple(Encoders.INT(),Encoders.kryo(double[].class)));
        System.out.println("map2"+map2.count());// 25
        JavaPairRDD<Integer, DoubleMatrix> map3 = map2.javaRDD()
                .mapToPair((PairFunction<Tuple2<Integer, double[]>, Integer, DoubleMatrix>)
                        item -> new Tuple2(item._1(), new DoubleMatrix(item._2())));

        System.out.println("map3"+map3.count()); //25

        // ?????????????????? ???????????????
        JavaRDD<ContentProductRec> flatMap = map3
                .cartesian(map3)
                .filter(item -> !item._1()._1().equals(item._2()._1()))
                .coalesce(7, true)
                .mapToPair((PairFunction<Tuple2<Tuple2<Integer, DoubleMatrix>,
                        Tuple2<Integer, DoubleMatrix>>, Integer, Tuple2<Integer, Double>>) item -> {
                    double sim = consinSim(item._1()._2(), item._2()._2());
                    return new Tuple2(item._1()._1(), new Tuple2(item._2()._1(), sim));
                })
                //.filter(f -> f._2._2 > standardNum)
                .combineByKey((Function<Tuple2<Integer, Double>, List<Tuple2<Integer, Double>>>) f -> {
                            List<Tuple2<Integer, Double>> list = new ArrayList<>();
                            list.add(f);
                            return (ArrayList) list;
                        },
                        (Function2<List<Tuple2<Integer, Double>>, Tuple2<Integer, Double>, List<Tuple2<Integer, Double>>>) (f1, f2) -> {
                            f1.add(f2);
                            return f1;
                        },
                        (Function2<List<Tuple2<Integer, Double>>, List<Tuple2<Integer, Double>>, List<Tuple2<Integer, Double>>>) (f1, f2) -> {
                            f2.addAll(f1);
                            return f2;
                        })
                .flatMap((FlatMapFunction<Tuple2<Integer, List<Tuple2<Integer, Double>>>, ContentProductRec>) f -> {
                    // ????????? ?????? hasNext()?????? ????????? ???????????????
                    f._2.sort((o1, o2) -> o1._2 - o2._2 > 0 ? 1 : 0);
                    List<ContentProductRec> newList = new ArrayList<>();
                    for (Tuple2<Integer, Double> v : f._2) {
                        newList.add(new ContentProductRec(f._1, v._1, v._2));
                    }
                    return newList.iterator();
                });
        Dataset<Row> frame = spark.createDataFrame(flatMap, ContentProductRec.class).toDF("productId1", "productId2", "score");
        writeToMysql(frame,"contentProductRec","overwrite");
        closeSparkSession(spark);
    }
    /**
     * ItemCFRecommend
     */
    public static void test222(){
        SparkSession spark = buildSparkSession("local[4]", "Rating",true);
//        Dataset<Row> dataset = spark.read().text(SIGHTS_RATING_PATH);
//        Dataset<Rating> map = dataset.map((MapFunction<Row, Rating>) item -> {
//            String[] split = item.getString(0).split(",");
//
//            int userId = Integer.parseInt(split[0]);
//            int productId = Integer.parseInt(split[1]);
//            double rating = Double.parseDouble(split[2]);
//            Long time = Long.parseLong(split[3]);
//            return new Rating(userId,productId,rating,time);
//        }, Encoders.bean(Rating.class));
//        writeToMysql(map,"rating","overwrite");

        Dataset<Rating> rating = (Dataset<Rating>) readFromMysql(spark, "rating");
        Dataset<Row> dataset = rating
                .as(Encoders.bean(Rating.class))
                .map((MapFunction<Rating, Tuple3<Integer, Integer, Double>>) item ->
                        new Tuple3(item.getUserId(), item.getProductId(), item.getRating()),
                        Encoders.tuple(Encoders.INT(), Encoders.INT(), Encoders.DOUBLE()))
                .toDF("userId", "productId", "score")
                .cache();

        // ???????????? ?????? ????????? ???????????????????????????
        // ????????????????????????????????? ??????productId ?????? group by

        System.out.println("rating"+rating.count());
        Dataset<Row> productRatingCountDF = dataset.groupBy("productId").count();
      //  productRatingCountDF.show();

        //????????????????????????rating ??????count
        Dataset<Row> ratingWithCountDF = dataset.join(productRatingCountDF, "productId");
        System.out.println("ratingDF"+ratingWithCountDF.count());
        // ratingWithCountDF.show();

        //?????????????????????id ???????????? ??????????????????????????????????????????????????????
        Dataset<Row> rowDataset = ratingWithCountDF.join(ratingWithCountDF, "userId")
                .toDF("userId", "product1", "score1", "count1", "product2", "score2", "count2")
                .select("userId", "product1", "count1", "product2", "count2");

       // rowDataset.show();
        // ????????????????????? ??????sql??????
        rowDataset.createOrReplaceTempView("joined");

        // ??????product1???2 ??? group by ??????userId ????????? ??????????????????????????????????????????
        String sql = "select product1,product2 ,count(userId) as cocount , " +
                "first(count1) as count1, first(count2) as count2 from joined group by product1,product2";

        Dataset<Row> cooccurrenceDF = spark.sql(sql).cache();
        System.out.println("coo"+cooccurrenceDF.count());
       // cooccurrenceDF.show();

        // ?????????????????????????????????( productId1, (productId2, score) )
        // ????????????Mysql??????????????????????????? ???????????????????????????
        // ?????????????????????????????? ?????????????????? ????????? ??????????????????  ?????????
        JavaRDD<RatingResult> javaRDD = cooccurrenceDF
                //??????????????????????????? ??????Exception
                .toJavaRDD()
                //??????????????? ???????????? Key-Value
                .mapToPair((PairFunction<Row, Integer, Tuple2<Integer, Double>>) (row) -> {
                    double cooccurrenceSim = cooccurrenceSim(row.getAs("cocount"), row.getAs("count1"), row.getAs("count2"));
                    return new Tuple2(row.getInt(0), new Tuple2(row.getInt(1), cooccurrenceSim));
                })
                .filter(f->!f._1.equals(f._2._1))
                //??????Key??????
                .combineByKey((Function<Tuple2<Integer, Double>, List<Tuple2<Integer,Double>>>) f->{
                    List<Tuple2<Integer,Double>> list = new LinkedList<>();
                    list.add(f);
                    return (LinkedList)list;
                }
                ,(Function2<List<Tuple2<Integer, Double>>, Tuple2<Integer, Double>, List<Tuple2<Integer, Double>>>)(f1,f2)->{
                    f1.add(f2);
                    return f1;
                }
                ,(Function2<List<Tuple2<Integer, Double>>, List<Tuple2<Integer, Double>>, List<Tuple2<Integer, Double>>>)(f1,f2)->{
                    f2.addAll(f1);
                    return f2;
                } )
                .flatMap((FlatMapFunction<Tuple2<Integer, List<Tuple2<Integer, Double>>>, RatingResult>) f->{
                    //?????? ??????????????????
                    f._2.sort((o1,o2)-> o1._2 - o2._2 > 0 ? 1 : 0 );
                    //????????????????????????
                    List<RatingResult> newList = new LinkedList<>();
                    int i = 0 ;
                    Iterator<Tuple2<Integer, Double>> iterator = f._2.iterator();
                    while (iterator.hasNext() && i < MaxNum){
                        Tuple2<Integer, Double> v = iterator.next();
                        newList.add(new RatingResult(f._1,v._1,v._2));
                        i++;
                    }
                    return newList.iterator();
                } );
        // ????????????????????????????????????
        Dataset<Row> dataFrame = spark.createDataFrame(javaRDD, RatingResult.class);
        long count = dataFrame.count();
        System.out.println("zzzzzzzzzzz"+count);
        dataFrame.show();

        // ??? ??????????????? Mysql ??????????????? ???????????? ??? (productId1, productId2, score)
        writeToMysql(dataFrame,"RatingResult","overwrite");

        System.out.println("?????????");
        //???????????????Double??????
        closeSparkSession(spark);


    }

    /**
     * ALSTrainer
     */
    public static void test333(){
        SparkSession spark = buildSparkSession("local[8]", "ALS", true);
        Dataset<Rating> dataset = (Dataset<Rating>) readFromMysql(spark, "rating");
        dataset.show();

        JavaRDD<org.apache.spark.mllib.recommendation.Rating> ratingData = dataset
                .as(Encoders.bean(Rating.class))
                .toJavaRDD()
                .map((Function<Rating, org.apache.spark.mllib.recommendation.Rating>)
                        f -> new org.apache.spark.mllib.recommendation.Rating(f.getUserId(), f.getProductId(), f.getRating()))
                .cache();

        double[] doubles ={0.8,0.2};
        JavaRDD<org.apache.spark.mllib.recommendation.Rating>[] split = ratingData.randomSplit(doubles);
        JavaRDD<org.apache.spark.mllib.recommendation.Rating> trainingData = split[0];
        JavaRDD<org.apache.spark.mllib.recommendation.Rating> testingData = split[1];
        adjustALSParams(trainingData,testingData);
        closeSparkSession(spark);
    }
    /**
     * adjustALSParams  ??????
     * @param trainData
     * @param testData
     */
    public static void adjustALSParams(JavaRDD<org.apache.spark.mllib.recommendation.Rating> trainData, JavaRDD<org.apache.spark.mllib.recommendation.Rating> testData){
        int[] rank = {5,10,20,50};
        double[] lambda= {1,0.1,0.01};
        List<Tuple3<Integer,Double,Double>> result = new LinkedList<>();

        for (int j : rank) {
            for (double v : lambda) {

                MatrixFactorizationModel model = ALS.train(trainData.rdd(), j, 10, v);
                Double rmse = getRMSE(model, testData.rdd());
                result.add(new Tuple3<>(j, v, rmse));

            }
        }
        result.forEach(System.out::println);

    }
    /**
     * ?????????????????????
     * @param model
     * @param javaRDD
     * @return
     */
    public static Double getRMSE(MatrixFactorizationModel model,RDD<org.apache.spark.mllib.recommendation.Rating> javaRDD){
        //??????userProducts ????????????????????????
        JavaPairRDD<Integer, Integer> userProducts = javaRDD.toJavaRDD().mapToPair((PairFunction<org.apache.spark.mllib.recommendation.Rating
                , Integer, Integer>) f -> new Tuple2(f.user(), f.product()));
//        userProducts = javaRDD.map((Function1<Rating, Integer, Integer>)
//                f -> new Tuple2(f.getUserId(), f.getProductId()));
        JavaRDD<org.apache.spark.mllib.recommendation.Rating> predictRating = model.predict(userProducts);
        //??????????????????RMSE ?????????????????????????????????????????????(userId???productId)?????????
        JavaPairRDD<Tuple2<Integer, Integer>, Double> observed = javaRDD.toJavaRDD().mapToPair((PairFunction<org.apache.spark.mllib.recommendation.Rating, Tuple2<Integer, Integer>, Double>)
                f -> new Tuple2(new Tuple2(f.user(),f.product()),f.rating()));
        JavaPairRDD<Tuple2<Integer, Integer>, Double> predict = predictRating.mapToPair((PairFunction<org.apache.spark.mllib.recommendation.Rating, Tuple2<Integer, Integer>, Double>)
                f -> new Tuple2(new Tuple2(f.user(), f.product()), f.rating()));
        JavaRDD<Double> countError = observed.join(predict).map((Function<Tuple2<Tuple2<Integer, Integer>, Tuple2<Double, Double>>, Double>) f -> {
            double err = f._2._1 - f._2._2;
         //   System.out.println(err*err);
            return err * err;
        });
        Double reduce = countError.reduce((Function2<Double, Double, Double>) Double::sum);
        return Math.sqrt(reduce/countError.count());
    }

    /**
     * OffLine ????????????
     */
    public static void test444(){
        SparkSession spark = buildSparkSession("local[8]", "Offline",true);
        Dataset<Tuple3<Integer, Integer, Double>> ratingData = readFromMysql(spark, "rating")
                .as(Encoders.bean(Rating.class))
                .map((MapFunction<Rating, Tuple3<Integer, Integer, Double>>)
                                f -> new Tuple3<>(f.getUserId(), f.getProductId(), f.getRating()),
                        Encoders.tuple(Encoders.INT(), Encoders.INT(), Encoders.DOUBLE()))
                .cache();
        //?????????????????????????????????????????? ??????????????????
        Dataset<Integer> userData = ratingData.map((MapFunction<Tuple3<Integer, Integer, Double>, Integer>)
                        Tuple3::_1, Encoders.INT())
                .distinct();
        Dataset<Integer> productData = ratingData.map((MapFunction<Tuple3<Integer, Integer, Double>, Integer>)
                        Tuple3::_2, Encoders.INT())
                .distinct();
        //??????????????????

        //1. ?????????????????????
        Dataset<org.apache.spark.mllib.recommendation.Rating> trainData = ratingData.map((MapFunction<Tuple3<Integer, Integer, Double>, org.apache.spark.mllib.recommendation.Rating>)
                        f -> new org.apache.spark.mllib.recommendation.Rating(f._1(), f._2(), f._3()),
                Encoders.bean(org.apache.spark.mllib.recommendation.Rating.class));
        //??????????????????????????????rank??????????????? iterations ???????????????lambda ???????????????
        // ????????????????????? ?????????????????????????????? ???????????? redis ??? ????????????????????????
        int rank = 5;
        int iteration = 10;
        double lambda= 0.1;
        MatrixFactorizationModel model = ALS.train(trainData.rdd(), rank, iteration, lambda);

        //2.????????????????????????????????? ????????? ????????????   ?????????????????????????????????
        //???user ??? product ??? ???????????? ????????????uerProducts?????????????????????
        JavaPairRDD<Integer, Integer> userProducts = userData
                .toJavaRDD()
                .cartesian(productData.toJavaRDD());
        JavaRDD<org.apache.spark.mllib.recommendation.Rating> predict = model.predict(userProducts);
        //??????????????????????????????????????????????????????    ???????????? ???????????????????????????  ?????????????????????
        //JavaPairRDD<Integer, Iterable<Tuple2<Integer, Double>>> pairRDD; ????????????????????? mysql ??????????????? ????????? ????????? Iterable
        JavaRDD<UserProductRec> javaRDD = predict
                .filter((Function<org.apache.spark.mllib.recommendation.Rating, Boolean>) f -> f.rating() > 0)
                // ???????????? ??????????????? 8  ???????????????????????? ???????????? ?????????shuffle??????
                .coalesce(6, true)
                .mapToPair((PairFunction<org.apache.spark.mllib.recommendation.Rating, Integer,Tuple2<Integer,Double>>)
                        f-> new Tuple2<>(f.user(),new Tuple2<>(f.product(),f.rating())))
//               ???????????????????????? V ???????????? ?????? ????????????   ?????????????????????????????????
//                .mapToPair((PairFunction<org.apache.spark.mllib.recommendation.Rating, Tuple2<Integer, Double>, Integer>)
//                        f -> new Tuple2<>(new Tuple2<>(f.product(), f.rating()), f.user()))
//                .sortByKey((o1, o2) -> o1._2 > o2._2 ? 1 : 0)

//                .mapToPair((PairFunction<Tuple2<Tuple2<Integer, Double>, Integer>, Integer, Tuple2<Integer, Double>>)
//                        f -> new Tuple2<>(f._2, new Tuple2<>(f._1()._1(), f._1()._2())))

                // ??? combineByKey ?????? GroupByKey
                .combineByKey((Function<Tuple2<Integer, Double>, List<Tuple2<Integer, Double>>>) f -> {
                            List<Tuple2<Integer, Double>> list = new ArrayList<>();
                            list.add(f);
                            return (ArrayList) list;
                        }
                        , (Function2<List<Tuple2<Integer, Double>>, Tuple2<Integer, Double>, List<Tuple2<Integer, Double>>>) (f1, f2) -> {
                            f1.add(f2);
                            return f1;
                        }
                        , (Function2<List<Tuple2<Integer, Double>>, List<Tuple2<Integer, Double>>, List<Tuple2<Integer, Double>>>) (f1, f2) -> {
                            f2.addAll(f1);
                            return f2;
                        })
                .flatMap((FlatMapFunction<Tuple2<Integer, List<Tuple2<Integer, Double>>>, UserProductRec>) f -> {
                    //??????????????? ????????????
                    f._2.sort((o1, o2) -> (o1._2 - o2._2 > 0 ? 1 : 0 ));
                    //????????????MaxNum?????????  ????????????????????????
                    List<UserProductRec> newList = new ArrayList<>(MaxNum);

                    int i = 0;
                    /**
                     * ?????? ???
                     * ????????????????????????  ??????????????????  ???????????? ???????????????????????? ??????
                     */
//                    //??????????????????
//                    List<Tuple2<Integer, Double>> tuple2s= new ArrayList<>(MaxNum);
//                    //?????? ????????? ????????????MaxNuM???????????? ??????????????????
//                    if (f._2.size()>MaxNum){
//                        tuple2s = f._2.subList(0, MaxNum);
//                    }else {
//                        //????????????MaxNum??? ?????????????????????
//                        tuple2s.addAll(f._2);
//                    }
//                    //??? ?????? ?????????
//                    while (tuple2s.iterator().hasNext()){
//                        newList.add(new RatingResult(f._1,tuple2s.iterator().next()._1,tuple2s.iterator().next()._2));
//                    }
                    /**
                     * ?????? ???
                     * ???????????????MaxNum ???  ???????????? ???????????????????????? ??????
                     */
//                    while (f._2.iterator().hasNext() && i<MaxNum){
//                        newList.add(new RatingResult(f._1,f._2.iterator().next()._1,f._2.iterator().next()._2));
//                        i++;
//                    }
                    /**
                     * ?????????
                     * ????????????Arrays?????????????????? ?????????0~MaxNum ???????????????
                     */
                    while (i < MaxNum){
                        //????????????????????????????????? ?????????????????????
                        if (f._2.get(i) == null){
                            break;
                        }
                        newList.add(new UserProductRec(f._1,f._2.get(i)._1,f._2.get(i)._2));
                        i++;
                    }
                    return newList.iterator();
                });
        //System.out.println("???????????????????????????"+javaRDD.count());    89230???
        Dataset<Row> frame = spark.createDataFrame(javaRDD, UserProductRec.class);
        writeToMysql(frame,"ratingDataCount","overwrite");


          //3.????????????????????????????????????????????????????????????
        RDD<Tuple2<Object, double[]>> tuple2RDD = model.productFeatures();
        JavaPairRDD<Integer, DoubleMatrix> productFeature = tuple2RDD.toJavaRDD().mapToPair((PairFunction<Tuple2<Object, double[]>, Integer, DoubleMatrix>) f -> {
            Integer in = null;
            if (f._1 instanceof Integer){
                in = (Integer) f._1;
            }
            return new Tuple2<Integer, DoubleMatrix>(in, new DoubleMatrix(f._2));
        });
        JavaRDD<ProductRec> productRecJavaRDD = productFeature
                .cartesian(productFeature)
                .filter(f -> f._1._1 != f._2._1)
                .coalesce(7)
                .mapToPair((PairFunction<Tuple2<Tuple2<Integer, DoubleMatrix>, Tuple2<Integer, DoubleMatrix>>, Integer, Tuple2<Integer, Double>>) f -> {
                    double v = consinSim(f._1._2(), f._2._2);
                    return new Tuple2<>(f._1._1, new Tuple2<>(f._2._1, v));
                })
                .filter(f -> f._2._2 > standardNum)
                .combineByKey((Function<Tuple2<Integer, Double>, List<Tuple2<Integer, Double>>>) f -> {
                            List<Tuple2<Integer, Double>> list = new ArrayList<>();
                            list.add(f);
                            return (ArrayList) list;
                        }
                        , (Function2<List<Tuple2<Integer, Double>>, Tuple2<Integer, Double>, List<Tuple2<Integer, Double>>>) (f1, f2) -> {
                            f1.add(f2);
                            return f1;
                        }
                        , (Function2<List<Tuple2<Integer, Double>>, List<Tuple2<Integer, Double>>, List<Tuple2<Integer, Double>>>) (f1, f2) -> {
                            f2.addAll(f1);
                            return f2;
                        })
                .flatMap((FlatMapFunction<Tuple2<Integer, List<Tuple2<Integer, Double>>>, ProductRec>) f -> {
                    f._2.sort((o1, o2) -> o1._2 - o2._2 > 0 ? 1 : 0);
                    List<ProductRec> newList = new ArrayList<>();
                    Iterator<Tuple2<Integer, Double>> iterator = f._2.iterator();
                    while (iterator.hasNext()) {
                        Tuple2<Integer, Double> v = iterator.next();
                        newList.add(new ProductRec(f._1, v._1,v._2));
                    }
                    return newList.iterator();
                });
        Dataset<Row> dataFrame = spark.createDataFrame(productRecJavaRDD, ProductRec.class);
        writeToMysql(dataFrame,"productRec","overwrite");
        closeSparkSession(spark);

    }
    /**
     * ????????????
     */
    public static void test555(){
        SparkSession spark = buildSparkSession("local[1]", "statistics", true);
        //????????????
        Dataset<Rating> rating = (Dataset<Rating>) readFromMysql(spark, "rating");
        System.out.println(1);
        //???????????????
        rating.createOrReplaceTempView("ratings");
        //???spark sql ????????????????????????

        //1. ?????????????????? ???????????????????????? productId count
        Dataset<Row> sql1 = spark.sql("select productId ,count(productId) as count from ratings group by productId order by count desc");
        writeToMysql(sql1,"RateMoreProducts","overwrite");

        System.out.println(2);

        //2. ?????????????????? ?????????????????????yyyyMM????????????????????????????????????????????? productId???count???yearmonth
        //?????????????????????????????????
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        //??????UDF??????timestamp?????????????????????yyyyMM
        spark.udf().register("changeDate",(UDF1<Long,Integer>)
                f -> Integer.parseInt(dateFormat.format(new Date(f*1000L)))
                , DataTypes.IntegerType);

        //????????????rating??????????????????????????? productId score yearmonth
        Dataset<Row> sql = spark.sql("select productId,rating,changeDate(timestamp) as yearmonth from ratings");
        sql.createOrReplaceTempView("ratingOfMonth");

        Dataset<Row> rowDataset = spark.sql("select productId, count(productId) as count, yearmonth from ratingOfMonth group by yearmonth, productId order by yearmonth desc, count desc");
        //??????????????????
        writeToMysql(rowDataset,"RateMoreRecentlyProducts","overwrite");
        System.out.println(3);

        //3. ?????????????????? ??????????????????
        Dataset<Row> ave = spark.sql("select productId, avg(score) as avg from ratings group by productId order by avg desc");
        writeToMysql(ave,"AverageProducts","overwrite");
        System.out.println(4);

        closeSparkSession(spark);

    }
    /**
     * Online ????????????
     */
    public static void test666(){
        SparkSession spark = buildSparkSession("local[8]", "Online", true);
        SparkContext sc = spark.sparkContext();
        StreamingContext scc = new StreamingContext(sc,new Duration(2000));
        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(JavaSparkContext.fromSparkContext(sc), new Duration(2000));

        Dataset<ProductRec> productrec = (Dataset<ProductRec>) readFromMysql(spark, "productrec");

        Map<Integer, Tuple2<Integer, Double>> integerTuple2Map = productrec.as(Encoders.bean(ProductRec.class))
                .javaRDD()
                .mapToPair((PairFunction<ProductRec, Integer, Tuple2<Integer, Double>>)
                        f -> new Tuple2<>(f.getProductId1(), new Tuple2<>(f.getProductId2(), f.getScore()))).collectAsMap();

        Broadcast<Map<Integer, Tuple2<Integer, Double>>> broadcast = sc.broadcast(integerTuple2Map, (ClassTag<Map<Integer, Tuple2<Integer, Double>>>) () -> Map.class);

        // ????????????(??????streaming) ??? ??????
        scc.start();
        scc.awaitTermination();


    }

    public static void main(String[] args) {
        test666();
    }


}

