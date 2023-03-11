package com.ruoyi.article.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.DTO.UserDTO;
import com.ruoyi.common.core.domain.entity.DTO.VisitorDTO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.entity.SysVisitor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction 文章详细信息
 * @data 2023-3
 */
public class ArticleDetail implements Serializable {
    /** 文章id */
    private Long articleId;

    /** 作者 */
    private Long userId;

    /** 作者部分信息 */
    private UserDTO user;

    /** 作者部分信息*/
    private VisitorDTO visitor;

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

    /** 点赞数 */
    private Long articleLike;

    /** 浏览量 */
    private Long articleView;

    /** 收藏量 */
    private Long articleCollect;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public VisitorDTO getVisitor() {
        return visitor;
    }

    public void setVisitor(VisitorDTO visitor) {
        this.visitor = visitor;
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
}
