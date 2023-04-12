package com.ruoyi.web.controller.speech;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.DateUtils;
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
import com.ruoyi.system.domain.SysVoice;
import com.ruoyi.system.service.ISysVoiceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 动态播报Controller
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
@RestController
@RequestMapping("/system/voice")
public class SysVoiceController extends BaseController
{
    @Autowired
    private ISysVoiceService sysVoiceService;

    /**
     * 查询动态播报列表
     */
    @PreAuthorize("@ss.hasPermi('system:voice:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysVoice sysVoice)
    {
        startPage();
        List<SysVoice> list = sysVoiceService.selectSysVoiceList(sysVoice);
        return getDataTable(list);
    }

    /**
     * 导出动态播报列表
     */
    @PreAuthorize("@ss.hasPermi('system:voice:export')")
    @Log(title = "动态播报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysVoice sysVoice)
    {
        List<SysVoice> list = sysVoiceService.selectSysVoiceList(sysVoice);
        ExcelUtil<SysVoice> util = new ExcelUtil<SysVoice>(SysVoice.class);
        util.exportExcel(response, list, "动态播报数据");
    }

    /**
     * 获取动态播报详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:voice:query')")
    @GetMapping(value = "/{voiceId}")
    public AjaxResult getInfo(@PathVariable("voiceId") Long voiceId)
    {
        return AjaxResult.success(sysVoiceService.selectSysVoiceByVoiceId(voiceId));
    }

    /**
     * 新增动态播报
     */
    @PreAuthorize("@ss.hasPermi('system:voice:add')")
    @Log(title = "动态播报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysVoice sysVoice)
    {
        sysVoice.setCreateBy(getUsername());
        sysVoice.setCreateTime(DateUtils.getNowDate());
        return toAjax(sysVoiceService.insertSysVoice(sysVoice));
    }

    /**
     * 修改动态播报
     */
    @PreAuthorize("@ss.hasPermi('system:voice:edit')")
    @Log(title = "动态播报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysVoice sysVoice)
    {
        sysVoice.setUpdateBy(getUsername());
        sysVoice.setUpdateTime(DateUtils.getNowDate());
        return toAjax(sysVoiceService.updateSysVoice(sysVoice));
    }

    /**
     * 删除动态播报
     */
    @PreAuthorize("@ss.hasPermi('system:voice:remove')")
    @Log(title = "动态播报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{voiceIds}")
    public AjaxResult remove(@PathVariable Long[] voiceIds)
    {
        return toAjax(sysVoiceService.deleteSysVoiceByVoiceIds(voiceIds));
    }
}
