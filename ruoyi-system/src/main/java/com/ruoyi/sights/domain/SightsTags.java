package com.ruoyi.sights.domain;

import java.io.Serializable;

public class SightsTags implements Serializable {
    private Long sightsId;
    private String sightsName;
    private String tags;

    public SightsTags() {
    }

    public SightsTags(Long sightsId, String sightsName, String tags) {
        this.sightsId = sightsId;
        this.sightsName = sightsName;
        this.tags = tags;
    }

    public Long getSightsId() {
        return sightsId;
    }

    public void setSightsId(Long sightsId) {
        this.sightsId = sightsId;
    }

    public String getSightsName() {
        return sightsName;
    }

    public void setSightsName(String sightsName) {
        this.sightsName = sightsName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "SightsTags{" +
                "sightsId=" + sightsId +
                ", sightsName='" + sightsName + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
