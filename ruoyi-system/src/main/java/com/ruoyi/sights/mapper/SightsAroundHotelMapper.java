package com.ruoyi.sights.mapper;

import java.util.List;
import com.ruoyi.sights.domain.SightsAroundHotel;

/**
 * 酒店Mapper接口
 * 
 * @author ruoyi chas
 * @date 2023-01-05
 */
public interface SightsAroundHotelMapper 
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
     * 删除酒店
     * 
     * @param hotelId 酒店主键
     * @return 结果
     */
    public int deleteSightsAroundHotelByHotelId(Long hotelId);

    /**
     * 批量删除酒店
     * 
     * @param hotelIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSightsAroundHotelByHotelIds(Long[] hotelIds);

    /**
     * 获取某景点周围酒店
     */

    public List<SightsAroundHotel> selectAroundHotelsBySightsId(Long sights);

    public List<SightsAroundHotel> getHotelBySightsId();
}
