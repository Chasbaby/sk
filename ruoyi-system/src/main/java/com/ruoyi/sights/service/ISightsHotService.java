package com.ruoyi.sights.service;

import com.ruoyi.sights.domain.DTO.SightsHotDTO;

import java.util.List;

/**
 * @author chas
 * @introduction 景点热度算法接口
 * @data 2023-3
 */
public interface ISightsHotService {

    /**
     * 当项目开始时初始化景点
     */
    public void InitSights();

    /**
     * 当项目结束时的操作
     */
    public void destroySights();

    /**
     * 检查是否过期
     */
    public void checkIfTimeout();

    /**
     * 提高浏览量
     * @param sightsId
     */
    public void addView(Long sightsId,Long userId);

    /**
     * 点赞
     * @param sightsId
     */
    public void addLike(Long sightsId,Long userId);

    /**
     * 取消点赞
     * @param sightsId
     */
    public void cancelLike(Long sightsId,Long userId);


    /**
     * 评分
     * @param sightsId
     */
    public void score(Long sightsId,Double score,Long userId);

    /**
     * 收藏
     * @param sightsId
     */
    public void addCollect(Long sightsId,Long userId);

    /**
     * 取消收藏
     * @param sightsId
     * @param userId
     */
    public void cancelCollect(Long sightsId,Long userId);

    /**
     * 增加点击
     * @param sightsId
     * @param userId
     */
    public void addHit(Long sightsId,Long userId);

    /**
     *  返回当前热度最高的一定量景点
     * @return
     */
    public List<SightsHotDTO> currentHotSights();

    /**
     * 判断是否点赞 并进入不同的效果
     */
    public void ifLike(Long sightsId,Long userId);

}
