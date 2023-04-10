package com.ruoyi.web.controller.activity.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.domainVo.ActivityPersonDTO;
import com.ruoyi.system.service.ISysActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chas
 * @introduction 活动接口
 * @date 2023-4
 */
@Api("活动展示")
@RestController
@RequestMapping("/activity")
public class ActivityAnController {

    @Autowired
    private ISysActivityService activityService;

    @ApiOperation("首页轮播")
    @GetMapping("/person/carousel")
    @Anonymous
    public AjaxResult getPersonSwiper(){
        List<ActivityPersonDTO> swiper = activityService.getPersonSwiper();
        return AjaxResult.success(swiper);
    }

    @ApiOperation("获取热门活动")
    @GetMapping("/person/hot")
    @Anonymous
    public AjaxResult getHotActivity(){
        List<ActivityPersonDTO> swiper = activityService.getPersonSwiper();
        return AjaxResult.success(swiper);
    }





}
