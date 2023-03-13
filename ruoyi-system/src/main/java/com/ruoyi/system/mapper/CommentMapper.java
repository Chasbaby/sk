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
     * 获取某的评论总数
     */
    public int selectAllComment(String source,Long id);

    /**
     * 获取某景点的所有父级评论
     */
    public List<Comment> selectAllParentComment(String source,Long id);

    /**
     * 根据父级评论查找 所有子集
     */
    public List<Comment> selectAllChildrenCommentByParentId(Long parentId);

    /**
     * 获取用户还没看的评论条数
     * 也就是 父级的评论 有多少子级还没有看的
     * @param userId
     * @return 数量
     */
    public int countUserUNStatusComment(Long userId);

    /**
     * 获取用户还没看的评论回复的信息
     * @param userId
     * @return
     */
    public List<Comment> selectUserUNStatusComment(Long userId);

    /**
     * 批量更新将 已经看的评论
     */
    public int updateUNStatusComments(Long[] commentIds);

    /**
     * 用户删单个评论
     * @param commentId
     * @return
     */
    public int deleteCommentByUser(Long commentId);

    /**
     * 批量用户删除评论
     * @param commentIds
     * @return
     */
    public int deleteCommentsByUser(Long[] commentIds);



}
