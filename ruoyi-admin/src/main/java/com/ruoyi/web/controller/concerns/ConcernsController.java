package com.ruoyi.web.controller.concerns;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.concerns.service.IConcernsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author chas
 * @introduction 用户之间
 * @data 2023-3
 */
@Api("用户之间操作")
@RestController
@RequestMapping("/inter")
public class ConcernsController extends BaseController {

    @Autowired
    private IConcernsService concernsService;

    /**
     * 取消/添加关注
     * 规定:  main  关注  prior
     * @param priorUser id
     * @return 状态信息
     */
    @ApiOperation("取消/增加关注")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/add/{priorUser}")
    public AjaxResult addConcerns(@PathVariable Long priorUser){
        Long mainUser = getUserId();
        int flag = concernsService.addConcerns(mainUser, priorUser);
        if (flag == 1 ){
            return AjaxResult.success("");
        }else {
            return AjaxResult.success("");
        }

    }

    /**
     * 展示粉丝列表
     * @return 列表数据
     */
    @ApiOperation("展示粉丝列表")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/showFans")
    public AjaxResult showFans(){

        return null;
    }

    /**
     * 展示关注列表
     * @return 列表数据
     */
    @ApiOperation("展示关注列表")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/showConcerns")
    public AjaxResult showConcerns(){
        return null;
    }






}
