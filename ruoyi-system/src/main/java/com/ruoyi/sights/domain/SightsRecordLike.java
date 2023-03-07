package com.ruoyi.sights.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 点赞记录表
 * @author Chas
 * @date 2022-10
 */
public class SightsRecordLike implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long sightsId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public SightsRecordLike() {
    }

    public SightsRecordLike(Long userId, Long sightsId, Date createTime) {
        this.userId = userId;
        this.sightsId = sightsId;
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSightsId() {
        return sightsId;
    }

    public void setSightsId(Long sightsId) {
        this.sightsId = sightsId;
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
                .append("sightsId",getSightsId())
                .append("createTime",getCreateTime()).toString();
    }
}
