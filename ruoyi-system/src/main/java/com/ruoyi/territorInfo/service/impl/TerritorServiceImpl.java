package com.ruoyi.territorInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.territorInfo.mapper.TerritorMapper;
import com.ruoyi.territorInfo.domain.Territor;
import com.ruoyi.territorInfo.service.ITerritorService;

/**
 * 国内数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@Service
public class TerritorServiceImpl implements ITerritorService {
    @Autowired
    private TerritorMapper territorMapper;

    /**
     * 查询国内数据
     * 
     * @param territorId 国内数据主键
     * @return 国内数据
     */
    @Override
    public Territor selectTerritorByTerritorId(Integer territorId)
    {
        return territorMapper.selectTerritorByTerritorId(territorId);
    }

    /**
     * 查询国内数据列表
     * 
     * @param territor 国内数据
     * @return 国内数据
     */
    @Override
    public List<Territor> selectTerritorList(Territor territor)
    {
        return territorMapper.selectTerritorList(territor);
    }

    /**
     * 新增国内数据
     * 
     * @param territor 国内数据
     * @return 结果
     */
    @Override
    public int insertTerritor(Territor territor)
    {
        return territorMapper.insertTerritor(territor);
    }

    /**
     * 修改国内数据
     * 
     * @param territor 国内数据
     * @return 结果
     */
    @Override
    public int updateTerritor(Territor territor)
    {
        return territorMapper.updateTerritor(territor);
    }

    /**
     * 批量删除国内数据
     * 
     * @param territorIds 需要删除的国内数据主键
     * @return 结果
     */
    @Override
    public int deleteTerritorByTerritorIds(Integer[] territorIds)
    {
        return territorMapper.deleteTerritorByTerritorIds(territorIds);
    }

    /**
     * 删除国内数据信息
     * 
     * @param territorId 国内数据主键
     * @return 结果
     */
    @Override
    public int deleteTerritorByTerritorId(Integer territorId)
    {
        return territorMapper.deleteTerritorByTerritorId(territorId);
    }
}
