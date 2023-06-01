package com.ruoyi.web.controller.tags;

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
import com.ruoyi.tags.domain.TagsChoice;
import com.ruoyi.tags.service.ITagsChoiceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 标签选择Controller
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@RestController
@RequestMapping("/system/tags")
public class TagsChoiceController extends BaseController
{
    @Autowired
    private ITagsChoiceService tagsChoiceService;

    /**
     * 查询标签选择列表
     */
    @PreAuthorize("@ss.hasPermi('system:tags:list')")
    @GetMapping("/list")
    public TableDataInfo list(TagsChoice tagsChoice)
    {
        startPage();
        List<TagsChoice> list = tagsChoiceService.selectTagsChoiceList(tagsChoice);
        return getDataTable(list);
    }

    /**
     * 导出标签选择列表
     */
    @PreAuthorize("@ss.hasPermi('system:tags:export')")
    @Log(title = "标签选择", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TagsChoice tagsChoice)
    {
        List<TagsChoice> list = tagsChoiceService.selectTagsChoiceList(tagsChoice);
        ExcelUtil<TagsChoice> util = new ExcelUtil<TagsChoice>(TagsChoice.class);
        util.exportExcel(response, list, "标签选择数据");
    }

    /**
     * 获取标签选择详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:tags:query')")
    @GetMapping(value = "/{tagsId}")
    public AjaxResult getInfo(@PathVariable("tagsId") Long tagsId)
    {
        return AjaxResult.success(tagsChoiceService.selectTagsChoiceByTagsId(tagsId));
    }

    /**
     * 新增标签选择
     */
    @PreAuthorize("@ss.hasPermi('system:tags:add')")
    @Log(title = "标签选择", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TagsChoice tagsChoice)
    {
        return toAjax(tagsChoiceService.insertTagsChoice(tagsChoice));
    }

    /**
     * 修改标签选择
     */
    @PreAuthorize("@ss.hasPermi('system:tags:edit')")
    @Log(title = "标签选择", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TagsChoice tagsChoice)
    {
        return toAjax(tagsChoiceService.updateTagsChoice(tagsChoice));
    }

    /**
     * 删除标签选择
     */
    @PreAuthorize("@ss.hasPermi('system:tags:remove')")
    @Log(title = "标签选择", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tagsIds}")
    public AjaxResult remove(@PathVariable Long[] tagsIds)
    {
        return toAjax(tagsChoiceService.deleteTagsChoiceByTagsIds(tagsIds));
    }
}
