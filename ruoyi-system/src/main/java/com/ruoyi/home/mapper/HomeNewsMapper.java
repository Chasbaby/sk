package com.ruoyi.home.mapper;

import java.util.List;

import com.ruoyi.home.domain.HomeNews;
import com.ruoyi.home.domain.dto.newsListDTO;
import org.apache.ibatis.annotations.Param;

/**
 * 新闻Mapper接口
 * 
 * @author ruoyi
 * @date 2022-08-19  2023-5-29
 */
public interface HomeNewsMapper {
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
     * 删除新闻
     * 
     * @param newsId 新闻主键
     * @return 结果
     */
    public int deleteHomeNewsByNewsIdByLogic(Long newsId);

    /**
     *
     * 批量删除新闻
     * 
     * @param newsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomeNewsByNewsIdsByLogic(Long[] newsIds);

    /**
     * 定时任务 物理删除 标记为 Y 的新闻 (也就是删除备份)
     */
    public int deleteHomeNewsByQuartz();

    /**
     * 点击量 ++
     */
    public int updateNewHits(Long newsId);

    /**
     * 查询历史热门
     */
    public List<HomeNews> selectHistoryByDate(HomeNews homeNews);

    /**
     * 查询最近热门
     */
    public List<HomeNews> selectRecentByDate(HomeNews homeNews);

//    /**
//     * 查阅置顶Ad数量
//     */
//    public int selectTopNum();

//    /**
//     * 查询逻辑删除的数量
//     */
//    public int selectDeleteAdNumByLogic();

    /** 获取新闻详细信息*/
    public HomeNews selectNewsDetail(Long newsId);

    public List<newsListDTO> selectNewsList();

    public List<HomeNews> selectRandomNews();

    public List<HomeNews> selectTopNews();

    public List<HomeNews> selectColumnNews(String columnId);

    public List<HomeNews> selectKeysNews();

    public List<HomeNews> selectSimilarNews(Long newsId);

    // 辅助相似度的
    public List<HomeNews> selectRandomLimitNews(@Param("newsId") Long[] newsId,@Param("num") Integer num);
}
