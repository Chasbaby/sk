package com.ruoyi.web.controller.page.permit;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.page.domain.SysFooter;
import com.ruoyi.page.domain.SysTypeset;
import com.ruoyi.page.service.ISysFooterService;
import com.ruoyi.page.service.ISysTypesetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Api("页面排版")
@Anonymous
@RestController
@RequestMapping("/page")
public class PageAnController {

    @Autowired
    private ISysFooterService sysFooterService;

    @Autowired
    private ISysTypesetService sysTypesetService;

    @ApiOperation("获取区域数据")
    @GetMapping("/typeset/{typesetPosition}")
    public AjaxResult getTypeset(@PathVariable("typesetPosition") Integer typesetPosition){
        SysTypeset sysTypeset = new SysTypeset();
        sysTypeset.setTypesetPosition(typesetPosition);
        List<SysTypeset> sysTypesets = sysTypesetService.selectSysTypesetList(sysTypeset);
        return AjaxResult.success(sysTypesets);
    }

    @ApiOperation("获取footer数据")
    @GetMapping("/footer")
    public AjaxResult getFooter(){
        AjaxResult ajaxResult = new AjaxResult();
        List<SysFooter> sysFooters = sysFooterService.selectSysFooterList(new SysFooter());
        Stream<SysFooter> sysFooterStream = sysFooters.stream().filter(sysFooter -> sysFooter.getFooterType() == 1);
        Stream<SysFooter> footerStream = sysFooters.stream().filter(sysFooter -> sysFooter.getFooterType() == 0);
        ajaxResult.put("community",footerStream);
        ajaxResult.put("link",sysFooterStream);
        return ajaxResult;
    }




}
