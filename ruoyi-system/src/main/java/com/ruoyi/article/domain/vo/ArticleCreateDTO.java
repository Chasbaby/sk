package com.ruoyi.article.domain.vo;

import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;

/**
 * 创作 DTO
 * @author Chas
 * @data 2023-3
 */
public class ArticleCreateDTO  implements Serializable {

    /** 文章id */
    private Long articleId;

    /** 作者 */
    private Long userId;

    /** 文章分类 */
    private String articleCategory;

    /** 文章缩略图 */
    private String articleCover;

    /** 文章标题 */
    private String articleTitle;

    /** 文章内容 */
    private String articleContent;

    /** 文章类型 1原创 2转载 3翻译 */
    private String articleType;

    /** 原文链接 */
    private String originalUrl;

    /** 状态值 1公开 2私密 3评论可见 */
    private String status;

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

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
