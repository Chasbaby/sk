package com.ruoyi.web.controller.home;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.home.HomeAdException;
import com.ruoyi.home.domain.HomeNews;
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
@Anonymous
@Api("面向游客新闻管理")
@RestController
@RequestMapping("/permitAll/News")
public class HomeNewsAnController extends BaseController {

    @Autowired
    private IHomeNewsService homeNewsService;

    @Autowired
    private ISysConfigService configService;


    /**
     *    对于新闻而言  这些数据是 随机的获取  最初的时候
     * @return AjaxResult
     */
    @ApiOperation("获取推荐信息")
    @PostMapping("/allNews")
    public AjaxResult getAll(){
        int num = Integer.parseInt(configService.selectConfigByKey("home.newsTop.number"));
        List<HomeNews> list = homeNewsService.selectNews(num);
        if (list==null){
            try {
                //先用这个代替
                throw new HomeAdException();

            }catch (Exception e){
                logger.warn("广告参数配置异常");
            }
        }
        return AjaxResult.success(list);
    }

    /**
     * 获得 历史热门新闻   以前 点击量 max的
     */
    @ApiOperation("获取历史热门列表")
    @PostMapping("/hisHot")
    public AjaxResult getHistoryHotNews(){
        String num = configService.selectConfigByKey("");
        HomeNews requiredHomeNews = getRequiredHomeNews();
        List<HomeNews> list = homeNewsService.selectNewsByHistoryDate(requiredHomeNews, Convert.toInt(num));
        return AjaxResult.success(list);
    }

    /**
     * 获得 近期热门新闻  分页面   最近一段时间 点击量 排在前面的
     */
    @ApiOperation("获取近期热门")
    @PostMapping("/recent")
    public AjaxResult getRecentNews(){
        String s = configService.selectConfigByKey("");
        HomeNews requiredHomeNews = getRequiredHomeNews();
        List<HomeNews> list = homeNewsService.selectNewsByRecentDate(requiredHomeNews, Convert.toInt(s));
        return AjaxResult.success(list);
    }

    /**
     * 获得 优质新闻  分页面    点击量 max      这些 时间约束 可以 我们自己定制
     */
    @ApiOperation("获取优质")
    @PostMapping("/good")
    public AjaxResult getGoodNews(){
        HomeNews homeNews = new HomeNews();
        homeNews.setDelFlag("N");
        String s = configService.selectConfigByKey("");
        List<HomeNews> list = homeNewsService.selectGoodNews(homeNews, Convert.toInt(s));
        return AjaxResult.success(list);
    }

    /**
     *   点击 ++
     * @param newsId
     */
    @ApiOperation("点击量++")
    @GetMapping("/hits/{newsId}")
    public AjaxResult improveHits(@PathVariable Long newsId){
        homeNewsService.updateNewsHits(newsId);
        return AjaxResult.success();
    }

    /**
     * 获取单个详细信息
     * @param newsId
     * @return
     */
    @ApiOperation("获取单个新闻信息")
    @GetMapping("/{newsId}")
    public AjaxResult getInfo(@PathVariable Long newsId){
        return AjaxResult.success(homeNewsService.selectHomeNewsByNewsId(newsId));
    }

    /** 获得规定的数据*/
    public HomeNews getRequiredHomeNews(){
        String day = configService.selectConfigByKey("home.news.day");
        Date date = new Date();
        HomeNews homeNews = new HomeNews();
        homeNews.setDelFlag("N");
        homeNews.setCreateTime(new Date(date.getTime()-(long)Integer.parseInt(day)*24*60*60*1000));
        return homeNews;
    }

}
