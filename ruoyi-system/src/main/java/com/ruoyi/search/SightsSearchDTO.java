package com.ruoyi.search;

public class SightsSearchDTO extends SearchTop{

    /** 景点ID */
    private Long sightsId;
    /** 景点名称**/
    private String sightsName;
    /** 地理位置**/
    private String sightsLocation;
    /**简介**/
    private String sightsIntro;
    /**详细内容**/
    private String sightsDetail;

    private String sightsImage;

    public SightsSearchDTO() {
    }

    public SightsSearchDTO(Long sightsId, String sightsName, String sightsLocation, String sightsIntro, String sightsDetail,String sightsImage) {
        this.sightsId = sightsId;
        this.sightsName = sightsName;
        this.sightsLocation = sightsLocation;
        this.sightsIntro = sightsIntro;
        this.sightsDetail = sightsDetail;
        this.sightsImage = sightsImage;
    }

    public String getSightsImage() {
        return sightsImage;
    }

    public void setSightsImage(String sightsImage) {
        this.sightsImage = sightsImage;
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

    public String getSightsLocation() {
        return sightsLocation;
    }

    public void setSightsLocation(String sightsLocation) {
        this.sightsLocation = sightsLocation;
    }

    public String getSightsIntro() {
        return sightsIntro;
    }

    public void setSightsIntro(String sightsIntro) {
        this.sightsIntro = sightsIntro;
    }

    public String getSightsDetail() {
        return sightsDetail;
    }

    public void setSightsDetail(String sightsDetail) {
        this.sightsDetail = sightsDetail;
    }
}
