package com.ruoyi.article.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章对象 article
 * 
 * @author ruoyi
 * @date 2023-03-05
 */
public class Article extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章id */
    private Long articleId;

    /** 作者 */
    private Long userId;

    /** 文章分类 */
    @Excel(name = "文章分类")
    private String articleCategory;

    /** 文章缩略图 */
    @Excel(name = "文章缩略图")
    private String articleCover;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String articleTitle;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String articleContent;

    /** 文章类型 1原创 2转载 3翻译 */
    @Excel(name = "文章类型 1原创 2转载 3翻译")
    private String articleType;

    /** 原文链接 */
    private String originalUrl;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long articleLike;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long articleView;

    /** 收藏量 */
    @Excel(name = "收藏量")
    private Long articleCollect;

    /** 是否置顶 0否 1是 */
    private String isTop;

    /** 是否删除 0否 1是 */
    private String isDelete;

    /** 审核是否通过 */
    @Excel(name = "审核是否通过")
    private String isOk;

    /** 状态值 1公开 2私密 3评论可见 */
    @Excel(name = "状态值 1公开 2私密 3评论可见 4.草稿")
    private String status;

    /** 审核者 */
    @Excel(name = "审核者")
    private String judgeBy;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date judgeTime;

    public void setArticleId(Long articleId) 
    {
        this.articleId = articleId;
    }

    public Long getArticleId() 
    {
        return articleId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setArticleCategory(String articleCategory) 
    {
        this.articleCategory = articleCategory;
    }

    public String getArticleCategory() 
    {
        return articleCategory;
    }
    public void setArticleCover(String articleCover) 
    {
        this.articleCover = articleCover;
    }

    public String getArticleCover() 
    {
        return articleCover;
    }
    public void setArticleTitle(String articleTitle) 
    {
        this.articleTitle = articleTitle;
    }

    public String getArticleTitle() 
    {
        return articleTitle;
    }
    public void setArticleContent(String articleContent) 
    {
        this.articleContent = articleContent;
    }

    public String getArticleContent() 
    {
        return articleContent;
    }
    public void setArticleType(String articleType) 
    {
        this.articleType = articleType;
    }

    public String getArticleType() 
    {
        return articleType;
    }
    public void setOriginalUrl(String originalUrl) 
    {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() 
    {
        return originalUrl;
    }
    public void setArticleLike(Long articleLike) 
    {
        this.articleLike = articleLike;
    }

    public Long getArticleLike() 
    {
        return articleLike;
    }
    public void setArticleView(Long articleView) 
    {
        this.articleView = articleView;
    }

    public Long getArticleView() 
    {
        return articleView;
    }
    public void setArticleCollect(Long articleCollect) 
    {
        this.articleCollect = articleCollect;
    }

    public Long getArticleCollect() 
    {
        return articleCollect;
    }
    public void setIsTop(String isTop) 
    {
        this.isTop = isTop;
    }

    public String getIsTop() 
    {
        return isTop;
    }
    public void setIsDelete(String isDelete) 
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete() 
    {
        return isDelete;
    }
    public void setIsOk(String isOk) 
    {
        this.isOk = isOk;
    }

    public String getIsOk() 
    {
        return isOk;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setJudgeBy(String judgeBy) 
    {
        this.judgeBy = judgeBy;
    }

    public String getJudgeBy() 
    {
        return judgeBy;
    }
    public void setJudgeTime(Date judgeTime) 
    {
        this.judgeTime = judgeTime;
    }

    public Date getJudgeTime() 
    {
        return judgeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("articleId", getArticleId())
            .append("userId", getUserId())
            .append("articleCategory", getArticleCategory())
            .append("articleCover", getArticleCover())
            .append("articleTitle", getArticleTitle())
            .append("articleContent", getArticleContent())
            .append("articleType", getArticleType())
            .append("originalUrl", getOriginalUrl())
            .append("articleLike", getArticleLike())
            .append("articleView", getArticleView())
            .append("articleCollect", getArticleCollect())
            .append("isTop", getIsTop())
            .append("isDelete", getIsDelete())
            .append("isOk", getIsOk())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("judgeBy", getJudgeBy())
            .append("judgeTime", getJudgeTime())
            .toString();
    }
}
