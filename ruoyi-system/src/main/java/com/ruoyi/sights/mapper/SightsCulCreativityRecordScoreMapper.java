package com.ruoyi.sights.mapper;


import com.ruoyi.sights.domain.SightsCulCreativityRecordScore;

/**
 * 景点评分
 * @author Chas
 * @date 2022-10
 */
public interface SightsCulCreativityRecordScoreMapper {

    /**
     * 插入 用户 景点 评分
     */
    public int insertUserSightsCulCreativityScore(SightsCulCreativityRecordScore sightsCulCreativityRecordScore);

    /**
     * 判断 用户 是否对景点评分
     */
    public int checkUserSightsCulCreativityScore(SightsCulCreativityRecordScore sightsCulCreativityRecordScore);

    /**
     * 修改 用户 对某景点的评分
     */
    public int updateUserSightsCulCreativityScore(SightsCulCreativityRecordScore sightsCulCreativityRecordScore);

    /**
     * 统计某景点的平均评分
     */
    public double selectAverageSightsCulCreativityScore(Long culCreativityId);

    /**
     *  获取某景点的评分总数
     */
    public int selectCountScoreNumByCulCreativityId(Long culCreativityId);



}
