package com.ruoyi.sights.domain.DTO;

/**
 * @author chas
 * @introduction 热度 景点展示内容
 * @data 2023-3
 */
public class SightsHotDTO {
    private Long sightsId;
    private String sightsName;
    private Long sightsHot;

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

    public Long getSightsHot() {
        return sightsHot;
    }

    public void setSightsHot(Long sightsHot) {
        this.sightsHot = sightsHot;
    }
}
