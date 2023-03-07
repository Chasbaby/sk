package com.ruoyi.framework.config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * hdfs的配置类
 * @author Chas
 * @date 2023-2
 */
@Component
public class HDFSConfiguration {

    @Bean
    public FileSystem config(){
        //设置操作Hadoop的用户
        System.setProperty("HADOOP_USER_NAME","root");
        Configuration config = new Configuration();
        config.set("fs.defaultFS","hdfs://localhost:9200");
        FileSystem fs = null;
        try {
            fs = FileSystem.get(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fs;
    }
}
