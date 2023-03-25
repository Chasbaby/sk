package com.ruoyi.web.controller.sights.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sights.service.ISightsHotService;
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
 * @introduction 热度接口
 * @data 2023-3
 */
@Api("热度算法接口")
@RestController
@RequestMapping("/hotSights")
public class SightsHotAnController extends BaseController {

    @Autowired
    private ISightsHotService hotService;


    @Anonymous
    @ApiOperation("获取目前热度排行版")
    @GetMapping("/getTop")
    public AjaxResult getCurrentHotSights(){
        return AjaxResult.success(hotService.currentHotSights());
    }


    @GetMapping("/view/{sightsId}")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult addView(@PathVariable Long sightsId){
        System.out.println(sightsId);
        return null;
    }


    @GetMapping("/hit/{sightsId}")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult addHit(@PathVariable Long sightsId){
        return null;
    }


    @GetMapping("/collect/{sightsId}")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult addCollect(@PathVariable Long sightsId){
        return null;
    }


}
