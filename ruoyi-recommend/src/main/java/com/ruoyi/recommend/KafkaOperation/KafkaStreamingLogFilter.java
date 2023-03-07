package com.ruoyi.recommend.KafkaOperation;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.springframework.beans.factory.annotation.Autowired;


import java.nio.charset.StandardCharsets;
import java.util.Date;

import static com.ruoyi.recommend.util.SparkUtil.SIGHTS_RATING_PREFIX;

public class KafkaStreamingLogFilter implements Processor<byte[],byte[],String,String> {
    @Autowired
    private ProcessorContext processorContext;

    @Override
    public void init(ProcessorContext<String, String> context) {
        Processor.super.init(context);
    }

    @Override
    public void process(Record<byte[], byte[]> record) {
        String input = record.value().toString();
        // 如果value是以   开头的 那么将此数据发送
        if (input.contains(SIGHTS_RATING_PREFIX)){
            String data = input.split(SIGHTS_RATING_PREFIX)[1].trim();
            Record newRecode = new Record(null,data.getBytes(StandardCharsets.UTF_8),new Date().getTime());
            processorContext.forward(newRecode,"OnLineRecommend");

        }
    }

    @Override
    public void close() {
        Processor.super.close();
    }
}
