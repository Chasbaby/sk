package com.ruoyi.article.domain.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction 返回部分数据
 * @data 2023-3
 */
public class ArticleReturnDTO implements Serializable {
    /** 文章id */
    private Long articleId;

    /** 作者 */
    private Long userId;

    private String articleCover;

    private String articleContent;

    private String articleType;

    /** 收藏时间*/
    private Date createTime;

//    private Long articleLike;
//
//    private Long articleView;
//
//    private Long articleCollect;



}
