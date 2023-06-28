package com.ruoyi.sights.service.impl;

import com.ruoyi.common.annotation.RateLimiter;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.LimitType;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.sights.domain.DTO.SightsHotDTO;
import com.ruoyi.sights.domain.DTO.SightsRandomDTO;
import com.ruoyi.sights.domain.Excel.SightsUserBehavior;
import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.sights.mapper.SightsBaseMapper;
import com.ruoyi.sights.mapper.SightsRecordMapper;
import com.ruoyi.sights.service.ISightsHotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.ruoyi.common.constant.HotConstants.HOTLABLE;
import static com.ruoyi.common.constant.HotConstants.MINTIME;
import static com.ruoyi.sights.domain.Excel.SightsUserBehavior.redisToExcelSightsUser;
import static com.ruoyi.sights.domain.Excel.SightsUserBehavior.storeSightsUserDataInRedis;

/**
 * @author chas
 * @introduction 景点热度算法的实现类
 * @data 2023-3
 */
@Service
public class SightsHotServiceImpl implements ISightsHotService {
    private static final Logger log = LoggerFactory.getLogger(SightsHotServiceImpl.class);

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SightsBaseMapper baseMapper;

    @Autowired
    private SightsRecordMapper recordMapper;

    /**
     * 项目建立时触发，从数据库中取出热度最高的一定量景点
     *
     *  TODO 从数据库中拿出热度最强的景点 缓存数据库 不用过期时间了
     */
    @PostConstruct
    @Override
    public void InitSights() {
        List<SightsBase> sightsBases = baseMapper.initSights();
        sightsBases.forEach(item->{
            item.setLastUpdated(new Date());
            // 启动时赠送 100 热度  防止热度过低
            item.setSightsHot(item.getSightsHot() + 100);
            // 将这些景点放入缓存
            redisCache.setCacheObject(HOTLABLE+item.getSightsId(),item);
        });
        log.info("初始化景点热度成功");
    }

    /**
     * 当项目结束时触发，将之前在缓存中的热度景点存到数据库中，以便再用
     * 关闭项目了 性能无所谓了
     */
    @PreDestroy
    @Override
    public void destroySights() {
        Collection<String> keys = redisCache.keys(HOTLABLE + "*");
        keys.forEach(item->{
            SightsBase sight= redisCache.getCacheObject(item);
            baseMapper.updateSightsBase(sight);
        });
        // 更新完后 消除记录 避免内存占用
        redisCache.deleteObject(keys);
        log.info("景点回溯成功");
    }

    /**
     *   更热度  在定时任务中调用
     */
    @Override
    public void updateHot() {
        // TODO 当有非redis上的景点被操作时 将其从数据库中取出，并将其加入redis热度中
        //  我们规定只有redis上的数据热度才会变化
        //  更新热度 :  乐观锁 开启事务 获取所有热度景点 并删除redis上的所有景点
        //  将所有景点热度减少 并更新回数据库

        Collection<String> keys = redisCache.keys(HOTLABLE + "*");
        redisCache.lockManyKey(keys);  //乐观锁
        keys.forEach(item->{
            SightsBase sight= redisCache.getCacheObject(item);  // 从缓存中获取数据
            sight.setSightsHot(new Double(sight.getSightsHot() * 0.9).longValue()); // 修改热度
//            sight.setSightsHits(null);  // 防止修改数据 此类数据以数据库中为准 防止 数据不一致
//            sight.setSightsLike(null);
//            sight.setSightsScore(null);
//            sight.setSightsView(null);
//            sight.setSightsCollect(null);
            baseMapper.updateSightsBase(sight); // 更新
            redisCache.deleteObject(HOTLABLE + sight.getSightsId());// 删除缓存
        });
        // 所有景点删除缓存后，从数据库中继续拿取数据
        InitSights();
    }
//    /**
//     * 定时任务 将redis中的热门数据更新回redis 保证数据的一致性
//     */
//    @Override
//    public void redisReturnMysql() {
//
//    }

    @Override
    public void redisToExcel() {
        redisToExcelSightsUser();
    }


    /**
     * 增加浏览量
     *      TODO  1. 从redis中获取信息 如果没有 则sql查询
     *            2. 如果是 redis 中的信息修改数据后 重置
     *            3. 将该信息存入excel
     *            4. 保存数据库
     *            5. 优化方法 -> 将此数据存放 kafka 中 异步
     *
     * @param sightsId
     * @param userId
     */
//    @RateLimiter( time = 30 ,limitType = LimitType.IP)
    @Transactional
    @Override
    public void addView(Long sightsId, Long userId) {
        // 1. 若缓存中有 且 没有过期 直接将缓存中的数据 + 1    && !redisCache.isExpire(HOTLABLE + sightsId)
        if(redisCache.hasKey(HOTLABLE + sightsId)){
            redisCache.lock(HOTLABLE + sightsId);
            SightsBase base = redisCache.getCacheObject(HOTLABLE + sightsId);
            base.addView(); // 提高热度
            redisCache.setCacheObject(HOTLABLE + sightsId,base);
        }else {
            //2. 若 缓存中没有 则重数据库中获取  并加入缓存
            SightsBase sightsBase = baseMapper.selectSightsBaseBySightsId(sightsId);
            sightsBase.addView(); // 提高热度
            redisCache.setCacheObject(HOTLABLE + sightsBase.getSightsId(),sightsBase);
        }
        // 3.创建 Excel 存储类
        SightsUserBehavior userBehavior = new SightsUserBehavior();
        userBehavior.setSightsView(1L);
        userBehavior.setUserId(userId);
        userBehavior.setSightsId(sightsId);
        userBehavior.setCreateTime(new Date());
        // 将信息保存至
        storeSightsUserDataInRedis(userBehavior);
        // 添加数据库
        recordMapper.addView(userId,sightsId);
    }

    /**
     *  热度比较
     * @param sightsBase
     */
    private void comHot(SightsBase sightsBase) {
        List<SightsBase> buff = getTopHotSights();
        // 获取其中热度第10位
        SightsBase minHot = buff.get(9);

        // 比较
        if (minHot.getSightsHot()>= sightsBase.getSightsHot()){
            // 暂时存储
            redisCache.setCacheObject(HOTLABLE+ sightsBase.getSightsId(), sightsBase,Integer.parseInt(MINTIME),TimeUnit.SECONDS);
        }else {
            redisCache.setCacheObject(HOTLABLE+minHot.getSightsId(),minHot,Integer.parseInt(MINTIME),TimeUnit.SECONDS);
            redisCache.setCacheObject(HOTLABLE+ sightsBase.getSightsId(), sightsBase,60 * 60,TimeUnit.SECONDS);// todo 记得改
        }
    }

    /**
     * 获取热度排榜 并排序 返回排序结果
     */
    private List<SightsBase> getTopHotSights() {
        // 获取热度榜
        Collection<String> keys = redisCache.keys(HOTLABLE+"*");
        List<SightsBase> buff = new ArrayList<>();
        keys.forEach(item->{
            SightsBase cacheObject = redisCache.getCacheObject(item);
            buff.add(cacheObject);
        });
        buff.sort(Comparator.comparingLong(SightsBase::getSightsHot).reversed());
        return buff;
    }


    /***
     * 判断是否点赞
     * @param sightsId
     * @param userId
     */
    @Override
    public int ifLike(Long sightsId,Long userId){
        int i = recordMapper.judgeLike(userId, sightsId);
        if (i == 1){
            cancelLike(sightsId,userId);
        }else {
            // 插入数据库
            recordMapper.addLike(userId,sightsId);
        }
        return i;
    }

    /**
     * 获取景点展示
     * @return
     */
    @Override
    public List<SightsRandomDTO> getRandomSights() {
        List<SightsBase> sights = baseMapper.getRandomSights();
        List<SightsRandomDTO> randomDTOS = new ArrayList<>();
        sights.stream().forEach(item->{
            SightsRandomDTO dto = new SightsRandomDTO();
            BeanUtils.copyBeanProp(dto,item);
            randomDTOS.add(dto);
        });
        return randomDTOS;

    }



    /**
     * 增加点赞
     * @param sightsId
     * @param userId
     */
    @Transactional
    @Override
    public void addLike(Long sightsId, Long userId) {
        //&& !redisCache.isExpire(HOTLABLE + sightsId)
        if(redisCache.hasKey(HOTLABLE + sightsId) ){
            // todo
            //redisCache.lock(HOTLABLE + sightsId);
            SightsBase base = redisCache.getCacheObject(HOTLABLE + sightsId);
            base.addLike();
            redisCache.setCacheObject(HOTLABLE + sightsId,base);
        }else {
            //2. 开启热度
            SightsBase sightsBase = baseMapper.selectSightsBaseBySightsId(sightsId);
            sightsBase.addLike();
            redisCache.setCacheObject(HOTLABLE+sightsBase.getSightsId(),sightsBase);
        }

        // 3. 创建Excel
        SightsUserBehavior userBehavior = new SightsUserBehavior();
        userBehavior.setSightsLike(1L);
        userBehavior.setUserId(userId);
        userBehavior.setSightsId(sightsId);
        userBehavior.setCreateTime(new Date());

        // 将信息保存至
        storeSightsUserDataInRedis(userBehavior);
    }

    /**
     * 取消点赞
     * 不产生热度计算
     * @param sightsId
     * @param userId
     */
    @Transactional
    @Override
    public void cancelLike(Long sightsId, Long userId) {
        // 如果在 缓存中 则直接在缓存中修改 && !redisCache.isExpire(HOTLABLE + sightsId)
        if(redisCache.hasKey(HOTLABLE + sightsId)){
            // todo
            //redisCache.lock(HOTLABLE + sightsId);
            SightsBase base = redisCache.getCacheObject(HOTLABLE + sightsId);
            base.setSightsLike(base.getSightsLike()-1);
            redisCache.setCacheObject(HOTLABLE+sightsId,base);
        }
        // 从数据库中删除
        recordMapper.deleteLike(userId,sightsId);
    }

    /**
     * 是否评分   插入 和 更新
     * @param sightsId
     * @param score
     * @param userId
     * @return
     */
    @Override
    public int ifScore(Long sightsId, Double score, Long userId) {
        int i = recordMapper.judgeScore(userId, sightsId);
        if (i==1){
            // 已经评分 则修改
            recordMapper.updateScore(score,userId,sightsId);
        }else {
            recordMapper.addScore(userId,sightsId,score);
        }

        return i;
    }

    /**
     * 增加得分
     * @param sightsId
     * @param score
     * @param userId
     */
    @Transactional
    @Override
    public void score(Long sightsId, Double score, Long userId) {
        if(redisCache.hasKey(HOTLABLE + sightsId)){
            // todo
            //redisCache.lock(HOTLABLE + sightsId);
            SightsBase base = redisCache.getCacheObject(HOTLABLE + sightsId);
            base.addScore(score);
            redisCache.setCacheObject(HOTLABLE + sightsId,base);
        }else {
            //2. 开启热度
            SightsBase sightsBase = baseMapper.selectSightsBaseBySightsId(sightsId);
            sightsBase.addScore(score);
            redisCache.setCacheObject(HOTLABLE+sightsBase.getSightsId(),sightsBase);
        }

        // 3. 创建Excel
        SightsUserBehavior userBehavior = new SightsUserBehavior();
        userBehavior.setSightsScore(score);
        userBehavior.setUserId(userId);
        userBehavior.setSightsId(sightsId);
        userBehavior.setCreateTime(new Date());

        // 将信息保存至
        storeSightsUserDataInRedis(userBehavior);

    }

    /**
     * 是否收藏
     * @param sightsId
     * @param userId
     * @return
     */
    @Override
    public int ifCollect(Long sightsId, Long userId) {
        int i = recordMapper.judgeCollect(userId, sightsId);

        if (i == 1){
            cancelCollect(sightsId,userId);
        }
        if (i==0){
            recordMapper.addCollect(userId, sightsId);
        }
        return i;
    }

    /**
     * 增加收藏
     * @param sightsId
     * @param userId
     */
    @Transactional
    @Override
    public void addCollect(Long sightsId, Long userId) {
        if(redisCache.hasKey(HOTLABLE + sightsId)){
            // todo
            //redisCache.lock(HOTLABLE + sightsId);
            SightsBase base = redisCache.getCacheObject(HOTLABLE + sightsId);
            base.addCollect();
            redisCache.setCacheObject(HOTLABLE + sightsId,base);
        }else {
            SightsBase sightsBase = baseMapper.selectSightsBaseBySightsId(sightsId);
            sightsBase.addCollect();
            redisCache.setCacheObject(HOTLABLE+sightsBase.getSightsId(),sightsBase);
        }
        // 3. 创建Excel
        SightsUserBehavior userBehavior = new SightsUserBehavior();
        userBehavior.setSightsCollect(1L);
        userBehavior.setUserId(userId);
        userBehavior.setSightsId(sightsId);
        userBehavior.setCreateTime(new Date());
        // 将信息保存至
        storeSightsUserDataInRedis(userBehavior);

    }

    /**
     * 取消收藏
     * @param sightsId
     * @param userId
     */
    @Transactional
    @Override
    public void cancelCollect(Long sightsId, Long userId) {
        if (redisCache.hasKey(HOTLABLE+sightsId)){

            //redisCache.lock(HOTLABLE+sightsId);

            SightsBase base = redisCache.getCacheObject(HOTLABLE + sightsId);
                // 已经通过触发器实现了
//            base.setSightsCollect(base.getSightsCollect()-1);

            redisCache.setCacheObject(HOTLABLE+sightsId,base);

        }
        recordMapper.deleteCollect(userId,sightsId);
    }

    /**
     * 增加点击
     * @param sightsId
     * @param userId
     */
    @Transactional
    @Override
    public void addHit(Long sightsId, Long userId) {
        if (redisCache.hasKey(HOTLABLE+sightsId) ){
            // todo
            //redisCache.lock(HOTLABLE+sightsId);
            SightsBase base = redisCache.getCacheObject(HOTLABLE + sightsId);
            base.addHits();
            redisCache.setCacheObject(HOTLABLE+sightsId,base);
        }else {
            SightsBase base = baseMapper.selectSightsBaseBySightsId(sightsId);
            base.addHits();
            redisCache.setCacheObject(HOTLABLE+base.getSightsId(),base);
        }

        SightsUserBehavior behavior = new SightsUserBehavior();
        behavior.setSightsId(sightsId);
        behavior.setUserId(userId);
        behavior.setSightsHits(1L);
        behavior.setCreateTime(new Date());
        storeSightsUserDataInRedis(behavior);
        recordMapper.addHits(userId,sightsId);
    }

    /**
     * 返回当前热度最高的一定量景点
     * @return
     */
    @Override
    public List<SightsHotDTO> currentHotSights() {
        List<SightsHotDTO> hotDTOS = new ArrayList<>();
        List<SightsBase> topHotSights = getTopHotSights();
        // 如果在缓存中的热度景点数量不足 则从数据库中取出一定的数量
        int size = topHotSights.size();
        if (  size< 10 ){
            int need = 10 - size;
            int i = 0;
            Long[] record = new Long[need];
            // 获取所有已经存在的景点id
            while (topHotSights.iterator().hasNext()) {
                SightsBase next = topHotSights.iterator().next();
                record[i++] = next.getSightsId();
            }
            List<SightsBase> sightsNeedHot = baseMapper.getNeedNumSightsHot(record, need);
            topHotSights.addAll(sightsNeedHot);
        }
        // 提取前十个
        topHotSights.stream().limit(10).forEach(item->{
            SightsHotDTO hotDTO = new SightsHotDTO();
            BeanUtils.copyBeanProp(hotDTO,item);
            hotDTOS.add(hotDTO);
        });
        return hotDTOS;
    }
}
