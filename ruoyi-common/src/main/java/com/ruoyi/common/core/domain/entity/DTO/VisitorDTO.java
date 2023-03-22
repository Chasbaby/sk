package com.ruoyi.common.core.domain.entity.DTO;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 传给前端的用户信息
 * @data 2023-3
 */
public class VisitorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 游客id*/
    private Long visitorId;

    /** 用户id*/
    private Long userId;

    /** 用户被订阅数量*/
    private Long visitorSubscribe;

    /** 用户文章发表数量*/
    private Long visitorArticle;

    /** 用户文创发布数量*/
    private Long visitorCul;

    /**用户被点赞*/
    private Long visitorLike;

    /** 用户被浏览*/
    private Long visitorView;

    /** 用户被收藏*/
    private Long visitorCollect;

    private Long subscribeFree;

    /** 原创数量*/
    private Long original;

    /** 评论数量**/
    private Long comments;

    /** 粉丝量*/
    private Long visitorFans;

    /** 关注量*/
    private Long visitorConcerns;

    public Long getVisitorCul() {
        return visitorCul;
    }

    public void setVisitorCul(Long visitorCul) {
        this.visitorCul = visitorCul;
    }

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
}
