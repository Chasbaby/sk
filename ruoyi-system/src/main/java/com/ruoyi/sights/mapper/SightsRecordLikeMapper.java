package com.ruoyi.sights.mapper;

import com.ruoyi.sights.domain.SightsRecordLike;

public interface SightsRecordLikeMapper {

    /**
     * 点赞 ++
     */
    public int updateSightsViaLike(Long sightsId);

    /**
     * 点赞--
     */
    public int declineSightsViaLike(Long sightsId);

    /**
     * 插入 用户 景点 点赞信息
     */
    public int insertUserSightsLike(SightsRecordLike sightsRecordLike);

    /**
     * 取消 用户 景点 点赞信息
     */
    public int deleteUserSightsLike(SightsRecordLike sightsRecordLike);

    /**
     * 判断用户对某景点是否点赞
     */
    public int judgeUserSightsExistLike(SightsRecordLike sightsRecordLike);

}
