package com.ruoyi.culCreativity.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.DTO.UserDTO;
import com.ruoyi.sights.domain.DTO.SightsCulDTO;

import java.util.Date;

/**
 * @author chas
 * @introduction culdto
 * @data 2023-3
 */
public class CulDetail {
    private static final long serialVersionUID = 1L;
    private Long culCreativityId;
    private Long sightsId;
    private SightsCulDTO sight;
    private Long userId;
    private UserDTO user;
    private String culCreativityTitle;
    private String culCreativityImage;
    private String culCreativityIntro;
    private String culCreativityContent;
    private String culCreativityCategory;
    private String culCreativityTags;
    private Long culCreativityLike;
    private Long culCreativityDislike;
    private Long culCreativityCollection;
    private Long culCreativityHits;
    private Long culCreativityView;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    public String getCulCreativityContent() {
        return culCreativityContent;
    }

    public void setCulCreativityContent(String culCreativityContent) {
        this.culCreativityContent = culCreativityContent;
    }

    public SightsCulDTO getSight() {
        return sight;
    }

    public void setSight(SightsCulDTO sight) {
        this.sight = sight;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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

    public String getCulCreativityTags() {
        return culCreativityTags;
    }

    public void setCulCreativityTags(String culCreativityTags) {
        this.culCreativityTags = culCreativityTags;
    }

    public Long getCulCreativityLike() {
        return culCreativityLike;
    }

    public void setCulCreativityLike(Long culCreativityLike) {
        this.culCreativityLike = culCreativityLike;
    }

    public Long getCulCreativityDislike() {
        return culCreativityDislike;
    }

    public void setCulCreativityDislike(Long culCreativityDislike) {
        this.culCreativityDislike = culCreativityDislike;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
