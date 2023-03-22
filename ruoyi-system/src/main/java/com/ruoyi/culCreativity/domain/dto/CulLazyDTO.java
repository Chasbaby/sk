package com.ruoyi.culCreativity.domain.dto;

import com.ruoyi.common.core.domain.entity.DTO.UserDTO;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction
 * @data
 */
public class CulLazyDTO implements Serializable {

    private Long culCreativityId;
    private Long userId;
    private String culCreativityTitle;
    private String culCreativityIntro;
    private Long culCreativityLike;
    private Long culCreativityCollection;
    private Long culCreativityHits;
    private Long culCreativityView;
    private UserDTO user;
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

    public String getCulCreativityIntro() {
        return culCreativityIntro;
    }

    public void setCulCreativityIntro(String culCreativityIntro) {
        this.culCreativityIntro = culCreativityIntro;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
