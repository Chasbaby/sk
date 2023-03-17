package com.ruoyi.culCreativity.domain.dto;

import java.io.Serializable;

/**
 * @author chas
 * @introduction
 * @data
 */
public class CulCreateDTO implements Serializable {
    private String culCreativityTitle;
    private String culCreativityImage;
    private String culCreativityIntro;
    private String culCreativityContent;
    private String culCreativityCategory;
    private String culCreativityTags;
    private String culCreativityKey;

    public String getCulCreativityTitle() {
        return culCreativityTitle;
    }

    public void setCulCreativityTitle(String culCreativityTitle) {
        this.culCreativityTitle = culCreativityTitle;
    }

    public String getCulCreativityImage() {
        return culCreativityImage;
    }

    public void setCulCreativityImage(String culCreativityImage) {
        this.culCreativityImage = culCreativityImage;
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

    public String getCulCreativityCategory() {
        return culCreativityCategory;
    }

    public void setCulCreativityCategory(String culCreativityCategory) {
        this.culCreativityCategory = culCreativityCategory;
    }

    public String getCulCreativityTags() {
        return culCreativityTags;
    }

    public void setCulCreativityTags(String culCreativityTags) {
        this.culCreativityTags = culCreativityTags;
    }

    public String getCulCreativityKey() {
        return culCreativityKey;
    }

    public void setCulCreativityKey(String culCreativityKey) {
        this.culCreativityKey = culCreativityKey;
    }
}
