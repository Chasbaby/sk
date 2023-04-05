package com.ruoyi.system.domain.domainVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.DTO.UserCommentDTO;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction 返回接口
 * @data 2023-3
 */
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long commentId;
    private Long parentId;
    private UserCommentDTO user;
    private String fatherName;
    private String commentContent;
    private Long objectId;
    private Long id;
    private Integer commentView;
    private Integer commentHits;
    private Integer commentLike;
    private String commentSource;
    private String commentIp;
    private String visableStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    public String getVisableStatus() {
        return visableStatus;
    }

    public void setVisableStatus(String visableStatus) {
        this.visableStatus = visableStatus;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public UserCommentDTO getUser() {
        return user;
    }

    public void setUser(UserCommentDTO user) {
        this.user = user;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCommentView() {
        return commentView;
    }

    public void setCommentView(Integer commentView) {
        this.commentView = commentView;
    }

    public Integer getCommentHits() {
        return commentHits;
    }

    public void setCommentHits(Integer commentHits) {
        this.commentHits = commentHits;
    }

    public Integer getCommentLike() {
        return commentLike;
    }

    public void setCommentLike(Integer commentLike) {
        this.commentLike = commentLike;
    }

    public String getCommentSource() {
        return commentSource;
    }

    public void setCommentSource(String commentSource) {
        this.commentSource = commentSource;
    }

    public String getCommentIp() {
        return commentIp;
    }

    public void setCommentIp(String commentIp) {
        this.commentIp = commentIp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
