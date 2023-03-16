package com.ruoyi.common.utils.hdfs;

import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * HDFS 文件处理工具
 * @author Chas
 * @date 2023-2
 */
public class HdfsUtils {
    private static final Logger logger = LoggerFactory.getLogger(HdfsUtils.class);
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

    // 写入文件到HDFS
    public static void writeFile(String srcPath, String dstPath) throws IOException {
        FileSystem fileSystem = FileSystem.get(new Configuration());
        Path src = new Path(srcPath);
        Path dst = new Path(dstPath);
        fileSystem.copyFromLocalFile(src, dst);
    }

    // 从HDFS读取文件
    public static void readFile(String filePath) throws IOException {
        FileSystem fileSystem = FileSystem.get(new Configuration());
        Path path = new Path(filePath);
        FSDataInputStream in = fileSystem.open(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        in.close();
    }


    /**
     * 从 hdfs上指定目录中读取excel文件
     * @param filePath 文件路径
     * @param clazz 类
     * @param <T>  类
     * @return
     * @throws IOException
     */
    public static  <T> List<T> readExcelFromHDFS(String filePath,Class<T> clazz) throws IOException {
        FileSystem fileSystem = FileSystem.get(new Configuration());
        Path path = new Path(filePath);
        InputStream inputStream = fileSystem.open(path);
        ExcelUtil<T> excel = new ExcelUtil(clazz);
        List<T> list = null;
        try {
             list = excel.importExcel(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将excel文件转入hdfs并将文件名返回
     * @param filePath 文件路径
     * @param list list
     * @param <T>
     * @return
     */
    public static <T> String writeExcelToHDFS(String filePath,List<T> list){
        int i = filePath.lastIndexOf("/");
        String fileName = filePath.substring(i);
        fileName = fileName.split(".")[0];

        return null;
    }


    // 创建目录
    public static void mkdir(String dirPath) throws IOException {
        FileSystem fileSystem = FileSystem.get(new Configuration());
        Path path = new Path(dirPath);
        // 路径不存在 则 建目录
        if (!fileSystem.exists(path)){
            fileSystem.mkdirs(path);
        }

    }

    // 重命名文件或目录
    public static void rename(String oldPath, String newPath) throws IOException {
        FileSystem fileSystem = FileSystem.get(new Configuration());
        Path old = new Path(oldPath);
        Path newP = new Path(newPath);
        boolean isRenamed = fileSystem.rename(old, newP);
        if (isRenamed) {
            logger.info("hdfs文件命名成功");
        } else {
            logger.warn("hdfs文件命名失败");
        }
    }

    // 删除文件或目录
    public static boolean delete(String filePath) throws IOException {
        FileSystem fileSystem = FileSystem.get(new Configuration());
        Path path = new Path(filePath);
        boolean flag;
        if (fileSystem.exists(path) && fileSystem.getFileStatus(path).isFile()){
            flag = fileSystem.delete(path, true);
            // 将第二个参数设为true，表示即使文件非空也会删除
            logger.info("hdfs文件删除成功");
            fileSystem.close();
            return flag;
        }else {
            fileSystem.close();
            return false;
        }
    }

    public static void close() throws IOException {
        FileSystem fileSystem = FileSystem.get(new Configuration());
        fileSystem.close();
    }



}
