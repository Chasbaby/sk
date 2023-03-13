package com.ruoyi.sights.mapper;

import com.ruoyi.culCreativity.domain.SightsCulCreativityRecordLike;

public interface SightsCulCreativityRecordLikeMapper {

    /**
     * 点赞 ++
     */
    public int updateSightsCulCreativityViaLike(Long culCreativityId);

    /**
     * 点赞--
     */
    public int declineSightsCulCreativityViaLike(Long culCreativityId);

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

}
