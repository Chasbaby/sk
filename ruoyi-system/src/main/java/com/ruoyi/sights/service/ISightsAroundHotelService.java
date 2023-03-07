package com.ruoyi.sights.service;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.sights.domain.SightsAroundHotel;

/**
 * 酒店Service接口
 * 
 * @author ruoyi
 * @date 2023-01-05
 */
public interface ISightsAroundHotelService 
{
    /**
     * 查询酒店
     * 
     * @param hotelId 酒店主键
     * @return 酒店
     */
    public SightsAroundHotel selectSightsAroundHotelByHotelId(Long hotelId);

    /**
     * 查询酒店列表
     * 
     * @param sightsAroundHotel 酒店
     * @return 酒店集合
     */
    public List<SightsAroundHotel> selectSightsAroundHotelList(SightsAroundHotel sightsAroundHotel);

    /**
     * 新增酒店
     * 
     * @param sightsAroundHotel 酒店
     * @return 结果
     */
    public int insertSightsAroundHotel(SightsAroundHotel sightsAroundHotel);

    /**
     * 修改酒店
     * 
     * @param sightsAroundHotel 酒店
     * @return 结果
     */
    public int updateSightsAroundHotel(SightsAroundHotel sightsAroundHotel);

    /**
     * 批量删除酒店
     * 
     * @param hotelIds 需要删除的酒店主键集合
     * @return 结果
     */
    public int deleteSightsAroundHotelByHotelIds(Long[] hotelIds);

    /**
     * 删除酒店信息
     * 
     * @param hotelId 酒店主键
     * @return 结果
     */
    public int deleteSightsAroundHotelByHotelId(Long hotelId);

    /**
     * 获取某景点周围hotel
     */
    public List<SightsAroundHotel> selectAroundHotelBySightsId(Long sightsId);

    /**
     * 匿名评分
     */

    public void scoreHotel(Long hotelId, BigDecimal score);
}
