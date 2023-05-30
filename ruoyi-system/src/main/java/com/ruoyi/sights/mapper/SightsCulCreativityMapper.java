package com.ruoyi.sights.mapper;

import java.util.List;

import com.ruoyi.article.domain.Article;
import com.ruoyi.culCreativity.domain.SightsCulCreativity;
import com.ruoyi.culCreativity.domain.dto.CulLazyDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    /**
     * 获取用户收藏
     * @param userId
     * @return
     */
    public List<SightsCulCreativity> selectCulCollectByUserId(Long userId);

    public List<SightsCulCreativity> selectCulLikeByUserId(Long userId);

    public List<SightsCulCreativity> selectCulViewByUserId(Long userId);

    /**
     * 懒加载
     * @param userId
     * @return
     */
    public List<CulLazyDTO> selectLazyCul(Long userId);

    /**
     * 获取审核状态可视化 文创通过率
     * @return
     */
    public List<SightsCulCreativity> getCulData();

    @Select("select COUNT(0) from sights_cul_creativity where YEAR(create_time) = YEAR(curdate())")
    // 今年累计发布
    public Long getYearCulData();

    @Select("select COUNT(0) from sights_cul_creativity where DATE_FORMAT(create_time,'YYYY-MM')= DATE_FORMAT(curdate(),'YYYY-MM')")
    // 本月累计发布
    public Long getMonthCulData();

    @Select("select COUNT(0) from sights_cul_creativity where DATE_FORMAT(create_time,'YYYY-MM-DD') = curdate()")
    // 本日累计发布
    public Long getDayCulData();

    @Select("select COUNT(0) from sights_cul_creativity where YEAR(create_time) = YEAR(curdate()) and is_ok ='Y' ")
    // 年通过
    public Long getYearOKCulData();

    @Select("select COUNT(0) from sights_cul_creativity where YEAR(create_time) = YEAR(curdate()) and is_ok ='N'")
    // 年不通过
    public Long getYearNOCulData();

    // 大数据排行
    public List<SightsCulCreativity> getCulTop();
    // 批量更新浏览量
    public int updateAddCulView(List<Long> culCreativityId);

    /**
     * 获取用户草稿文创
     */
    public List<SightsCulCreativity> getCulDraft(Long userId);

    /**批量删除*/
    public int deleteBatchesCul(Long[] culCreativityId);

    /** 获取用户所有文创*/
    public List<SightsCulCreativity> getAllCUlByWays(@Param("userId") Long userId,@Param("ways") Integer ways);


}
