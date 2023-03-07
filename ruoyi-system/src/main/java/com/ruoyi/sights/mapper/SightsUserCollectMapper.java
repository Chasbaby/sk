package com.ruoyi.sights.mapper;

import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.sights.domain.SightsUserCollect;

import java.util.List;

public interface SightsUserCollectMapper {
    /**
     * 添加收藏
     * @param sightsUserCollect
     * @return
     */
    public int addSightsCollection(SightsUserCollect sightsUserCollect);

    /**
     * 取消收藏
     * @param sightsUserCollect
     * @return
     */
    public int cancelSightsCollection(SightsUserCollect sightsUserCollect);

    /**
     * 查询收藏记录
     * @param userId
     * @return
     */
    public List<SightsBase> selectCollectSightsRecord(Long userId);

    /**
     * 判断是否收藏
     */

    public int judgeCollect(SightsUserCollect sightsUserCollect);

    /**
     *  批量查询收藏记录
     */
    public int insertCollectByBatches(List<SightsUserCollect> list);



}
