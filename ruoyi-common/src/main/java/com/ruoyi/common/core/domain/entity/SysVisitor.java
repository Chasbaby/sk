package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction 用户信息
 * @data 2023-3
 */
public class SysVisitor implements Serializable {

    /** 游客id*/
    private Long visitorId;

    /** 用户id*/
    private Long userId;

    /**充值金额*/
    private Double visitorMoney;

    /** 用户兴趣 用 | 分割*/
    private String visitorInterest;

    /** 用户特征 用 | 分割*/
    private String visitorFeature;

    /** 用户被订阅数量*/
    private Long visitorSubscribe;

    /** 用户文章发表数量*/
    private Long visitorArticle;

    /**用户被点赞*/
    private Long visitorLike;

    /** 用户被浏览*/
    private Long visitorView;

    /** 用户被收藏*/
    private Long visitorCollect;

    /** 粉丝量*/
    private Long visitorFans;

    /** 关注量*/
    private Long visitorConcerns;

    /** 数据上一次更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 免费订阅次数*/
    private Long subscribeFree;

    /** 原创数量*/
    private Long original;

    /** 评论数量**/
    private Long comments;

    public Long getVisitorFans() {
        return visitorFans;
    }

    public void setVisitorFans(Long visitorFans) {
        this.visitorFans = visitorFans;
    }

    public Long getVisitorConcerns() {
        return visitorConcerns;
    }

    public void setVisitorConcerns(Long visitorConcerns) {
        this.visitorConcerns = visitorConcerns;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getVisitorMoney() {
        return visitorMoney;
    }

    public void setVisitorMoney(Double visitorMoney) {
        this.visitorMoney = visitorMoney;
    }

    public String getVisitorInterest() {
        return visitorInterest;
    }

    public void setVisitorInterest(String visitorInterest) {
        this.visitorInterest = visitorInterest;
    }

    public String getVisitorFeature() {
        return visitorFeature;
    }

    public void setVisitorFeature(String visitorFeature) {
        this.visitorFeature = visitorFeature;
    }

    public Long getVisitorSubscribe() {
        return visitorSubscribe;
    }

    public void setVisitorSubscribe(Long visitorSubscribe) {
        this.visitorSubscribe = visitorSubscribe;
    }

    public Long getVisitorArticle() {
        return visitorArticle;
    }

    public void setVisitorArticle(Long visitorArticle) {
        this.visitorArticle = visitorArticle;
    }

    public Long getVisitorLike() {
        return visitorLike;
    }

    public void setVisitorLike(Long visitorLike) {
        this.visitorLike = visitorLike;
    }

    public Long getVisitorView() {
        return visitorView;
    }

    public void setVisitorView(Long visitorView) {
        this.visitorView = visitorView;
    }

    public Long getVisitorCollect() {
        return visitorCollect;
    }

    public void setVisitorCollect(Long visitorCollect) {
        this.visitorCollect = visitorCollect;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getSubscribeFree() {
        return subscribeFree;
    }

    public void setSubscribeFree(Long subscribeFree) {
        this.subscribeFree = subscribeFree;
    }

    public Long getOriginal() {
        return original;
    }

    public void setOriginal(Long original) {
        this.original = original;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("VisitorId",getVisitorId())
                .append("UserId",getUserId())
                .append("Comments",getComments())
                .append("Original",getOriginal())
                .append("VisitorArticle",getVisitorArticle())
                .append("SubscribeFree", getSubscribeFree())
                .append("UpdateTime",getUpdateTime())
                .append("VisitorCollect",getVisitorCollect())
                .append("VisitorFeature",getVisitorFeature())
                .append("VisitorInterest",getVisitorInterest())
                .append("VisitorMoney",getVisitorMoney())
                .append("VisitorSubscribe",getVisitorSubscribe())
                .append("VisitorView",getVisitorView())
                .append("VisitorLike",getVisitorLike())
                .toString();
    }
}
