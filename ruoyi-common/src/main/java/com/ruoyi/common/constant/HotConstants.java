package com.ruoyi.common.constant;

/**
 * @author chas
 * @introduction 热度算法 常量
 * @data 2023-3
 */
public class HotConstants {

    /** 热门景点redis的KEY*/
    public static final String HOTLABLE = "sights_hot:";
    /** 缓存最长时间 只有最强的10大景点才配拥有*/
    public static final String MAXTIME = "24*60";
    /** 缓存最短时间 普通小景点拥有*/
    public static final String MINTIME = "60";
}
