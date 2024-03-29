package com.ruoyi.culCreativity.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction
 * @date 2023-4
 */
public class CulHomeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long culCreativityId;
    private Long userId;
    private String culCreativityTitle;
    private String culCreativityImage;
    private String culCreativityIntro;
    private String culCreativityCategory;
    private String culCreativityType;
    private Long culCreativityLike;
    private Long culCreativityCollection;
    private Long culCreativityHits;
    private Long culCreativityView;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCulCreativityId() {
        return culCreativityId;
    }

    public void setCulCreativityId(Long culCreativityId) {
        this.culCreativityId = culCreativityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getCulCreativityLike() {
        return culCreativityLike;
    }

    public void setCulCreativityLike(Long culCreativityLike) {
        this.culCreativityLike = culCreativityLike;
    }

    public Long getCulCreativityCollection() {
        return culCreativityCollection;
    }

    public void setCulCreativityCollection(Long culCreativityCollection) {
        this.culCreativityCollection = culCreativityCollection;
    }

    public Long getCulCreativityHits() {
        return culCreativityHits;
    }

    public void setCulCreativityHits(Long culCreativityHits) {
        this.culCreativityHits = culCreativityHits;
    }

    public Long getCulCreativityView() {
        return culCreativityView;
    }

    public void setCulCreativityView(Long culCreativityView) {
        this.culCreativityView = culCreativityView;
    }
}
