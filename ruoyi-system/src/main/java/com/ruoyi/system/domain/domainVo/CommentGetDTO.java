package com.ruoyi.system.domain.domainVo;

import java.io.Serializable;
import java.util.Map;

/**
 * @author chas
 * @introduction 获取评论dto
 * @data 2023-3
 */
public class CommentGetDTO implements Serializable{
    private Long parentId;

    private String commentSource;
    private Long id;
    /** 请求参数 */
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
