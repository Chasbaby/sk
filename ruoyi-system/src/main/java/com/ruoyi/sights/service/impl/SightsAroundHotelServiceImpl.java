package com.ruoyi.sights.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.sights.domain.DTO.HotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sights.mapper.SightsAroundHotelMapper;
import com.ruoyi.sights.domain.SightsAroundHotel;
import com.ruoyi.sights.service.ISightsAroundHotelService;

/**
 * 酒店Service业务层处理
 * 
 * @author ruoyi chas
 * @date 2023-01-05
 */
@Service
public class SightsAroundHotelServiceImpl implements ISightsAroundHotelService 
{
    @Autowired
    private SightsAroundHotelMapper sightsAroundHotelMapper;

    /**
     * 查询酒店
     * 
     * @param hotelId 酒店主键
     * @return 酒店
     */
    @Override
    public SightsAroundHotel selectSightsAroundHotelByHotelId(Long hotelId)
    {
        return sightsAroundHotelMapper.selectSightsAroundHotelByHotelId(hotelId);
    }

    /**
     * 查询酒店列表
     * 
     * @param sightsAroundHotel 酒店
     * @return 酒店
     */
    @Override
    public List<SightsAroundHotel> selectSightsAroundHotelList(SightsAroundHotel sightsAroundHotel)
    {
        return sightsAroundHotelMapper.selectSightsAroundHotelList(sightsAroundHotel);
    }

    /**
     * 新增酒店
     * 
     * @param sightsAroundHotel 酒店
     * @return 结果
     */
    @Override
    public int insertSightsAroundHotel(SightsAroundHotel sightsAroundHotel)
    {
        sightsAroundHotel.setCreateTime(DateUtils.getNowDate());
        return sightsAroundHotelMapper.insertSightsAroundHotel(sightsAroundHotel);
    }

    /**
     * 修改酒店
     * 
     * @param sightsAroundHotel 酒店
     * @return 结果
     */
    @Override
    public int updateSightsAroundHotel(SightsAroundHotel sightsAroundHotel)
    {
        sightsAroundHotel.setUpdateTime(DateUtils.getNowDate());
        return sightsAroundHotelMapper.updateSightsAroundHotel(sightsAroundHotel);
    }

    /**
     * 批量删除酒店
     * 
     * @param hotelIds 需要删除的酒店主键
     * @return 结果
     */
    @Override
    public int deleteSightsAroundHotelByHotelIds(Long[] hotelIds)
    {
        return sightsAroundHotelMapper.deleteSightsAroundHotelByHotelIds(hotelIds);
    }

    /**
     * 删除酒店信息
     * 
     * @param hotelId 酒店主键
     * @return 结果
     */
    @Override
    public int deleteSightsAroundHotelByHotelId(Long hotelId)
    {
        return sightsAroundHotelMapper.deleteSightsAroundHotelByHotelId(hotelId);
    }

    @Override
    public List<SightsAroundHotel> selectAroundHotelBySightsId(Long sightsId) {
        return sightsAroundHotelMapper.selectAroundHotelsBySightsId(sightsId);
    }

    @Override
    public void scoreHotel(Long hotelId, BigDecimal score) {
        SightsAroundHotel hotel = selectSightsAroundHotelByHotelId(hotelId);
        BigDecimal hotelScore = hotel.getHotelScore();
        Integer reviewers = hotel.getHotelReviewers();
        // 计算均分
        BigDecimal result = hotelScore.multiply(BigDecimal.valueOf(reviewers)).add(score).divide(BigDecimal.valueOf(reviewers + 1));
        SightsAroundHotel sightsAroundHotel = new SightsAroundHotel();
        sightsAroundHotel.setSightsId(hotelId);
        sightsAroundHotel.setHotelScore(result);
        sightsAroundHotel.setHotelReviewers(reviewers);
        // 更新数据
        sightsAroundHotelMapper.updateSightsAroundHotel(sightsAroundHotel);
    }

    @Override
    public List<HotelDTO> getHotelBySightsId(Long sightsId) {
        List<SightsAroundHotel> hotels = sightsAroundHotelMapper.selectAroundHotelsBySightsId(sightsId);
        return hotels.stream().map(item->{
            HotelDTO dto = new HotelDTO();
            BeanUtils.copyBeanProp(dto,item);
            return dto;
        }).collect(Collectors.toList());
    }
}
