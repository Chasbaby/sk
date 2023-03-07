package com.ruoyi.web.controller.use.permit;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.use.domain.CreativeConvention;
import com.ruoyi.use.service.ICreativeConventionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("创作公约")
@Anonymous
@RestController
@RequestMapping("/convention")
public class CreativeConventionAnController {

    @Autowired
    private ICreativeConventionService creativeConventionService;
    /**
     * 前台获取
     */
    @Anonymous
    @GetMapping("/getAllConvention")
    public AjaxResult getAll(){
        CreativeConvention creativeConvention = new CreativeConvention();
        List<CreativeConvention> list = creativeConventionService.selectCreativeConventionList(creativeConvention);
        return AjaxResult.success(list);
    }
}
