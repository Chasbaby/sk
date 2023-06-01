package com.ruoyi.web.controller.territor;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.territorInfo.domain.Territor;
import com.ruoyi.territorInfo.service.ITerritorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 国内数据Controller
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@RestController
@RequestMapping("/system/territor")
public class TerritorController extends BaseController
{
    @Autowired
    private ITerritorService territorService;

    /**
     * 查询国内数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:territor:list')")
    @GetMapping("/list")
    public TableDataInfo list(Territor territor)
    {
        startPage();
        List<Territor> list = territorService.selectTerritorList(territor);
        return getDataTable(list);
    }

    /**
     * 导出国内数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:territor:export')")
    @Log(title = "国内数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Territor territor)
    {
        List<Territor> list = territorService.selectTerritorList(territor);
        ExcelUtil<Territor> util = new ExcelUtil<Territor>(Territor.class);
        util.exportExcel(response, list, "国内数据数据");
    }

    /**
     * 获取国内数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:territor:query')")
    @GetMapping(value = "/{territorId}")
    public AjaxResult getInfo(@PathVariable("territorId") Integer territorId)
    {
        return AjaxResult.success(territorService.selectTerritorByTerritorId(territorId));
    }

    /**
     * 新增国内数据
     */
    @PreAuthorize("@ss.hasPermi('system:territor:add')")
    @Log(title = "国内数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Territor territor)
    {
        return toAjax(territorService.insertTerritor(territor));
    }

    /**
     * 修改国内数据
     */
    @PreAuthorize("@ss.hasPermi('system:territor:edit')")
    @Log(title = "国内数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Territor territor)
    {
        return toAjax(territorService.updateTerritor(territor));
    }

    /**
     * 删除国内数据
     */
    @PreAuthorize("@ss.hasPermi('system:territor:remove')")
    @Log(title = "国内数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{territorIds}")
    public AjaxResult remove(@PathVariable Integer[] territorIds)
    {
        return toAjax(territorService.deleteTerritorByTerritorIds(territorIds));
    }
}
