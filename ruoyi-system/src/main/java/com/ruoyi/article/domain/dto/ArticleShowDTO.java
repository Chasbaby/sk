package com.ruoyi.article.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction show
 * @date 2023-5-29
 */
@Data
public class ArticleShowDTO implements Serializable {
    private Long articleId;
    private Long userId;
    private String articleCover;
    private String articleTitle;
    private String articleContent;
    private Long articleView;
    private String avatar;
    private String nickName;
}
