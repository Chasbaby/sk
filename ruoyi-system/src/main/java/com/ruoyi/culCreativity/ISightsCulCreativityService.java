package com.ruoyi.culCreativity;

import java.util.List;

import com.ruoyi.culCreativity.domain.*;
import com.ruoyi.culCreativity.domain.dto.*;
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
     * 增加浏览(非登录)
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

    /**
     * 获取用户收藏
     * @param userId
     * @return
     */
   public List<CulHomeDTO> getAllCulCollect(Long userId);

   /** 获取用户点赞*/
   public List<CulHomeDTO> getAllCulLike(Long userId);
   /** 获取用户浏览*/
   public List<CulHomeDTO>  getAllCulView(Long userId);

    /**
     * 文创懒加载
     * @param userId
     * @return
     */
   public List<CulLazyDTO> getConcernsLazyCul(Long userId,Integer pageSize,Integer pageNum);

    /**
     * 文创当天通过率
     * @return
     */
   public Double getCulRate();

    /**
     * 获取文创可视化数据
     * @return
     */
   public CulStatisticPie getCulData();

    /**
     * 获取审核状态可视化
     * @return
     */
   public Long[] getJudgeData();

    /**
     * 发布数量
     */
    public Long[] getCuLDMY();

    /**
     * 获取综合排名文创
     * @return
     */
    public List<CulTopDTO> getTopCul();

    /**
     * 获取cul重编辑资料
     * @param culId
     * @return
     */
    public CulCreateDTO reEditCul(Long culId);

    /**
     * 定时任务 更新非登录浏览量
     */
    public void addAnonymousByRedis();

    /**
     * 获取文创稿件
     * @param userId
     * @return
     */
    public List<CulHomeDTO> getDraft(Long userId);
    /** 批量删除*/
    public int deleteBatchesCulByIds(Long[] culId);

    public  List<CulHomeDTO> getUserAllArticleByWays(Long user,Integer way);

    public CulVoiceDTO transReturnCul(Long id ,Integer position,Long audioId);

    public List<CulRandomLazyDTO> getFallLazyDTO();

}
