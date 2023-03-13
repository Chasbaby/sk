package com.ruoyi.sights.SightsCulture;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 景点特色文化对象 sights_culture
 * 
 * @author ruoyi
 * @date 2022-08-09
 */
public class SightsCulture extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文化信息ID */
    private Long cultureId;

    /** 文化信息标题 */
    @Excel(name = "文化信息标题")
    private String cultureTitle;

    /** 文化历史 */
    private String cultureHistory;

    /** 文化信息特色 */
    private String cultureFeature;


    /** 文化信息简介 */
    @Excel(name = "文化信息简介")
    private String cultureIntro;

    /** 信息文件 */
    private String cultureDocument;

    /** 文化信息类别 */
    @Excel(name = "文化信息类别")
    private String cultureCategory;

    /** 关键字 */
    @Excel(name = "关键字")
    private String cultureKey;

    /** 是否展示(0:是 1:否) */
    private Integer showFlag;

    public void setCultureId(Long cultureId) 
    {
        this.cultureId = cultureId;
    }

    public Long getCultureId() 
    {
        return cultureId;
    }
    public void setCultureTitle(String cultureTitle) 
    {
        this.cultureTitle = cultureTitle;
    }

    public String getCultureTitle() 
    {
        return cultureTitle;
    }
    public void setCultureHistory(String cultureHistory) 
    {
        this.cultureHistory = cultureHistory;
    }

    public String getCultureHistory() 
    {
        return cultureHistory;
    }
    public void setCultureFeature(String cultureFeature) 
    {
        this.cultureFeature = cultureFeature;
    }

    public String getCultureFeature() 
    {
        return cultureFeature;
    }
    public void setCultureIntro(String cultureIntro) 
    {
        this.cultureIntro = cultureIntro;
    }

    public String getCultureIntro() 
    {
        return cultureIntro;
    }
    public void setCultureDocument(String cultureDocument) 
    {
        this.cultureDocument = cultureDocument;
    }

    public String getCultureDocument() 
    {
        return cultureDocument;
    }
    public void setCultureCategory(String cultureCategory) 
    {
        this.cultureCategory = cultureCategory;
    }

    public String getCultureCategory() 
    {
        return cultureCategory;
    }
    public void setCultureKey(String cultureKey) 
    {
        this.cultureKey = cultureKey;
    }

    public String getCultureKey() 
    {
        return cultureKey;
    }
    public void setShowFlag(Integer showFlag) 
    {
        this.showFlag = showFlag;
    }

    public Integer getShowFlag() 
    {
        return showFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cultureId", getCultureId())
            .append("cultureTitle", getCultureTitle())
            .append("cultureHistory", getCultureHistory())
            .append("cultureFeature", getCultureFeature())
            .append("cultureIntro", getCultureIntro())
            .append("cultureDocument", getCultureDocument())
            .append("cultureCategory", getCultureCategory())
            .append("cultureKey", getCultureKey())
            .append("showFlag", getShowFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
