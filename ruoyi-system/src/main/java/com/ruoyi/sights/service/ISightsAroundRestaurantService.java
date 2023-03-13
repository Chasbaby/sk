package com.ruoyi.sights.service;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.sights.SightsAround.SightsAroundRestaurant;

/**
 * 餐馆Service接口
 * 
 * @author ruoyi
 * @date 2023-01-05
 */
public interface ISightsAroundRestaurantService 
{
    /**
     * 查询餐馆
     * 
     * @param restaurantId 餐馆主键
     * @return 餐馆
     */
    public SightsAroundRestaurant selectSightsAroundRestaurantByRestaurantId(Long restaurantId);

    /**
     * 查询餐馆列表
     * 
     * @param sightsAroundRestaurant 餐馆
     * @return 餐馆集合
     */
    public List<SightsAroundRestaurant> selectSightsAroundRestaurantList(SightsAroundRestaurant sightsAroundRestaurant);

    /**
     * 新增餐馆
     * 
     * @param sightsAroundRestaurant 餐馆
     * @return 结果
     */
    public int insertSightsAroundRestaurant(SightsAroundRestaurant sightsAroundRestaurant);

    /**
     * 修改餐馆
     * 
     * @param sightsAroundRestaurant 餐馆
     * @return 结果
     */
    public int updateSightsAroundRestaurant(SightsAroundRestaurant sightsAroundRestaurant);

    /**
     * 批量删除餐馆
     * 
     * @param restaurantIds 需要删除的餐馆主键集合
     * @return 结果
     */
    public int deleteSightsAroundRestaurantByRestaurantIds(Long[] restaurantIds);

    /**
     * 删除餐馆信息
     * 
     * @param restaurantId 餐馆主键
     * @return 结果
     */
    public int deleteSightsAroundRestaurantByRestaurantId(Long restaurantId);

    /**
     * 匿名评分
     */
    public void scoreRestaurant(Long restaurantId, BigDecimal score);

    /**
     * 获取某景点相关餐馆
     */
    public List<SightsAroundRestaurant> selectAroundRestBySights(Long sightsId);
}
