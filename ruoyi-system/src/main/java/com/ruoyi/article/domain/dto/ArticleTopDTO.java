package com.ruoyi.article.domain.dto;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 文章排行
 * @data
 */
public class ArticleTopDTO implements Serializable {
    private Long articleId;
    private Long userId;
    private String articleTitle;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
}
