package com.ruoyi.web.controller.sights.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.constant.KafkaTopicsConstant;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sights.service.ISightsHotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chas
 * @introduction 热度接口
 * @data 2023-3
 */
@Api("热度算法接口")
@RestController
@RequestMapping("/hotSights")
public class SightsHotAnController extends BaseController {

    @Autowired
    private ISightsHotService hotService;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


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
//        hotService.addView(sightsId,getUserId());
        kafkaTemplate.send(KafkaTopicsConstant.SIGHTSVIEW, sightsId +KafkaTopicsConstant.DELIMITER + getUserId());
        return null;
    }

    @ApiOperation("热度->增加取消点赞")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/like/{sightsId}")
    public AjaxResult addLike(@PathVariable Long sightsId){
        return null;
    }

    @ApiOperation("热度->点击量")
    @GetMapping("/hit/{sightsId}")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult addHit(@PathVariable Long sightsId){
        return null;
    }

    @ApiOperation("热度 ->增加取消收藏")
    @GetMapping("/collect/{sightsId}")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult addCollect(@PathVariable Long sightsId){
        return null;
    }

    @ApiOperation("热度->增加评分")
    @GetMapping("/score/{sightsId}/{score}")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult addScore(@PathVariable Long sightsId,@PathVariable Double score){
        return null;
    }


}
