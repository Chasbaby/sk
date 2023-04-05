package com.ruoyi.culCreativity.domain.dto;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 文创排行
 * @date
 */
public class CulTopDTO implements Serializable {
    private Long culCreativityId;
    private Long userId;
    private Long sightsId;
    private String culCreativityTitle;

    public Long getCulCreativityId() {
        return culCreativityId;
    }

    public void setCulCreativityId(Long culCreativityId) {
        this.culCreativityId = culCreativityId;
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

    public String getCulCreativityTitle() {
        return culCreativityTitle;
    }

    public void setCulCreativityTitle(String culCreativityTitle) {
        this.culCreativityTitle = culCreativityTitle;
    }
}
