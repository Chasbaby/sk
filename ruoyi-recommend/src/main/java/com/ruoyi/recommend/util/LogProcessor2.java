package com.ruoyi.recommend.util;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;

public class LogProcessor2 implements Processor<byte[],byte[],String,String>  {
    @Override
    public void init(ProcessorContext<String, String> context) {
        Processor.super.init(context);
    }

    @Override
    public void process(Record<byte[], byte[]> record) {
        String s = new String(record.value());
        System.out.println("这是在init里面的哦"+record.key());
        System.out.println("这是在init里面的哦"+ s);

    }


    @Override
    public void close() {
        Processor.super.close();
    }
}
