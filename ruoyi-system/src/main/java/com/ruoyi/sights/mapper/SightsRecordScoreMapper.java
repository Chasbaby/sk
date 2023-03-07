package com.ruoyi.sights.mapper;


import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.sights.domain.SightsRecordScore;

import java.util.List;

/**
 * 景点评分
 * @author Chas
 * @date 2022-10
 */
public interface SightsRecordScoreMapper {

    /**
     * 插入 用户 景点 评分
     */
    public int insertUserSightsScore(SightsRecordScore sightsRecordScore);

    /**
     * 判断 用户 是否对景点评分
     */
    public int checkUserSightsScore(SightsRecordScore sightsRecordScore);

    /**
     * 修改 用户 对某景点的评分
     */
    public int updateUserSightsScore(SightsRecordScore sightsRecordScore);

    /**
     * 统计某景点的平均评分
     */
    public double selectAverageSightsScore(Long sightsId);

    /**
     *  获取某景点的评分总数
     */
    public int selectCountScoreNumBySightsId(Long sightsId);

    /**
     * 获取用户评分列表
     * 推荐里面用到
     * @param userId
     * @return List
     */
    public List<SightsRecordScore> getRatingListByUserId(Long userId);



}
