package com.ruoyi.sights.mapper;

import com.ruoyi.sights.domain.DTO.SightsReturnDTO;
import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.sights.domain.SightsRecordLike;

import java.util.List;

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


    public List<SightsReturnDTO> getUserSightsCollect(Long userId);

    public List<SightsBase> getUserSightsLike(Long userId);

    public List<SightsBase> getUserSightsHits(Long userId);

    public List<SightsBase> getUserSightsView(Long userId);



}
