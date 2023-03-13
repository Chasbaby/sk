package com.ruoyi.web.controller.sights;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
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
import com.ruoyi.sights.domain.SightsBulletin;
import com.ruoyi.sights.service.ISightsBulletinService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 景点公告Controller
 * 
 * @author ruoyi chas
 * @date 2023-01-04
 */
@RestController
@RequestMapping("/system/bulletin")
public class SightsBulletinController extends BaseController
{
    @Autowired
    private ISightsBulletinService sightsBulletinService;

    /**
     * 查询景点公告列表
     */
    @PreAuthorize("@ss.hasPermi('system:bulletin:list')")
    @GetMapping("/list")
    public TableDataInfo list(SightsBulletin sightsBulletin)
    {
        startPage();
        List<SightsBulletin> list = sightsBulletinService.selectSightsBulletinList(sightsBulletin);
        return getDataTable(list);
    }

    /**
     * 导出景点公告列表
     */
    @PreAuthorize("@ss.hasPermi('system:bulletin:export')")
    @Log(title = "景点公告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SightsBulletin sightsBulletin)
    {
        List<SightsBulletin> list = sightsBulletinService.selectSightsBulletinList(sightsBulletin);
        ExcelUtil<SightsBulletin> util = new ExcelUtil<SightsBulletin>(SightsBulletin.class);
        util.exportExcel(response, list, "景点公告数据");
    }

    /**
     * 获取景点公告详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:bulletin:query')")
    @GetMapping(value = "/{bulletinId}")
    public AjaxResult getInfo(@PathVariable("bulletinId") Long bulletinId)
    {
        return AjaxResult.success(sightsBulletinService.selectSightsBulletinByBulletinId(bulletinId));
    }

    /**
     * 新增景点公告
     */
    @PreAuthorize("@ss.hasPermi('system:bulletin:add')")
    @Log(title = "景点公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SightsBulletin sightsBulletin)
    {
        sightsBulletin.setCreateBy(getUsername());
        sightsBulletin.setCreateTime(new Date());
        return toAjax(sightsBulletinService.insertSightsBulletin(sightsBulletin));
    }

    /**
     * 修改景点公告
     */
    @PreAuthorize("@ss.hasPermi('system:bulletin:edit')")
    @Log(title = "景点公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SightsBulletin sightsBulletin)
    {
        sightsBulletin.setUpdateBy(getUsername());
        sightsBulletin.setUpdateTime(new Date());
        return toAjax(sightsBulletinService.updateSightsBulletin(sightsBulletin));
    }

    /**
     * 删除景点公告
     */
    @PreAuthorize("@ss.hasPermi('system:bulletin:remove')")
    @Log(title = "景点公告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bulletinIds}")
    public AjaxResult remove(@PathVariable Long[] bulletinIds)
    {
        return toAjax(sightsBulletinService.deleteSightsBulletinByBulletinIds(bulletinIds));
    }

    /**
     * 景点公告绑定景点
     */
    @PreAuthorize("@ss.hasPermi('system:bulletin:edit')")
    @Log(title = "绑定公告",businessType = BusinessType.GRANT)
    @GetMapping("/connect/{sightsId}/{bulletinId}")
    public AjaxResult connectSights(@PathVariable Long sightsId,@PathVariable Long bulletinId){
        // 一堆小判断
        // 唯一性 一定要有哦
        SightsBulletin sightsBulletin = new SightsBulletin();
        sightsBulletin.setBulletinId(bulletinId);
        sightsBulletin.setSightsId(sightsId);
        sightsBulletinService.updateSightsBulletin(sightsBulletin);
        return AjaxResult.success("绑定成功");
    }

    /**
     * 获取某景点的公告
     */
    @Anonymous
    @PostMapping("/getBulletin/{sightsId}")
    public AjaxResult getBulletinBySightsId(@PathVariable Long sightsId){
        return AjaxResult.success(sightsBulletinService.selectSightsBulletinByBulletinId(sightsId));
    }

}
