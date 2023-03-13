package com.ruoyi.common.enums;

/**
 * @author chas
 * @introduction  评论方位枚举
 * @data 2023-3
 */
public enum CommentLocationType {
    /** 景点*/
    SIGHTS(0),
    /* 文章**/
    ARTICLE(2),
    /** 文创*/
    CULCREATIVITY(1);

    private int source ;

    CommentLocationType() {
    }

    CommentLocationType(int source) {
        this.source = source;
    }

    public int getSource() {
        return source;
    }
}
