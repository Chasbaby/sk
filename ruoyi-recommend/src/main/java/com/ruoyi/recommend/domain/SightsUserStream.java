package com.ruoyi.recommend.domain;

import java.io.Serializable;

public class SightsUserStream implements Serializable {
    private Long userId;
    private Long sightsId;
    private Double sightsScore;
    private Long timestamp;

    public SightsUserStream() {
    }

    public SightsUserStream(Long userId, Long sightsId, Double sightsScore, Long timestamp) {
        this.userId = userId;
        this.sightsId = sightsId;
        this.sightsScore = sightsScore;
        this.timestamp = timestamp;
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
