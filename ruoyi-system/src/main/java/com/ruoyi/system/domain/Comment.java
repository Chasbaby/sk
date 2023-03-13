package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 评论对象 comment
 * 
 * @author ruoyi
 * @date 2022-10-19
 */
public class Comment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论ID */
    private Long commentId;

    /** 父级ID */
    private Long parentId;

    /** 评论该条评论用户的id */
    private Long userId;

    /** 这条评论的父亲id*/
    private Long objectId;

    /** 评论内容 */
    private String commentContent;

    /**与scource 指名确定的comment*/
    private Long id;

    /** 评论的浏览量 */
    @Excel(name = "评论的浏览量")
    private Integer commentView;

    /** 评论浏览量 */
    @Excel(name = "评论点击")
    private Integer commentHits;

    /** 评论点赞量 */
    @Excel(name = "评论点赞量")
    private Integer commentLike;


    /** 评论来源(’0‘景点，’1‘系统) */
    @Excel(name = "评论来源(’0‘景点，’1‘系统)")
    private String commentSource;

    /** 评论者IP */
    @Excel(name = "评论者IP")
    private String commentIp;

    /** 审核状态(‘2’表示未审核‘0’表示通过审核 ’1‘未通过审核) */
    @Excel(name = "审核状态(‘0’表示未审核‘1’表示通过审核 ’2‘未通过审核)")
    private String judgeStatus;

    /** 审核人 */
    @Excel(name = "审核人")
    private String judger;

    /** 状态(0不可见 1可见) */
    private String visableStatus;

    /** 是否删除(Y表示已删除N表示未删除) */
    private String delFlag;


    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCommentId(Long commentId)
    {
        this.commentId = commentId;
    }

    public Long getCommentId() 
    {
        return commentId;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setCommentContent(String commentContent) 
    {
        this.commentContent = commentContent;
    }

    public String getCommentContent() 
    {
        return commentContent;
    }
    public void setCommentView(Integer commentView) 
    {
        this.commentView = commentView;
    }

    public Integer getCommentView() 
    {
        return commentView;
    }
    public void setCommentHits(Integer commentHits) 
    {
        this.commentHits = commentHits;
    }

    public Integer getCommentHits() 
    {
        return commentHits;
    }
    public void setCommentLike(Integer commentLike) 
    {
        this.commentLike = commentLike;
    }

    public Integer getCommentLike() 
    {
        return commentLike;
    }
    public void setCommentSource(String commentSource) 
    {
        this.commentSource = commentSource;
    }

    public String getCommentSource() 
    {
        return commentSource;
    }
    public void setCommentIp(String commentIp) 
    {
        this.commentIp = commentIp;
    }

    public String getCommentIp() 
    {
        return commentIp;
    }
    public void setJudgeStatus(String judgeStatus) 
    {
        this.judgeStatus = judgeStatus;
    }

    public String getJudgeStatus() 
    {
        return judgeStatus;
    }
    public void setJudger(String judger) 
    {
        this.judger = judger;
    }

    public String getJudger() 
    {
        return judger;
    }
    public void setVisableStatus(String visableStatus) 
    {
        this.visableStatus = visableStatus;
    }

    public String getVisableStatus() 
    {
        return visableStatus;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("commentId", getCommentId())
            .append("parentId", getParentId())
            .append("userId", getUserId())
            .append("objectId",getObjectId())
            .append("id",getId())
            .append("commentContent", getCommentContent())
            .append("commentView", getCommentView())
            .append("commentHits", getCommentHits())
            .append("commentLike", getCommentLike())
            .append("commentSource", getCommentSource())
            .append("commentIp", getCommentIp())
            .append("judgeStatus", getJudgeStatus())
            .append("judger", getJudger())
            .append("visableStatus", getVisableStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
