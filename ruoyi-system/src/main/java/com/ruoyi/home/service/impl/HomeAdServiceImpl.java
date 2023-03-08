package com.ruoyi.home.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.ToIntFunction;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.home.mapper.HomeAdMapper;
import com.ruoyi.home.domain.HomeAd;
import com.ruoyi.home.service.IHomeAdService;

/**
 * 广告Service业务层处理
 * 
 * @author ruoyi chas
 * @date 2022-08-15
 */
@Service
public class HomeAdServiceImpl implements IHomeAdService {
    @Autowired
    private HomeAdMapper homeAdMapper;
    /**
     * 查询广告
     * @param adId 广告主键
     * @return 广告
     */
    @Override
    public HomeAd selectHomeAdByAdId(Long adId)
    {
        return homeAdMapper.selectHomeAdByAdId(adId);
    }

    /**
     * 查询广告列表
     * 
     * @param homeAd 广告
     * @return 广告
     */
    @Override
    public List<HomeAd> selectHomeAdList(HomeAd homeAd)
    {
        if (homeAd.getDelFlag()== null ){
            homeAd.setDelFlag("N");
        }
        return homeAdMapper.selectHomeAdList(homeAd);
    }

    /**
     * 新增广告
     * 
     * @param homeAd 广告
     * @return 结果
     */
    @Override
    public int insertHomeAd(HomeAd homeAd)
    {
        homeAd.setCreateTime(DateUtils.getNowDate());
        return homeAdMapper.insertHomeAd(homeAd);
    }

    /**
     * 修改广告
     * @param homeAd 广告
     * @return 结果
     */
    @Override
    public int updateHomeAd(HomeAd homeAd)
    {

        homeAd.setUpdateTime(DateUtils.getNowDate());
        return homeAdMapper.updateHomeAd(homeAd);
    }

    /**
     * 批量删除广告
     * @param adIds 需要删除的广告主键
     * @return 结果
     */
    @Override
    public int deleteHomeAdByAdIds(Long[] adIds)
    {
        return homeAdMapper.deleteHomeAdByAdIdsByLogic(adIds);
    }

    /**
     * 删除广告信息
     * @param adId 广告主键
     * @return 结果
     */
    @Override
    public int deleteHomeAdByAdId(Long adId)
    {
        return homeAdMapper.deleteHomeAdByAdIdByLogic(adId);
    }

    /**
     * 定时任务物理删除广告
     */
    @Override
    public int deleteHomeAdByQuartz() {
        System.out.println("自动删除广告成功");
        return homeAdMapper.deleteHomeAdByQuartz();
    }

    /**
     * 点击量++
     * @param  adId
     * @return 成功个数
     */
    @Override
    public int updateAdHits(Long adId) {
        return homeAdMapper.updateAdHits(adId);
    }

    /**
     * 获得规定数目的广告
     */
    @Override
    public List<HomeAd> selectAds(int n){
        if (n>=0){
            // 先获得所有置顶的广告
            HomeAd homeAd = new HomeAd();
            homeAd.setTopFlag("Y");
            homeAd.setDelFlag("N");
            List<HomeAd> list = homeAdMapper.selectHomeAdList(homeAd);
            int size = list.size();
            // 如果需要的数量比规定的多
            if (size >= n){
                // 那么 按照 热度排序 取前 num个  有reversed是倒序 也就是大的在前面小的在后面
                list.sort(Comparator.comparingInt(HomeAd::getAdHits).reversed());
                return list.subList(0, n);
            }else {
                //如果 size 少 从不置顶的里面选
                homeAd.setTopFlag("N");
                List<HomeAd> newList =homeAdMapper.selectHomeAdList(homeAd);
                int newSize = newList.size();
                // 一直选到 满足的数量  或者说 库里面已经没有了
                while (n-size>0){
                    if (newSize<=0){
                        break;
                    }
                    int i = new Random().nextInt(newList.size());
                    list.add(newList.get(i));
                    size++;
                    newList.remove(i);
                    newSize--;

                }
                return list;
            }
        }

        return null;
    }

    /**
     * 历史热门
     * @param homeAd
     * @return 广告
     */
    @Override
    public List<HomeAd> selectAdByHistoryDate(HomeAd homeAd,int number) {
        //已排序
        List<HomeAd> list = homeAdMapper.selectHistoryByDate(homeAd);
        // 如果 需求小 则
        if (list.size()>=number){
            return list.subList(0,number);
        }
        // 如果 需求大 但是 不足 那么 直接返回
        return list;
    }

    /**
     * 近期热门
     */
    @Override
    public List<HomeAd> selectAdByRecentDate(HomeAd homeAd, int number) {
        //已排序
        List<HomeAd> list = homeAdMapper.selectRecentByDate(homeAd);
        // 如果 需求小 则
        if (list.size()>=number){
            return list.subList(0,number);
        }
        // 如果 需求大 但是 不足 那么 直接返回
        return list;
    }

    /**
     * 优质广告
     * @param homeAd
     * @param number
     * @return homeAd
     */
    @Override
    public List<HomeAd> selectGoodAd(HomeAd homeAd, int number) {
        List<HomeAd> list = homeAdMapper.selectHomeAdList(homeAd);
        list.sort(Comparator.comparingInt(HomeAd::getAdHits).reversed());
        // 如果 需求小 则
        if (list.size()>=number){
            return list.subList(0,number);
        }
        // 如果 需求大 但是 不足 那么 直接返回
        return list;
    }


    /**
     * 查询质量数量
     * @return num
     */
    @Override
    public int selectTopNum() {
        return homeAdMapper.selectTopNum();
    }

    /***逻辑删除数量*/
    @Override
    public int selectDeleteAdNumByLogic() {
        return homeAdMapper.selectDeleteAdNumByLogic();
    }



}
