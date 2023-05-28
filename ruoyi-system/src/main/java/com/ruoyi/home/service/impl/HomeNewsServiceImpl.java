package com.ruoyi.home.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.home.domain.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.home.mapper.HomeNewsMapper;
import com.ruoyi.home.domain.HomeNews;
import com.ruoyi.home.service.IHomeNewsService;

/**
 * 新闻Service业务层处理
 * 
 * @author ruoyi chas
 * @date 2022-08-19  2023-6-25
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
    public int insertHomeNews(HomeNews homeNews) {
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
    public int updateHomeNews(HomeNews homeNews) {
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
    public int deleteHomeNewsByNewsIds(Long[] newsIds) {
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

    /** 获取新闻详细信息*/
    @Override
    public newsDTO getNewsInfo(Long newsId) {
        HomeNews news = homeNewsMapper.selectNewsDetail(newsId);
        newsDTO dto = new newsDTO();
        BeanUtils.copyBeanProp(dto,news);
        return dto;
    }

    /** 获取新闻列表 **/
    @Override
    public List<newsListDTO> getListNews() {
        List<HomeNews> list = homeNewsMapper.selectNewsList();
        return list.stream().map(item->{
            newsListDTO listDTO = new newsListDTO();
            BeanUtils.copyBeanProp(listDTO,item);
            return listDTO;
        }).collect(Collectors.toList());
    }

    /*** 获取随机新闻*/
    @Override
    public List<newsRandomDTO> getRandomNew() {
        List<HomeNews> list = homeNewsMapper.selectRandomNews();
        return list.stream().map(item->{
            newsRandomDTO randomDTO = new newsRandomDTO();
            BeanUtils.copyBeanProp(randomDTO,item);
            return randomDTO;
        }).collect(Collectors.toList());
    }

    /** swiper
     *  默认 第二章图片为
     * **/
    @Override
    public List<newsSwiperDTO> getSwiper(){
        List<HomeNews> list = homeNewsMapper.selectTopNews();

        List<newsSwiperDTO> swiperDTOS = new ArrayList<>();
        for (HomeNews next : list) {
            next.setImageId(next.getImageId().split(",")[1]);
            newsSwiperDTO swiperDTO = new newsSwiperDTO();
            BeanUtils.copyBeanProp(swiperDTO, next);
            String date = DateToMyString(next.getCreateTime().toString());
            swiperDTO.setCreateTime(date);
            swiperDTOS.add(swiperDTO);
        }
        return swiperDTOS;
    }

    /** 专栏显示*/
    @Override
    public List<newsColumnDTO> getNewsColumn(String columnId) {
        List<HomeNews> list = homeNewsMapper.selectColumnNews(columnId);

        List<newsColumnDTO> columnDTOS = new LinkedList<>();
        list.stream().forEach(item->{
            newsColumnDTO columnDTO = new newsColumnDTO();
            BeanUtils.copyBeanProp(columnDTO,item);
            columnDTOS.add(columnDTO);
        });
        return columnDTOS;
    }

    /** 提取key*/
    @Override
    public List<newsKeyDTO> convertToKey() {
        List<HomeNews> news = homeNewsMapper.selectKeysNews();

        if (news == null){
            return new LinkedList<>();
        }
        return news.stream().map(item->{
            newsKeyDTO keyDTO = new newsKeyDTO();
            BeanUtils.copyBeanProp(keyDTO,item);
            return keyDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<newsListDTO> getSimilarNews(Long newsId) {
        List<HomeNews> list = homeNewsMapper.selectSimilarNews(newsId);
        int size = list.size();
        if(size != 5){
            Iterator<HomeNews> iterator = list.iterator();
            Long[] need = new Long[5-size];
            int i = 0;
            while (iterator.hasNext()){
                HomeNews next = iterator.next();
                need[i] = next.getNewsId();
                i++;
            }
            List<HomeNews> news = homeNewsMapper.selectRandomLimitNews(5 - size,need);
            list.addAll(news);
        }
        return list.stream().map(item->{
            newsListDTO dto = new newsListDTO();
            BeanUtils.copyBeanProp(dto,item);
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     *  适用于 Wed Nov 09 09:26:24 CST 2022  转为   09|/Nov.|2022   记得用 | 分割
     * */
    public static String DateToMyString(String date){
        StringBuilder builder = new StringBuilder();
        String[] strings = date.split(" ");
        return builder.
                append(strings[2]).
                append("|").
                append("/").
                append(strings[1]).
                append(".").
                append("|").
                append(strings[5]).
                toString();
    }
}

