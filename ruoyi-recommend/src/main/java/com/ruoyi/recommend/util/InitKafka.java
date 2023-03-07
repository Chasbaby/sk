package com.ruoyi.recommend.util;

import com.ruoyi.recommend.KafkaOperation.KafkaStreamingLogFilter;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

import java.util.Properties;

public class InitKafka {

    public static void config11(){
        Properties setting = new Properties();
        setting.put(StreamsConfig.APPLICATION_ID_CONFIG,"logFilter");
        setting.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");


        Topology topology = new Topology();
        topology.addSource("SOURCE","myFlume")
                .addProcessor("PROCESSOR", KafkaStreamingLogFilter::new,"SOURCE")
                .addSink("SINK","OnLineRecommend","PROCESSOR");

        KafkaStreams kafkaStreams = new KafkaStreams(topology,setting);

        kafkaStreams.start();
        System.out.println("kafka stream 开启");

    }
}
