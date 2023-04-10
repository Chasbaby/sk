package com.ruoyi.web.controller.sights.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.sights.domain.DTO.HotelDTO;
import com.ruoyi.sights.domain.DTO.RestDTO;
import com.ruoyi.sights.service.ISightsAroundHotelService;
import com.ruoyi.sights.service.ISightsAroundRestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author chas
 * @introduction
 * @date
 */
@Api("景点周边")
@Anonymous
@RestController
@RequestMapping("/around")
public class SightsAroundAnController extends BaseController {

    @Autowired
    private ISightsAroundHotelService sightsAroundHotelService;


    @Autowired
    private ISightsAroundRestaurantService sightsAroundRestaurantService;

    /**
     * 获取周边旅馆列表
     */
    @Anonymous
    @ApiOperation("获取周边旅馆列表")
    @GetMapping("/hotel/{sightsId}")
    public TableDataInfo getHotel(@PathVariable Long sightsId){
        startPage();
        List<HotelDTO> hotel = sightsAroundHotelService.getHotelBySightsId(sightsId);
        return getDataTable(hotel);
    }

    @Anonymous
    @ApiOperation("获取周边酒店列表")
    @GetMapping("/rest/{sightsId}")
    public TableDataInfo getRest(@PathVariable Long sightsId){
        startPage();
        List<RestDTO> dtos = sightsAroundRestaurantService.selectAroundRestBySights(sightsId);
        return getDataTable(dtos);
    }

    /**
     * 匿名评分
     */
    @RepeatSubmit(interval = 10000,message = "请勿重复评分")
    @Anonymous
    @GetMapping("/scoreHotel/{hotelId}/{score}")
    public AjaxResult scoreHotel(@PathVariable Long hotelId,@PathVariable BigDecimal score){
        sightsAroundHotelService.scoreHotel(hotelId,score);
        return AjaxResult.success("评分成功");
    }

    /**
     * 匿名评分
     */
    @Anonymous
    @RepeatSubmit(interval = 10000,message = "请勿重复评分")
    @GetMapping("/scoreRest/{restaurantId}/{score}")
    public AjaxResult scoreRestaurant(@PathVariable Long restaurantId,@PathVariable BigDecimal score){
        sightsAroundRestaurantService.scoreRestaurant(restaurantId,score);
        return AjaxResult.success("评分成功");
    }

}
