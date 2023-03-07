package com.ruoyi.sights.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 景点用户收藏
 * 2022-10
 */
public class SightsCulCreativityUserCollect implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long culCreativityId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date collectTime;

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

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId",getUserId())
                .append("culCreativityId", getCulCreativityId())
                .append("collectTime",getCollectTime())
                .toString();
    }
}
