package com.ruoyi.web.controller.sights;

import java.math.BigDecimal;
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
import com.ruoyi.sights.domain.SightsAroundRestaurant;
import com.ruoyi.sights.service.ISightsAroundRestaurantService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 餐馆Controller
 * 
 * @author ruoyi chas
 * @date 2023-01-05
 */
@RestController
@RequestMapping("/system/restaurant")
public class SightsAroundRestaurantController extends BaseController
{
    @Autowired
    private ISightsAroundRestaurantService sightsAroundRestaurantService;

    /**
     * 查询餐馆列表
     */
    @PreAuthorize("@ss.hasPermi('system:restaurant:list')")
    @GetMapping("/list")
    public TableDataInfo list(SightsAroundRestaurant sightsAroundRestaurant)
    {
        startPage();
        List<SightsAroundRestaurant> list = sightsAroundRestaurantService.selectSightsAroundRestaurantList(sightsAroundRestaurant);
        return getDataTable(list);
    }

    /**
     * 导出餐馆列表
     */
    @PreAuthorize("@ss.hasPermi('system:restaurant:export')")
    @Log(title = "餐馆", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SightsAroundRestaurant sightsAroundRestaurant)
    {
        List<SightsAroundRestaurant> list = sightsAroundRestaurantService.selectSightsAroundRestaurantList(sightsAroundRestaurant);
        ExcelUtil<SightsAroundRestaurant> util = new ExcelUtil<SightsAroundRestaurant>(SightsAroundRestaurant.class);
        util.exportExcel(response, list, "餐馆数据");
    }

    /**
     * 获取餐馆详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:restaurant:query')")
    @GetMapping(value = "/{restaurantId}")
    public AjaxResult getInfo(@PathVariable("restaurantId") Long restaurantId)
    {
        return AjaxResult.success(sightsAroundRestaurantService.selectSightsAroundRestaurantByRestaurantId(restaurantId));
    }

    /**
     * 新增餐馆
     */
    @PreAuthorize("@ss.hasPermi('system:restaurant:add')")
    @Log(title = "餐馆", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SightsAroundRestaurant sightsAroundRestaurant)
    {
        sightsAroundRestaurant.setCreateBy(getUsername());
        sightsAroundRestaurant.setCreateTime(new Date());
        return toAjax(sightsAroundRestaurantService.insertSightsAroundRestaurant(sightsAroundRestaurant));
    }

    /**
     * 修改餐馆
     */
    @PreAuthorize("@ss.hasPermi('system:restaurant:edit')")
    @Log(title = "餐馆", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SightsAroundRestaurant sightsAroundRestaurant)
    {
        sightsAroundRestaurant.setUpdateBy(getUsername());
        sightsAroundRestaurant.setUpdateTime(new Date());
        return toAjax(sightsAroundRestaurantService.updateSightsAroundRestaurant(sightsAroundRestaurant));
    }

    /**
     * 删除餐馆
     */
    @PreAuthorize("@ss.hasPermi('system:restaurant:remove')")
    @Log(title = "餐馆", businessType = BusinessType.DELETE)
	@DeleteMapping("/{restaurantIds}")
    public AjaxResult remove(@PathVariable Long[] restaurantIds)
    {
        return toAjax(sightsAroundRestaurantService.deleteSightsAroundRestaurantByRestaurantIds(restaurantIds));
    }

    /**
     * 餐馆绑定景点
     */
    @PreAuthorize("@ss.hasPermi('system:restaurant:edit')")
    @Log(title = "绑定餐馆",businessType = BusinessType.GRANT)
    @GetMapping("/connect/{sightsId}/{restaurantId}")
    public AjaxResult connectSights(@PathVariable Long sightsId,@PathVariable Long restaurantId){
        SightsAroundRestaurant sightsAroundRestaurant = new SightsAroundRestaurant();
        sightsAroundRestaurant.setRestaurantId(restaurantId);
        sightsAroundRestaurant.setSightsId(sightsId);
        sightsAroundRestaurantService.updateSightsAroundRestaurant(sightsAroundRestaurant);
        return AjaxResult.success("绑定成功");
    }


}
