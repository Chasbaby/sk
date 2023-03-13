package com.ruoyi.sights.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 景点公告对象 sights_bulletin
 * 
 * @author ruoyi
 * @date 2023-01-04
 */
public class SightsBulletin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 景点公告ID */
    private Long bulletinId;

    /** 景点id */
    @Excel(name = "景点id")
    private Long sightsId;

    /** 景点公告标题 */
    @Excel(name = "景点公告标题")
    private String bulletinTitle;

    /** 景点公告类型（1通知 2公告） */
    @Excel(name = "景点公告类型", readConverterExp = "1=通知,2=公告")
    private String bulletinType;

    /** 景点公告简介 */
    @Excel(name = "景点公告简介")
    private String bulletinIntro;

    /** 景点公告内容 */
    private String bulletinContent;

    /** 相关文件 */
    private String bulletinMultiparts;

    /** 公告状态（0正常 1关闭） */
    @Excel(name = "公告状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    /** 是否删除(Y表示已删除N表示未删除) */
    @Excel(name = "是否删除(Y表示已删除N表示未删除)")
    private String delFlag;

    /** 是否置顶(Y表示置顶N表示未置顶) */
    @Excel(name = "是否置顶(Y表示置顶N表示未置顶)")
    private String topFlag;

    public void setBulletinId(Long bulletinId) 
    {
        this.bulletinId = bulletinId;
    }

    public Long getBulletinId() 
    {
        return bulletinId;
    }
    public void setSightsId(Long sightsId) 
    {
        this.sightsId = sightsId;
    }

    public Long getSightsId() 
    {
        return sightsId;
    }
    public void setBulletinTitle(String bulletinTitle) 
    {
        this.bulletinTitle = bulletinTitle;
    }

    public String getBulletinTitle() 
    {
        return bulletinTitle;
    }
    public void setBulletinType(String bulletinType) 
    {
        this.bulletinType = bulletinType;
    }

    public String getBulletinType() 
    {
        return bulletinType;
    }
    public void setBulletinIntro(String bulletinIntro) 
    {
        this.bulletinIntro = bulletinIntro;
    }

    public String getBulletinIntro() 
    {
        return bulletinIntro;
    }
    public void setBulletinContent(String bulletinContent) 
    {
        this.bulletinContent = bulletinContent;
    }

    public String getBulletinContent() 
    {
        return bulletinContent;
    }
    public void setBulletinMultiparts(String bulletinMultiparts) 
    {
        this.bulletinMultiparts = bulletinMultiparts;
    }

    public String getBulletinMultiparts() 
    {
        return bulletinMultiparts;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setTopFlag(String topFlag) 
    {
        this.topFlag = topFlag;
    }

    public String getTopFlag() 
    {
        return topFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bulletinId", getBulletinId())
            .append("sightsId", getSightsId())
            .append("bulletinTitle", getBulletinTitle())
            .append("bulletinType", getBulletinType())
            .append("bulletinIntro", getBulletinIntro())
            .append("bulletinContent", getBulletinContent())
            .append("bulletinMultiparts", getBulletinMultiparts())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("topFlag", getTopFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
