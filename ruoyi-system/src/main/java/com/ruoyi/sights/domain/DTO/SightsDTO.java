package com.ruoyi.sights.domain.DTO;

import com.ruoyi.sights.domain.SightsTicket;

import java.io.Serializable;
import java.util.List;

/**
 * @author chas
 * @introduction 核心地带 景点DTO
 * @data 2023-3
 */
public class SightsDTO implements Serializable {
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

    private String sightsName;

    /** 景点热度*/
    private Long sightsHot;

    /** 景点联系电话*/
    private String sightsTelephone;

    /** 景点英文*/
    private String sightsEng;

    /** 景点开放信息*/
    private String sightsOpen;

    private String sightsLocation;

    private String sightsImage;

    private String sightsIntro;

    private String sightsDetail;

    private String sightsCategory;

    private String sightsNation;

    private String sightsCity;

    private List<BulletinDTO> bulletin ;

    private List<TicketDTO> tickets;

    private Integer scoreNum;

    private Integer CommentNum;

    private Integer ifCollect;
    private Integer ifLike;
    private Double score;

    public Integer getIfCollect() {
        return ifCollect;
    }

    public void setIfCollect(Integer ifCollect) {
        this.ifCollect = ifCollect;
    }

    public Integer getIfLike() {
        return ifLike;
    }

    public void setIfLike(Integer ifLike) {
        this.ifLike = ifLike;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDTO> tickets) {
        this.tickets = tickets;
    }

    public Integer getCommentNum() {
        return CommentNum;
    }


    public void setCommentNum(Integer commentNum) {
        CommentNum = commentNum;
    }

    public Integer getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
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

    public List<BulletinDTO> getBulletin() {
        return bulletin;
    }

    public void setBulletin(List<BulletinDTO> bulletin) {
        this.bulletin = bulletin;
    }
}


