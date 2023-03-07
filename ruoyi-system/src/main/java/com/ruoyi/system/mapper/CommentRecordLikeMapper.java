package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.CommentRecordLike;

/**
 * (评论)点赞与用户关联表
 * @author Chas
 * 2022-10
 */
public interface CommentRecordLikeMapper {

    /**
     * 插入 用户 点赞评论信息
     */
    public int insertUserCommentLike(CommentRecordLike commentRecordLike);

    /**
     * 取消 用户 点赞评论信息
     */
    public int deleteUserCommentLike(CommentRecordLike commentRecordLike);

    /**
     * 判断 用户 对某评论是否点赞
     */
    public int checkUserCommentLike(CommentRecordLike commentRecordLike);

    /**
     *  获取某评论的点赞总数 (好像不需要)
     */
    public int selectCommentAllLikeNum(Long commentId);

}
