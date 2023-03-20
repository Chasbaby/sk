package com.ruoyi.culCreativity.domain.dto;

import java.io.Serializable;

/**
 * @author chas
 * @introduction
 * @data
 */
public class CulCreateDTO implements Serializable {
    private String culCreativityTitle;

    private String culCreativityIntro;
    /** 文创内容*/
    private String culCreativityContent;

    private String culCreativityImage;

    private String culCreativityCategory;

    private String culCreativityType;

    private String status;

    public String getCulCreativityTitle() {
        return culCreativityTitle;
    }

    public void setCulCreativityTitle(String culCreativityTitle) {
        this.culCreativityTitle = culCreativityTitle;
    }

    public String getCulCreativityIntro() {
        return culCreativityIntro;
    }

    public void setCulCreativityIntro(String culCreativityIntro) {
        this.culCreativityIntro = culCreativityIntro;
    }

    public String getCulCreativityContent() {
        return culCreativityContent;
    }

    public void setCulCreativityContent(String culCreativityContent) {
        this.culCreativityContent = culCreativityContent;
    }

    public String getCulCreativityImage() {
        return culCreativityImage;
    }

    public void setCulCreativityImage(String culCreativityImage) {
        this.culCreativityImage = culCreativityImage;
    }

    public String getCulCreativityCategory() {
        return culCreativityCategory;
    }

    public void setCulCreativityCategory(String culCreativityCategory) {
        this.culCreativityCategory = culCreativityCategory;
    }

    public String getCulCreativityType() {
        return culCreativityType;
    }

    public void setCulCreativityType(String culCreativityType) {
        this.culCreativityType = culCreativityType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
