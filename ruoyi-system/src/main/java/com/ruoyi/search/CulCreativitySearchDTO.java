package com.ruoyi.search;

public class CulCreativitySearchDTO {
    /** 文创ID */
    private Long culCreativityId;

    /** 景点ID */
    private Long sightsId;

    private String culCreativityTitle;

    /**图片*/
    private String culCreativityImage;

    private String culCreativityIntro;
    private String culCreativityTags;
    private String culCreativityKey;

    public CulCreativitySearchDTO() {
    }

    public CulCreativitySearchDTO(Long culCreativityId, Long sightsId, String culCreativityTitle, String culCreativityImage, String culCreativityIntro, String culCreativityTags, String culCreativityKey) {
        this.culCreativityId = culCreativityId;
        this.sightsId = sightsId;
        this.culCreativityTitle = culCreativityTitle;
        this.culCreativityImage = culCreativityImage;
        this.culCreativityIntro = culCreativityIntro;
        this.culCreativityTags = culCreativityTags;
        this.culCreativityKey = culCreativityKey;
    }

    public Long getCulCreativityId() {
        return culCreativityId;
    }

    public void setCulCreativityId(Long culCreativityId) {
        this.culCreativityId = culCreativityId;
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
