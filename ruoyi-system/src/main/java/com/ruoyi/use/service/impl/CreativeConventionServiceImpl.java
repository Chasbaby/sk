package com.ruoyi.use.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.use.mapper.CreativeConventionMapper;
import com.ruoyi.use.domain.CreativeConvention;
import com.ruoyi.use.service.ICreativeConventionService;

/**
 * 创作公约Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-31
 */
@Service
public class CreativeConventionServiceImpl implements ICreativeConventionService 
{
    @Autowired
    private CreativeConventionMapper creativeConventionMapper;

    /**
     * 查询创作公约
     * 
     * @param conventionId 创作公约主键
     * @return 创作公约
     */
    @Override
    public CreativeConvention selectCreativeConventionByConventionId(Long conventionId)
    {
        return creativeConventionMapper.selectCreativeConventionByConventionId(conventionId);
    }

    /**
     * 查询创作公约列表
     * 
     * @param creativeConvention 创作公约
     * @return 创作公约
     */
    @Override
    public List<CreativeConvention> selectCreativeConventionList(CreativeConvention creativeConvention)
    {
        return creativeConventionMapper.selectCreativeConventionList(creativeConvention);
    }

    /**
     * 新增创作公约
     * 
     * @param creativeConvention 创作公约
     * @return 结果
     */
    @Override
    public int insertCreativeConvention(CreativeConvention creativeConvention)
    {
        creativeConvention.setCreateTime(DateUtils.getNowDate());
        return creativeConventionMapper.insertCreativeConvention(creativeConvention);
    }

    /**
     * 修改创作公约
     * 
     * @param creativeConvention 创作公约
     * @return 结果
     */
    @Override
    public int updateCreativeConvention(CreativeConvention creativeConvention)
    {
        creativeConvention.setUpdateTime(DateUtils.getNowDate());
        return creativeConventionMapper.updateCreativeConvention(creativeConvention);
    }

    /**
     * 批量删除创作公约
     * 
     * @param conventionIds 需要删除的创作公约主键
     * @return 结果
     */
    @Override
    public int deleteCreativeConventionByConventionIds(Long[] conventionIds)
    {
        return creativeConventionMapper.deleteCreativeConventionByConventionIds(conventionIds);
    }

    /**
     * 删除创作公约信息
     * 
     * @param conventionId 创作公约主键
     * @return 结果
     */
    @Override
    public int deleteCreativeConventionByConventionId(Long conventionId)
    {
        return creativeConventionMapper.deleteCreativeConventionByConventionId(conventionId);
    }
}
