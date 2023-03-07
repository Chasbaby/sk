package com.ruoyi.web.controller.sights;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.sights.domain.SightsCulture;
import com.ruoyi.sights.service.ISightsCultureService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;


import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 景点特色文化Controller
 * 
 * @author ruoyi
 * @date 2022-08-09
 */
@RestController
@RequestMapping("/sights/culture")
public class SightsCultureController extends BaseController
{
    @Autowired
    private ISightsCultureService sightsCultureService;

    /**
     * 查询景点特色文化列表
     */
    @PreAuthorize("@ss.hasPermi('sights:culture:list')")
    @GetMapping("/list")
    public TableDataInfo list(SightsCulture sightsCulture)
    {
        startPage();
        List<SightsCulture> list = sightsCultureService.selectSightsCultureList(sightsCulture);
        return getDataTable(list);
    }

    /**
     * 导出景点特色文化列表
     */
    @PreAuthorize("@ss.hasPermi('sights:culture:export')")
    @Log(title = "景点特色文化", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SightsCulture sightsCulture)
    {
        List<SightsCulture> list = sightsCultureService.selectSightsCultureList(sightsCulture);
        ExcelUtil<SightsCulture> util = new ExcelUtil<SightsCulture>(SightsCulture.class);
        util.exportExcel(response, list, "景点特色文化数据");
    }

    /**
     * 获取景点特色文化详细信息
     */
    @PreAuthorize("@ss.hasPermi('sights:culture:query')")
    @GetMapping(value = "/{cultureId}")
    public AjaxResult getInfo(@PathVariable("cultureId") Long cultureId)
    {
        return AjaxResult.success(sightsCultureService.selectSightsCultureByCultureId(cultureId));
    }

    /**
     * 新增景点特色文化
     */
    @PreAuthorize("@ss.hasPermi('sights:culture:add')")
    @Log(title = "景点特色文化", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SightsCulture sightsCulture)
    {
        return toAjax(sightsCultureService.insertSightsCulture(sightsCulture));
    }

    /**
     * 修改景点特色文化
     */
    @PreAuthorize("@ss.hasPermi('sights:culture:edit')")
    @Log(title = "景点特色文化", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SightsCulture sightsCulture)
    {
        return toAjax(sightsCultureService.updateSightsCulture(sightsCulture));
    }

    /**
     * 删除景点特色文化
     */
    @PreAuthorize("@ss.hasPermi('sights:culture:remove')")
    @Log(title = "景点特色文化", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cultureIds}")
    public AjaxResult remove(@PathVariable Long[] cultureIds)
    {
        return toAjax(sightsCultureService.deleteSightsCultureByCultureIds(cultureIds));
    }
}
