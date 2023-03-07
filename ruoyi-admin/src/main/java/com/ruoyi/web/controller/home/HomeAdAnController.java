package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.home.HomeAdException;
import com.ruoyi.home.domain.HomeAd;
import com.ruoyi.home.service.IHomeAdService;
import com.ruoyi.system.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 *  广告前端展示
 * @author Chas 2022-10
 */
@Anonymous
@Api("面向游客广告管理")
@RestController
@RequestMapping("/permitAll/Ad")
public class HomeAdAnController extends BaseController {
    @Autowired
    private IHomeAdService iHomeAdService;

    @Autowired
    private ISysConfigService iSysConfigService;



    /**
     * 这是对于投放广告的人 而言 拿广告的地方 一堆约束   放在 主页面
     * @return AjaxResult
     */
    @ApiOperation("获取推荐列表")
    @PostMapping("/allAd")
    public AjaxResult getAll(){
        // 先获取要收取的广告个数
        int num = Integer.parseInt(iSysConfigService.selectConfigByKey("home.adTop.number"));
        // 得到规定数目的 广告
        List<HomeAd> list1 = iHomeAdService.selectAds(num);
        //如果一个广告都没有
        if (list1==null){
            try {
                throw new HomeAdException();
            } catch (Exception e) {
                logger.warn("广告配置参数异常");
            }
        }
        return AjaxResult.success(list1);
    }

    /**
     * 获得 历史热门广告   以前 点击量比较多的
     */
    @ApiOperation("获取历史热门列表")
    @PostMapping("/hisHot")
    public AjaxResult getHistoryHotAd(){
        //只是展示作用的 无条件展示
        String num = iSysConfigService.selectConfigByKey("home.adOther.number");
        HomeAd requiredHomeAd = getRequiredHomeAd();
        List<HomeAd> list = iHomeAdService.selectAdByHistoryDate(requiredHomeAd,Integer.parseInt(num));
        return AjaxResult.success(list);
    }

    /**
     * 获得 近期热门广告  分页面     最近一段时间  点击量比较多的
     */
    @ApiOperation("获取近期热门列表")
    @PostMapping("/recent")
    public AjaxResult getRecentAd(){
        //只是展示作用的 无条件展示
        String num = iSysConfigService.selectConfigByKey("home.adOther.number");
        HomeAd requiredHomeAd = getRequiredHomeAd();
        List<HomeAd> list = iHomeAdService.selectAdByRecentDate(requiredHomeAd, Integer.parseInt(num));
        return AjaxResult.success(list);
    }

    /**
     * 获得 优质广告  分页面
     */
    @ApiOperation("获取优质广告列表")
    @PostMapping("/good")
    public AjaxResult getGoodAd(){
        HomeAd homeAd = new HomeAd();
        homeAd.setDelFlag("N");
        String num = iSysConfigService.selectConfigByKey("home.adOther.number");
        List<HomeAd> list = iHomeAdService.selectGoodAd(homeAd,Integer.parseInt(num));
        return AjaxResult.success(list);
    }

    /**
     *   点击 ++
     * @param adId
     */
    @ApiOperation("提高广告点击量")
    @GetMapping("/hit/{adId}")
    public AjaxResult improveHits(@PathVariable Long adId){
        iHomeAdService.updateAdHits(adId);
        return AjaxResult.success();
    }

    /**
     * 获取单个详细信息
     */
    @ApiOperation("获取单个广告信息")
    @GetMapping("/{adId}")
    public AjaxResult getInfo(@PathVariable Long adId){
        return AjaxResult.success(iHomeAdService.selectHomeAdByAdId(adId));
    }

    /**
     * 获取规定的数据
     * @return
     */
    public HomeAd getRequiredHomeAd(){
        //获得前台规定历史延迟时间   --->   获得当前时间 多少天之前的信息   得到的是少于小于这个时间的广告信息 且 hits是比较高的
        String day = iSysConfigService.selectConfigByKey("home.ad.day");
        Date date = new Date();
        HomeAd homeAd = new HomeAd();
        homeAd.setDelFlag("N");
        // 查询 未删除 而且 设置 距离现在的时间
        homeAd.setCreateTime(new Date(date.getTime()- (long) Integer.parseInt(day) *24*60*60*1000));
        return homeAd;
    }
}
