package com.ruoyi.home.service;

import java.util.List;
import com.ruoyi.home.domain.HomeAd;

/**
 * 广告Service接口
 * 
 * @author ruoyi
 * @date 2022-08-15
 */
public interface IHomeAdService 
{
    /**
     * 获得规定数目的广告
     * @param n
     * @return 广告
     */
    public List<HomeAd> selectAds(int n);
    /**
     * 查询广告
     * @param adId 广告主键
     * @return 广告
     */
    public HomeAd selectHomeAdByAdId(Long adId);

    /**
     * 查询广告列表
     * @param homeAd 广告
     * @return 广告集合
     */
    public List<HomeAd> selectHomeAdList(HomeAd homeAd);

    /**
     * 新增广告
     * @param homeAd 广告
     * @return 结果
     */
    public int insertHomeAd(HomeAd homeAd);

    /**
     * 修改广告
     * @param homeAd 广告
     * @return 结果
     */
    public int updateHomeAd(HomeAd homeAd);

    /**
     * 逻辑批量删除广告
     * @param adIds 需要删除的广告主键集合
     * @return 结果
     */
    public int deleteHomeAdByAdIds(Long[] adIds);

    /**
     * 逻辑删除广告信息
     * @param adId 广告主键
     * @return 结果
     */
    public int deleteHomeAdByAdId(Long adId);

    /**
     * 自动物理删除所有 逻辑删除的广告
     *
     */
    public int deleteHomeAdByQuartz();

    /**
     *  点击量++
     */
    public int updateAdHits(Long adId);

    /**
     * 查询时间周期  也就是 规定时间之前的  (获得历史热门)
     */
    public List<HomeAd> selectAdByHistoryDate(HomeAd homeAd , int number);

    /**
     *  查询时间周期 规定时间之后 (近期热门)
     */
    public List<HomeAd> selectAdByRecentDate(HomeAd homeAd,int number);

    /**
     * 获取优质广告
     */
    public List<HomeAd> selectGoodAd(HomeAd homeAd,int number);
    /**
     * 查阅置顶数量
     */
    public int selectTopNum();
    /**
     * 查询逻辑删除数量
     */
    public int selectDeleteAdNumByLogic();

}
