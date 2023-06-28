package com.ruoyi.sights.domain.Excel;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.ExcelConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.excel.domain.excelRecord;
import com.ruoyi.excel.mapper.excelRecordMapper;
import com.ruoyi.excel.service.impl.ExcelServiceImpl;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author chas
 * @introduction 景点用户行为数据统计 存入excel
 * @data 2023-3
 */
@Data
public class SightsUserBehavior implements Serializable {

    private static final String excelName = "sightsUser";
    private static final String SUKEY = "SIGHTSUERSEXCEL";

    @Excel(name ="用户id")
    private Long userId;
    @Excel(name="景点id")
    private Long sightsId;
    @Excel(defaultValue="0",name="点赞")
    private Long sightsLike;
    @Excel(defaultValue="0",name="点击")
    private Long sightsHits;
    @Excel(defaultValue="0",name="浏览")
    private Long sightsView;
    @Excel(defaultValue="0",name ="收藏")
    private Long sightsCollect;
    @Excel(defaultValue="0",name = "评分")
    private Double sightsScore;
    @Excel(name = "时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 将信息放入redis
     * 操作随意 但是同一数据每日只能记录一次 保证了 用户 构造虚假数据
     *
     * 用set更好哦
     *
     * @param behavior
     */
    public static void storeSightsUserDataInRedis(SightsUserBehavior behavior){
        SpringUtils.getBean(RedisCache.class).lock(SUKEY);
        List<SightsUserBehavior> cacheList = SpringUtils.getBean(RedisCache.class).getCacheList(SUKEY);
        // 如果列表中存在数据 则结束
        while(cacheList.iterator().hasNext()){
            SightsUserBehavior next = cacheList.iterator().next();
            if (behavior.equals(next)){
                return;
            }
        }
        // 直接向list 里面放数据啦
        SpringUtils.getBean(RedisCache.class).lPush(SUKEY,behavior);
//        cacheList.add(behavior);
//        // 删除redis中key数据
//        SpringUtils.getBean(RedisCache.class).deleteObject(SUKEY);
//        // 重新设置
//        SpringUtils.getBean(RedisCache.class).setCacheList(SUKEY,cacheList);
    }

    /**
     * 将redis上的信息存入excel
     */
    public static void redisToExcelSightsUser(){
        List<SightsUserBehavior> cacheList = SpringUtils.getBean(RedisCache.class).getCacheList(SUKEY);
        ExcelUtil<SightsUserBehavior> behaviorExcelUtil = new ExcelUtil<>(SightsUserBehavior.class);
        behaviorExcelUtil.init(cacheList, ExcelConstants.SIGHTS_EXCEL,null, Excel.Type.EXPORT);
        String fileName = behaviorExcelUtil.exportExcelData();
        excelRecord record = new excelRecord();
        record.setExcelName(RuoYiConfig.getDownloadPath()+fileName);
        record.setSheetName(ExcelConstants.SIGHTS_EXCEL);
        record.setType("0");
        record.setCreateTime(DateUtils.getNowDate());
        System.out.println(record);
        int i = SpringUtils.getBean(excelRecordMapper.class).insertExcel(record);
        System.out.println(i+"条记录插入成功");
        SpringUtils.getBean(RedisCache.class).deleteObject(SUKEY);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SightsUserBehavior)) return false;
        SightsUserBehavior that = (SightsUserBehavior) o;
        return userId.equals(that.userId) && sightsId.equals(that.sightsId)
                && Objects.equals(sightsLike, that.sightsLike) && Objects.equals(sightsHits, that.sightsHits)
                && Objects.equals(sightsView, that.sightsView) && Objects.equals(sightsCollect, that.sightsCollect)
                && Objects.equals(sightsScore, that.sightsScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, sightsId, sightsLike, sightsHits, sightsView, sightsCollect, sightsScore);
    }
}
