package com.ruoyi.web.controller.album;

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
import com.ruoyi.album.domain.CulAlbum;
import com.ruoyi.album.service.ICulAlbumService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文创专栏Controller
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@RestController
@RequestMapping("/system/album")
public class CulAlbumController extends BaseController
{
    @Autowired
    private ICulAlbumService culAlbumService;

    /**
     * 查询文创专栏列表
     */
    @PreAuthorize("@ss.hasPermi('system:album:list')")
    @GetMapping("/list")
    public TableDataInfo list(CulAlbum culAlbum)
    {
        startPage();
        List<CulAlbum> list = culAlbumService.selectCulAlbumList(culAlbum);
        return getDataTable(list);
    }

    /**
     * 导出文创专栏列表
     */
    @PreAuthorize("@ss.hasPermi('system:album:export')")
    @Log(title = "文创专栏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CulAlbum culAlbum)
    {
        List<CulAlbum> list = culAlbumService.selectCulAlbumList(culAlbum);
        ExcelUtil<CulAlbum> util = new ExcelUtil<CulAlbum>(CulAlbum.class);
        util.exportExcel(response, list, "文创专栏数据");
    }

    /**
     * 获取文创专栏详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:album:query')")
    @GetMapping(value = "/{albumId}")
    public AjaxResult getInfo(@PathVariable("albumId") Long albumId)
    {
        return AjaxResult.success(culAlbumService.selectCulAlbumByAlbumId(albumId));
    }

    /**
     * 新增文创专栏
     */
    @PreAuthorize("@ss.hasPermi('system:album:add')")
    @Log(title = "文创专栏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CulAlbum culAlbum)
    {
        return toAjax(culAlbumService.insertCulAlbum(culAlbum));
    }

    /**
     * 修改文创专栏
     */
    @PreAuthorize("@ss.hasPermi('system:album:edit')")
    @Log(title = "文创专栏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CulAlbum culAlbum)
    {
        return toAjax(culAlbumService.updateCulAlbum(culAlbum));
    }

    /**
     * 删除文创专栏
     */
    @PreAuthorize("@ss.hasPermi('system:album:remove')")
    @Log(title = "文创专栏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{albumIds}")
    public AjaxResult remove(@PathVariable Long[] albumIds)
    {
        return toAjax(culAlbumService.deleteCulAlbumByAlbumIds(albumIds));
    }
}
