package com.ruoyi.sights.domain.DTO;

import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;

/**
 * @author chas
 * @introduction topDTO
 * @data 2023-3
 */
public class SightsTopDTO implements Serializable {
    /** 景点ID */
    private Long sightsId;
    /**景点点击量*/
    private Long sightsHits;

    /**景点浏览量*/
    private Long sightsView;

    /** 收藏量**/
    private Long sightsCollect;

    /**景点平均分*/
    private Double sightsScore;
    /** 景点名称 */

    private String sightsName;

    /** 景点热度*/
    private Long sightsHot;

    private String sightsIntro;
    private String sightsImage;

    public Long getSightsId() {
        return sightsId;
    }

    public void setSightsId(Long sightsId) {
        this.sightsId = sightsId;
    }

    public Long getSightsHits() {
        return sightsHits;
    }

    public void setSightsHits(Long sightsHits) {
        this.sightsHits = sightsHits;
    }

    public Long getSightsView() {
        return sightsView;
    }

    public void setSightsView(Long sightsView) {
        this.sightsView = sightsView;
    }

    public Long getSightsCollect() {
        return sightsCollect;
    }

    public void setSightsCollect(Long sightsCollect) {
        this.sightsCollect = sightsCollect;
    }

    public Double getSightsScore() {
        return sightsScore;
    }

    public void setSightsScore(Double sightsScore) {
        this.sightsScore = sightsScore;
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

    public String getSightsIntro() {
        return sightsIntro;
    }

    public void setSightsIntro(String sightsIntro) {
        this.sightsIntro = sightsIntro;
    }

    public String getSightsImage() {
        return sightsImage;
    }

    public void setSightsImage(String sightsImage) {
        this.sightsImage = sightsImage;
    }
}
