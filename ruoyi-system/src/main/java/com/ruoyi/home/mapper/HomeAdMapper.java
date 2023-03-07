package com.ruoyi.home.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.home.domain.HomeAd;

/**
 * 广告Mapper接口
 * 
 * @author ruoyi and chas
 * @date 2022-08-15
 */
public interface HomeAdMapper 
{
    /**
     * 查询广告
     * 
     * @param adId 广告主键
     * @return 广告
     */
    public HomeAd selectHomeAdByAdId(Long adId);

    /**
     * 查询广告列表
     * 
     * @param homeAd 广告
     * @return 广告集合
     */
    public List<HomeAd> selectHomeAdList(HomeAd homeAd);

    /**
     * 新增广告
     * 
     * @param homeAd 广告
     * @return 结果
     */
    public int insertHomeAd(HomeAd homeAd);

    /**
     * 修改广告
     * 
     * @param homeAd 广告
     * @return 结果
     */
    public int updateHomeAd(HomeAd homeAd);

    /**
     * 删除广告
     *
     * @param adId 广告主键
     * @return 结果
     */
    public int deleteHomeAdByAdIdByLogic(Long adId);

    /**
     * 批量删除广告
     * 
     * @param adIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomeAdByAdIdsByLogic(Long[] adIds);

    /**
     * 定时任务 物理删除 标记为 Y 的广告 (也就是删除备份)
     */
    public int deleteHomeAdByQuartz();

    /**
     * 点击量 ++
     */
    public int updateAdHits(Long adId);

    /**
     * 查询历史热门广告
     */
    public List<HomeAd> selectHistoryByDate(HomeAd homeAd);

    /**
     * 查询最近热门广告
     */
    public List<HomeAd> selectRecentByDate(HomeAd home);

    /**
     * 查阅置顶Ad数量
     */
    public int selectTopNum();

    /**
     * 查询逻辑删除的数量
     */
    public int selectDeleteAdNumByLogic();

}
