package com.ruoyi.web.controller.CulCreativity.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.culCreativity.domain.dto.CulDetail;
import com.ruoyi.sights.service.ISightsCulCreativityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author chas
 * @introduction 文创服务
 * @data 2023-3
 */
@ApiOperation("文创游客板块")
@RestController
@RequestMapping("/creativity")
public class CulCreativityAnController {

    @Autowired
    private ISightsCulCreativityService creativityService;


    /**
     * 文创点赞
     * @param culCreativityId id
     * @return  状态信息
     */
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/like/{culCreativityId}")
    public AjaxResult CreativityLike(@PathVariable Long culCreativityId){

        return null;
    }

    /**
     * 文创浏览 登陆性浏览
     * @param culCreativityId id
     * @return 状态信息
     */
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/view/{culCreativityId}")
    public AjaxResult CreativityView(@PathVariable Long culCreativityId){

        return null;
    }

    /**
     * 文创非登录浏览
     * @param culCreativityId id
     * @return 状态信息
     */
    @Anonymous
    @GetMapping("/view/anonymous/{culCreativityId}")
    public AjaxResult CreativityViewAnonymous(@PathVariable Long culCreativityId){
        return null;
    }

    @Anonymous
    @GetMapping("/getDetail/{culCreativityId}")
    public AjaxResult getCulDetail(@PathVariable Long culCreativityId){
        CulDetail detail =  creativityService.getCulDetail(culCreativityId);
        return AjaxResult.success(detail);
    }

    /**
     * 订阅文创
     * @param culCreativityId id
     * @return 状态信息
     */
    @GetMapping("/subscription/{culCreativityId}")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult subscription(@PathVariable Long culCreativityId){


        return null;
    }

    /**
     * 用户创作文创
     * @return 状态信息
     */
    @PreAuthorize("@ss.hasRole('common')")
    @PostMapping("/create")
    public AjaxResult crate(){


        return null;
    }






}
