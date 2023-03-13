package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Comment;
import com.ruoyi.system.domain.CommentRecordLike;
import com.ruoyi.system.domain.domainVo.CommentDTO;
import com.ruoyi.system.domain.domainVo.CommentData;
import com.ruoyi.system.domain.domainVo.CommentGetDTO;

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
     * 获取某个页面的评论总数数
     */
    public int selectCommentNum(String source,Long id);

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

//    /**
//     * 获取用户所有的评论
//     * @param userId
//     * @return
//     */
//    public List<CommentDTO> getUserAllComment(Long userId);

    /**
     * 获取所有的父级评论
     * @param commentGetDTO
     * @return
     */
    public List<CommentDTO> getPageAllFatherComment(CommentGetDTO commentGetDTO);

    /** 获取某个父级下的所有子级*/
    public List<CommentDTO> getChildComment(Long commentId);

    /**
     * 批量更新还没有看的
     * @param commentIds
     * @return
     */
    public int updateUNStatusComments(Long[] commentIds);

    /**
     * 获取用户未查看的评论
     * @param userId
     * @return
     */
    public List<CommentDTO> getUnStatusComments(Long userId);

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

    /**
     * 分类获取用户所有评论
     */
    public List<CommentDTO> getAllCommentsByWays(Long userId,Integer way);

}
