package com.ruoyi.sights.service;

import java.util.List;

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
     * 下面是景点评分系列操作
     */

    /**
     * 插入 用户 景点 评分
     */
    public void insertUserSightsCulCreativityScore(SightsCulCreativityRecordScore sightsCulCreativityRecordScore);

    /**
     * 判断 用户 是否对景点评分
     */
    public boolean checkUserSightsCulCreativityScore(SightsCulCreativityRecordScore sightsCulCreativityRecordScore);

    /**
     * 修改 用户 对某景点的评分
     */
    public int updateUserSightsCulCreativityScore(SightsCulCreativityRecordScore sightsCulCreativityRecordScore);

    /**
     * 评分排行榜
     */
    public List<SightsBase> selectSightsCulCreativityTopViaScore(int num);

    /**
     * 统计某景点的平均评分
     */
    public double selectAverageSightsCulCreativityScore(Long culCreativityId);

    /**
     *  获取某景点的评分总数
     */
    public int selectCountScoreNumByCulCreativityId(Long culCreativityId);

    /**
     * 下面是景点点赞系列操作
     */

    /**
     * 点赞总管理
     */
    public void SightsCulCreativityManageViaLike(SightsRecordLike sightsRecordLike);

    /**
     * 点赞 ++
     */
    public int updateSightsCulCreativityViaLike(Long culCreativityId);

    /**
     * 点赞--
     */
    public int declineSightsCulCreativityViaLike(Long culCreativityId);

    /**
     * 点赞排行榜
     */
    public List<SightsCulCreativity> selectSightsCulCreativityTopViaLike(int num);

    /**
     * 插入 用户 景点 点赞信息
     */
    public int insertUserSightsCulCreativityLike(SightsCulCreativityRecordLike sightsCulCreativityRecordLike);

    /**
     * 取消 用户 景点 点赞信息
     */
    public int deleteUserSightsCulCreativityLike(SightsCulCreativityRecordLike sightsCulCreativityRecordLike);

    /**
     * 判断用户对某景点是否点赞
     */
    public int judgeUserSightsCulCreativityExistLike(SightsCulCreativityRecordLike sightsCulCreativityRecordLike);


    /**
     * 下面是景点点击系列操作
     */

    public int updateSightsCulCreativityViaHits(Long culCreativityId);

    /**
     * 点击排行榜
     */
    public List<SightsCulCreativity> selectSightsCulCreativityTopViaHit(int num);


    /**
     * 下面是景点浏览系列操作
     */

    public int updateSightsCulCreativityViaView(Long culCreativityId);

    /**
     * 浏览量排行榜
     * @return
     */
    public List<SightsCulCreativity> selectSightsCulCreativityTopViaView(int num);

    /**
     * 推荐部分
     */

    /**
     * 推荐 核心
     *
     * 获取某用户推荐列表
     */
    public List<SightsCulCreativity> getRecommendSightsCulCreativity(Long userId);

    /**
     * 获取历史热门景点推荐
     */
    public List<SightsCulCreativity> getHistoryHotSightsCulCreativity();

    /**
     * 获取最近热门景点
     */
    public List<SightsCulCreativity> getRecentHotSightsCulCreativity();

    /**
     * 获取优质景点列表
     */
    public List<SightsCulCreativity> getGoodSightsCulCreativity();

    /**
     * 收藏景点相关
     */

    /**
     * 收藏景点
     */
    public boolean addSightsCulCreativityCollection(SightsCulCreativityUserCollect sightsCulCreativityUserCollect);

    /**
     * 取消收藏
     */
    public boolean cancelSightsCulCreativityCollection(SightsCulCreativityUserCollect sightsCulCreativityUserCollect);

    /**
     * 收藏景点记录
     */
    public List<SightsCulCreativity> selectCollectSightsCulCreativityRecord(Long userId);
}
