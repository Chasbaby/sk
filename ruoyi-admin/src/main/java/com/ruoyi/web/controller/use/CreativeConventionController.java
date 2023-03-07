package com.ruoyi.web.controller.use;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
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
import com.ruoyi.use.domain.CreativeConvention;
import com.ruoyi.use.service.ICreativeConventionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 创作公约Controller
 * 
 * @author ruoyi
 * @date 2023-01-31
 */
@RestController
@RequestMapping("/system/convention")
public class CreativeConventionController extends BaseController
{
    @Autowired
    private ICreativeConventionService creativeConventionService;

    /**
     * 查询创作公约列表
     */
    @PreAuthorize("@ss.hasPermi('system:convention:list')")
    @GetMapping("/list")
    public TableDataInfo list(CreativeConvention creativeConvention)
    {
        startPage();
        List<CreativeConvention> list = creativeConventionService.selectCreativeConventionList(creativeConvention);
        return getDataTable(list);
    }

    /**
     * 导出创作公约列表
     */
    @PreAuthorize("@ss.hasPermi('system:convention:export')")
    @Log(title = "创作公约", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CreativeConvention creativeConvention)
    {
        List<CreativeConvention> list = creativeConventionService.selectCreativeConventionList(creativeConvention);
        ExcelUtil<CreativeConvention> util = new ExcelUtil<CreativeConvention>(CreativeConvention.class);
        util.exportExcel(response, list, "创作公约数据");
    }

    /**
     * 获取创作公约详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:convention:query')")
    @GetMapping(value = "/{conventionId}")
    public AjaxResult getInfo(@PathVariable("conventionId") Long conventionId)
    {
        return AjaxResult.success(creativeConventionService.selectCreativeConventionByConventionId(conventionId));
    }

    /**
     * 新增创作公约
     */
    @PreAuthorize("@ss.hasPermi('system:convention:add')")
    @Log(title = "创作公约", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CreativeConvention creativeConvention)
    {
        creativeConvention.setCreateBy(getUsername());
        creativeConvention.setCreateTime(DateUtils.getNowDate());
        return toAjax(creativeConventionService.insertCreativeConvention(creativeConvention));
    }

    /**
     * 修改创作公约
     */
    @PreAuthorize("@ss.hasPermi('system:convention:edit')")
    @Log(title = "创作公约", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CreativeConvention creativeConvention)
    {
        System.out.println(creativeConvention.getConventionContent());
        creativeConvention.setUpdateBy(getUsername());
        creativeConvention.setUpdateTime(DateUtils.getNowDate());
        return toAjax(creativeConventionService.updateCreativeConvention(creativeConvention));
    }

    /**
     * 删除创作公约
     */
    @PreAuthorize("@ss.hasPermi('system:convention:remove')")
    @Log(title = "创作公约", businessType = BusinessType.DELETE)
	@DeleteMapping("/{conventionIds}")
    public AjaxResult remove(@PathVariable Long[] conventionIds)
    {
        return toAjax(creativeConventionService.deleteCreativeConventionByConventionIds(conventionIds));
    }


}
