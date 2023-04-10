package com.ruoyi.web.controller.activity.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.system.domain.SysActivity;
import com.ruoyi.system.domain.domainVo.ActivityListDTO;
import com.ruoyi.system.domain.domainVo.ActivityPersonDTO;
import com.ruoyi.system.service.ISysActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chas
 * @introduction 活动接口
 * @date 2023-4
 */
@Api("活动展示")
@RestController
@RequestMapping("/activity")
public class ActivityAnController extends BaseController {

    @Autowired
    private ISysActivityService activityService;

    @Autowired
    private RedisCache redisCache;

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
        List<ActivityListDTO> list = activityService.getActList();
        return AjaxResult.success(list);
    }

    @ApiOperation("获取活动列表")
    @GetMapping("/list")
    @Anonymous
    public TableDataInfo getListAct(){
        startPage();
        List<SysActivity> list = activityService.getAllList();
        return getDataTable(list);
    }

    @ApiOperation("获取活动详细信息")
    @GetMapping("/detail/{activityId}")
    @Anonymous
    public AjaxResult getActDetail(@PathVariable Long activityId){
        // 存缓存
        if (redisCache.hasKey(Constants.ACTIVITY_RECORD)){
            redisCache.lock(Constants.ACTIVITY_RECORD);
            List<Long> cacheList = redisCache.getCacheList(Constants.ACTIVITY_RECORD);
            redisCache.deleteObject(Constants.ACTIVITY_RECORD);
            cacheList.add(activityId);
            redisCache.setCacheList(Constants.ACTIVITY_RECORD,cacheList);
        }else {
            List<Long> list = new ArrayList<>();
            list.add(activityId);
            redisCache.setCacheList(Constants.ACTIVITY_RECORD,list);
        }

        SysActivity activity = activityService.selectSysActivityByActivityId(activityId);
        return AjaxResult.success(activity);
    }






}
