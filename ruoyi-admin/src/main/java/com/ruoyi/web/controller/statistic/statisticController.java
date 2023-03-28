package com.ruoyi.web.controller.statistic;

import com.ruoyi.article.domain.dto.ArticleStatisticPie;
import com.ruoyi.article.service.IArticleService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.culCreativity.ISightsCulCreativityService;
import com.ruoyi.culCreativity.domain.dto.CulStatisticPie;
import com.ruoyi.framework.web.domain.Server;
import com.ruoyi.statistic.domain.LeftPie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chas
 * @introduction 数据可视化
 * @data 2023-3
 */
@Api("数据可视化")
@RestController
@RequestMapping("/statistic")
public class statisticController {

    @Autowired
    private ISightsCulCreativityService creativityService;

    @Autowired
    private IArticleService articleService;

    /**
     * todo 1. 今日文创通过率  2.今日文章通过率
     *      3. 剩余内存     4.CPU负载
     * @return
     */
    @ApiOperation("中下区域")
    @GetMapping("/water")
    public AjaxResult water() throws Exception {
        AjaxResult result = new AjaxResult();
        // cpu 和 mem
        Server server = new Server();
        server.copyTo();
        result.put("server",server);
        //文创通过率
        Double culRate = creativityService.getCulRate() * 100;
        result.put("culRate",culRate);
        // 文章通过率
        Double articleRate = articleService.getArticleRate() * 100;
        result.put("articleRate",articleRate);
        return result;
    }

    /**
     * todo 1.未审核  已通过  未通过  总数
     *      2. 文章 浏览 点赞 收藏  文创  浏览 点赞 收藏
     * @return
     */
    @ApiOperation("leftPie")
    @GetMapping("/leftPie")
    public AjaxResult LeftPie(){
        LeftPie leftPie = new LeftPie();
        // 文章三大数据
        ArticleStatisticPie articleData = articleService.getArticleData();
        leftPie.setArticlePie(articleData);
        // 文创三大数据
        CulStatisticPie culData = creativityService.getCulData();
        leftPie.setCulPie(culData);
        // 文创4大数据
        Long[] culJu = creativityService.getJudgeData();
        // 文章四大数据

        // 未审核

        // 已通过

        // 未通过

        // 总数

        return AjaxResult.success(leftPie);
    }

    /**
     * todo 今日累计发布  本月累计发布 今年累计发布
     *      今年总通过    今年累计发布  在线用户人数
     *
     * @return
     */
    @GetMapping("/yy")
    public AjaxResult mediumNum(){
        return null;
    }

    /**
     * todo  景点排行版   文创排行榜   文章排行版
     *
     * 3个接口
     */

    public AjaxResult sightsTables(){
        return null;
    }

    public AjaxResult culTables(){
        return null;
    }

    public AjaxResult articleTables(){
        return null;
    }

    /**
     * todo  省景点排行
     */
}
