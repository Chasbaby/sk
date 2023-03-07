package com.ruoyi.search;

import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;

public class ArticleSearchDTO implements Serializable {

    /** 文章id */
    private Long articleId;
    /** 作者 */
    private Long userId;
    /**分类*/
    private String articleCategory;
    /** 文章缩略图 */
    private String articleCover;
    /** 标题 */
    private String articleTitle;
    /** 文章内容 */
    private String articleContent;
    /** 原文链接 */
    private String originalUrl;

    public ArticleSearchDTO() {
    }

    public ArticleSearchDTO(Long articleId, Long userId, String articleCategory, String articleCover, String articleTitle, String articleContent, String originalUrl) {
        this.articleId = articleId;
        this.userId = userId;
        this.articleCategory = articleCategory;
        this.articleCover = articleCover;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.originalUrl = originalUrl;
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

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getArticleCover() {
        return articleCover;
    }

    public void setArticleCover(String articleCover) {
        this.articleCover = articleCover;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
