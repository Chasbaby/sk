package com.ruoyi.web.controller.recommend;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.recommend.service.SightsRecommendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chas
 * @introduction 推荐接口 最终大整合
 * @date 2023-4
 */
@Api("推荐算法对外展示接口")
@RestController
@RequestMapping("/recommend")
public class SightsRecommendAnController extends BaseController {

    @Autowired
    private SightsRecommendService recommendService;



    @ApiOperation("相似景点")
    @Anonymous()
    @GetMapping("/similar/attractions/{sightsId}")
    public AjaxResult SimilarAttractions(@PathVariable Long sightsId){
        return AjaxResult.success(recommendService.SimilarAttractions(sightsId));
    }

    @ApiOperation("实时推荐")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/online")
    public AjaxResult onlineRecommend(){
        return AjaxResult.success(recommendService.onlineAttractions(getUserId()));
    }

    @ApiOperation("普通推荐")
    @Anonymous
    @GetMapping("/common")
    public AjaxResult commonRecommend(){
        return AjaxResult.success(recommendService.getRandomAttractions());
    }


}
