package com.ruoyi.web.controller.speech;

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
import com.ruoyi.system.domain.SysAudio;
import com.ruoyi.system.service.ISysAudioService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 语音映射Controller
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
@RestController
@RequestMapping("/system/audio")
public class SysAudioController extends BaseController
{
    @Autowired
    private ISysAudioService sysAudioService;

    /**
     * 查询语音映射列表
     */
    @PreAuthorize("@ss.hasPermi('system:audio:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysAudio sysAudio)
    {
        startPage();
        List<SysAudio> list = sysAudioService.selectSysAudioList(sysAudio);
        return getDataTable(list);
    }

    /**
     * 导出语音映射列表
     */
    @PreAuthorize("@ss.hasPermi('system:audio:export')")
    @Log(title = "语音映射", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysAudio sysAudio)
    {
        List<SysAudio> list = sysAudioService.selectSysAudioList(sysAudio);
        ExcelUtil<SysAudio> util = new ExcelUtil<SysAudio>(SysAudio.class);
        util.exportExcel(response, list, "语音映射数据");
    }

    /**
     * 获取语音映射详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:audio:query')")
    @GetMapping(value = "/{audioId}")
    public AjaxResult getInfo(@PathVariable("audioId") Long audioId)
    {
        return AjaxResult.success(sysAudioService.selectSysAudioByAudioId(audioId));
    }

    /**
     * 新增语音映射
     */
    @PreAuthorize("@ss.hasPermi('system:audio:add')")
    @Log(title = "语音映射", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysAudio sysAudio)
    {
        return toAjax(sysAudioService.insertSysAudio(sysAudio));
    }

    /**
     * 修改语音映射
     */
    @PreAuthorize("@ss.hasPermi('system:audio:edit')")
    @Log(title = "语音映射", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysAudio sysAudio)
    {
        return toAjax(sysAudioService.updateSysAudio(sysAudio));
    }

    /**
     * 删除语音映射
     */
    @PreAuthorize("@ss.hasPermi('system:audio:remove')")
    @Log(title = "语音映射", businessType = BusinessType.DELETE)
	@DeleteMapping("/{audioIds}")
    public AjaxResult remove(@PathVariable Long[] audioIds)
    {
        return toAjax(sysAudioService.deleteSysAudioByAudioIds(audioIds));
    }
}
