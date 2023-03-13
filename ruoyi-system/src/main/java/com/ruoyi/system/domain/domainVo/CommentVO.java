package com.ruoyi.system.domain.domainVo;

/**
 * @author chas
 * @introduction 景点VO
 * @data 2023-3
 */
public class CommentVO {

    /** 父级ID  -1 表示爹爹  */
    private Long parentId;

    /** 父用户id */
    private Long objectId;

    /** 内容 */
    private String commentContent;

    /** 评论来源  景点 文章 文创*/
    private String commentSource;

    /** 表示 景点id  文章id  文创id */
    private Long id;

    /**  如果是景点 景点评分*/
    private Double sightsScore;


    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Double getSightsScore() {
        return sightsScore;
    }

    public void setSightsScore(Double sightsScore) {
        this.sightsScore = sightsScore;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentSource() {
        return commentSource;
    }

    public void setCommentSource(String commentSource) {
        this.commentSource = commentSource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
