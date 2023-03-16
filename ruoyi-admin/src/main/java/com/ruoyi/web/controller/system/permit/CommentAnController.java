package com.ruoyi.web.controller.system.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.domain.Comment;
import com.ruoyi.system.domain.CommentRecordLike;
import com.ruoyi.system.domain.domainVo.CommentDTO;
import com.ruoyi.system.domain.domainVo.CommentGetDTO;
import com.ruoyi.system.domain.domainVo.CommentVO;
import com.ruoyi.system.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

import static com.ruoyi.common.utils.EmojiUtils.emojiConverterUnicodeStr;
import static com.ruoyi.framework.config.SensitiveConfig.filter;

/**
 * 通用评论接口
 * @author Chas
 * @date 2022-10
 */

@Api("评论管理")
@RestController
@RequestMapping("/permitAll/comment")
public class CommentAnController extends BaseController {

    @Autowired
    private ICommentService commentService;


    /**
     * 新增评论
     * 前端传的数据中要有 parent_id  user_id  comment_content  comment_source
     * parent_id : 是否是根评论  若不是 那么2级评论是什么 只需要2级
     * comment_content : 评论内容
     * comment_source :  评论来源
     *
     */
    @PreAuthorize("@ss.hasRole('common')")
    @ApiOperation("新增评论")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody CommentVO commentVO)
    {
        if (commentVO.getCommentContent().trim().isEmpty()){
            return AjaxResult.error("评论不能为空");
        }
        if (commentVO.getCommentSource().isEmpty() || commentVO.getId().equals(null)){
            return AjaxResult.error("评论失败,未知评论坐标");
        }
        if (commentVO.getParentId() !=-1 && commentVO.getObjectId()==null){
            return AjaxResult.error("请选择原评论");
        }
        String content = emojiConverterUnicodeStr(commentVO.getCommentContent());
        commentVO.setCommentContent(content);
        String ip = getLoginUser().getIpaddr();
        Comment comment = new Comment();
        BeanUtils.copyBeanProp(comment,commentVO);
        comment.setCommentIp(ip);
        comment.setUserId(getUserId());
        comment.setCreateBy(getUsername());
        if (commentVO.getParentId()==-1){
            comment.setObjectId(getUserId());
        }
        // 评论一次过滤
        comment.setCommentContent(filter(comment.getCommentContent()));
        commentService.insertComment(comment);

        return AjaxResult.success("评论成功，请等待审核后显示");
    }

    /**
     * 浏览量++
     * @param commentId
     * @return AjaxResult
     * 前端只需要 传入 commentId
     */
    @Anonymous
    @ApiOperation("增加浏览量")
    @GetMapping("/view/{commentId}")
    public AjaxResult addView(@PathVariable Long commentId){

        commentService.updateCommentViaView(commentId);
        return AjaxResult.success();
    }

    /**
     * 统计日/月/年总浏览量
     * @return
     */
    @ApiOperation("获取所有评论的浏览量")
    @GetMapping("")
    public AjaxResult countView(){

        return AjaxResult.success();
    }

    /**
     * 点击量++
     * @param commentId
     * @return AjaxResult
     */
    @Anonymous
    @ApiOperation("增加点击量")
    @GetMapping("/hits/{commentId}")
    public AjaxResult addHits(@PathVariable Long commentId){
        commentService.updateCommentViaHits(commentId);
        return AjaxResult.success();
    }

    /**
     * 点赞量++  --
     * 相比于其他的较特殊  单独一个表
     * @param commentId
     * @return AjaxResult
     */
    @Anonymous
    @ApiOperation("增加点赞量")
    @GetMapping("/like/{commentId}")
    public AjaxResult addOrCancelLike(@PathVariable Long commentId){

        commentService.CommentManageViaLike(new CommentRecordLike(getUserId(), commentId, "评论"));
        return AjaxResult.success();

    }

    /**
     *  获取 某个页面 的所有父级评论
     */
    @Anonymous
    @ApiOperation("获取某个页面的所有父级评论")
    @PostMapping("/getComment")
    public TableDataInfo getFatherComment(CommentGetDTO commentGetDTO){

        if (commentGetDTO.getCommentSource().trim().isEmpty() || commentGetDTO.getId() == null){
            return errorMsg("地址定位失败，请联系管理员");
        }
        startPage();
        List<CommentDTO> comment = commentService.getPageAllFatherComment(commentGetDTO);
        return getDataTable(comment);
    }

    /**
     * 获取某用户的所有评论
     *
     *  num = 0 是 通过审核的
     *  num = 1 是 还没有审核的
     *  num = 2 是 审核不通过的 可以申诉
     */
    @PreAuthorize("@ss.hasRole('common')")
    @ApiOperation("获取某用户的所有评论")
    @GetMapping("/person/{num}")
    public TableDataInfo getUserAllComment(@PathVariable Integer num){
        if (num!=1&&num!=2){
            return errorMsg("请联系管理员，数据获取失败");
        }
        startPage();
        Long userId = getUserId();
        List<CommentDTO> comments = commentService.getAllCommentsByWays(userId, num);
        return getDataTable(comments);
    }

    /**
     * 获取某个父级下的所有评论
     * @param commentId  只要父级id
     * @return list
     */
    @Anonymous
    @ApiOperation("获取某个父级下的所有评论")
    @GetMapping("/getChildComment/{commentId}")
    public TableDataInfo getChildComment(@PathVariable Long commentId){
        if (commentId == null){
            return errorMsg("请选择评论");
        }
        startPage();
        List<CommentDTO> childComments = commentService.
                getChildComment(commentId);
        return getDataTable(childComments);
    }

    @ApiOperation("更新评论可见状态")
    @GetMapping("/updateComments")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult  updateUnStatusComments(Long[] commentIds){
        commentService.updateUNStatusComments(commentIds);
        return AjaxResult.success();
    }

    @PreAuthorize("@ss.hasRole('common')")
    @ApiOperation("获取用户未查看的评论")
    @GetMapping("/getUnStatusComments")
    public TableDataInfo getUnStatusComments(){
        startPage();
        return getDataTable(commentService.getUnStatusComments(getUserId()));
    }

    @ApiOperation("用户批量删除评论")
    @DeleteMapping("/delete/comments/{commentIds}")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult deleteComments(@PathVariable Long[] commentIds){
        int i = commentService.deleteCommentsByUser(commentIds);
        return AjaxResult.success("成功删除"+i+"条评论");
    }




}
