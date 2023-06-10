package com.ruoyi.web.controller.territor.permit;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sights.service.ISightsBaseService;
import com.ruoyi.territorInfo.service.ITerritorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chas
 * @introduction 地域选择
 * @date 2023-6-10
 */
@Api("地域选择接口")
@RestController
@RequestMapping("/areaChoice")
public class TerritorAnController {

    @Autowired
    private ITerritorService territorService;

    @Autowired
    private ISightsBaseService baseService;

    @ApiOperation("根据国家选择省份")
    @PreAuthorize("@ss.hasPermi('system:territor:edit')")
    @GetMapping("/country/{nation}")
    public AjaxResult getProvince(@PathVariable Integer nation){
        /**
         * 1 表示中国 0表示国外
         */
        switch (nation){
            case 1:  return AjaxResult.success(territorService.getProvinceInfo());
            case 0: return null;
            default: return AjaxResult.error("没有此国家，请联系管理员修正");
        }
    }


    @ApiOperation("根据省份获得市")
    @PreAuthorize("@ss.hasPermi('system:territor:edit')")
    @GetMapping("/city/{province}")
    public AjaxResult getProCity(@PathVariable String province){
        return AjaxResult.success(territorService.getCityInfo(province));
    }

    @ApiOperation("根据市获得区")
    @PreAuthorize("@ss.hasPermi('system:territor:edit')")
    @GetMapping("/area/{cityGeocode}")
    public AjaxResult getCityArea(@PathVariable String cityGeocode){
        return AjaxResult.success(territorService.getAreaInfo(cityGeocode));
    }

//    /**
//     * 选择区域id 可以获取更多数据 而不是只是把编码给sights 有利于扩展系统
//     */
//    @ApiOperation("给景点赋值地域id")
//    @PreAuthorize("@ss.hasPermi('system:territor:edit')")
//    @GetMapping("/sights/{sightsId}/{territorId}")
//    public AjaxResult setSightsTerritor(@PathVariable Long sightsId,@PathVariable String territorId){
//        SightsBase base = new SightsBase();
//        base.setSightsId(sightsId);
//        base.setTerritorId(territorId);
//        baseService.updateSightsBase(base);
//        return AjaxResult.success("设置区域成功");
//    }

    @ApiOperation("模糊查询省份")
    @PreAuthorize("@ss.hasPermi('system:territor:edit')")
    @GetMapping("/search/pro/{province}")
    public AjaxResult searchProvinceTerrior(@PathVariable String province){
        return AjaxResult.success(territorService.searchProvinceInfo(province));
    }

    @ApiOperation("模糊查询city")
    @PreAuthorize("@ss.hasPermi('system:territor:edit')")
    @GetMapping("/search/cc/{city}/{cityGeocode}")
    public AjaxResult searchCityTerritor(@PathVariable String city,@PathVariable String cityGeocode){
        return AjaxResult.success(territorService.searchCityInfo(city,cityGeocode));
    }

}
