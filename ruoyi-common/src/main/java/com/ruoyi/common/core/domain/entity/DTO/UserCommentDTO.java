package com.ruoyi.common.core.domain.entity.DTO;

/**
 * @author chas
 * @introduction 评论用户dto
 * @data 2023-3
 */
public class UserCommentDTO {
    private Long userId;
    private String nickName;
    private String avatar;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
