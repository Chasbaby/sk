package com.ruoyi.home.service;

import java.util.List;
import com.ruoyi.home.domain.HomeNews;
import com.ruoyi.home.domain.dto.newsDTO;
import com.ruoyi.home.domain.dto.newsListDTO;

/**
 * 新闻Service接口
 * 
 * @author ruoyi
 * @date 2022-08-19
 */
public interface IHomeNewsService {
    /**
     * 查询新闻
     * 
     * @param newsId 新闻主键
     * @return 新闻
     */
    public HomeNews selectHomeNewsByNewsId(Long newsId);

    /**
     * 查询新闻列表
     * 
     * @param homeNews 新闻
     * @return 新闻集合
     */
    public List<HomeNews> selectHomeNewsList(HomeNews homeNews);

    /**
     * 新增新闻
     * 
     * @param homeNews 新闻
     * @return 结果
     */
    public int insertHomeNews(HomeNews homeNews);

    /**
     * 修改新闻
     * 
     * @param homeNews 新闻
     * @return 结果
     */
    public int updateHomeNews(HomeNews homeNews);

    /**
     * 批量删除新闻
     * 
     * @param newsIds 需要删除的新闻主键集合
     * @return 结果
     */
    public int deleteHomeNewsByNewsIds(Long[] newsIds);

    /**
     * 删除新闻信息
     * 
     * @param newsId 新闻主键
     * @return 结果
     */
    public int deleteHomeNewsByNewsId(Long newsId);

    public int deleteHomeNewsByQuartz();

    /**
     * 获得规定数目的新闻
     */
    public List<HomeNews> selectNews(int n);

    /**
     * 获取历史热门
     * @param homeNews
     * @param num
     * @return
     */
    public List<HomeNews> selectNewsByHistoryDate(HomeNews homeNews , int num);

    /**获取近期热门*/
    public List<HomeNews> selectNewsByRecentDate(HomeNews homeNews,int num);

    /**获取优质*/
    public List<HomeNews> selectGoodNews(HomeNews homeNews,int num);

    public int updateNewsHits(Long newsId);

    /** 获取单个新闻详细信息*/
    public newsDTO getNewsInfo(Long newsId);

    /** 获取最新的新闻**/
    public List<newsListDTO> getListNews();



}
