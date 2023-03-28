package com.ruoyi.article.domain.dto;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 文章pie
 * @data 2023-3
 */
public class ArticleStatisticPie implements Serializable {
    private Long articleView;
    private Long articleLike;
    private Long articleCollect;

    public Long getArticleView() {
        return articleView;
    }

    public void setArticleView(Long articleView) {
        this.articleView = articleView;
    }

    public Long getArticleLike() {
        return articleLike;
    }

    public void setArticleLike(Long articleLike) {
        this.articleLike = articleLike;
    }

    public Long getArticleCollect() {
        return articleCollect;
    }

    public void setArticleCollect(Long articleCollect) {
        this.articleCollect = articleCollect;
    }
}
