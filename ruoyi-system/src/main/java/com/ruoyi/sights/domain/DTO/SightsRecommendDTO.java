package com.ruoyi.sights.domain.DTO;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 推荐返回列表
 * @data 2023-3
 */
public class SightsRecommendDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long sightsId;
    private Double sightsScore;
    private Long sightsHot;
    private String sightsName;
    private String sightsEng;
    private String sightsLocation;
    private String sightsImage;
    private String sightsIntro;

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

    public Long getSightsHot() {
        return sightsHot;
    }

    public void setSightsHot(Long sightsHot) {
        this.sightsHot = sightsHot;
    }

    public String getSightsName() {
        return sightsName;
    }

    public void setSightsName(String sightsName) {
        this.sightsName = sightsName;
    }

    public String getSightsEng() {
        return sightsEng;
    }

    public void setSightsEng(String sightsEng) {
        this.sightsEng = sightsEng;
    }

    public String getSightsLocation() {
        return sightsLocation;
    }

    public void setSightsLocation(String sightsLocation) {
        this.sightsLocation = sightsLocation;
    }

    public String getSightsImage() {
        return sightsImage;
    }

    public void setSightsImage(String sightsImage) {
        this.sightsImage = sightsImage;
    }

    public String getSightsIntro() {
        return sightsIntro;
    }

    public void setSightsIntro(String sightsIntro) {
        this.sightsIntro = sightsIntro;
    }
}
