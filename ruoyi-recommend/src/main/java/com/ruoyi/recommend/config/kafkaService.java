package com.ruoyi.recommend.config;

import com.ruoyi.mapperEs.ee.SightsESMapper;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;


public class kafkaService{

    @Autowired
    private ElasticsearchRestTemplate es;

    @Autowired
    private SightsESMapper sightsESMapper;

    @Autowired
    private FileSystem system;

    public void test(){







    }

}