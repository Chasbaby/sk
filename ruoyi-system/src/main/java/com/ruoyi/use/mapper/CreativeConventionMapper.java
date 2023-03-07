package com.ruoyi.use.mapper;

import java.util.List;
import com.ruoyi.use.domain.CreativeConvention;

/**
 * 创作公约Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-31
 */
public interface CreativeConventionMapper 
{
    /**
     * 查询创作公约
     * 
     * @param conventionId 创作公约主键
     * @return 创作公约
     */
    public CreativeConvention selectCreativeConventionByConventionId(Long conventionId);

    /**
     * 查询创作公约列表
     * 
     * @param creativeConvention 创作公约
     * @return 创作公约集合
     */
    public List<CreativeConvention> selectCreativeConventionList(CreativeConvention creativeConvention);

    /**
     * 新增创作公约
     * 
     * @param creativeConvention 创作公约
     * @return 结果
     */
    public int insertCreativeConvention(CreativeConvention creativeConvention);

    /**
     * 修改创作公约
     * 
     * @param creativeConvention 创作公约
     * @return 结果
     */
    public int updateCreativeConvention(CreativeConvention creativeConvention);

    /**
     * 删除创作公约
     * 
     * @param conventionId 创作公约主键
     * @return 结果
     */
    public int deleteCreativeConventionByConventionId(Long conventionId);

    /**
     * 批量删除创作公约
     * 
     * @param conventionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCreativeConventionByConventionIds(Long[] conventionIds);
}
