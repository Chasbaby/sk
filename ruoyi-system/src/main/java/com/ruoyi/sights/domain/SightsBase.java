package com.ruoyi.sights.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 景点基本信息对象 sights_base
 * 
 * @author ruoyi chas
 * @date 2022-10-25
 */
@Document(indexName = "sightsInformation")
public class SightsBase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 景点ID */
    private Long sightsId;

    /** 景点点赞量*/
    private Long sightsLike;

    /**景点点击量*/
    private Long sightsHits;

    /**景点浏览量*/
    private Long sightsView;

    /** 收藏量**/
    private Long sightsCollect;

    /**景点平均分*/
    private Double sightsScore;

    /** 景点名称 */
    @Excel(name = "景点名称")
    private String sightsName;

    /** 景点热度*/
    private Long sightsHot;

    /** 景点联系电话*/
    private String sightsTelephone;

    /** 景点英文*/
    private String sightsEng;

    /** 景点开放信息*/
    private String sightsOpen;

    /** 景点位置(文字描述) */
    @Excel(name = "景点位置")
    private String sightsLocation;

    @Excel(name = "景点经度")
    /** 景点经度 */
    private Double sightsLongitude;
    @Excel(name="景点纬度")
    /** 景点纬度 */
    private Double sightsLatitude;

    /** 景点图片url  最多5张*/
    private String sightsImage;

    /**景点购物二维码*/
    private String sightsCode;

    /** 景点简介 */
    @Excel(name = "景点简介")
    private String sightsIntro;

    @Excel(name = "景点详细")
    /** 景点的细节 */
    private String sightsDetail;

    /** 景点的种类 */
    @Excel(name = "景点的种类")
    private String sightsCategory;

    /** 景点国家 */
    @Excel(name = "景点国家")
    private String sightsNation;

    /** 景点城市 */
    @Excel(name = "景点城市")
    private String sightsCity;

    public Long getSightsHot() {
        return sightsHot;
    }

    public void setSightsHot(Long sightsHot) {
        this.sightsHot = sightsHot;
    }

    public String getSightsTelephone() {
        return sightsTelephone;
    }

    public void setSightsTelephone(String sightsTelephone) {
        this.sightsTelephone = sightsTelephone;
    }

    public String getSightsEng() {
        return sightsEng;
    }

    public void setSightsEng(String sightsEng) {
        this.sightsEng = sightsEng;
    }

    public String getSightsOpen() {
        return sightsOpen;
    }

    public void setSightsOpen(String sightsOpen) {
        this.sightsOpen = sightsOpen;
    }

    public Long getSightsCollect() {
        return sightsCollect;
    }

    public void setSightsCollect(Long sightsCollect) {
        this.sightsCollect = sightsCollect;
    }

    public String getSightsCode() {
        return sightsCode;
    }

    public void setSightsCode(String sightsCode) {
        this.sightsCode = sightsCode;
    }

    public Double getSightsScore() {
        return sightsScore;
    }

    public void setSightsScore(Double sightsScore) {
        this.sightsScore = sightsScore;
    }

    public Long getSightsId() {
        return sightsId;
    }

    public void setSightsId(Long sightsId) {
        this.sightsId = sightsId;
    }

    public Long getSightsLike() {
        return sightsLike;
    }

    public void setSightsLike(Long sightsLike) {
        this.sightsLike = sightsLike;
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

    public Double getSightsLongitude() {
        return sightsLongitude;
    }

    public void setSightsLongitude(Double sightsLongitude) {
        this.sightsLongitude = sightsLongitude;
    }

    public Double getSightsLatitude() {
        return sightsLatitude;
    }

    public void setSightsLatitude(Double sightsLatitude) {
        this.sightsLatitude = sightsLatitude;
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

    public String getSightsDetail() {
        return sightsDetail;
    }

    public void setSightsDetail(String sightsDetail) {
        this.sightsDetail = sightsDetail;
    }

    public String getSightsCategory() {
        return sightsCategory;
    }

    public void setSightsCategory(String sightsCategory) {
        this.sightsCategory = sightsCategory;
    }

    public String getSightsNation() {
        return sightsNation;
    }

    public void setSightsNation(String sightsNation) {
        this.sightsNation = sightsNation;
    }

    public String getSightsCity() {
        return sightsCity;
    }

    public void setSightsCity(String sightsCity) {
        this.sightsCity = sightsCity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sightsId", getSightsId())
            .append("sightsLike",getSightsLike())
            .append("sightsHits",getSightsHits())
            .append("sightsView",getSightsView())
            .append("sightsCollect",getSightsCollect())
            .append("sightsScore",getSightsScore())
            .append("hot",getSightsHot())
            .append("tele",getSightsTelephone())
            .append("eng",getSightsEng())
             .append("open",getSightsOpen())
            .append("sightsName", getSightsName())
            .append("sightsLocation", getSightsLocation())
            .append("sightsLongitude", getSightsLongitude())
            .append("sightsLatitude", getSightsLatitude())
            .append("sightsCode",getSightsCode())
            .append("sightsImage", getSightsImage())
            .append("sightsIntro", getSightsIntro())
            .append("sightsDetail", getSightsDetail())
            .append("sightsCategory", getSightsCategory())
            .append("sightsNation", getSightsNation())
            .append("sightsCity", getSightsCity())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
