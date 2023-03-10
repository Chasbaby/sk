package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.SysSensitive;
import com.ruoyi.system.service.ISysSensitiveService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 敏感词Controller
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
@RestController
@RequestMapping("/system/sensitive")
public class SysSensitiveController extends BaseController
{
    @Autowired
    private ISysSensitiveService sysSensitiveService;

    /**
     * 查询敏感词列表
     */
    @PreAuthorize("@ss.hasPermi('system:sensitive:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysSensitive sysSensitive)
    {
        startPage();
        List<SysSensitive> list = sysSensitiveService.selectSysSensitiveList(sysSensitive);
        return getDataTable(list);
    }

    /**
     * 导出敏感词列表
     */
    @PreAuthorize("@ss.hasPermi('system:sensitive:export')")
    @Log(title = "敏感词", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSensitive sysSensitive)
    {
        List<SysSensitive> list = sysSensitiveService.selectSysSensitiveList(sysSensitive);
        ExcelUtil<SysSensitive> util = new ExcelUtil<SysSensitive>(SysSensitive.class);
        util.exportExcel(response, list, "敏感词数据");
    }

    /**
     * 获取敏感词详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sensitive:query')")
    @GetMapping(value = "/{sensitiveId}")
    public AjaxResult getInfo(@PathVariable("sensitiveId") Long sensitiveId)
    {
        return AjaxResult.success(sysSensitiveService.selectSysSensitiveBySensitiveId(sensitiveId));
    }

    /**
     * 新增敏感词
     */
    @PreAuthorize("@ss.hasPermi('system:sensitive:add')")
    @Log(title = "敏感词", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSensitive sysSensitive)
    {
        return toAjax(sysSensitiveService.insertSysSensitive(sysSensitive));
    }

    /**
     * 修改敏感词
     */
    @PreAuthorize("@ss.hasPermi('system:sensitive:edit')")
    @Log(title = "敏感词", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSensitive sysSensitive)
    {
        return toAjax(sysSensitiveService.updateSysSensitive(sysSensitive));
    }

    /**
     * 删除敏感词
     */
    @PreAuthorize("@ss.hasPermi('system:sensitive:remove')")
    @Log(title = "敏感词", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sensitiveIds}")
    public AjaxResult remove(@PathVariable Long[] sensitiveIds)
    {
        return toAjax(sysSensitiveService.deleteSysSensitiveBySensitiveIds(sensitiveIds));
    }
}
