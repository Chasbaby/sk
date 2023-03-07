package com.ruoyi.sights.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 景点评分记录
 * @author Chas
 * @date 2022-10
 */
public class SightsCulCreativityRecordScore implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long culCreativityId;
    private Double score;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    public SightsCulCreativityRecordScore() {
    }


    public SightsCulCreativityRecordScore(Long userId, Long culCreativityId, Double score, Date createTime) {
        this.userId = userId;
        this.culCreativityId = culCreativityId;
        this.score = score;
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCulCreativityId() {
        return culCreativityId;
    }

    public void setCulCreativityId(Long culCreativityId) {
        this.culCreativityId = culCreativityId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId",getUserId())
                .append("culCreativityId", getCulCreativityId())
                .append("score",getScore()).append("createTime",getCreateTime())
                .toString();
    }
}
