package com.ruoyi.recommend.domain;

import java.io.Serializable;

public class UserRecs implements Serializable {
    private Long userId;
    private Long sightsId;
    private Double sightsScore;

    public UserRecs() {
    }

    public UserRecs(Long userId, Long sightsId, Double sightsScore) {
        this.userId = userId;
        this.sightsId = sightsId;
        this.sightsScore = sightsScore;
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

    public Double getSightsScore() {
        return sightsScore;
    }

    public void setSightsScore(Double sightsScore) {
        this.sightsScore = sightsScore;
    }
}
