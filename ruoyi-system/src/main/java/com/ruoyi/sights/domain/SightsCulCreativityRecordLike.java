package com.ruoyi.sights.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 文创点赞记录表
 * @author Chas
 * @date 2022-10
 */
public class SightsCulCreativityRecordLike implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long culCreativityId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public SightsCulCreativityRecordLike() {
    }

    public SightsCulCreativityRecordLike(Long userId, Long culCreativityId, Date createTime) {
        this.userId = userId;
        this.culCreativityId = culCreativityId;
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
                .append("createTime",getCreateTime()).toString();
    }
}
