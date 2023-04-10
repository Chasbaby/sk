package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动对象 sys_activity
 * 
 * @author ruoyi
 * @date 2023-04-10
 */
public class SysActivity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动id */
    private Long activityId;

    /** 景点id */
    private Long sightsId;

    /** 用户id */
    private Long userId;

    /** 来源(0景点 1用户 2 系统) */
    @Excel(name = "来源(0景点 1用户 2 系统)")
    private String source;

    /** 活动标题 */
    @Excel(name = "活动标题")
    private String activityTitle;

    /** 活动介绍 */
    @Excel(name = "活动介绍")
    private String activityIntroduction;

    /** 活动内容 */
    @Excel(name = "活动内容")
    private String activityContent;

    /** 附加文件 */
    @Excel(name = "附加文件")
    private String activityMultiparts;

    /** 活动图片 */
    @Excel(name = "活动图片")
    private String activityImage;

    /** 活动(热度) */
    @Excel(name = "活动(热度)")
    private Long activityHot;

    /** 活动奖励 */
    private String activityReward;

    /** 发布者 */
    @Excel(name = "发布者")
    private String activityPublisher;

    /** 活动开始时间 */
    private Date beginTime;

    /** 活动结束时间 */
    private Date overTime;

    public void setActivityId(Long activityId) 
    {
        this.activityId = activityId;
    }

    public Long getActivityId() 
    {
        return activityId;
    }
    public void setSightsId(Long sightsId) 
    {
        this.sightsId = sightsId;
    }

    public Long getSightsId() 
    {
        return sightsId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setActivityTitle(String activityTitle) 
    {
        this.activityTitle = activityTitle;
    }

    public String getActivityTitle() 
    {
        return activityTitle;
    }
    public void setActivityIntroduction(String activityIntroduction) 
    {
        this.activityIntroduction = activityIntroduction;
    }

    public String getActivityIntroduction() 
    {
        return activityIntroduction;
    }
    public void setActivityContent(String activityContent) 
    {
        this.activityContent = activityContent;
    }

    public String getActivityContent() 
    {
        return activityContent;
    }
    public void setActivityMultiparts(String activityMultiparts) 
    {
        this.activityMultiparts = activityMultiparts;
    }

    public String getActivityMultiparts() 
    {
        return activityMultiparts;
    }
    public void setActivityImage(String activityImage) 
    {
        this.activityImage = activityImage;
    }

    public String getActivityImage() 
    {
        return activityImage;
    }
    public void setActivityHot(Long activityHot) 
    {
        this.activityHot = activityHot;
    }

    public Long getActivityHot() 
    {
        return activityHot;
    }
    public void setActivityReward(String activityReward) 
    {
        this.activityReward = activityReward;
    }

    public String getActivityReward() 
    {
        return activityReward;
    }
    public void setActivityPublisher(String activityPublisher) 
    {
        this.activityPublisher = activityPublisher;
    }

    public String getActivityPublisher() 
    {
        return activityPublisher;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("activityId", getActivityId())
            .append("sightsId", getSightsId())
            .append("userId", getUserId())
            .append("source", getSource())
            .append("activityTitle", getActivityTitle())
            .append("activityIntroduction", getActivityIntroduction())
            .append("activityContent", getActivityContent())
            .append("activityMultiparts", getActivityMultiparts())
            .append("activityImage", getActivityImage())
            .append("activityHot", getActivityHot())
            .append("activityReward", getActivityReward())
            .append("activityPublisher", getActivityPublisher())
            .append("beginTime", getBeginTime())
            .append("overTime", getOverTime())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
