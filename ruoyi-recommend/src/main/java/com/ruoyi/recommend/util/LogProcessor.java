package com.ruoyi.recommend.util;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;


public class LogProcessor implements Processor<byte[], byte[]> {
    private ProcessorContext processorContext;
    @Override
    public void init(ProcessorContext processorContext) {
        this.processorContext = processorContext;
    }

    @Override
    public void process(byte[] dummy, byte[] line) {
        //核心处理流程
        String input = new String(line);
        //提取数据，以固定前缀过滤日志信息
        if(input.contains("PRODUCT_RATING_PREFIX:")){
            System.out.println(input);
            input = input.split("PRODUCT_RATING_PREFIX:")[1].trim();
            processorContext.forward("logProcessor".getBytes(),input.getBytes());
        }

    }

    @Override
    public void close() {

    }
}

