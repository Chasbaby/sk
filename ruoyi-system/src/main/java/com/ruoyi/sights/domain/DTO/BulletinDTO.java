package com.ruoyi.sights.domain.DTO;

import java.io.Serializable;

/**
 * @author chas
 * @introduction dto
 * @data 2023-3
 */
public class BulletinDTO implements Serializable {
    /** 景点公告ID */
    private Long bulletinId;
    private Long sightsId;
    private String bulletinTitle;
    private String bulletinType;
    private String bulletinIntro;
    /** 景点公告内容 */
    private String bulletinContent;
    private String bulletinMultiparts;


    public Long getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(Long bulletinId) {
        this.bulletinId = bulletinId;
    }

    public Long getSightsId() {
        return sightsId;
    }

    public void setSightsId(Long sightsId) {
        this.sightsId = sightsId;
    }

    public String getBulletinTitle() {
        return bulletinTitle;
    }

    public void setBulletinTitle(String bulletinTitle) {
        this.bulletinTitle = bulletinTitle;
    }

    public String getBulletinType() {
        return bulletinType;
    }

    public void setBulletinType(String bulletinType) {
        this.bulletinType = bulletinType;
    }

    public String getBulletinIntro() {
        return bulletinIntro;
    }

    public void setBulletinIntro(String bulletinIntro) {
        this.bulletinIntro = bulletinIntro;
    }

    public String getBulletinContent() {
        return bulletinContent;
    }

    public void setBulletinContent(String bulletinContent) {
        this.bulletinContent = bulletinContent;
    }

    public String getBulletinMultiparts() {
        return bulletinMultiparts;
    }

    public void setBulletinMultiparts(String bulletinMultiparts) {
        this.bulletinMultiparts = bulletinMultiparts;
    }
}
