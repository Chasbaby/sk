package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.entity.DTO.UserCommentDTO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.domain.CommentRecordLike;
import com.ruoyi.system.domain.domainVo.CommentDTO;
import com.ruoyi.system.domain.domainVo.CommentGetDTO;
import com.ruoyi.system.mapper.CommentRecordLikeMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CommentMapper;
import com.ruoyi.system.domain.Comment;
import com.ruoyi.system.service.ICommentService;
import org.springframework.transaction.annotation.Transactional;



/**
 * 评论Service业务层处理
 * 
 * @author ruoyi chas
 * @date 2022-10-19
 */
@Service
public class CommentServiceImpl implements ICommentService
{

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentRecordLikeMapper commentRecordLikeMapper;

    /**
     * 下面几个是后台的函数
     * 管理员的增删改查
     */

    /**
     * 查询评论
     * 
     * @param commentId 评论主键
     * @return 评论
     */
    @Override
    public Comment selectCommentByCommentId(Long commentId)
    {
        return commentMapper.selectCommentByCommentId(commentId);
    }

    /**
     * 查询评论列表
     * 
     * @param comment 评论
     * @return 评论
     */
    @Override
    public List<Comment> selectCommentList(Comment comment)
    {
        return commentMapper.selectCommentList(comment);
    }

    /**
     * 新增评论
     * 
     * @param comment 评论
     * @return 结果
     */
    @Override
    public int insertComment(Comment comment)
    {
        comment.setCreateTime(DateUtils.getNowDate());
        return commentMapper.insertComment(comment);
    }

    /**
     * 修改评论
     * 
     * @param comment 评论
     * @return 结果
     */
    @Override
    public int updateComment(Comment comment)
    {
        comment.setUpdateTime(DateUtils.getNowDate());
        return commentMapper.updateComment(comment);
    }

    /**
     * 批量删除评论
     * 
     * @param commentIds 需要删除的评论主键
     * @return 结果
     */
    @Override
    public int deleteCommentByCommentIds(Long[] commentIds)
    {
        return commentMapper.deleteCommentByCommentIds(commentIds);
    }

    @Override
    public int getNoJudgeCommentNum() {
        return commentMapper.selectCommentNoJudgeNum();
    }

    @Override
    public String getMaxCommentSource() {
        return commentMapper.getMaxCommentSource();
    }


    /**
     * 下面几个是 面向游客 的业务逻辑
     */

    /**
     * 点赞总管理 ( controller 调这个啦)
     * @param commentRecordLike
     */
    @Transactional
    @Override
    public void CommentManageViaLike(CommentRecordLike commentRecordLike) {
        int i = checkUserCommentLike(commentRecordLike);
        // 如果 i=1 说明存在点赞 再次点赞 = 取消点赞
        if(i==1){
            //1.点赞量 --
            //2.删除关联表中记录
            deleteUserCommentLike(commentRecordLike);
            //3.记录到日志中 (这其实没必要)

        }else {
            //1.点赞量++
            updateCommentViaLike(commentRecordLike.getCommentId());
            //2.记录到关联表中
            insertUserCommentLike(commentRecordLike);
            //3.记录到日志中

        }
    }


    /**
     * 删除评论信息
     * 
     * @param commentId 评论主键
     * @return 结果
     */
    @Override
    public int deleteCommentByCommentId(Long commentId)
    {
        return commentMapper.deleteCommentByCommentId(commentId);
    }


    /**
     * 点击量
     * @param commentId
     * @return
     */
    @Override
    public int updateCommentViaHits(Long commentId) {
        return commentMapper.updateCommentViaHits(commentId);
    }

    /**
     * 点赞量
     * @param commentId
     * @return
     */
    @Override
    public int updateCommentViaLike(Long commentId) {
        return commentMapper.updateCommentViaLike(commentId);
    }

    /**
     * 浏览量
     * @param commentId
     * @return
     */
    @Override
    public int updateCommentViaView(Long commentId) {
        return commentMapper.updateCommentViaView(commentId);
    }

    /**
     * 获取某个评论个数
     *
     * 在获得单个数据的时候顺便把这个也送过去
     */
    @Override
    public int selectCommentNum(String source,Long id) {

        return commentMapper.selectAllComment(source,id);
    }

    /**
     * 插入 用户 点赞评论信息
     */
    @Override
    public int insertUserCommentLike(CommentRecordLike commentRecordLike) {
        return commentRecordLikeMapper.insertUserCommentLike(commentRecordLike);
    }

    /**
     * 用户 取消 点赞评论信息
     */
    @Override
    public int deleteUserCommentLike(CommentRecordLike commentRecordLike) {
        return commentRecordLikeMapper.deleteUserCommentLike(commentRecordLike);
    }

    /**
     * 判断 用户 对某评论是否点赞
     */
    @Override
    public int checkUserCommentLike(CommentRecordLike commentRecordLike) {
        return commentRecordLikeMapper.checkUserCommentLike(commentRecordLike);
    }


    /**
     * 获取某地所有父级评论
     * @param commentGetDTO
     * @return
     */
    @Override
    public List<CommentDTO> getPageAllFatherComment(CommentGetDTO commentGetDTO) {
        List<CommentDTO> commentDTOS =commentMapper.selectAllParentComment(commentGetDTO.getCommentSource(), commentGetDTO.getId());

        handleCommentDTO(commentDTOS);
        return commentDTOS;
    }

    private void handleCommentDTO(List<CommentDTO> commentDTOS) {
        Iterator<CommentDTO> iterator = commentDTOS.iterator();
        while(iterator.hasNext()){
            CommentDTO next = iterator.next();
            Long userId = next.getUserId();
            SysUser sysUser = userMapper.selectUserById(userId);
            UserCommentDTO user = new UserCommentDTO();
            BeanUtils.copyBeanProp(user, sysUser);
            next.setUser(user);
        }
    }


    private CommentDTO handleComment(Comment item) {

        CommentDTO commentDTO = new CommentDTO();
        // 查询用户
        SysUser sysUser = userMapper.selectUserById(item.getUserId());
        // 获取部分信息
        UserCommentDTO user = new UserCommentDTO();
        BeanUtils.copyBeanProp(user, sysUser);
        // 放入
        commentDTO.setUser(user);
        BeanUtils.copyBeanProp(commentDTO, item);
        return commentDTO;
    }

    /**
     * 获取某个父级下的所有子级评论
     * @param commentId 父级 id
     * @return
     */
    @Override
    public List<CommentDTO> getChildComment(Long commentId) {
        List<CommentDTO> comments = commentMapper.selectAllChildrenCommentByParentId(commentId);
        if (comments.size() == 0){
            return new ArrayList<>();
        }
        Iterator<CommentDTO> iterator = comments.iterator();
        while (iterator.hasNext()){
            CommentDTO next = iterator.next();
            SysUser sysUser = userMapper.selectUserById(next.getUserId());
            UserCommentDTO userCommentDTO = new UserCommentDTO();
            BeanUtils.copyBeanProp(userCommentDTO,sysUser);

            next.setUser(userCommentDTO);

            SysUser user = userMapper.selectUserById(next.getObjectId());

            next.setFatherName(user.getNickName());
        }
        return comments;
    }

    @Override
    public int updateUNStatusComments(Long[] commentIds) {
        int i = commentMapper.updateUNStatusComments(commentIds);
        return i;
    }

    /**
     * 获取用户未查看的评论
     * @param userId
     * @return
     */
    @Override
    public List<CommentDTO> getUnStatusComments(Long userId) {
        List<Comment> comments = commentMapper.selectUserUNStatusComment(userId);
//        List<Long> change = new ArrayList<>();
        List<CommentDTO> dtos = comments.stream().map(item -> {
//            change.add(item.getCommentId());
            CommentDTO commentDTO = handleComment(item);
            return commentDTO;
        }).collect(Collectors.toList());
        // 修改已读状态
//        commentMapper.updateVisible(change);
// 降序 是 1
        return dtos
                .stream()
                .sorted((o1, o2) -> o1.getVisableStatus() ==  "N" ? 1 : -1 )
                .sorted((o1,o2)->o1.getCreateTime().getTime()>o2.getCreateTime().getTime()?1:-1)
                .collect(Collectors.toList());
    }

    /**
     * 删除单条评论
     * @param commentId
     * @return
     */
    @Override
    public int deleteCommentByUser(Long commentId) {
        return commentMapper.deleteCommentByUser(commentId);
    }

    /**
     * 批量删除
     * @param commentIds
     * @return
     */
    @Override
    public int deleteCommentsByUser(Long[] commentIds) {
        return commentMapper.deleteCommentsByUser(commentIds);
    }

    /**
     * 分类获取用户所有评论
     *      *  num = 0 是 通过审核的
     *      *  num = 1 是 还没有审核的
     *      *  num = 2 是 审核不通过的 可以申诉
     * @param userId
     * @param way
     * @return
     */
    @Override
    public List<CommentDTO> getAllCommentsByWays(Long userId, Integer way) {

        List<CommentDTO> comments = commentMapper.getAllCommentsByWays(userId, way);
        handleCommentDTO(comments);
        return comments;
    }

    @Override
    public int returnUnVisibleNum(Long userId) {
        return commentMapper.getUnvisibleNum(userId);
    }


}
