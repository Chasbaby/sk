package com.ruoyi.sights.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sights.mapper.SightsAroundRestaurantMapper;
import com.ruoyi.sights.domain.SightsAroundRestaurant;
import com.ruoyi.sights.service.ISightsAroundRestaurantService;

/**
 * 餐馆Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-05
 */
@Service
public class SightsAroundRestaurantServiceImpl implements ISightsAroundRestaurantService 
{
    @Autowired
    private SightsAroundRestaurantMapper sightsAroundRestaurantMapper;

    /**
     * 查询餐馆
     * 
     * @param restaurantId 餐馆主键
     * @return 餐馆
     */
    @Override
    public SightsAroundRestaurant selectSightsAroundRestaurantByRestaurantId(Long restaurantId)
    {
        return sightsAroundRestaurantMapper.selectSightsAroundRestaurantByRestaurantId(restaurantId);
    }

    /**
     * 查询餐馆列表
     * 
     * @param sightsAroundRestaurant 餐馆
     * @return 餐馆
     */
    @Override
    public List<SightsAroundRestaurant> selectSightsAroundRestaurantList(SightsAroundRestaurant sightsAroundRestaurant)
    {
        return sightsAroundRestaurantMapper.selectSightsAroundRestaurantList(sightsAroundRestaurant);
    }

    /**
     * 新增餐馆
     * 
     * @param sightsAroundRestaurant 餐馆
     * @return 结果
     */
    @Override
    public int insertSightsAroundRestaurant(SightsAroundRestaurant sightsAroundRestaurant)
    {
        sightsAroundRestaurant.setCreateTime(DateUtils.getNowDate());
        return sightsAroundRestaurantMapper.insertSightsAroundRestaurant(sightsAroundRestaurant);
    }

    /**
     * 修改餐馆
     * 
     * @param sightsAroundRestaurant 餐馆
     * @return 结果
     */
    @Override
    public int updateSightsAroundRestaurant(SightsAroundRestaurant sightsAroundRestaurant)
    {
        sightsAroundRestaurant.setUpdateTime(DateUtils.getNowDate());
        return sightsAroundRestaurantMapper.updateSightsAroundRestaurant(sightsAroundRestaurant);
    }

    /**
     * 批量删除餐馆
     * 
     * @param restaurantIds 需要删除的餐馆主键
     * @return 结果
     */
    @Override
    public int deleteSightsAroundRestaurantByRestaurantIds(Long[] restaurantIds)
    {
        return sightsAroundRestaurantMapper.deleteSightsAroundRestaurantByRestaurantIds(restaurantIds);
    }

    /**
     * 删除餐馆信息
     * 
     * @param restaurantId 餐馆主键
     * @return 结果
     */
    @Override
    public int deleteSightsAroundRestaurantByRestaurantId(Long restaurantId)
    {
        return sightsAroundRestaurantMapper.deleteSightsAroundRestaurantByRestaurantId(restaurantId);
    }

    /**
     * 匿名评分
     * @param restaurantId
     * @param score
     */
    @Override
    public void scoreRestaurant(Long restaurantId, BigDecimal score) {
        SightsAroundRestaurant restaurant = selectSightsAroundRestaurantByRestaurantId(restaurantId);
        BigDecimal restaurantScore = restaurant.getRestaurantScore();
        Integer restaurantReviewers = restaurant.getRestaurantReviewers();
        BigDecimal newScore = restaurantScore.multiply(BigDecimal.valueOf(restaurantReviewers)).add(score).divide(BigDecimal.valueOf(restaurantReviewers + 1));
        SightsAroundRestaurant sightsAroundRestaurant = new SightsAroundRestaurant();
        sightsAroundRestaurant.setRestaurantId(restaurantId);
        sightsAroundRestaurant.setRestaurantReviewers(restaurantReviewers+1);
        sightsAroundRestaurant.setRestaurantScore(newScore);
        updateSightsAroundRestaurant(sightsAroundRestaurant);
    }

    @Override
    public List<SightsAroundRestaurant> selectAroundRestBySights(Long sightsId) {
        return sightsAroundRestaurantMapper.selectAroundRestBySights(sightsId);
    }
}
