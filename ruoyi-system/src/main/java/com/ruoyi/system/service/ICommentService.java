package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Comment;
import com.ruoyi.system.domain.CommentRecordLike;

/**
 * 评论Service接口
 * 
 * @author ruoyi chas
 * @date 2022-10-19
 *
 */

public interface ICommentService 
{
    /**
     * 下面的是后台函数
     */
    /**
     * 查询评论
     * 
     * @param commentId 评论主键
     * @return 评论
     */
    public Comment selectCommentByCommentId(Long commentId);

    /**
     * 查询评论列表
     * 
     * @param comment 评论
     * @return 评论集合
     */
    public List<Comment> selectCommentList(Comment comment);

    /**
     * 新增评论
     * 
     * @param comment 评论
     * @return 结果
     */
    public int insertComment(Comment comment);

    /**
     * 修改评论
     * 
     * @param comment 评论
     * @return 结果
     */
    public int updateComment(Comment comment);

    /**
     * 批量删除评论
     * 
     * @param commentIds 需要删除的评论主键集合
     * @return 结果
     */
    public int deleteCommentByCommentIds(Long[] commentIds);

    /**
     * 删除评论信息
     * 
     * @param commentId 评论主键
     * @return 结果
     */
    public int deleteCommentByCommentId(Long commentId);

    /**
     * 获取未审核的评论数量
     */
    public int getNoJudgeCommentNum();

    /**
     * 获取评论最多的来源
     */
    public String getMaxCommentSource();

    /**
     * 下面的是 面向游客的函数
     */


    /**
     * 点赞总管理
     * @return 是否成功
     */
    public void CommentManageViaLike(CommentRecordLike commentRecordLike);

    /**
     * 点击量++
     */
    public int updateCommentViaHits(Long commentId);

    /**
     * 点赞量++
     */
    public int updateCommentViaLike(Long commentId);

    /**
     * 浏览量++
     */
    public int updateCommentViaView(Long commentId);

    /**
     * 获取某个景点的评论个数
     */
    public int selectCommentNumBySightsId(Long sights);

    /**
     * 插入 用户 点赞 评论 信息
     */
    public int insertUserCommentLike(CommentRecordLike commentRecordLike);

    /**
     * 用户 取消 点赞评论信息
     */
    public int deleteUserCommentLike(CommentRecordLike commentRecordLike);

    /**
     * 判断 用户 对某评论是否点赞
     */
    public int checkUserCommentLike(CommentRecordLike commentRecordLike);


}
