package com.ruoyi.web.controller.home;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.quartz.service.ISysJobService;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.domain.HomeAd;
import com.ruoyi.home.service.IHomeAdService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;



/**
 * 广告Controller
 * 
 * @author ruoyi chas
 * @date 2022-08-15
 */
@RestController
@RequestMapping("/home/ad")
public class HomeAdController extends BaseController {
    @Autowired
    private IHomeAdService homeAdService;
    @Autowired
    private ISysJobService iSysJobService;

    /**
     * 返回列表中所有符合条件的数据
     * @return AjaxResult
     */
    @GetMapping("/allAd")
    public AjaxResult getAll(){
        /**
         * 1.展示广告数量  先根据推荐的来 若无法推荐到固定个数 那么就用默认的来 按照 热度即可
         *
         * 2.广告持续时间
         *
         * 3.广告不能为空 所以说存在默认的广告
         *
         * 4.规定 置顶广告不受推荐影响 (拿资金的渠道之一)
         *
         */
        HomeAd homeAd = new HomeAd();
        List<HomeAd> list = homeAdService.selectHomeAdList(homeAd);
        return AjaxResult.success(list);
    }

    /**
     * 查询广告列表
     */
    @PreAuthorize("@ss.hasPermi('home:ad:list')")
    @GetMapping("/list")
    public TableDataInfo list(HomeAd homeAd)
    {
        startPage();
        List<HomeAd> list = homeAdService.selectHomeAdList(homeAd);
//
//        logger.info("flume看看我 我是数据啊");
//        logger.error("xxxxxxxx");
//        logger.warn("woaini");
//        ListenableFuture topic = kafkaTemplate.send("topic","xxx");
//        System.out.println(topic);

        //test111();
        //test222();
        //test333();
        //test444();
        //test555();
        return getDataTable(list);
    }

    /**
     * 导出广告列表
     */
    @PreAuthorize("@ss.hasPermi('home:ad:export')")
    @Log(title = "广告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomeAd homeAd)
    {
        List<HomeAd> list = homeAdService.selectHomeAdList(homeAd);
        ExcelUtil<HomeAd> util = new ExcelUtil<HomeAd>(HomeAd.class);
        util.exportExcel(response, list, "广告数据");
    }

    /**
     * 获取广告详细信息
     */
    @PreAuthorize("@ss.hasPermi('home:ad:query')")
    @GetMapping(value = "/{adId}")
    public AjaxResult getInfo(@PathVariable("adId") Long adId)
    {
        return AjaxResult.success(homeAdService.selectHomeAdByAdId(adId));
    }

    /**
     * 新增广告
     */
    @PreAuthorize("@ss.hasPermi('home:ad:add')")
    @Log(title = "广告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomeAd homeAd)
    {
        homeAd.setCreateTime(new Date());
        homeAd.setCreateBy(getUsername());
        return toAjax(homeAdService.insertHomeAd(homeAd));
    }

    /**
     * 修改广告
     */
    @PreAuthorize("@ss.hasPermi('home:ad:edit')")
    @Log(title = "广告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomeAd homeAd)
    {
        homeAd.setUpdateTime(new Date());
        homeAd.setUpdateBy(getUsername());
        return toAjax(homeAdService.updateHomeAd(homeAd));
    }

    /**
     * 删除广告
     */
    @PreAuthorize("@ss.hasPermi('home:ad:remove')")
    @Log(title = "广告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{adIds}")
    public AjaxResult remove(@PathVariable Long[] adIds)
    {
        return toAjax(homeAdService.deleteHomeAdByAdIds(adIds));
    }

    /**
     * 数据统计
     */
    @PreAuthorize("@ss.hasPermi('home:ad:statistic')")
    @GetMapping("/statistic")
    @Log(title = "广告",businessType = BusinessType.STATISTIC)
    public AjaxResult adStatistic(){
        AjaxResult ajaxResult = AjaxResult.success();
        int topNum = homeAdService.selectTopNum();
        ajaxResult.put("topNum",topNum);
        int delNum = homeAdService.selectDeleteAdNumByLogic();
        ajaxResult.put("delNum",delNum);
        SysJob sysJob = iSysJobService.selectJobById(100L);
        ajaxResult.put("cron",sysJob.getCronExpression());
        return ajaxResult;
    }
}
