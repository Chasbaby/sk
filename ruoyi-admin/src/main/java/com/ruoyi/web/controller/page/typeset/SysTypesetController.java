package com.ruoyi.web.controller.page.typeset;

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
import com.ruoyi.page.domain.SysTypeset;
import com.ruoyi.page.service.ISysTypesetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 动态排版Controller
 * 
 * @author ruoyi
 * @date 2023-02-04
 */
@RestController
@RequestMapping("/system/typeset")
public class SysTypesetController extends BaseController
{
    @Autowired
    private ISysTypesetService sysTypesetService;

    /**
     * 查询动态排版列表
     */
    @PreAuthorize("@ss.hasPermi('system:typeset:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysTypeset sysTypeset)
    {
        startPage();
        List<SysTypeset> list = sysTypesetService.selectSysTypesetList(sysTypeset);
        return getDataTable(list);
    }

    /**
     * 导出动态排版列表
     */
    @PreAuthorize("@ss.hasPermi('system:typeset:export')")
    @Log(title = "动态排版", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTypeset sysTypeset)
    {
        List<SysTypeset> list = sysTypesetService.selectSysTypesetList(sysTypeset);
        ExcelUtil<SysTypeset> util = new ExcelUtil<SysTypeset>(SysTypeset.class);
        util.exportExcel(response, list, "动态排版数据");
    }

    /**
     * 获取动态排版详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:typeset:query')")
    @GetMapping(value = "/{typesetId}")
    public AjaxResult getInfo(@PathVariable("typesetId") Long typesetId)
    {
        return AjaxResult.success(sysTypesetService.selectSysTypesetByTypesetId(typesetId));
    }

    /**
     * 新增动态排版
     */
    @PreAuthorize("@ss.hasPermi('system:typeset:add')")
    @Log(title = "动态排版", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTypeset sysTypeset)
    {
        return toAjax(sysTypesetService.insertSysTypeset(sysTypeset));
    }

    /**
     * 修改动态排版
     */
    @PreAuthorize("@ss.hasPermi('system:typeset:edit')")
    @Log(title = "动态排版", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTypeset sysTypeset)
    {
        return toAjax(sysTypesetService.updateSysTypeset(sysTypeset));
    }

    /**
     * 删除动态排版
     */
    @PreAuthorize("@ss.hasPermi('system:typeset:remove')")
    @Log(title = "动态排版", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typesetIds}")
    public AjaxResult remove(@PathVariable Long[] typesetIds)
    {
        return toAjax(sysTypesetService.deleteSysTypesetByTypesetIds(typesetIds));
    }
}
