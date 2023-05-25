package com.ruoyi.web.controller.home;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.home.service.IHomeNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 面向游客展示  新闻模块
 * @author Chas
 */

@Api("面向游客新闻管理")
@RestController
@RequestMapping("/permitAll/News")
public class HomeNewsAnController extends BaseController {
    @Autowired
    private IHomeNewsService homeNewsService;


    @Anonymous
    @ApiOperation("/获取随机新闻")
    @GetMapping("/getRandomNew")
    public AjaxResult getRandomNews(){
        return AjaxResult.success(homeNewsService.getRandomNew());
    }

    @Anonymous
    @ApiOperation("获取最近新闻(7天内)")
    @GetMapping("/getRecentlyNew")
    public AjaxResult getRecentlyNew(){
        return AjaxResult.success(homeNewsService.getListNews());
    }

    @Anonymous
    @ApiOperation("获取新闻详细信息")
    @GetMapping("/getInfo/{newsId}")
    public AjaxResult getNewInfo(@PathVariable Long newsId){
        return AjaxResult.success(homeNewsService.getNewsInfo(newsId));
    }

    @Anonymous
    @ApiOperation("轮播新闻展示 (置顶机制)")
    @GetMapping("/swiper")
    public AjaxResult getSwiperShow(){
        return AjaxResult.success(homeNewsService.getSwiper());
    }

}
