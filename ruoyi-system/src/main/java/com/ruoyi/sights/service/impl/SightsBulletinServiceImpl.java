package com.ruoyi.sights.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sights.mapper.SightsBulletinMapper;
import com.ruoyi.sights.domain.SightsBulletin;
import com.ruoyi.sights.service.ISightsBulletinService;

/**
 * 景点公告Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-04
 */
@Service
public class SightsBulletinServiceImpl implements ISightsBulletinService 
{
    @Autowired
    private SightsBulletinMapper sightsBulletinMapper;

    /**
     * 查询景点公告
     * 
     * @param bulletinId 景点公告主键
     * @return 景点公告
     */
    @Override
    public SightsBulletin selectSightsBulletinByBulletinId(Long bulletinId)
    {
        return sightsBulletinMapper.selectSightsBulletinByBulletinId(bulletinId);
    }

    /**
     * 查询景点公告列表
     * 
     * @param sightsBulletin 景点公告
     * @return 景点公告
     */
    @Override
    public List<SightsBulletin> selectSightsBulletinList(SightsBulletin sightsBulletin)
    {
        return sightsBulletinMapper.selectSightsBulletinList(sightsBulletin);
    }

    /**
     * 新增景点公告
     * 
     * @param sightsBulletin 景点公告
     * @return 结果
     */
    @Override
    public int insertSightsBulletin(SightsBulletin sightsBulletin)
    {
        sightsBulletin.setCreateTime(DateUtils.getNowDate());
        return sightsBulletinMapper.insertSightsBulletin(sightsBulletin);
    }

    /**
     * 修改景点公告
     * 
     * @param sightsBulletin 景点公告
     * @return 结果
     */
    @Override
    public int updateSightsBulletin(SightsBulletin sightsBulletin)
    {
        sightsBulletin.setUpdateTime(DateUtils.getNowDate());
        return sightsBulletinMapper.updateSightsBulletin(sightsBulletin);
    }

    /**
     * 批量删除景点公告
     * 
     * @param bulletinIds 需要删除的景点公告主键
     * @return 结果
     */
    @Override
    public int deleteSightsBulletinByBulletinIds(Long[] bulletinIds)
    {
        return sightsBulletinMapper.deleteSightsBulletinByBulletinIds(bulletinIds);
    }

    /**
     * 删除景点公告信息
     * 
     * @param bulletinId 景点公告主键
     * @return 结果
     */
    @Override
    public int deleteSightsBulletinByBulletinId(Long bulletinId)
    {
        return sightsBulletinMapper.deleteSightsBulletinByBulletinId(bulletinId);
    }
}
