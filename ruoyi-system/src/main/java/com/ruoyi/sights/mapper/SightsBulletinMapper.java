package com.ruoyi.sights.mapper;

import java.util.List;
import com.ruoyi.sights.SightsBulletin.SightsBulletin;

/**
 * 景点公告Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-04
 */
public interface SightsBulletinMapper 
{
    /**
     * 查询景点公告
     * 
     * @param bulletinId 景点公告主键
     * @return 景点公告
     */
    public SightsBulletin selectSightsBulletinByBulletinId(Long bulletinId);

    /**
     * 查询景点公告列表
     * 
     * @param sightsBulletin 景点公告
     * @return 景点公告集合
     */
    public List<SightsBulletin> selectSightsBulletinList(SightsBulletin sightsBulletin);

    /**
     * 新增景点公告
     * 
     * @param sightsBulletin 景点公告
     * @return 结果
     */
    public int insertSightsBulletin(SightsBulletin sightsBulletin);

    /**
     * 修改景点公告
     * 
     * @param sightsBulletin 景点公告
     * @return 结果
     */
    public int updateSightsBulletin(SightsBulletin sightsBulletin);

    /**
     * 删除景点公告
     * 
     * @param bulletinId 景点公告主键
     * @return 结果
     */
    public int deleteSightsBulletinByBulletinId(Long bulletinId);

    /**
     * 批量删除景点公告
     * 
     * @param bulletinIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSightsBulletinByBulletinIds(Long[] bulletinIds);
}
