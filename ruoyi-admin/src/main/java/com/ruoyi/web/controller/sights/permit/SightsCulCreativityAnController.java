package com.ruoyi.web.controller.sights.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.sights.domain.SightsCulCreativity;
import com.ruoyi.sights.service.ISightsCulCreativityService;
import com.ruoyi.system.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chas
 * @date 2022-11
 */
@Api("面向用户文创信息")
@Anonymous
@RestController
@RequestMapping("/culCreativity")
public class SightsCulCreativityAnController {

    @Autowired
    private ISightsCulCreativityService iSightsCulCreativityService ;
    @Autowired
    private ISysConfigService iSysConfigService;

    @ApiOperation("获取全部文创信息")
    @PostMapping("/getAll")
    public AjaxResult getAll(){
        String byKey = iSysConfigService.selectConfigByKey("culCreativity:all:num");
        List<SightsCulCreativity> sightsCulCreativities = iSightsCulCreativityService.selectSightsCulCreativityList(new SightsCulCreativity());
        List<SightsCulCreativity> sightsCulCreativities1 = sightsCulCreativities.subList(0, Convert.toInt(byKey));
        return AjaxResult.success(sightsCulCreativities1);
    }
}
