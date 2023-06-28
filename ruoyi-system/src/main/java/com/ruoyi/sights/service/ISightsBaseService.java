package com.ruoyi.sights.service;

import java.util.List;

import com.ruoyi.sights.domain.*;
import com.ruoyi.sights.domain.DTO.*;

/**
 * 景点基本信息Service接口
 * 
 * @author ruoyi chas
 * @date 2022-10-25
 */
public interface ISightsBaseService 
{



    /**
     * 下面是后台管理员系列 操作
     */

    /**
     * 查询景点基本信息
     * 
     * @param sightsId 景点基本信息主键
     * @return 景点基本信息
     */
    public SightsBase selectSightsBaseBySightsId(Long sightsId);

    /**
     * 返回单个景点信息
     * @param sightsId
     * @return
     */
    public SightsDTO selectDetailSightsById(Long sightsId,Long userId);

    /**
     * 查询景点基本信息列表
     * 
     * @param sightsBase 景点基本信息
     * @return 景点基本信息集合
     */
    public List<SightsBase> selectSightsBaseList(SightsBase sightsBase);

    /**
     * 新增景点基本信息
     * 
     * @param sightsBase 景点基本信息
     * @return 结果
     */
    public int insertSightsBase(SightsBase sightsBase);

    /**
     * 修改景点基本信息
     * 
     * @param sightsBase 景点基本信息
     * @return 结果
     */
    public int updateSightsBase(SightsBase sightsBase);

    /**
     * 批量删除景点基本信息
     * 
     * @param sightsIds 需要删除的景点基本信息主键集合
     * @return 结果
     */
    public int deleteSightsBaseBySightsIds(Long[] sightsIds);

    /**
     * 删除景点基本信息信息
     * 
     * @param sightsId 景点基本信息主键
     * @return 结果
     */
    public int deleteSightsBaseBySightsId(Long sightsId);

    /**
     * 下面是景点评分系列操作
     */

    /**
     * 插入 用户 景点 评分
     */
    public Boolean insertUserSightsScore(SightsRecordScore sightsRecordScore);

    /**
     * 判断 用户 是否对景点评分
     */
    public boolean checkUserSightsScore(SightsRecordScore sightsRecordScore);

//    /**
//     * 修改 用户 对某景点的评分
//     */
//    public int updateUserSightsScore(SightsRecordScore sightsRecordScore);

    /**
     * 评分排行榜
     */
    public List<SightsBase> selectSightsTopViaScore(int num);

    /**
     * 统计某景点的平均评分
     */
    public double selectAverageSightsScore(Long sightsId);

    /**
     *  获取某景点的评分总数
     */
    public int selectCountScoreNumBySightsId(Long sightsId);

    /**
     * 下面是景点点赞系列操作
     */

    /**
     * 点赞总管理
     */
    public Boolean SightsManageViaLike(SightsRecordLike sightsRecordLike);

//    /**
//     * 点赞 ++
//     */
//    public int updateSightsViaLike(Long sightsId);
//
//    /**
//     * 点赞--
//     */
//    public int declineSightsViaLike(Long sightsId);

    /**
     * 点赞排行榜
     */
    public List<SightsBase> selectSightsTopViaLike(int num);

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


    /**
     * 下面是景点点击系列操作
     */

    public Boolean updateSightsViaHits(Long sightsId);

    /**
     * 点击排行榜
     */
    public List<SightsBase> selectSightsTopViaHit(int num);


    /**
     * 下面是景点浏览系列操作
     */

    public Boolean updateSightsViaView(Long sightsId);

    /**
     * 浏览量排行榜
     * @return
     */
    public List<SightsBase> selectSightsTopViaView(int num);

    /**
     * 推荐部分
     */

    /**
     * 推荐 核心
     *
     * 获取某用户推荐列表
     */
    public List<SightsRecommendDTO> getRecommendSights(Long userId);

    /**
     * 获取历史热门景点推荐
     */
    public List<SightsRecommendDTO> getHistoryHotSights();

    /**
     * 获取最近热门景点
     */
    public List<SightsRecommendDTO> getRecentHotSights();

    /**
     * 获取优质景点列表
     * @return
     */
    public List<SightsRecommendDTO> getGoodSights();

    /**
     * 收藏景点相关
     */

    /**
     * 收藏景点
     */
    public boolean changeSightsCollection(SightsUserCollect sightsUserCollect);

    /**
     * 取消收藏
     */
    public boolean cancelSightsCollection(SightsUserCollect sightsUserCollect);

    /**
     * 收藏景点记录
     */
    public List<SightsBase> selectCollectSightsRecord(Long userId);

    /**
     * 用户浏览景点历史记录表
     */
    public List<SightsBase> selectHistoryRecordByUserId(Long userId);

    /**
     * 加入用户浏览景点记录
     */
    public boolean addSightsHistoryRecord(SightsRecordHistory recordHistory);

    /**
     * 定时任务
     */
    public void OpenHitViewLikeByQuartz();

    public List<SightsStatisticTopDTO> getStatisticSightsTop();

    public SightsVoiceDTO transReturn(Long id ,Integer position,Long audioId);

    public int selectTopNum();

    public List<SightsSwiperDTO> getSightsSwiper();

    public List<SightsCountryDTO> getCountryData();

}
