package com.ruoyi.culCreativity.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction
 * @data 2023-3
 */
public class CulRecord implements Serializable {
    private Long culId;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    public Long getCulId() {
        return culId;
    }

    public void setCulId(Long culId) {
        this.culId = culId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
