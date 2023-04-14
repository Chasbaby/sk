package com.ruoyi.quartz.task;

import com.ruoyi.article.service.impl.ArticleServiceImpl;
import com.ruoyi.culCreativity.ISightsCulCreativityService;
import com.ruoyi.home.service.IHomeAdService;
import com.ruoyi.home.service.IHomeNewsService;
import com.ruoyi.recommend.sightsRecommend.OfflineRecommend;
import com.ruoyi.sights.service.ISightsBaseService;
import com.ruoyi.sights.service.ISightsHotService;
import com.ruoyi.system.service.ISysActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;

import static com.ruoyi.sights.domain.SightsBase.hotTimeReduce;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    private static final Logger log = LoggerFactory.getLogger(RyTask.class);
    @Autowired
    private IHomeAdService iHomeAdService;
    public void AutoDeleteAd(){
        iHomeAdService.deleteHomeAdByQuartz();
    }
    @Autowired
    IHomeNewsService iHomeNewsService;
    @Autowired
    private ISightsBaseService iSightsBaseService;

    @Autowired
    private ISightsHotService hotService;
    @Autowired
    private ISightsCulCreativityService culCreativityService;

    @Autowired
    private ISysActivityService activityService;

    public void AutoDeleteNews(){iHomeNewsService.deleteHomeNewsByQuartz();}

    public void loadingHitLikeView(){
        iSightsBaseService.OpenHitViewLikeByQuartz();
    }
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }

    public  void redisToExcel(){
        hotService.redisToExcel();
    }

    public void hotReduce(){
        hotTimeReduce();
    }

    public void clearSightsHistoryRecord(){

    }
    public void addAnonymousByRedis(){
        culCreativityService.addAnonymousByRedis();
    }

    public void addActHot(){
        activityService.addHot();
    }
    public void reduceActHot(){
        activityService.reduceHot();
    }

    public void offlineRecommend(){
        log.info("离线计算开始喽");
        OfflineRecommend recommend = new OfflineRecommend();
    }


}
