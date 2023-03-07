package com.ruoyi.recommend.logFilter;

import com.ruoyi.recommend.KafkaOperation.KafkaStreamingLogFilter;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableKafkaStreams
public class logFilter {

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs() {

        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "logFilter");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public KStream<String,String> config1212(StreamsBuilder streamsBuilder){
        Properties setting = new Properties();
        setting.put(StreamsConfig.APPLICATION_ID_CONFIG,"logFilter");
        setting.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");

//        Topology topology = streamsBuilder
//                .build()
//                .addSource("SOURCE", "myFlume")
//                .addProcessor("PROCESSOR", KafkaStreamingLogFilter::new, "SOURCE")
//                .addSink("SINK", "OnLineRecommend", "PROCESSOR");
//        KafkaStreams kafkaStreams = new KafkaStreams(topology, setting);

//        kafkaStreams.start();

        System.out.println("开始了");
        KStream<String, String> myFlume = streamsBuilder.stream("recommender");
        myFlume.map((KeyValueMapper<String, String, KeyValue<?, ?>>) (s, s2) -> {
            System.out.println("ssss");
            System.out.println(s+s2);

            return new KeyValue<>("key",s+s2);
        }).to("WordsWithCountsTopic");
//        streamsBuilder.stream()



        return myFlume;
    }
}
