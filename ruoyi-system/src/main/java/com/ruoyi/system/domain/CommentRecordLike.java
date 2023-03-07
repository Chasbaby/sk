package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CommentRecordLike {
    private Long userId;
    private Long commentId;
    private String commentSource;

    public CommentRecordLike() {
    }

    public CommentRecordLike(Long userId, Long commentId, String commentSource) {
        this.userId = userId;
        this.commentId = commentId;
        this.commentSource = commentSource;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentSource() {
        return commentSource;
    }

    public void setCommentSource(String commentSource) {
        this.commentSource = commentSource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("commentId",getCommentId())
                .append("userId",getUserId())
                .append("commentSource",getCommentSource()).toString();
    }
}
