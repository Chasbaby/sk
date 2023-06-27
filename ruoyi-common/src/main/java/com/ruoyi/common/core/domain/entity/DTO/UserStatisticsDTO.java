package com.ruoyi.common.core.domain.entity.DTO;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 用户数据统计显示
 * @date 2023-6-27
 */
public class UserStatisticsDTO implements Serializable {
    private Long visitorId;
    private Long userId;
    private Long visitorArticle;
    private Long visitorCul;
    private Long visitorLike;
    private Long visitorView;
    private Long visitorCollect;
    private Long visitorFans;
    private Long visitorConcerns;
    private Long comments;


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

    public Long getVisitorArticle() {
        return visitorArticle;
    }

    public void setVisitorArticle(Long visitorArticle) {
        this.visitorArticle = visitorArticle;
    }

    public Long getVisitorCul() {
        return visitorCul;
    }

    public void setVisitorCul(Long visitorCul) {
        this.visitorCul = visitorCul;
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

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }
}
