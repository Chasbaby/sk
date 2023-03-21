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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@Api("页面动态排版")
@Anonymous
@RestController
@RequestMapping("/page")
public class PageAnController {

    @Autowired
    private ISysFooterService sysFooterService;

    @Autowired
    private ISysTypesetService sysTypesetService;

    /**
     * 完美 的 设计   通用
     * @param typesetPage
     * @param typesetPosition
     * @return
     */
    @ApiOperation("获取区域数据")
    @GetMapping("/typeset/{typesetPage}/{typesetPosition}")
    public AjaxResult getTypeset(@PathVariable("typesetPage") Integer typesetPage,
                                 @PathVariable("typesetPosition") Integer typesetPosition){
        SysTypeset sysTypeset = new SysTypeset();
        sysTypeset.setTypesetPosition(typesetPosition);
        sysTypeset.setTypesetPage(typesetPage);
        List<SysTypeset> sysTypesets = sysTypesetService.selectSysTypesetList(sysTypeset);
        return AjaxResult.success(sysTypesets);
    }

    /**
     * 景点三者
     * @return
     */
    @ApiOperation("三景点排版")
    @GetMapping("/typeset/Sights")
    public AjaxResult getTypesetSights3(){
        List<SysTypeset> sights3 = sysTypesetService.getTypesetSights3();
        return AjaxResult.success(sights3);
    }

    /**
     * 文创三者
     * @return
     */
    @ApiOperation("三文创排版")
    @GetMapping("/typeset/cul")
    public AjaxResult getTypesetCuls(){
        List<SysTypeset> typesetCul3 = sysTypesetService.getTypesetCul3();
        return AjaxResult.success(typesetCul3);
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
