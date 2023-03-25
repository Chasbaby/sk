package com.ruoyi.web.controller.sights.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sights.service.ISightsHotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AjaxResult getCurrentHotSights(){

        return null;
    }


}
