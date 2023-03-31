package com.ruoyi.sights.domain;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.sights.service.ISightsHotService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Collection;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static com.ruoyi.common.constant.HotConstants.HOTLABLE;

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

    @Excel(name = "景点ID",type = Excel.Type.ALL,cellType= Excel.ColumnType.NUMERIC)
    /** 景点ID */
    private Long sightsId;

    @Excel(name = "点赞量",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    /** 景点点赞量*/
    private Long sightsLike;

    @Excel(name = "点击量",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    /**景点点击量*/
    private Long sightsHits;

    @Excel(name = "浏览量",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    /**景点浏览量*/
    private Long sightsView;
    @Excel(name = "收藏",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    /** 收藏量**/
    private Long sightsCollect;
    @Excel(name = "平均分",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    /**景点平均分*/
    private Double sightsScore;
    /** 景点名称 */
    @Excel(name = "景点名称",type = Excel.Type.ALL)
    private String sightsName;
    @Excel(name = "热度",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    /** 景点热度*/
    private Long sightsHot;
    @Excel(name = "联系电话",type = Excel.Type.ALL)
    /** 景点联系电话*/
    private String sightsTelephone;
    @Excel(name = "英文",type = Excel.Type.ALL)
    /** 景点英文*/
    private String sightsEng;
    @Excel(name = "开放信息",type = Excel.Type.ALL)
    /** 景点开放信息*/
    private String sightsOpen;
    @Excel(name = "景点位置",type = Excel.Type.ALL)
    /** 景点位置(文字描述) */
    private String sightsLocation;
    @Excel(name = "经度",type = Excel.Type.ALL,cellType= Excel.ColumnType.NUMERIC,scale=3)
    /** 景点经度 */
    private Double sightsLongitude;
    @Excel(name="纬度",type = Excel.Type.ALL,cellType= Excel.ColumnType.NUMERIC,scale = 3)
    /** 景点纬度 */
    private Double sightsLatitude;
    @Excel(name="图片",type = Excel.Type.ALL,cellType= Excel.ColumnType.IMAGE)
    /** 景点图片url  最多5张*/
    private String sightsImage;

    @Excel(name = "二维码",type = Excel.Type.ALL,cellType = Excel.ColumnType.IMAGE)
    /**景点购物二维码*/
    private String sightsCode;

    /** 景点简介 */
    @Excel(name = "景点简介",type = Excel.Type.ALL)
    private String sightsIntro;

    @Excel(name = "景点详细",type = Excel.Type.ALL)
    /** 景点的细节 */
    private String sightsDetail;

    /** 景点的种类 */
    @Excel(name = "景点的种类",type = Excel.Type.ALL,dictType = "sights_category")
    private String sightsCategory;

    /** 景点国家 */
    @Excel(name = "景点国家",type = Excel.Type.ALL)
    private String sightsNation;

    /** 景点城市 */
    @Excel(name = "景点城市",type = Excel.Type.ALL)
    private String sightsCity;


    private Date lastUpdated;


    public synchronized void addView(){
        sightsView ++;
        refresh();
    }

    public synchronized void addLike(){
        sightsLike ++;
        refresh();
    }

    public synchronized void reduceLike(){
        sightsLike--;
    }

    public synchronized void addCollect(){
        sightsCollect ++;
        refresh();
    }

    public synchronized void reduceCollect(){
        sightsCollect--;
    }

    public synchronized void addHits(){
        sightsHits ++;
        refresh();
    }

    public synchronized void addScore(Double score){
        this.sightsScore = (score + this.sightsScore) / 2.0;
        refresh();
    }

    private void refresh(){
        this.lastUpdated = new Date();
        calculateHot();

    }

    private void calculateHot(){
        // 这个变化可能有点大  暂定
        this.sightsHot = sightsHot + new Double(sightsLike * 0.3 + sightsHits * 0.1
                + sightsCollect * 0.4 + sightsView * 0.2 + sightsScore * 10).longValue()/
                new Double(time(lastUpdated,getUpdateTime())).intValue();
    }

    private double time(Date now,Date updated){
        System.out.println("xxxxxxxx"+now);
        System.out.println("qqqqqqqqqqqqq"+updated);
        return Math.exp(now.getTime() - updated.getTime());
    }


    /**
     * 更新景点热度 在定时任务
     */
    public static void hotTimeReduce(){
        SpringUtils.getBean(ISightsHotService.class).updateHot();
    }

    /**
     * 下面是set get方法
     * @return
     */

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

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
