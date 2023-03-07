package com.ruoyi.web.controller.system.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.Comment;
import com.ruoyi.system.domain.CommentRecordLike;
import com.ruoyi.system.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

/**
 * @author Chas
 * @date 2022-10
 */

@Api("评论管理")
@RestController
@RequestMapping("/permitAll/comment")
public class CommentAnController extends BaseController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 新增评论
     * 前端传的数据中要有 parent_id  user_id  comment_content  comment_source
     * parent_id : 是否是根评论  若不是 那么2级评论是什么 只需要2级
     * comment_content : 评论内容
     * comment_source :  评论来源
     *
     * 对于 无登录的无法评论 这边直接异常返回
     */
//    @PreAuthorize("@ss.hasPermi('comment:add:permit')")
    @ApiOperation("新增评论")
    @PostMapping("/add")
    @Anonymous
    public AjaxResult add(@RequestBody Comment comment)
    {
        ListenableFuture send =null;
        logger.info("我是小吃吃啊啊啊啊 a");
        // 这里还需要kafka流进行一次判定
        for (int i=0;i<=100;i++){
            kafkaTemplate.send("comment", comment.getCommentContent()).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable ex) {
                    //失败的话怎么办
                }
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    ProducerRecord<String, String> producerRecord = result.getProducerRecord();
                    RecordMetadata recordMetadata = result.getRecordMetadata();
                }
            });
        }
//        comment.setCommentIp(getLoginUser().getIpaddr());
//        comment.setCreateBy(getUsername());
        comment.setCreateTime(new Date());
        return toAjax(commentService.insertComment(comment));
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
     * 获取所有 层级 评论
     *
     * 首次发起请求自然都是 parentId = -1
     * 往后 根据传来的 parentId 调整  并进行分页传送
     *
     * 前端对于 -1 的评论直接写在改在的地方下面
     * 对于 评论的评论 用弹出框进行书写 (这个框做好看一点哦)
     */
    @Anonymous
    @ApiOperation("获得子评论")
    @GetMapping("/all")
    public TableDataInfo getAll(Comment comment){
        startPage();
        List<Comment> comments;
        if(comment.getParentId()==-1){



        }else {


        }

        return getDataTable(null);
    }

    /**
     * 获取某用户的所有评论
     */
    @ApiOperation("获取某用户的所有评论")
    @PostMapping
    public TableDataInfo getUserAllComment(){
        Long userId = getUserId();
        startPage();
        List list=null;
        return getDataTable(list);
    }


}
