package com.ruoyi.framework.config;

import com.ruoyi.common.constant.KafkaTopicsConstant;
import com.ruoyi.common.utils.JsonUtils;
import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.sights.service.ISightsHotService;
import com.sun.javaws.IconUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;

@Component
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private ISightsHotService sightsHotService;

    @KafkaListener(topics = KafkaTopicsConstant.SIGHTSVIEW)
    public void addView(String sightsUserId){
        String[] split = sightsUserId.split(KafkaTopicsConstant.DELIMITER);
        Long sightsId = Long.parseLong(split[0]);
        Long userId = Long.parseLong(split[1]);
        sightsHotService.addView(sightsId,userId);
    }

    @KafkaListener(topics = KafkaTopicsConstant.SIGHTSLIKE)
    public void addLike(String sightsUserId){
        String[] split = sightsUserId.split(KafkaTopicsConstant.DELIMITER);
        Long sightsId = Long.parseLong(split[0]);
        Long userId = Long.parseLong(split[1]);
        sightsHotService.addLike(sightsId,userId);
    }

    @KafkaListener(topics = KafkaTopicsConstant.SIGHTSHIT)
    public void addHit(String sightsUserId){
        String[] split = sightsUserId.split(KafkaTopicsConstant.DELIMITER);
        Long sightsId = Long.parseLong(split[0]);
        Long userId = Long.parseLong(split[1]);
        sightsHotService.addHit(sightsId,userId);
    }

    @KafkaListener(topics = KafkaTopicsConstant.SIGHTSCOLLECT)
    public void addCollect(String sightsUserId){
        String[] split = sightsUserId.split(KafkaTopicsConstant.DELIMITER);
        Long sightsId = Long.parseLong(split[0]);
        Long userId = Long.parseLong(split[1]);
        sightsHotService.addCollect(sightsId,userId);
    }

    @KafkaListener(topics = KafkaTopicsConstant.SIGHTSSCORE)
    public void addScore(String sightsUserId){
        String[] split = sightsUserId.split(KafkaTopicsConstant.DELIMITER);
        Long sightsId = Long.parseLong(split[0]);
        Long userId = Long.parseLong(split[1]);
        Double score = Double.parseDouble(split[2]);
        sightsHotService.score(sightsId,score,userId);
    }

    @KafkaListener(topics = "comment")
    public void CommentHandler(String commentContent){
        logger.info("ojbkkkkk");
        System.out.println(commentContent);
    }
    @KafkaListener(topics = "sights")
    public void sightHandle(ConsumerRecord<String, String> consumerRecord){
        SightsBase sightsBase = JsonUtils.parseObject(consumerRecord.value(), SightsBase.class);
        System.out.println(sightsBase);

        System.out.println(consumerRecord.key());
        System.out.println(consumerRecord.value());
    }

    @KafkaListener(topics = "myFlume")
    public void FlumeHandler(ConsumerRecord<String, String> consumerRecord){


        // myFlume 进行数据过滤
        System.out.println("flume");
    }
    
    @KafkaListener(topics = "recommender")
    public void test(String key,String value){
        System.out.println("recommender"+key+value);

    }


    @KafkaListener(topics = "WordsWithCountsTopic")
    public void Words(String msg,String value){

        System.out.println("WordsWithCountsTopic"+msg+value);
    }
    @KafkaListener(topics = "OnLineRecommend")
    public void sss(String msg,String value){
        System.out.println("这是onLineRecommend"+ value);
    }

    public void KafkaStreaming(){
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG,"dataFiltering");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());


        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, String> myFlume = streamsBuilder.stream("myFlume");
        KTable<String, Long> wordCounts = myFlume.flatMapValues(line -> Arrays.asList(line.toLowerCase().split("\\W+")))
                .groupBy((key, word) -> word).count(Materialized.as("word-store"));
        wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(),Serdes.Long()));

        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), props);
        kafkaStreams.start();
    }
}
