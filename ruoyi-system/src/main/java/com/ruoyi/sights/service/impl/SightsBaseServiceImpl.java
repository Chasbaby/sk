package com.ruoyi.sights.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.sights.domain.DTO.*;
import com.ruoyi.sights.domain.*;
import com.ruoyi.sights.mapper.*;
import com.ruoyi.sights.service.ISightsBaseService;
import com.ruoyi.sights.service.ISightsTicketService;
import com.ruoyi.system.domain.SysAudio;
import com.ruoyi.system.service.ICommentService;
import com.ruoyi.system.service.ISysAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.ruoyi.common.utils.baidu.TranslateUtils.getTranslateResult;

/**
 *
 * 景点基本信息Service业务层处理
 * @author ruoyi chas
 * @date 2022-10-25
 *
 */
@Service
public class SightsBaseServiceImpl implements ISightsBaseService 
{
    private final static String pattern="<(\\S*?)[^>]*>.*?|<.*? />";
    @Autowired
    private SightsBaseMapper sightsBaseMapper;

    @Autowired
    private SightsRecordScoreMapper sightsRecordScoreMapper;

    @Autowired
    private SightsRecordLikeMapper sightsRecordLikeMapper;

    @Autowired
    private SightsUserCollectMapper sightsUserCollectMapper;

    @Autowired
    private SightsBulletinMapper  bulletinMapper ;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private ISightsTicketService ticketService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SightsRecordMapper recordMapper;

    @Autowired
    private ISysAudioService audioService;



    /**
     * 下面是后台管理员系列操作
     */

    /**
     * 查询景点基本信息
     * 
     * @param sightsId 景点基本信息主键
     * @return 景点基本信息
     */
    @Override
    public SightsBase selectSightsBaseBySightsId(Long sightsId)
    {
        return sightsBaseMapper.selectSightsBaseBySightsId(sightsId);
    }

    /**
     * 查询景点详细信息
     * @param sightsId id
     * @return dto
     */
    public SightsDTO selectDetailSightsById(Long sightsId,Long userId){
        SightsDTO sightsDTO = new SightsDTO();

        SightsBulletin sightsBulletin = new SightsBulletin();
        sightsBulletin.setSightsId(sightsId);
        sightsBulletin.setDelFlag("N");// 没有删除
        sightsBulletin.setTopFlag("Y"); // 置顶啦啦啦啦
        sightsBulletin.setStatus("0"); // 正常

        List<SightsBulletin> sightsBulletins = bulletinMapper.searchBullAboutSights(sightsBulletin);// 最多限制5个了啦
        List<BulletinDTO> bulletinDTOS = new LinkedList<>();
        if(!sightsBulletins.isEmpty()){
            bulletinDTOS = sightsBulletins.stream().map(item -> {
                BulletinDTO dto = new BulletinDTO();
                BeanUtils.copyBeanProp(dto, item);
                return dto;
            }).collect(Collectors.toList());
        }else {
            BulletinDTO dto = new BulletinDTO();
            dto.setBulletinContent("暂时没有公告哦,请等待相关景点安排，谢谢!");
            bulletinDTOS.add(dto);
        }
        SightsBase sightsBase = sightsBaseMapper.selectSightsBaseBySightsId(sightsId);
        BeanUtils.copyBeanProp(sightsDTO,sightsBase);
        sightsDTO.setBulletin(bulletinDTOS);
        int scoreNum = selectCountScoreNumBySightsId(sightsId);
        sightsDTO.setScoreNum(scoreNum);
        int commentNum = commentService.selectCommentNum("0",sightsId);
        sightsDTO.setCommentNum(commentNum);
        List<TicketDTO> ticketDTOS = ticketService.selectTicketsBySightsId(sightsId);
        sightsDTO.setTickets(ticketDTOS);

        sightsDTO.setIfCollect(recordMapper.judgeCollect(userId,sightsId));
        sightsDTO.setIfLike(recordMapper.judgeLike(userId,sightsId));
        Double score = recordMapper.getSightsScoreByUser(userId, sightsId);
        sightsDTO.setScore(score == null ? -1 : score);
        return sightsDTO;
    }


    /**
     * 查询景点基本信息列表
     * 
     * @param sightsBase 景点基本信息
     * @return 景点基本信息
     */
    @Override
    public List<SightsBase> selectSightsBaseList(SightsBase sightsBase)
    {
        return sightsBaseMapper.selectSightsBaseList(sightsBase);
    }

    /**
     * 新增景点基本信息
     * 
     * @param sightsBase 景点基本信息
     * @return 结果
     */
    @Override
    public int insertSightsBase(SightsBase sightsBase)
    {
        sightsBase.setCreateTime(DateUtils.getNowDate());
        return sightsBaseMapper.insertSightsBase(sightsBase);
    }

    /**
     * 修改景点基本信息
     * 
     * @param sightsBase 景点基本信息
     * @return 结果
     */
    @Override
    public int updateSightsBase(SightsBase sightsBase)
    {
        sightsBase.setUpdateTime(DateUtils.getNowDate());
        return sightsBaseMapper.updateSightsBase(sightsBase);
    }

    /**
     * 批量删除景点基本信息
     * 
     * @param sightsIds 需要删除的景点基本信息主键
     * @return 结果
     */
    @Override
    public int deleteSightsBaseBySightsIds(Long[] sightsIds)
    {
        return sightsBaseMapper.deleteSightsBaseBySightsIds(sightsIds);
    }

    /**
     * 删除景点基本信息信息
     * 
     * @param sightsId 景点基本信息主键
     * @return 结果
     */
    @Override
    public int deleteSightsBaseBySightsId(Long sightsId)
    {
        return sightsBaseMapper.deleteSightsBaseBySightsId(sightsId);
    }

    /**
     * 下面是景点评分系列操作
     * 完成 全 okk
     */

    /**
     * 插入 用户 景点 评分
     * 直接调用sql
     */
    @Override
    public Boolean insertUserSightsScore(SightsRecordScore sightsRecordScore) {
        List<SightsRecordScore> cache = redisCache.getCacheList(Constants.SIGHTS_SCORE);
        // 如果有缓存
        if (cache.size()>0){
            // 遍历整个 cache
            for (SightsRecordScore item : cache) {// 如果 用户相同 且 景点相同
                if (item.getUserId().equals( sightsRecordScore.getUserId()) && item.getSightsId().equals(sightsRecordScore.getSightsId())) {
                    // 说明 缓存中已经有数据 在有数据的情况下再评分就是 修改得分 修改缓存中的数据
                    item.setScore(sightsRecordScore.getScore());//修改得分
                    item.setCreateTime(sightsRecordScore.getCreateTime());//修改日期

                    // 修正redis
                    redisCache.setCacheList(Constants.SIGHTS_SCORE,cache);
                    return true; // 若已经改正 则直接结束
                }
            }
            /** 无需 再 判断数据库中有没有 浪费时间 到时候直接覆盖就行 */
            // 到这里说明 缓存中没有 满足条件的数据 那就加入缓存
            cache.add(sightsRecordScore);
            // 修正 redis
            redisCache.setCacheList(Constants.SIGHTS_SCORE,cache);
            return true;
        }
        // 到这里说明没有缓存
        List<SightsRecordScore> scoreList = new LinkedList<>();
        scoreList.add(sightsRecordScore);
        redisCache.setCacheList(Constants.SIGHTS_SCORE,scoreList);
        return true;
//        //检查是否已经评分
//        boolean check = checkUserSightsScore(sightsRecordScore);
//        //如果已经评分
//        if (check){
//            //更新评分
//            updateUserSightsScore(sightsRecordScore);
//        }else{
//            //没有则加入
//            sightsRecordScoreMapper.insertUserSightsScore(sightsRecordScore);
//        }
    }

    /**
     * 判断 用户 是否对景点评分
     * 直接调用sql
     */
    @Override
    public boolean checkUserSightsScore(SightsRecordScore sightsRecordScore) {
        List<SightsRecordScore> list = redisCache.getCacheList(Constants.SIGHTS_SCORE);
        // 遍历缓存   如果没有缓存就不用啦
        if (list.size()>0){
            for (SightsRecordScore score : list) {
                if (sightsRecordScore.getUserId().equals(score.getUserId())&&
                        sightsRecordScore.getSightsId().equals(score.getSightsId())){
                    // 如果缓存中有满足条件的数据 就直接返回
                    return true;
                }
            }
        }
        // 到这里说明 缓存中没有 就直接从数据库中拿啦~~
        return sightsRecordScoreMapper.checkUserSightsScore(sightsRecordScore) > 0;
    }
/*

     修改 用户 对某景点的评分

    @Override
    public int updateUserSightsScore(SightsRecordScore sightsRecordScore) {
        return sightsRecordScoreMapper.updateUserSightsScore(sightsRecordScore);
    }

*/
    /**
     * 评分排行榜
     */
    @Override
    public List<SightsBase> selectSightsTopViaScore(int num) {
        List<SightsBase> baseList = sightsBaseMapper.selectSightsTopViaScore(num);
        return baseList;
    }

    /**
     * 统计某景点的平均评分
     */
    @Override
    public double selectAverageSightsScore(Long sightsId) {
        return sightsRecordScoreMapper.selectAverageSightsScore(sightsId);
    }

    /**
     *  获取某景点的评分总数
     */
    @Override
    public int selectCountScoreNumBySightsId(Long sightsId) {
        return sightsRecordScoreMapper.selectCountScoreNumBySightsId(sightsId);
    }



    /**
     * 下面是景点点赞系列操作
     */
/*

    @Transactional
    @Override
    public void SightsManageViaLike(SightsRecordLike sightsRecordLike) {
        int i = judgeUserSightsExistLike(sightsRecordLike);
        if (i==1){
            //1.点赞量--
            declineSightsViaLike(sightsRecordLike.getSightsId());

            //2.删除关联表记录
            deleteUserSightsLike(sightsRecordLike);
            //3.记录到日志

        }else {
            //1.点赞量 ++
            updateSightsViaLike(sightsRecordLike.getSightsId());

            //2.记录到关联表中
            insertUserSightsLike(sightsRecordLike);

            //3.记录到日志

        }

    }
 */

    /**
     * 点赞 ++ --
     * 存储格式 : 用户名|景点ID|时间
     */
    @Transactional
    @Override
    public Boolean SightsManageViaLike(SightsRecordLike sightsRecordLike) {
        // 获取 cache
        List<String> list = redisCache.getCacheList(Constants.SIGHTS_LIKE);
        String content = new StringBuilder()
                .append(sightsRecordLike.getUserId()).append(Constants.SPLIT_LINE)
                .append(sightsRecordLike.getSightsId()).append(Constants.SPLIT_LINE)
                .append(sightsRecordLike.getCreateTime()).toString();
        // // 检测是否存在此缓存
        if(list.size()==0){
            //如果没有此缓存 检测数据库中是否有此数据
            int i = judgeUserSightsExistLike(sightsRecordLike);
            if (i<1){
                // 如果没有此数据 那么将此数据加入缓存
                List<String> cache = new LinkedList<>();
                cache.add(content);
                redisCache.setCacheList(Constants.SIGHTS_LIKE,cache);
                // TODO 添加日志 点赞
            }else {
                // 如果数据库中 有这个数据 那说明是取消点赞
                // 取消点赞 然后就结束了
                // TODO 1. 取消点赞数目  2.取消点赞关联 3.添加日志
            }
            return true;
        }
        //能到这里 说明 存在缓存

        // 存在缓存 判断是否里面有此数据
        boolean contains = list.contains(content);
        if (!contains){
            // 判断数据库中是否有记录
            int i = judgeUserSightsExistLike(sightsRecordLike);
            // 如果数据库中没有数据
            if (i < 1){
                //缓存中 和 数据库 中 都无信息 说明是来点赞的
                //说明是来点赞的 暂时加入缓存
                list.add(content);
                redisCache.setCacheList(Constants.SIGHTS_LIKE,list);
                // TODO 添加日志
                return true;
            }
            // 如果数据库中存在数据 说明是来取消点赞的
            // 取消点赞
            // TODO 1. 取消点赞数目  2.取消点赞关联  3. 添加日志
            return true;
        }
        // 到这里 说明 缓存中存在这个数据 说明是 在缓存中取消点赞
        list.remove(content);
        redisCache.setCacheList(Constants.SIGHTS_LIKE,list);
        // TODO 添加日志
        return true;
    }


    /**
     * 点赞排行榜
     */
    @Override
    public List<SightsBase> selectSightsTopViaLike(int num) {
        List<SightsBase> baseList = sightsBaseMapper.selectSightsTopViaLike(num);
        return baseList;
    }

    /**
     * 插入 用户 景点 点赞信息
     */
    @Override
    public int insertUserSightsLike(SightsRecordLike sightsRecordLike) {
        return sightsRecordLikeMapper.insertUserSightsLike(sightsRecordLike);
    }

    /**
     * 取消 用户 景点 点赞信息
     */
    @Override
    public int deleteUserSightsLike(SightsRecordLike sightsRecordLike) {
        return sightsRecordLikeMapper.deleteUserSightsLike(sightsRecordLike);
    }

    /**
     * 判断用户对某景点是否点赞
     */
    @Override
    public int judgeUserSightsExistLike(SightsRecordLike sightsRecordLike) {
        return sightsRecordLikeMapper.judgeUserSightsExistLike(sightsRecordLike);
    }



    /**
     * 下面是景点点击系列操作
     */

    /**
     * 点击量++(暂时完结） (未测试)
     * TODO 再想想用List做 而不是Map
     * 测试的时候注意一下 key 和 value
     * 这里的日志 写在 Controller
     */
    @Override
    public Boolean updateSightsViaHits(Long sightsId) {
        // 检测是否存在此缓存
        Map<String, Integer> map = redisCache.getCacheMap(Constants.SIGHTS_HITS);
        int size = map.size();
        if (size == 0){
            //如果没有就创建 并把此记录添加上
            Map<String,Integer> newMap = new HashMap<>();
            newMap.put(Convert.toStr(sightsId),1);
            redisCache.setCacheMap(Constants.SIGHTS_HITS,newMap);
            return true;
        }
        //如果有 获得此 K 的V
        // 如果没有这个V 就加入这个K
        // 如果有这个V 那么就让V + 1
        map.merge(Convert.toStr(sightsId), 1, Integer::sum);
        // 刷新这个记录
        redisCache.setCacheMap(Constants.SIGHTS_HITS,map);
        return true;
/*
        List<Long> list = redisCache.getCacheList(Constants.SIGHTS_HITS);
        if ( list == null){
                List<Long> newList = new LinkedList<>();
                list.add(sightsId);
            return redisCache.setCacheList(Constants.SIGHTS_HITS, newList);
        }
        list.add(sightsId);
        return redisCache.setCacheList(Constants.SIGHTS_HITS, list);
*/
    }

    /**
     * 定时任务 (暂时完结) (未测试)
     * TODO 后期需要改进
     */
    @Transactional
    public void OpenHitViewLikeByQuartz(){
        // 对于 Hits
        Map<String, Integer> hitMap = redisCache.getCacheMap(Constants.SIGHTS_HITS);
        hitMap.forEach((x,y)-> sightsBaseMapper.updateSightsHitBySightsIds(Convert.toLong(x),y));
        redisCache.deleteObject(Constants.SIGHTS_HITS);

        // 对于 View
        Map<String,Integer> viewMap = redisCache.getCacheMap(Constants.SIGHTS_VIEW);
        viewMap.forEach((x,y)-> sightsBaseMapper.updateSightsViewBySightsIds(Convert.toLong(x),y));
        redisCache.deleteObject(Constants.SIGHTS_VIEW);

        // 对于 Like
        List<String> List = redisCache.getCacheList(Constants.SIGHTS_LIKE);
        // 用户名|景点ID|时间
        List.forEach(item -> {
            String[] split = item.split(Constants.SPLIT_LINE);
            long userId = Long.parseLong(split[0]);
            long sights_id = Long.parseLong(split[1]);
            SightsRecordLike recordLike = new SightsRecordLike();
            Date date = new Date(split[2]);
            recordLike.setCreateTime(date);
            recordLike.setSightsId(sights_id);
            recordLike.setUserId(userId);
            //加入关联表
            sightsRecordLikeMapper.insertUserSightsLike(recordLike);
            //原数据库中点赞++
            sightsRecordLikeMapper.updateSightsViaLike(sights_id);
        });

        // 对于历史浏览记录

        // 对于景点收藏

        redisCache.deleteObject(Constants.SIGHTS_LIKE);
        redisCache.deleteObject(Constants.SIGHTS_VIEW);
        redisCache.deleteObject(Constants.SIGHTS_HITS);
    }

    /**
     * 大数据可视化 景点排行
     * @return
     */
    @Override
    public List<SightsStatisticTopDTO> getStatisticSightsTop() {
        List<SightsStatisticTopDTO> statisticTopDTOS = new ArrayList<>();
        List<SightsBase> sights = sightsBaseMapper.getTopSights();
        sights.stream().forEach(item->{
            SightsStatisticTopDTO dto = new SightsStatisticTopDTO();
            BeanUtils.copyBeanProp(dto,item);
            statisticTopDTOS.add(dto);
        });
        return statisticTopDTOS;
    }

    /**
     * position 0 是景点详细
     * position 1 是公告
     * position 2 是景点介绍
     * @param id 景点id
     * @param position 0 1 2
     * @param audioId id
     * @return
     */
    @Override
    public SightsVoiceDTO transReturn(Long id, Integer position, Long audioId) {
        SysAudio audio = audioService.selectSysAudioByAudioId(audioId);
        SightsVoiceDTO voiceDTO = new SightsVoiceDTO();
        if (position == 0 || position == 2){
            SightsBase base = sightsBaseMapper.selectSightsBaseBySightsId(id);
            if (position == 0){
                String content = base.getSightsDetail().replaceAll(pattern, "");
                String result = getTranslateResult(content, null, audio.getBaiduLabel());
                voiceDTO.setSightsDetailOUT(result);
            }else {
                String intro = base.getSightsIntro().replaceAll(pattern, "");
                String result = getTranslateResult(intro, null, audio.getBaiduLabel());
                voiceDTO.setSightsIntroOUT(result);
            }
        }else{
            SightsBulletin bulletin = new SightsBulletin();
            bulletin.setSightsId(id);
            bulletin.setStatus("0");
            bulletin.setTopFlag("Y");
            bulletin.setDelFlag("N");
            List<SightsBulletin> bulletins = bulletinMapper.selectSightsBulletinList(bulletin);
            SightsBulletin sightsBulletin = bulletins.get(0);
            String content = sightsBulletin.getBulletinContent();
            String result = content.replaceAll(pattern, "");
            String s = getTranslateResult(result, null, audio.getBaiduLabel());
            voiceDTO.setBulletContentOUT(s);
        }
        voiceDTO.setSpeakTTS(audio.getSpeakLabel());
        voiceDTO.setSightsId(id);
        return voiceDTO;
    }

    /**
     * 点击排行榜 ( 定时多久刷新一次 )
     * @param num
     */
    @Override
    public List<SightsBase> selectSightsTopViaHit(int num) {
        return sightsBaseMapper.selectSightsTopViaHit(num);
    }

    /**
     * 浏览量++  (暂时完结) (未测试)
     * TODO 再想想用List做 而不是Map  在controller中写日志
     */
    @Override
    public Boolean updateSightsViaView(Long sightsId) {
        Map<String, Integer> map = redisCache.getCacheMap(Constants.SIGHTS_VIEW);
        if (map.size() ==0 ){
            Map<String,Integer> newMap = new HashMap<>();
            newMap.put(Convert.toStr(sightsId),1);
            redisCache.setCacheMap(Constants.SIGHTS_VIEW,newMap);
            return true;
        }
        map.merge(Convert.toStr(sightsId), 1, Integer::sum);
        redisCache.setCacheMap(Constants.SIGHTS_VIEW,map);
        return true;
        /*
        List<Object> list = redisCache.getCacheList(Constants.SIGHTS_VIEW);
        if (list == null){
            List<Long> newList = new LinkedList<>();
            newList.add(sightsId);
            return redisCache.setCacheList(Constants.SIGHTS_VIEW, newList);
        }
        list.add(sightsId);
        return redisCache.setCacheList(Constants.SIGHTS_VIEW,list);

         */
    }

    /**
     * 浏览排行榜  ( 定时多久刷新一次 )
     * @param num
     */
    @Override
    public List<SightsBase> selectSightsTopViaView(int num) {
        return sightsBaseMapper.selectSightsTopViaView(num);
    }


    /**
     * 下面是推荐部分
     * 难点
     */

    @Override
    public List<SightsRecommendDTO> getRecommendSights(Long userId) {
        List<SightsBase> sights = sightsBaseMapper.getRecommendSights(userId);
        return sights.stream().map(item->{
            SightsRecommendDTO recommendDTO = new SightsRecommendDTO();
            BeanUtils.copyBeanProp(recommendDTO,item);
            return recommendDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SightsRecommendDTO> getHistoryHotSights() {
        List<SightsBase> sights = sightsBaseMapper.getHistoryHotSights();
        return sights.stream().map(item->{
            SightsRecommendDTO recommendDTO = new SightsRecommendDTO();
            BeanUtils.copyBeanProp(recommendDTO,item);
            return recommendDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SightsRecommendDTO> getRecentHotSights() {
        List<SightsBase> hotSights = sightsBaseMapper.getRecentHotSights();
        return hotSights.stream().map(item->{
            SightsRecommendDTO recommendDTO = new SightsRecommendDTO();
            BeanUtils.copyBeanProp(recommendDTO,item);
            return recommendDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SightsRecommendDTO> getGoodSights() {
        List<SightsBase> goodSights = sightsBaseMapper.getGoodSights();
        return goodSights.stream().map(item->{
            SightsRecommendDTO recommendDTO = new SightsRecommendDTO();
            BeanUtils.copyBeanProp(recommendDTO,item);
            return recommendDTO;
        }).collect(Collectors.toList());
    }



    /*
      下面是收藏景点部分
     */

    /**
     * 添加景点收藏 (未测试)
     * return  true = 收藏    false = 取消收藏
     */
    @Override
    public boolean changeSightsCollection(SightsUserCollect sightsUserCollect){
        List<SightsUserCollect> list = redisCache.getCacheList(Constants.SIGHTS_COLLECTION);
        // 如果有缓存
        if (list.size()>0){
            for (SightsUserCollect collect : list) {
                if (collect.getSightsId().equals(sightsUserCollect.getSightsId())
                        &&collect.getUserId().equals(sightsUserCollect.getUserId())){
                    // 重复收藏 == 取消收藏
                    list.remove(collect);
                    // 修正
                    redisCache.setCacheList(Constants.SIGHTS_COLLECTION,list);
                    return false;
                }
            }
            // 检查是否收藏
            int collect = sightsUserCollectMapper.judgeCollect(sightsUserCollect);
            if (collect==1){
                // 如果数据库中有数据
                // 取消收藏
                cancelSightsCollection(sightsUserCollect);
                return true;
            }
            // 到这里 说明 数据库中没有收藏
            // 到这里 说明 缓存中没有
            list.add(sightsUserCollect);
            redisCache.setCacheList(Constants.SIGHTS_COLLECTION,list);
            return true;
        }
        // 检测数据库
        int collect = sightsUserCollectMapper.judgeCollect(sightsUserCollect);
        if (collect==1){
            // 没有缓存 数据库中有
            // 取消收藏
            cancelSightsCollection(sightsUserCollect);
            return false;
        }
        // 到这里 说明 无 缓存 且 数据库中没有  就加入缓存
        List<SightsUserCollect> newList = new LinkedList<>();
        newList.add(sightsUserCollect);
        redisCache.setCacheList(Constants.SIGHTS_COLLECTION,newList);
        return true;
    }

    /**
     * 取消景点收藏
     */
    @Override
    public boolean cancelSightsCollection(SightsUserCollect sightsUserCollect) {
        int i = sightsUserCollectMapper.cancelSightsCollection(sightsUserCollect);
        return i == 1;
    }

    /**
     * 查询某用户所有景点收藏 (未测试)
     */
    @Override
    public List<SightsBase> selectCollectSightsRecord(Long userId) {
        String key = Constants.SIGHTS_USER + Convert.toStr(userId);
        // 如果缓存中有数据 那么就直接把数据返回了 并重制时间
        List<SightsBase> cacheList = redisCache.getCacheList(key);
        if (cacheList.size()>0){
            redisCache.expire(key,30);
            return cacheList;
        }

        List<SightsUserCollect> list = redisCache.getCacheList(Constants.SIGHTS_COLLECTION);
        List<SightsUserCollect> newList = new LinkedList<>();
        if (list.size()>0){
            for (SightsUserCollect userCollect : list) {
                if (userCollect.getUserId().equals(userId)){
                    newList.add(userCollect);
                    list.remove(userCollect);
                    // 将数据移除
                }
            }
            if (newList.size()>0){
                // 修正
                redisCache.setCacheList(Constants.SIGHTS_COLLECTION,list);
                //批量新增
                sightsUserCollectMapper.insertCollectByBatches(newList);
            }
        }
        // 为了 提高 分页性能 将存到cache中 设置时间 30 s
        List<SightsBase> sightsBases = sightsUserCollectMapper.selectCollectSightsRecord(userId);
        redisCache.setCacheList(key,sightsBases);
        redisCache.expire(key,30);
        return sightsBases;
    }

    /**
     * 用户历史浏览记录 (未测试)
     */
    @Override
    public List<SightsBase> selectHistoryRecordByUserId(Long userId) {
        String key = Constants.SIGHTS_HISTORY_USER + Convert.toStr(userId);
        List<SightsBase> cacheList = redisCache.getCacheList(key);
        // 如果 缓存中已经有了数据了 就直接返回
        if (cacheList.size()>0){
            // 重制时间
            redisCache.expire(key,30);
            return cacheList;
        }
        List<SightsRecordHistory> list = redisCache.getCacheList(Constants.SIGHTS_VIEW_RECORD);
        List<SightsRecordHistory> newList = new LinkedList<>();
        if (list.size()>0){
            for (SightsRecordHistory recordHistory : list) {
                if (recordHistory.getUserId().equals(userId)){
                    newList.add(recordHistory);
                    list.remove(recordHistory);
                    // 将数据从缓存中移除
                }
            }
            if (newList.size()>0){
                // 修正
                redisCache.setCacheList(Constants.SIGHTS_VIEW_RECORD,list);
                // 批量添加
                sightsBaseMapper.insertSightsHistoryByBatches(newList);
            }
        }
        // 提高分页性能 将存到Cache 设置时间为30s
        List<SightsBase> sightsBases = sightsBaseMapper.getHistoryRecord(userId);
        redisCache.setCacheList(key,sightsBases);
        redisCache.expire(key,30);
        return sightsBases;
    }
    /**
     * 增加用户浏览景点历史记录  (未测试)
     */
    @Override
    public boolean addSightsHistoryRecord(SightsRecordHistory recordHistory) {
        List<SightsRecordHistory> list = redisCache.getCacheList(Constants.SIGHTS_VIEW_RECORD);
        // 如果存在缓存
        if (list.size()>0) {
            for (SightsRecordHistory history : list) {
                if (history.getSightsId().equals(recordHistory.getSightsId())
                        && history.getUserId().equals(recordHistory.getUserId())){
                    // 修改历史记录
                    history.setCreateTime(recordHistory.getCreateTime());
                    // 修正
                    redisCache.setCacheList(Constants.SIGHTS_VIEW_RECORD,list);
                    return true;
                }
            }
            // 到这里 说明 缓存中没有历史记录
            int historyOnlyOne = sightsBaseMapper.judgeSightsHistoryOnlyOne(recordHistory);
            // 如果数据库中存在数据
            if (historyOnlyOne > 0){
                // 更新数据
                sightsBaseMapper.updateSightsHistory(recordHistory);
                return true;
            }
            // 到这里说明数据库 和 缓存 中都没有数据
            list.add(recordHistory);
            redisCache.setCacheList(Constants.SIGHTS_VIEW_RECORD,list);
            return true;
        }
        // 不存在缓存
        // 数据库中有数据
        if (sightsBaseMapper.judgeSightsHistoryOnlyOne(recordHistory)>0){
            sightsBaseMapper.updateSightsHistory(recordHistory);
            return true;
        }
        // 到这里说明不存在缓存
        List<SightsRecordHistory> newList = new LinkedList<>();
        newList.add(recordHistory);
        redisCache.setCacheList(Constants.SIGHTS_VIEW_RECORD,newList);
        return true;

    }

}
