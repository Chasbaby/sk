package com.ruoyi.home.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 广告对象 home_ad
 * 
 * @author ruoyi and chas
 * @date 2022-08-15
 */
public class HomeAd extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 广告ID */
    private Long adId;

    /** 广告标题 */
    @Excel(name = "广告标题")
    private String adTitle;

    /** 广告类型 */
    @Excel(name = "广告类型")
    private Integer adType;

    /** 广告内容 */
    private String adContent;

    /** 广告点击量 */
    @Excel(name = "广告点击量")
    private Integer adHits;

    /** 广告图片 */
    private String adImage;

    /** 广告关键字 */
    @Excel(name = "广告关键字")
    private String adKey;

    /** 广告文档 */
    private String adDocument;

    /** 广告团队介绍 */
    @Excel(name = "广告团队介绍")
    private String adTeam;

    /** 广告赞助商 */
    @Excel(name = "广告赞助商")
    private String adSponsor;

    /** 开始时间 */
    private Date beginTime;

    /** 结束时间 */
    private Date overTime;

    /** 是否置顶(N:否 Y:是) */
    private String topFlag;

    /** 删除标志(N:否 Y:是) */
    private String delFlag;

    public void setAdId(Long adId) 
    {
        this.adId = adId;
    }

    public Long getAdId() 
    {
        return adId;
    }
    public void setAdTitle(String adTitle) 
    {
        this.adTitle = adTitle;
    }

    public String getAdTitle() 
    {
        return adTitle;
    }
    public void setAdType(Integer adType) 
    {
        this.adType = adType;
    }

    public Integer getAdType() 
    {
        return adType;
    }
    public void setAdContent(String adContent) 
    {
        this.adContent = adContent;
    }

    public String getAdContent() 
    {
        return adContent;
    }
    public void setAdHits(Integer adHits) 
    {
        this.adHits = adHits;
    }

    public Integer getAdHits() 
    {
        return adHits;
    }
    public void setAdImage(String adImage) 
    {
        this.adImage = adImage;
    }

    public String getAdImage() 
    {
        return adImage;
    }
    public void setAdKey(String adKey) 
    {
        this.adKey = adKey;
    }

    public String getAdKey() 
    {
        return adKey;
    }
    public void setAdDocument(String adDocument) 
    {
        this.adDocument = adDocument;
    }

    public String getAdDocument() 
    {
        return adDocument;
    }
    public void setAdTeam(String adTeam) 
    {
        this.adTeam = adTeam;
    }

    public String getAdTeam() 
    {
        return adTeam;
    }
    public void setAdSponsor(String adSponsor) 
    {
        this.adSponsor = adSponsor;
    }

    public String getAdSponsor() 
    {
        return adSponsor;
    }
    public void setBeginTime(Date beginTime) 
    {
        this.beginTime = beginTime;
    }

    public Date getBeginTime() 
    {
        return beginTime;
    }
    public void setOverTime(Date overTime) 
    {
        this.overTime = overTime;
    }

    public Date getOverTime() 
    {
        return overTime;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("adId", getAdId())
            .append("adTitle", getAdTitle())
            .append("adType", getAdType())
            .append("adContent", getAdContent())
            .append("adHits", getAdHits())
            .append("adImage", getAdImage())
            .append("adKey", getAdKey())
            .append("adDocument", getAdDocument())
            .append("adTeam", getAdTeam())
            .append("adSponsor", getAdSponsor())
            .append("beginTime", getBeginTime())
            .append("overTime", getOverTime())
            .append("topFlag", getTopFlag())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
