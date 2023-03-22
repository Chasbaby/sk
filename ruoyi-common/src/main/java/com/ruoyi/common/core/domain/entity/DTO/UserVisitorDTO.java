package com.ruoyi.common.core.domain.entity.DTO;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 文创页面的信息
 * @data
 */
public class UserVisitorDTO implements Serializable {
    private Long userId;
    private String nickName;
    private String intro;
    private String avatar;
    private String backgroundImage;
    private VisitorDTO visitor;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public VisitorDTO getVisitor() {
        return visitor;
    }

    public void setVisitor(VisitorDTO visitor) {
        this.visitor = visitor;
    }
}
