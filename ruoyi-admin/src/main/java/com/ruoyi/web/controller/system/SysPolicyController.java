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
import com.ruoyi.system.domain.SysPolicy;
import com.ruoyi.system.service.ISysPolicyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 政策Controller
 * 
 * @author ruoyi
 * @date 2022-08-09
 */
@RestController
@RequestMapping("/system/policy")
public class SysPolicyController extends BaseController
{
    @Autowired
    private ISysPolicyService sysPolicyService;

    /**
     * 查询政策列表
     */
    @PreAuthorize("@ss.hasPermi('system:policy:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysPolicy sysPolicy)
    {
        startPage();
        List<SysPolicy> list = sysPolicyService.selectSysPolicyList(sysPolicy);
        return getDataTable(list);
    }

    /**
     * 导出政策列表
     */
    @PreAuthorize("@ss.hasPermi('system:policy:export')")
    @Log(title = "政策", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPolicy sysPolicy)
    {
        List<SysPolicy> list = sysPolicyService.selectSysPolicyList(sysPolicy);
        ExcelUtil<SysPolicy> util = new ExcelUtil<SysPolicy>(SysPolicy.class);
        util.exportExcel(response, list, "政策数据");
    }

    /**
     * 获取政策详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:policy:query')")
    @GetMapping(value = "/{policyId}")
    public AjaxResult getInfo(@PathVariable("policyId") Long policyId)
    {
        return AjaxResult.success(sysPolicyService.selectSysPolicyByPolicyId(policyId));
    }

    /**
     * 新增政策
     */
    @PreAuthorize("@ss.hasPermi('system:policy:add')")
    @Log(title = "政策", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPolicy sysPolicy)
    {
        return toAjax(sysPolicyService.insertSysPolicy(sysPolicy));
    }

    /**
     * 修改政策
     */
    @PreAuthorize("@ss.hasPermi('system:policy:edit')")
    @Log(title = "政策", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysPolicy sysPolicy)
    {
        return toAjax(sysPolicyService.updateSysPolicy(sysPolicy));
    }

    /**
     * 删除政策
     */
    @PreAuthorize("@ss.hasPermi('system:policy:remove')")
    @Log(title = "政策", businessType = BusinessType.DELETE)
	@DeleteMapping("/{policyIds}")
    public AjaxResult remove(@PathVariable Long[] policyIds)
    {
        return toAjax(sysPolicyService.deleteSysPolicyByPolicyIds(policyIds));
    }
}
