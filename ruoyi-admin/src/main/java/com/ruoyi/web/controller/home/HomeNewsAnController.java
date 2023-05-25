package com.ruoyi.web.controller.home;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.home.HomeAdException;
import com.ruoyi.home.domain.HomeNews;
import com.ruoyi.home.domain.dto.newsDTO;
import com.ruoyi.home.service.IHomeNewsService;
import com.ruoyi.system.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 面向游客展示  新闻模块
 *
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
        return null;
    }

    @Anonymous
    @ApiOperation("获取最近新闻(7天内)")
    @GetMapping("/getRecentlyNew")
    public AjaxResult getRecentlyNew(){
        return null;
    }

    @Anonymous
    @ApiOperation("获取新闻详细信息")
    @GetMapping("/getInfo/{newsId}")
    public AjaxResult getNewInfo(@PathVariable Long newsId){
        return AjaxResult.success(homeNewsService.getNewsInfo(newsId));
    }

}
