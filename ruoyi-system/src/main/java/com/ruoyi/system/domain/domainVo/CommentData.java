package com.ruoyi.system.domain.domainVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author chas
 * @introduction 评论数据整合
 * @data 2023-3
 */
public class CommentData implements Serializable {
    private List<CommentDTO> comment;
    private int commentNum;

    public List<CommentDTO> getComment() {
        return comment;
    }

    public void setComment(List<CommentDTO> comment) {
        this.comment = comment;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }


}
