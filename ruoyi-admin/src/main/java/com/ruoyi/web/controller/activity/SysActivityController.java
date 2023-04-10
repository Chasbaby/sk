package com.ruoyi.web.controller.activity;

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
import com.ruoyi.system.domain.SysActivity;
import com.ruoyi.system.service.ISysActivityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动Controller
 * 
 * @author ruoyi
 * @date 2023-04-10
 */
@RestController
@RequestMapping("/system/activity")
public class SysActivityController extends BaseController
{
    @Autowired
    private ISysActivityService sysActivityService;

    /**
     * 查询活动列表
     */
    @PreAuthorize("@ss.hasPermi('system:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysActivity sysActivity)
    {
        startPage();
        List<SysActivity> list = sysActivityService.selectSysActivityList(sysActivity);
        return getDataTable(list);
    }

    /**
     * 导出活动列表
     */
    @PreAuthorize("@ss.hasPermi('system:activity:export')")
    @Log(title = "活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysActivity sysActivity)
    {
        List<SysActivity> list = sysActivityService.selectSysActivityList(sysActivity);
        ExcelUtil<SysActivity> util = new ExcelUtil<SysActivity>(SysActivity.class);
        util.exportExcel(response, list, "活动数据");
    }

    /**
     * 获取活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:activity:query')")
    @GetMapping(value = "/{activityId}")
    public AjaxResult getInfo(@PathVariable("activityId") Long activityId)
    {
        return AjaxResult.success(sysActivityService.selectSysActivityByActivityId(activityId));
    }

    /**
     * 新增活动
     */
    @PreAuthorize("@ss.hasPermi('system:activity:add')")
    @Log(title = "活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysActivity sysActivity)
    {
        return toAjax(sysActivityService.insertSysActivity(sysActivity));
    }

    /**
     * 修改活动
     */
    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @Log(title = "活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysActivity sysActivity)
    {
        return toAjax(sysActivityService.updateSysActivity(sysActivity));
    }

    /**
     * 删除活动
     */
    @PreAuthorize("@ss.hasPermi('system:activity:remove')")
    @Log(title = "活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{activityIds}")
    public AjaxResult remove(@PathVariable Long[] activityIds)
    {
        return toAjax(sysActivityService.deleteSysActivityByActivityIds(activityIds));
    }
}
