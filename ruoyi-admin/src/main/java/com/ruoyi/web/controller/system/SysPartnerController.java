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
import com.ruoyi.system.domain.SysPartner;
import com.ruoyi.system.service.ISysPartnerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 合作伙伴Controller
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
@RestController
@RequestMapping("/system/partner")
public class SysPartnerController extends BaseController
{
    @Autowired
    private ISysPartnerService sysPartnerService;

    /**
     * 查询合作伙伴列表
     */
    @PreAuthorize("@ss.hasPermi('system:partner:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysPartner sysPartner)
    {
        startPage();
        List<SysPartner> list = sysPartnerService.selectSysPartnerList(sysPartner);
        return getDataTable(list);
    }

    /**
     * 导出合作伙伴列表
     */
    @PreAuthorize("@ss.hasPermi('system:partner:export')")
    @Log(title = "合作伙伴", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPartner sysPartner)
    {
        List<SysPartner> list = sysPartnerService.selectSysPartnerList(sysPartner);
        ExcelUtil<SysPartner> util = new ExcelUtil<SysPartner>(SysPartner.class);
        util.exportExcel(response, list, "合作伙伴数据");
    }

    /**
     * 获取合作伙伴详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:partner:query')")
    @GetMapping(value = "/{partnerId}")
    public AjaxResult getInfo(@PathVariable("partnerId") Long partnerId)
    {

        return AjaxResult.success(sysPartnerService.selectSysPartnerByPartnerId(partnerId));
    }

    /**
     * 新增合作伙伴
     */
    @PreAuthorize("@ss.hasPermi('system:partner:add')")
    @Log(title = "合作伙伴", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPartner sysPartner)
    {

        sysPartner.setUpdateBy(getUsername());
        return toAjax(sysPartnerService.insertSysPartner(sysPartner));
    }

    /**
     * 修改合作伙伴
     */
    @PreAuthorize("@ss.hasPermi('system:partner:edit')")
    @Log(title = "合作伙伴", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysPartner sysPartner)
    {
        System.out.println(sysPartner.getPartnerLogo());
        sysPartner.setUpdateBy(getUsername());
        return toAjax(sysPartnerService.updateSysPartner(sysPartner));
    }

    /**
     * 删除合作伙伴
     */
    @PreAuthorize("@ss.hasPermi('system:partner:remove')")
    @Log(title = "合作伙伴", businessType = BusinessType.DELETE)
	@DeleteMapping("/{partnerIds}")
    public AjaxResult remove(@PathVariable Long[] partnerIds)
    {
        return toAjax(sysPartnerService.deleteSysPartnerByPartnerIds(partnerIds));
    }
}
