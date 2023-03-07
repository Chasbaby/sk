package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Comment;

/**
 * 评论Mapper接口
 * @author ruoyi chas
 * @date 2022-10
 */
public interface CommentMapper 
{
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
     * 删除评论
     * 
     * @param commentId 评论主键
     * @return 结果
     */
    public int deleteCommentByCommentId(Long commentId);

    /**
     * 批量删除评论
     * 
     * @param commentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommentByCommentIds(Long[] commentIds);

    /**
     * 获取未审核的评论数量
     */
    public int selectCommentNoJudgeNum();

    /**
     * 获取最多的评论来源
     */
    public String getMaxCommentSource();

    /**
     * 浏览量++
     */
    public int updateCommentViaView(Long commentId);

    /**
     * 点赞++
     */
    public int updateCommentViaLike(Long commentId);

    /**
     * 点击++
     */
    public int updateCommentViaHits(Long commentId);

    /**
     * 获取某景点的评论总数   多表关联
     */
    public int selectAllCommentNumBySightsId(Long sightsId);

    /**
     * 获取某景点的所有父级评论 多表关联
     */
    public List<Comment> selectAllParentCommentBySightsId(Long SightsId);

    /**
     * 根据父级评论查找 所有子集
     */
    public List<Comment> selectAllChildrenCommentByParentId(Long parentId);



}
