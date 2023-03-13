package com.ruoyi.sights.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sights.mapper.SightsCultureMapper;
import com.ruoyi.sights.SightsCulture.SightsCulture;
import com.ruoyi.sights.service.ISightsCultureService;

/**
 * 景点特色文化Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-09
 */
@Service
public class SightsCultureServiceImpl implements ISightsCultureService 
{
    @Autowired
    private SightsCultureMapper sightsCultureMapper;

    /**
     * 查询景点特色文化
     * 
     * @param cultureId 景点特色文化主键
     * @return 景点特色文化
     */
    @Override
    public SightsCulture selectSightsCultureByCultureId(Long cultureId)
    {
        return sightsCultureMapper.selectSightsCultureByCultureId(cultureId);
    }

    /**
     * 查询景点特色文化列表
     * 
     * @param sightsCulture 景点特色文化
     * @return 景点特色文化
     */
    @Override
    public List<SightsCulture> selectSightsCultureList(SightsCulture sightsCulture)
    {
        return sightsCultureMapper.selectSightsCultureList(sightsCulture);
    }

    /**
     * 新增景点特色文化
     * 
     * @param sightsCulture 景点特色文化
     * @return 结果
     */
    @Override
    public int insertSightsCulture(SightsCulture sightsCulture)
    {
        sightsCulture.setCreateTime(DateUtils.getNowDate());
        return sightsCultureMapper.insertSightsCulture(sightsCulture);
    }

    /**
     * 修改景点特色文化
     * 
     * @param sightsCulture 景点特色文化
     * @return 结果
     */
    @Override
    public int updateSightsCulture(SightsCulture sightsCulture)
    {
        sightsCulture.setUpdateTime(DateUtils.getNowDate());
        return sightsCultureMapper.updateSightsCulture(sightsCulture);
    }

    /**
     * 批量删除景点特色文化
     * 
     * @param cultureIds 需要删除的景点特色文化主键
     * @return 结果
     */
    @Override
    public int deleteSightsCultureByCultureIds(Long[] cultureIds)
    {
        return sightsCultureMapper.deleteSightsCultureByCultureIds(cultureIds);
    }

    /**
     * 删除景点特色文化信息
     * 
     * @param cultureId 景点特色文化主键
     * @return 结果
     */
    @Override
    public int deleteSightsCultureByCultureId(Long cultureId)
    {
        return sightsCultureMapper.deleteSightsCultureByCultureId(cultureId);
    }
}
