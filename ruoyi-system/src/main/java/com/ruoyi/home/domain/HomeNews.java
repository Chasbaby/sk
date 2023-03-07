package com.ruoyi.home.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新闻对象 home_news
 * 
 * @author ruoyi
 * @date 2022-08-19
 */
public class HomeNews extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 新闻ID */
    private Long newsId;

    /** 新闻标题 */
    @Excel(name = "新闻标题")
    private String newsTitle;

    /** 新闻类型(0:技术 1:行业 3:政策) */
    @Excel(name = "新闻类型(0:技术 1:行业 3:政策)")
    private Integer newsType;

    @Excel(name="新闻简介")
    /**新闻简介*/
    private String newsIntroduction;
    /** 新闻内容 */
    private String newsContent;

    /** 新闻作者 */
    @Excel(name = "新闻作者")
    private String newsAuthor;

    /** 点击量 */
    @Excel(name = "点击量")
    private Integer newsHits;

    /** 新闻关键字(','分割) */
    @Excel(name = "新闻关键字(','分割)")
    private String newsKey;

    /** 主图片路径 */
    private String imageId;

    /** 置顶(0否 1是) */
    private String topFlag;

    /** 删除标志(0:否 1:是 ) */
    private String delFlag;

    /** 显示资格(0:有 1:无) */
    private String showFlag;

    public String getNewsIntroduction() {
        return newsIntroduction;
    }

    public void setNewsIntroduction(String newsIntroduction) {
        this.newsIntroduction = newsIntroduction;
    }

    public void setNewsId(Long newsId)
    {
        this.newsId = newsId;
    }

    public Long getNewsId() 
    {
        return newsId;
    }
    public void setNewsTitle(String newsTitle) 
    {
        this.newsTitle = newsTitle;
    }

    public String getNewsTitle() 
    {
        return newsTitle;
    }
    public void setNewsType(Integer newsType) 
    {
        this.newsType = newsType;
    }

    public Integer getNewsType() 
    {
        return newsType;
    }
    public void setNewsContent(String newsContent) 
    {
        this.newsContent = newsContent;
    }

    public String getNewsContent() 
    {
        return newsContent;
    }
    public void setNewsAuthor(String newsAuthor) 
    {
        this.newsAuthor = newsAuthor;
    }

    public String getNewsAuthor() 
    {
        return newsAuthor;
    }
    public void setNewsHits(Integer newsHits) 
    {
        this.newsHits = newsHits;
    }

    public Integer getNewsHits() 
    {
        return newsHits;
    }
    public void setNewsKey(String newsKey) 
    {
        this.newsKey = newsKey;
    }

    public String getNewsKey() 
    {
        return newsKey;
    }
    public void setImageId(String imageId) 
    {
        this.imageId = imageId;
    }

    public String getImageId() 
    {
        return imageId;
    }
    public void setTopFlag(String topFlag) 
    {
        this.topFlag = topFlag;
    }

    public String getTopFlag() 
    {
        return topFlag;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setShowFlag(String showFlag) 
    {
        this.showFlag = showFlag;
    }

    public String getShowFlag() 
    {
        return showFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("newsId", getNewsId())
            .append("newsTitle", getNewsTitle())
            .append("newsType", getNewsType())
            .append("newsContent", getNewsContent())
            .append("newsAuthor", getNewsAuthor())
            .append("newsIntroduction",getNewsIntroduction())
            .append("newsHits", getNewsHits())
            .append("newsKey", getNewsKey())
            .append("imageId", getImageId())
            .append("topFlag", getTopFlag())
            .append("delFlag", getDelFlag())
            .append("showFlag", getShowFlag())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
