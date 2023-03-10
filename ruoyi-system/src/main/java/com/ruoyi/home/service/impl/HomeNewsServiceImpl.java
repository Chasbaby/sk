package com.ruoyi.home.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.home.mapper.HomeNewsMapper;
import com.ruoyi.home.domain.HomeNews;
import com.ruoyi.home.service.IHomeNewsService;

/**
 * 新闻Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-19
 */
@Service
public class HomeNewsServiceImpl implements IHomeNewsService 
{
    @Autowired
    private HomeNewsMapper homeNewsMapper;

    /**
     * 查询新闻
     * 
     * @param newsId 新闻主键
     * @return 新闻
     */
    @Override
    public HomeNews selectHomeNewsByNewsId(Long newsId)
    {
        return homeNewsMapper.selectHomeNewsByNewsId(newsId);
    }

    /**
     * 查询新闻列表
     * 
     * @param homeNews 新闻
     * @return 新闻
     */
    @Override
    public List<HomeNews> selectHomeNewsList(HomeNews homeNews)
    {
        if (homeNews.getDelFlag()== null ){
            homeNews.setDelFlag("N");
        }
        return homeNewsMapper.selectHomeNewsList(homeNews);
    }

    /**
     * 新增新闻
     * 
     * @param homeNews 新闻
     * @return 结果
     */
    @Override
    public int insertHomeNews(HomeNews homeNews)
    {
        homeNews.setCreateTime(DateUtils.getNowDate());
        return homeNewsMapper.insertHomeNews(homeNews);
    }

    /**
     * 修改新闻
     * 
     * @param homeNews 新闻
     * @return 结果
     */
    @Override
    public int updateHomeNews(HomeNews homeNews)
    {
        homeNews.setUpdateTime(DateUtils.getNowDate());
        return homeNewsMapper.updateHomeNews(homeNews);
    }

    /**
     * 批量删除新闻
     * 
     * @param newsIds 需要删除的新闻主键
     * @return 结果
     */
    @Override
    public int deleteHomeNewsByNewsIds(Long[] newsIds)
    {
        return homeNewsMapper.deleteHomeNewsByNewsIdsByLogic(newsIds);
    }

    /**
     * 删除新闻信息
     * 
     * @param newsId 新闻主键
     * @return 结果
     */
    @Override
    public int deleteHomeNewsByNewsId(Long newsId)
    {
        return homeNewsMapper.deleteHomeNewsByNewsIdByLogic(newsId);
    }

    /**
     * 定时任务物理删除新闻
     */
    @Override
    public int deleteHomeNewsByQuartz() {
        return homeNewsMapper.deleteHomeNewsByQuartz();
    }

    /**核心推荐*/
    @Override
    public List<HomeNews> selectNews(int n) {
        if (n>0){
            //先获得所有置顶的新闻
            HomeNews homeNews = new HomeNews();
            homeNews.setDelFlag("N");
            homeNews.setTopFlag("Y");
            List<HomeNews> list = homeNewsMapper.selectHomeNewsList(homeNews);
            int size = list.size();
            if (size>=n){
                list.sort((Comparator.comparingInt(HomeNews::getNewsHits)).reversed());
                return list.subList(0,n);
            }else {
                homeNews.setTopFlag("N");
                List<HomeNews> newsList = homeNewsMapper.selectHomeNewsList(homeNews);
                int newSize = newsList.size();
                while (n-size>0){
                    if (newSize<=0){
                        break;
                    }
                    int i = new Random().nextInt(newsList.size());
                    list.add(newsList.get(i));
                    size++;
                    newsList.remove(i);
                    newSize--;
                }
                return list;
            }
        }
        return null;
    }

    /**获取历史热门*/
    @Override
    public List<HomeNews> selectNewsByHistoryDate(HomeNews homeNews, int num) {
        //已排序
        List<HomeNews> list = homeNewsMapper.selectHistoryByDate(homeNews);

        if (list.size()>=num){
            return list.subList(0,num);
        }
        return list;
    }

    /**获取近期热门*/
    @Override
    public List<HomeNews> selectNewsByRecentDate(HomeNews homeNews, int num) {
        List<HomeNews> list = homeNewsMapper.selectRecentByDate(homeNews);

        if (list.size()>=num){
            return list.subList(0,num);
        }
        return list;
    }

    /**获取近期热门*/
    @Override
    public List<HomeNews> selectGoodNews(HomeNews homeNews, int num) {
        List<HomeNews> list = homeNewsMapper.selectHomeNewsList(homeNews);
        list.sort(Comparator.comparingInt(HomeNews::getNewsHits).reversed());
        if (list.size()>=num){
            return list.subList(0,num);
        }
        return list;
    }

    /**点击量++*/
    @Override
    public int updateNewsHits(Long newsId) {
        return homeNewsMapper.updateNewHits(newsId);
    }
}
