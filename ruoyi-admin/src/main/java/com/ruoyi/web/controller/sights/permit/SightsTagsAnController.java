package com.ruoyi.web.controller.sights.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chas
 * @introduction 景点Tags
 * @date 2023-5-28
 */
@Api("景点标签操作")
@Anonymous
@RestController
@RequestMapping("/tags")
public class SightsTagsAnController {

    /**
     *  只有给景点评论过的
     *
     * 为景点添加 tags 便于推荐
     * 字数限制  5 字以内 请前端判断一下 谢谢
     * 并加入推荐 用于 内容匹配
     * 标签集合格式   |内容 @ userId|
     * 算法 : 1.提取数据库中对应sightsId的tags
     *       2.tags 2次 分词
     *       3.比较内容 若相同 则tags无需加入
     *       4.         不同 若数量达标则 根据循环队列的做法 从第一个开始覆盖
     * 存入日志采用 kafka 异步
     */
    @ApiOperation("为景点添加tags")
    @PostMapping("/addTags")
    @PreAuthorize("@ss.hasAnyRoles('common')")
    public AjaxResult addSightsTags(@PathVariable Long sightsId, @PathVariable String tags){
        // 1. 检查tags 格式合理性 (手动)
        // 2. 检查tags 内容合理性 (kafka)
        // 3. 加入日志 用于 内容匹配
        // 4. 存入数据库 用于后期展示
        return AjaxResult.success();
    }


    /**
     * 默认展示的 tags 用户可选的tags
     * 直接从数据库中获取即可
     * 限于篇幅 每次只传一定的量 内容是随机的
     */
    @ApiOperation("默认展示tag")
    @PostMapping("/defaultTags")
    public AjaxResult defaultTags(){

        return AjaxResult.success();

    }
}
