package com.ruoyi.sights.service;

import java.util.List;
import com.ruoyi.sights.domain.SightsCulture;

/**
 * 景点特色文化Service接口
 * 
 * @author ruoyi
 * @date 2022-08-09
 */
public interface ISightsCultureService 
{
    /**
     * 查询景点特色文化
     * 
     * @param cultureId 景点特色文化主键
     * @return 景点特色文化
     */
    public SightsCulture selectSightsCultureByCultureId(Long cultureId);

    /**
     * 查询景点特色文化列表
     * 
     * @param sightsCulture 景点特色文化
     * @return 景点特色文化集合
     */
    public List<SightsCulture> selectSightsCultureList(SightsCulture sightsCulture);

    /**
     * 新增景点特色文化
     * 
     * @param sightsCulture 景点特色文化
     * @return 结果
     */
    public int insertSightsCulture(SightsCulture sightsCulture);

    /**
     * 修改景点特色文化
     * 
     * @param sightsCulture 景点特色文化
     * @return 结果
     */
    public int updateSightsCulture(SightsCulture sightsCulture);

    /**
     * 批量删除景点特色文化
     * 
     * @param cultureIds 需要删除的景点特色文化主键集合
     * @return 结果
     */
    public int deleteSightsCultureByCultureIds(Long[] cultureIds);

    /**
     * 删除景点特色文化信息
     * 
     * @param cultureId 景点特色文化主键
     * @return 结果
     */
    public int deleteSightsCultureByCultureId(Long cultureId);
}
