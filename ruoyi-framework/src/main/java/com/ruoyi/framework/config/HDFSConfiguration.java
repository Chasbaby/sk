package com.ruoyi.framework.config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.security.UserGroupInformation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.PrivilegedExceptionAction;

/**
 * hdfs的配置类
 * @author Chas
 * @date 2023-2
 */
@Component
public class HDFSConfiguration {

    @Value("${hadoop.namenode}")
    private String namenode;

    @Value("${hadoop.port}")
    private String port;

    @Value("${hadoop.username}")
    private String username;

    @Bean
    public Configuration hadoopConfig() {
        Configuration config = new Configuration();
        config.set("fs.defaultFS", "hdfs://" + namenode + ":" + port);
        config.set("dfs.client.use.datanode.hostname", "true");
        config.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
        config.set("dfs.client.block.write.replace-datanode-on-failure.enable", "true");
        config.set("dfs.replication", "1");
        return config;
    }

    @Bean
    public FileSystem hdfsFileSystem(Configuration config) throws IOException, InterruptedException {
        UserGroupInformation.setConfiguration(config);
        UserGroupInformation ugi = UserGroupInformation.createRemoteUser(username);
        return ugi.doAs((PrivilegedExceptionAction<FileSystem>) () -> FileSystem.get(config));
    }
}

