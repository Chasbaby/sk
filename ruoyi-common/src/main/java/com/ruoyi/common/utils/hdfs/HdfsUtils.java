package com.ruoyi.common.utils.hdfs;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * HDFS 文件处理工具
 * @author Chas
 * @date 2023-2
 */
public class HdfsUtils {
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

    @Autowired
    private FileSystem system;


    public boolean hdfsUpload(Path src, Path dst,boolean overwrite) throws IOException {
        system.copyFromLocalFile(src,dst);


        system.close();
        return true;
    }




}
