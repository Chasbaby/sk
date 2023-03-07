package com.ruoyi.recommend.config;

import com.ruoyi.sights.mapper.SightsESMapper;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;

import java.nio.file.FileSystems;


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