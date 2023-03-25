package com.ruoyi.sights.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.sights.domain.SightsRecordHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

/**
 * 景点基本信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-25
 */
public interface SightsBaseMapper 
{
    /**
     * 下面是热度算法用到的sql
     */
    /**
     * 初始化热门景点
     * @return list
     */
    public List<SightsBase> initSights();

    /**
     * 查询景点基本信息
     * 
     * @param sightsId 景点基本信息主键
     * @return 景点基本信息
     */
    public SightsBase selectSightsBaseBySightsId(Long sightsId);

    /**
     * 查询景点基本信息列表
     * 
     * @param sightsBase 景点基本信息
     * @return 景点基本信息集合
     */
    public List<SightsBase> selectSightsBaseList(SightsBase sightsBase);

    /**
     * 全局搜索景点专门用一个表示一下
     * @param sightsBase 景点的基本信息
     * @return 基本信息
     */
    public List<SightsBase> selectSightsInSearchList(SightsBase sightsBase);

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
     * 删除景点基本信息
     * 
     * @param sightsId 景点基本信息主键
     * @return 结果
     */
    public int deleteSightsBaseBySightsId(Long sightsId);

    /**
     * 批量删除景点基本信息
     * 
     * @param sightsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSightsBaseBySightsIds(Long[] sightsIds);

    /**
     * 获取评分最高的一定量的景点
     */
    public List<SightsBase> selectSightsTopViaScore(Integer num);

    /**
     * 获取点赞最高的一定量的景点
     */
    public List<SightsBase> selectSightsTopViaLike(int num);

    /**
     * 获取点击最高的一定量的景点
     */
    public List<SightsBase> selectSightsTopViaHit(int num);

    /**
     * 获取浏览最高的一定量的景点
     */
    public List<SightsBase> selectSightsTopViaView(int num);

    /**
     * 点击量 ++
     */
    public int updateSightsViaHits(Long sightsId);

    /**
     * 批量增加点击量
     */
    public int updateSightsHitBySightsIds(@Param("sightsId") Long sightsId,@Param("value")Integer value);

    /**
     * 浏览量++
     */
    public int updateSightsViaView(Long sightsId);

    /**
     * 批量添加点击量
     */
    public int updateSightsViewBySightsIds(@Param("sightsId") Long sightsId, @Param("value")Integer value);

    /**
     * 获取某用户推荐的景点信息
     */
    public List<SightsBase> getRecommendSights(Long userId);

    /**
     * 获取历史热门景点信息
     */
    public List<SightsBase> getHistoryHotSights();

    /**
     * 获取近期热门景点信息
     */
    public List<SightsBase> getRecentHotSights();

    /**
     * 获取优质景点信息
     */
    public List<SightsBase> getGoodSights();


    /**
     * 历史浏览记录
     */
    public List<SightsBase> getHistoryRecord(Long userId);

    /**
     * 加入历史浏览记录表
     */
    public int addSightsHistoryRecord(SightsRecordHistory sightsRecordHistory);

    /**
     * 判断用户浏览记录是否唯一
     */
    public int judgeSightsHistoryOnlyOne(SightsRecordHistory sightsRecordHistory);

    /**
     * 更新用户浏览记录
     */
    public int updateSightsHistory(SightsRecordHistory sightsRecordHistory);

    /**
     * 批量增加历史浏览记录
     */
    public int insertSightsHistoryByBatches(List<SightsRecordHistory> sightsRecordHistories);


}
