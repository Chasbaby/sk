package com.ruoyi.web.controller.sights.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.constant.KafkaTopicsConstant;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.recommend.sightsRecommend.ItemCFRecommend;
import com.ruoyi.recommend.sightsRecommend.OfflineRecommend;
import com.ruoyi.recommend.sightsRecommend.OnLineRecommend;
import com.ruoyi.sights.domain.DTO.SightsReturnDTO;
import com.ruoyi.sights.service.ISightsHotService;
import com.ruoyi.sights.service.ISightsRecordService;
import com.ruoyi.web.controller.common.CommonController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chas
 * @introduction 热度接口
 * @date 2023-3
 */
@Api("热度算法接口")
@RestController
@RequestMapping("/hotSights")
public class SightsHotAnController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(SightsHotAnController.class);


    @Autowired
    private ISightsHotService hotService;

    @Autowired
    private ISightsRecordService recordService;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private RedisCache redisCache;


    @Anonymous
    @ApiOperation("获取目前热度排行版")
    @GetMapping("/getTop")
    public AjaxResult getCurrentHotSights(){
        return AjaxResult.success(hotService.currentHotSights());
    }

    @ApiOperation("随机获取景点展示")
    @GetMapping("/randomSights")
    public AjaxResult getRandomSights(){
        return AjaxResult.success(hotService.getRandomSights());
    }

    @ApiOperation("热度->增加浏览")
    @GetMapping("/view/{sightsId}")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult addView(@PathVariable Long sightsId){
        kafkaTemplate.send(KafkaTopicsConstant.SIGHTSVIEW, sightsId +KafkaTopicsConstant.DELIMITER + getUserId());
        return AjaxResult.success();
    }

    @ApiOperation("热度->增加取消点赞")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/like/{sightsId}")
    public AjaxResult addLike(@PathVariable Long sightsId){
        int i = hotService.ifLike(sightsId, getUserId());

        if ( i == 0 ){
            kafkaTemplate.send(KafkaTopicsConstant.SIGHTSLIKE,sightsId+KafkaTopicsConstant.DELIMITER+getUserId());
            return AjaxResult.success("点赞成功了哦");
        }
        return AjaxResult.success("取消点赞,期待下一次哦");
    }

    @ApiOperation("热度->点击量")
    @GetMapping("/hit/{sightsId}")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult addHit(@PathVariable Long sightsId){
        kafkaTemplate.send(KafkaTopicsConstant.SIGHTSHIT,sightsId+KafkaTopicsConstant.DELIMITER+getUserId());
        return AjaxResult.success();
    }

    @ApiOperation("热度 ->增加取消收藏")
    @GetMapping("/collect/{sightsId}")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult addCollect(@PathVariable Long sightsId){
        int i = hotService.ifCollect(sightsId, getUserId());

        if (i == 0){
            kafkaTemplate.send(KafkaTopicsConstant.SIGHTSCOLLECT,sightsId+KafkaTopicsConstant.DELIMITER+getUserId());
            return AjaxResult.success("收藏成功");
        }
        return AjaxResult.success("取消收藏,期待下一次见面");
    }

    @ApiOperation("热度->增加评分")
    @GetMapping("/score/{sightsId}/{score}")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult addScore(@PathVariable Long sightsId,@PathVariable Double score){
        // 实时推荐 将数据传入redis
        String key = "userId:"+getUserId();
        Boolean hasKey = redisCache.hasKey(key);
        String value = sightsId + ":" + score;
        if (hasKey){
            redisCache.lock(key); // 加锁
            // 格式 : sightsId:score
            List<String> list = redisCache.getCacheList(key); //获取值
            String temp = null ;
            //遍历
            while (list.iterator().hasNext()) {
                String next = list.iterator().next();
                if (next.split(":")[0].equals(sightsId.toString())) { // 是不是一个景点呀
                    String s = next.replace(next.split(":")[1], score.toString());// 是的话修改个得分
                    list.iterator().remove();                               // 将数据移除
                    temp = s;                                               // 保存 修改值
                    break;  // 结束喽
                }
            }
            // 如果值被修改 也就是 不为null  则将该数据插入list
            if (StringUtils.isNotNull(temp)){
                list.add(temp);
            }
            redisCache.setCacheList(key,list);
        }else {
            List<String> list = new ArrayList<>();
            list.add(value);
            redisCache.setCacheList(key,list);
        }

        logger.info("SIGHTS:"+getUserId()+"|"+sightsId+"|"+score+"|"+ DateUtils.getNowDate().getTime());

        int i = hotService.ifScore(sightsId, score, getUserId());
        if (i == 0){
            kafkaTemplate.send(KafkaTopicsConstant.SIGHTSSCORE,sightsId+KafkaTopicsConstant.DELIMITER
                    +getUserId()+KafkaTopicsConstant.DELIMITER+score);
        }

        return AjaxResult.success("评分成功喽");
    }

    @PostConstruct
    public void startRecommend(){
        OnLineRecommend recommend = new OnLineRecommend();
        recommend.OnlineService();
    }

    @PostConstruct
    public void xxx(){
        ItemCFRecommend cfRecommend = new ItemCFRecommend();
        cfRecommend.ItemCFService();
    }


}
