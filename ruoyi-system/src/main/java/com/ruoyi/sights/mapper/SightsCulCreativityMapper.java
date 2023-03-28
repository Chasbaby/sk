package com.ruoyi.sights.mapper;

import java.util.List;

import com.ruoyi.article.domain.Article;
import com.ruoyi.culCreativity.domain.SightsCulCreativity;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

/**
 * 文创Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
public interface SightsCulCreativityMapper 
{
    /**
     * 查询文创
     * 
     * @param culCreativityId 文创主键
     * @return 文创
     */
    public SightsCulCreativity selectSightsCulCreativityByCulCreativityId(Long culCreativityId);

    /**
     * 查询文创列表
     * 
     * @param sightsCulCreativity 文创
     * @return 文创集合
     */
    public List<SightsCulCreativity> selectSightsCulCreativityList(SightsCulCreativity sightsCulCreativity);

    /**
     * 新增文创
     * 
     * @param sightsCulCreativity 文创
     * @return 结果
     */
    public int insertSightsCulCreativity(SightsCulCreativity sightsCulCreativity);

    /**
     * 修改文创
     * 
     * @param sightsCulCreativity 文创
     * @return 结果
     */
    public int updateSightsCulCreativity(SightsCulCreativity sightsCulCreativity);

    /**
     * 删除文创
     * 
     * @param culCreativityId 文创主键
     * @return 结果
     */
    public int deleteSightsCulCreativityByCulCreativityId(Long culCreativityId);

    /**
     * 批量删除文创
     * 
     * @param culCreativityIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSightsCulCreativityByCulCreativityIds(Long[] culCreativityIds);

    /**
     * 全局搜索景点专门用一个表示一下
     * @param sightsCulCreativity 文创的基本信息
     * @return 基本信息
     */
    public List<SightsCulCreativity> selectSightsInSearchList(SightsCulCreativity sightsCulCreativity);

    /**
     * 获取详细信息
     * @param culCreativityId
     * @return
     */
    public SightsCulCreativity selectDetailById(Long culCreativityId);

    /**
     * 获取个人cul
     * @param userId
     * @param way
     * @return
     */
    public List<SightsCulCreativity> selectCulInPersonPage(@Param("userId") Long userId, @Param("way") Integer way);

    public List<SightsCulCreativity> selectCulCollectByUserId(Long userId);

    /**
     * 懒加载
     * @param userId
     * @return
     */
    public List<SightsCulCreativity> selectLazyCul(Long userId);

    /**
     * 文创通过率
     */
    public Double selectCulRate();

}
