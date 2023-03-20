package com.ruoyi.culCreativity;

import java.util.List;

import com.ruoyi.culCreativity.domain.*;
import com.ruoyi.culCreativity.domain.dto.CulDetail;
import com.ruoyi.culCreativity.domain.dto.CulHomeDTO;
import com.ruoyi.sights.domain.*;

/**
 * 文创Service接口
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
public interface ISightsCulCreativityService
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
     * 批量删除文创
     * 
     * @param culCreativityIds 需要删除的文创主键集合
     * @return 结果
     */
    public int deleteSightsCulCreativityByCulCreativityIds(Long[] culCreativityIds);

    /**
     * 删除文创信息
     * 
     * @param culCreativityId 文创主键
     * @return 结果
     */
    public int deleteSightsCulCreativityByCulCreativityId(Long culCreativityId);


    /**
     * 获取文创详细信息
     * @param culCreativityId id
     * @return 详细信息
     */
    public CulDetail getCulDetail(Long culCreativityId);


    /**
     * 点赞 ++
     * @param record
     * @return 0 取消 / 1 点赞
     */
   public int addCulLike(CulRecord record);

    /**
     * 登录用户
     * 增加浏览量
     * @param record
     * @return
     */
   public int addCulView(CulRecord record);

    /**
     *
     * @return
     */
   public int addCulViewAnonymous(Long culId);

    /**
     * 增加收藏量
     * @param record
     * @return
     */
   public int addCulCollect(CulRecord record);

    /**
     * 获取某用户所有Cul列表
     * 可以未通过审核
     * @param userId
     * @param way
     * @return
     */
   public  List<CulHomeDTO> getAllCulByUserId(Long userId,Integer way);


   public List<CulHomeDTO> getAllCulCollect(Long userId);

}
