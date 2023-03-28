package com.ruoyi.sights.service.impl;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.sights.domain.DTO.SightsHotDTO;
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
     *  TODO 只有最强的10大景点才拥有 长时间的timeout 其他景点的timeout较低 节省空间
     */
    @PostConstruct
    @Override
    public void InitSights() {
        List<SightsBase> sightsBases = baseMapper.initSights();
        sightsBases.stream().forEach(item->{

            item.setLastUpdated(new Date());
            // 启动时赠送 100 热度  防止热度过低
            item.setSightsHot(item.getSightsHot() + 100);
            // 24 小时 过期时间  为 生产生活
            // redisCache.setCacheObject(HOTLABLE+item.getSightsId(),item,MAXTIME, TimeUnit.SECONDS);
            // 10分钟 测试中
            redisCache.setCacheObject(HOTLABLE+item.getSightsId(),item,60 * 60,TimeUnit.SECONDS);

            // 存在定时任务监测
        });

        Collection<String> keys = redisCache.keys(HOTLABLE + "*");
        keys.stream().forEach(item-> System.out.println(item));
        log.info("初始化景点热度成功");
    }

    /**
     * 当项目结束时触发，将之前在缓存中的热度景点存到数据库中，以便再用
     *
     * 关闭项目了 性能无所谓了
     */
    @PreDestroy
    @Override
    public void destroySights() {
        Collection<String> keys = redisCache.keys(HOTLABLE + "*");
        keys.stream().forEach(item->{
            SightsBase sight= redisCache.getCacheObject(item);
            baseMapper.updateSightsBase(sight);
        });
        // 更新完后 消除记录 避免内存占用
        redisCache.deleteObject(keys);

        log.info("景点回溯成功");
    }

    /**
     * 检查热门中的景点是否过期
     * 如果景点过期，则将其 更新回数据库
     * 一定程度上防止了 独景点称霸
     */
    @Override
    public void checkIfTimeout() {
        // TODO 当有非redis上的景点被操作时 将其从数据库中取出，处理 ，并将其加入redis热度中但是此时
        //  要比较 如果比前十的其中一个热度高 说明这个景点已经长时间没有被访问了，那么将该景点过期值降低，
        //  将新的景点 的过期值设置为  max 时常  这里已经通过配置完成

    }
    /**
     * 定时任务 将redis中的热门数据更新回redis 保证数据的一致性
     */
    @Override
    public void redisReturnMysql() {

    }

    @Override
    public void redisToExcel() {
        redisToExcelSightsUser();
    }

    /**
     * 增加浏览量
     *      TODO  1. 从redis中获取信息 如果没有 则sql查询
     *            2. 如果是 redis 中的信息修改数据后 重置  如果是sql的
     *               获取所有缓存，将查到的与 缓存中的热度进行比较  (为了编程方便 我们只拿最后一个比)
     *               如果 新的 大于他们 则 将timeout设置为MAX 并将另一个设置为 MIN
     *               如果 新的 小于等于他们 就暂时MIN缓存该数据
     *            3.  将该信息存入excel
     *            4.  保存数据库
     *
     * @param sightsId
     * @param userId
     */
    @Transactional
    @Override
    public void addView(Long sightsId, Long userId) {
        // 1.
        if(redisCache.hasKey(HOTLABLE + sightsId) && !redisCache.isExpire(HOTLABLE + sightsId)){
            SightsBase base = redisCache.getCacheObject(HOTLABLE + sightsId);
            base.addView();
            redisCache.setCacheObject(HOTLABLE + sightsId,base,60 * 60,TimeUnit.SECONDS);// TODO 记得改时间
        }else {
            //2. 获取数据开始 热度
            SightsBase sightsBase = baseMapper.selectSightsBaseBySightsId(sightsId);
            sightsBase.addView();
//            sightsBase.startTimer();
            comHot(sightsBase);
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
     * @return
     */
    private List<SightsBase> getTopHotSights() {
        // 获取热度榜
        Collection<String> keys = redisCache.keys(HOTLABLE+"*");
        List<SightsBase> buff = new ArrayList<>();
        keys.stream().forEach(item->{
            SightsBase cacheObject = redisCache.getCacheObject(item);
            buff.add(cacheObject);
        });
        buff.sort(Comparator.comparingLong(SightsBase::getSightsHot).reversed());
        return buff;
    }


    /***
     * 判断是否点赞 并进入不同的效果
     * @param sightsId
     * @param userId
     */
    @Override
    public void ifLike(Long sightsId,Long userId){
        // 查表
    }

    /**
     * 增加点赞
     * @param sightsId
     * @param userId
     */
    @Transactional
    @Override
    public void addLike(Long sightsId, Long userId) {
        if(redisCache.hasKey(HOTLABLE + sightsId) && !redisCache.isExpire(HOTLABLE + sightsId)){
            SightsBase base = redisCache.getCacheObject(HOTLABLE + sightsId);
            base.addLike();
            redisCache.setCacheObject(HOTLABLE + sightsId,base,10,TimeUnit.SECONDS);// TODO 记得改时间
        }else {
            //2. 开启热度
            SightsBase sightsBase = baseMapper.selectSightsBaseBySightsId(sightsId);
            sightsBase.addLike();
//            sightsBase.startTimer();

            comHot(sightsBase);
        }

        // 3. 创建Excel
        SightsUserBehavior userBehavior = new SightsUserBehavior();
        userBehavior.setSightsLike(1L);
        userBehavior.setUserId(userId);
        userBehavior.setSightsId(sightsId);
        userBehavior.setCreateTime(new Date());

        // 将信息保存至
        storeSightsUserDataInRedis(userBehavior);

        // todo 保存数据库 先省

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


        // 如果在 缓存中 则直接在缓存中修改
        if(redisCache.hasKey(HOTLABLE + sightsId) && !redisCache.isExpire(HOTLABLE + sightsId)){
            SightsBase base = redisCache.getCacheObject(HOTLABLE + sightsId);
            base.setSightsLike(base.getSightsLike()-1);
            List<SightsBase> hotSights = getTopHotSights();
            SightsBase minHot = hotSights.get(9);

            if (minHot.getSightsHot()>=base.getSightsHot()){
                redisCache.setCacheObject(HOTLABLE + sightsId,base,Integer.parseInt(MINTIME),TimeUnit.SECONDS);
            }else {
                redisCache.setCacheObject(HOTLABLE + sightsId,base,10,TimeUnit.SECONDS);//TODO 记得改
            }

        }else {
            // 如果在数据库中 则直接改


        }
    }

    /**
     * 增加得分
     * @param sightsId
     * @param score
     * @param userId
     */
    @Override
    public void score(Long sightsId, Double score, Long userId) {

    }

    /**
     * 增加收藏
     * @param sightsId
     * @param userId
     */
    @Override
    public void addCollect(Long sightsId, Long userId) {

    }

    /**
     * 取消收藏
     * @param sightsId
     * @param userId
     */
    @Override
    public void cancelCollect(Long sightsId, Long userId) {

    }

    /**
     * 增加点击
     * @param sightsId
     * @param userId
     */
    @Override
    public void addHit(Long sightsId, Long userId) {

    }

    /**
     * 返回当前热度最高的一定量景点
     * @return
     */
    @Override
    public List<SightsHotDTO> currentHotSights() {
        List<SightsHotDTO> hotDTOS = new ArrayList<>();
        List<SightsBase> topHotSights = getTopHotSights();
        topHotSights.stream().forEach(item->{
            System.out.println(item.getSightsId());
        });
        topHotSights.stream().limit(10).forEach(item->{
            SightsHotDTO hotDTO = new SightsHotDTO();
            BeanUtils.copyBeanProp(hotDTO,item);
            hotDTOS.add(hotDTO);
        });
        return hotDTOS;
    }


}
