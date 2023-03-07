package com.ruoyi.web.controller.sights;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.RepeatSubmit;
import org.apache.el.stream.Optional;
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
import com.ruoyi.sights.domain.SightsAroundHotel;
import com.ruoyi.sights.service.ISightsAroundHotelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 酒店Controller
 * 
 * @author ruoyi chas
 * @date 2023-01-05
 */
@RestController
@RequestMapping("/system/hotel")
public class SightsAroundHotelController extends BaseController
{
    @Autowired
    private ISightsAroundHotelService sightsAroundHotelService;

    /**
     * 查询酒店列表
     */
    @PreAuthorize("@ss.hasPermi('system:hotel:list')")
    @GetMapping("/list")
    public TableDataInfo list(SightsAroundHotel sightsAroundHotel)
    {
        startPage();
        List<SightsAroundHotel> list = sightsAroundHotelService.selectSightsAroundHotelList(sightsAroundHotel);
        return getDataTable(list);
    }

    /**
     * 导出酒店列表
     */
    @PreAuthorize("@ss.hasPermi('system:hotel:export')")
    @Log(title = "酒店", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SightsAroundHotel sightsAroundHotel)
    {
        List<SightsAroundHotel> list = sightsAroundHotelService.selectSightsAroundHotelList(sightsAroundHotel);
        ExcelUtil<SightsAroundHotel> util = new ExcelUtil<SightsAroundHotel>(SightsAroundHotel.class);
        util.exportExcel(response, list, "酒店数据");
    }

    /**
     * 获取酒店详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:hotel:query')")
    @GetMapping(value = "/{hotelId}")
    public AjaxResult getInfo(@PathVariable("hotelId") Long hotelId)
    {
        return AjaxResult.success(sightsAroundHotelService.selectSightsAroundHotelByHotelId(hotelId));
    }

    /**
     * 新增酒店
     */
    @PreAuthorize("@ss.hasPermi('system:hotel:add')")
    @Log(title = "酒店", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SightsAroundHotel sightsAroundHotel)
    {
        sightsAroundHotel.setCreateBy(getUsername());
        sightsAroundHotel.setCreateTime(new Date());
        return toAjax(sightsAroundHotelService.insertSightsAroundHotel(sightsAroundHotel));
    }

    /**
     * 修改酒店
     */
    @PreAuthorize("@ss.hasPermi('system:hotel:edit')")
    @Log(title = "酒店", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SightsAroundHotel sightsAroundHotel)
    {
        sightsAroundHotel.setUpdateTime(new Date());
        sightsAroundHotel.setUpdateBy(getUsername());
        return toAjax(sightsAroundHotelService.updateSightsAroundHotel(sightsAroundHotel));
    }

    /**
     * 删除酒店
     */
    @PreAuthorize("@ss.hasPermi('system:hotel:remove')")
    @Log(title = "酒店", businessType = BusinessType.DELETE)
	@DeleteMapping("/{hotelIds}")
    public AjaxResult remove(@PathVariable Long[] hotelIds)
    {
        return toAjax(sightsAroundHotelService.deleteSightsAroundHotelByHotelIds(hotelIds));
    }
    /**
     * 餐馆绑定景点
     */
    @PreAuthorize("@ss.hasPermi('system:hotel:edit')")
    @Log(title = "绑定餐馆",businessType = BusinessType.GRANT)
    @GetMapping("/connect/{sightsId}/{hotelId}")
    public AjaxResult connectSights(@PathVariable Long sightsId,@PathVariable Long hotelId){
        SightsAroundHotel sightsAroundHotel = new SightsAroundHotel();
        sightsAroundHotel.setHotelId(hotelId);
        sightsAroundHotel.setSightsId(sightsId);
        sightsAroundHotelService.updateSightsAroundHotel(sightsAroundHotel);
        return AjaxResult.success("绑定成功");
    }

    /**
     * 获取景点周边餐馆
     * 展示的数量要固定呀
     */
    @Anonymous
    @PostMapping("/around/{sightsId}")
    public AjaxResult getAroundHotel(@PathVariable Long sightsId){
        return AjaxResult.success(sightsAroundHotelService.selectAroundHotelBySightsId(sightsId));
    }

    /**
     * 匿名评分
     */
    @RepeatSubmit(interval = 10000,message = "请勿重复评分")
    @Anonymous
    @GetMapping("/score")
    public AjaxResult scoreHotel(Long hotelId, BigDecimal score){
        sightsAroundHotelService.scoreHotel(hotelId,score);
        return AjaxResult.success("评分成功");
    }
}
