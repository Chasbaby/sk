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

    /** 收藏时间 */
    private Date createTime;

    private Long articleLike;

    private Long articleView;

    private Long articleCollect;


    public Long getArticleLike() {
        return articleLike;
    }

    public void setArticleLike(Long articleLike) {
        this.articleLike = articleLike;
    }

    public Long getArticleView() {
        return articleView;
    }

    public void setArticleView(Long articleView) {
        this.articleView = articleView;
    }

    public Long getArticleCollect() {
        return articleCollect;
    }

    public void setArticleCollect(Long articleCollect) {
        this.articleCollect = articleCollect;
    }

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

    public String getArticleCover() {
        return articleCover;
    }

    public void setArticleCover(String articleCover) {
        this.articleCover = articleCover;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
