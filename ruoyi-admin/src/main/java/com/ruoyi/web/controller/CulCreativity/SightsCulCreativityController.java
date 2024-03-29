package com.ruoyi.web.controller.CulCreativity;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.album.service.ICulAlbumService;
import com.ruoyi.culCreativity.domain.dto.CulAlbumShowDTO;
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
import com.ruoyi.culCreativity.domain.SightsCulCreativity;
import com.ruoyi.culCreativity.ISightsCulCreativityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文创Controller
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@RestController
@RequestMapping("/system/cul_creativity")

public class SightsCulCreativityController extends BaseController {
    @Autowired
    private ISightsCulCreativityService sightsCulCreativityService;

    @Autowired
    private ICulAlbumService albumService;

    /**
     * 查询文创列表
     */
    @PreAuthorize("@ss.hasPermi('system:cul_creativity:list')")
    @GetMapping("/list")
    public TableDataInfo list(SightsCulCreativity sightsCulCreativity) {
        startPage();
        List<SightsCulCreativity> list = sightsCulCreativityService.selectSightsCulCreativityList(sightsCulCreativity);
        return getDataTable(list);
    }

    /**
     * 导出文创列表
     */
    @PreAuthorize("@ss.hasPermi('system:cul_creativity:export')")
    @Log(title = "文创", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SightsCulCreativity sightsCulCreativity) {
        List<SightsCulCreativity> list = sightsCulCreativityService.selectSightsCulCreativityList(sightsCulCreativity);
        ExcelUtil<SightsCulCreativity> util = new ExcelUtil<SightsCulCreativity>(SightsCulCreativity.class);
        util.exportExcel(response, list, "文创数据");
    }

    /**
     * 获取文创详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cul_creativity:query')")
    @GetMapping(value = "/{culCreativityId}")
    public AjaxResult getInfo(@PathVariable("culCreativityId") Long culCreativityId) {
        return AjaxResult.success(sightsCulCreativityService.selectSightsCulCreativityByCulCreativityId(culCreativityId));
    }

    /**
     * 新增文创
     */
    @PreAuthorize("@ss.hasPermi('system:cul_creativity:add')")
    @Log(title = "文创", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SightsCulCreativity sightsCulCreativity) {
        sightsCulCreativity.setCreateBy(getUsername());
        sightsCulCreativity.setUpdateTime(new Date());
        return toAjax(sightsCulCreativityService.insertSightsCulCreativity(sightsCulCreativity));
    }

    /**
     * 修改文创
     */
    @PreAuthorize("@ss.hasPermi('system:cul_creativity:edit')")
    @Log(title = "文创", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SightsCulCreativity sightsCulCreativity) {
        sightsCulCreativity.setUpdateBy(getUsername());
        sightsCulCreativity.setUpdateTime(new Date());
        sightsCulCreativity.setJudgeBy(getUsername());
        sightsCulCreativity.setJudgeTime(new Date());
        return toAjax(sightsCulCreativityService.updateSightsCulCreativity(sightsCulCreativity));
    }

    /**
     * 删除文创
     */
    @PreAuthorize("@ss.hasPermi('system:cul_creativity:remove')")
    @Log(title = "文创", businessType = BusinessType.DELETE)
	@DeleteMapping("/{culCreativityIds}")
    public AjaxResult remove(@PathVariable Long[] culCreativityIds) {
        return toAjax(sightsCulCreativityService.deleteSightsCulCreativityByCulCreativityIds(culCreativityIds));
    }

    /**
     * 文创绑定景点
     */
    @PreAuthorize("@ss.hasPermi('system:cul_creativity:edit')")
    @Log(title = "绑定文创",businessType = BusinessType.GRANT)
    @GetMapping("/connect/{sightsId}/{culCreativityIds}")
    public AjaxResult connectSights(@PathVariable Long sightsId,@PathVariable Long culCreativityIds){
       SightsCulCreativity sightsCulCreativity = new SightsCulCreativity();
       sightsCulCreativity.setCulCreativityId(culCreativityIds);
       sightsCulCreativity.setSightsId(sightsId);
       sightsCulCreativityService.updateSightsCulCreativity(sightsCulCreativity);
        return AjaxResult.success("绑定成功");
    }

    @PreAuthorize("@ss.hasPermi('system:cul_creativity:edit')")
    @Log(title = "加入专辑",businessType = BusinessType.GRANT)
    @GetMapping("/addAlbum/{albumId}/{culCreativityId}")
    public AjaxResult addAlbum(@PathVariable Long albumId,@PathVariable Long culCreativityId){
        int i = albumService.AddCulToAlbum(albumId, culCreativityId,getUsername());
        if (i>0){
            return AjaxResult.success("移除专辑成功");
        }
        return AjaxResult.success("加入专辑成功");
    }

    @PreAuthorize("@ss.hasPermi('system:cul_creativity:list')")
    @GetMapping("/listCulAlbum/{albumId}")
    public TableDataInfo listCulAlbum(SightsCulCreativity sightsCulCreativity,@PathVariable Long albumId) {
        startPage();
        List<CulAlbumShowDTO> list = sightsCulCreativityService.selectAlbumCulLists(sightsCulCreativity,albumId);
        return getDataTable(list);
    }


}
