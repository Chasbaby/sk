package com.ruoyi.article.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction 返回部分数据
 * @data 2023-3
 */
@Data
public class ArticleReturnDTO implements Serializable {
    /** 文章id */
    private Long articleId;

    /** 作者 */
    private Long userId;

    private String articleTitle;

    private String articleCover;

    private String articleContent;

    private String articleType;

    /** 收藏时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Long articleLike;

    private Long articleView;

    private Long articleCollect;

}
