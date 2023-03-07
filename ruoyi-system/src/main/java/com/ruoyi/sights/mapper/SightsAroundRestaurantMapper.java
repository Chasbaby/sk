package com.ruoyi.sights.mapper;

import java.util.List;
import com.ruoyi.sights.domain.SightsAroundRestaurant;

/**
 * 餐馆Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-05
 */
public interface SightsAroundRestaurantMapper 
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
     * 删除餐馆
     * 
     * @param restaurantId 餐馆主键
     * @return 结果
     */
    public int deleteSightsAroundRestaurantByRestaurantId(Long restaurantId);

    /**
     * 批量删除餐馆
     * 
     * @param restaurantIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSightsAroundRestaurantByRestaurantIds(Long[] restaurantIds);

    /**
     * 获取 某景点周围餐馆
     * @param sightsId
     * @return
     */
    public List<SightsAroundRestaurant> selectAroundRestBySights(Long sightsId);
}
