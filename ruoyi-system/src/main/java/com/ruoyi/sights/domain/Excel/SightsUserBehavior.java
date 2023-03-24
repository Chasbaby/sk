package com.ruoyi.sights.domain.Excel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chas
 * @introduction 景点用户行为数据统计 存入excel
 * @data 2023-3
 */
public class SightsUserBehavior implements Serializable {

    @Autowired
    private RedisCache redisCache;

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
     * @param behavior
     */
    public  void storeSightsUserDataInRedis(SightsUserBehavior behavior){
        redisCache.lock(SUKEY);
        List<SightsUserBehavior> cacheList = redisCache.getCacheList(SUKEY);
        cacheList.add(behavior);
        redisCache.setCacheList(SUKEY,cacheList);
    }

    /**
     * 将redis上的信息存入excel
     */
    public void redisToExcelSightsUser(){
        List<SightsUserBehavior> cacheList = redisCache.getCacheList(SUKEY);
        ExcelUtil<SightsUserBehavior> behaviorExcelUtil = new ExcelUtil<>(SightsUserBehavior.class);
        behaviorExcelUtil.init(cacheList,"景点用户数据",null, Excel.Type.EXPORT);
        behaviorExcelUtil.exportExcel();
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSightsId() {
        return sightsId;
    }

    public void setSightsId(Long sightsId) {
        this.sightsId = sightsId;
    }

    public Long getSightsLike() {
        return sightsLike;
    }

    public void setSightsLike(Long sightsLike) {
        this.sightsLike = sightsLike;
    }

    public Long getSightsHits() {
        return sightsHits;
    }

    public void setSightsHits(Long sightsHits) {
        this.sightsHits = sightsHits;
    }

    public Long getSightsView() {
        return sightsView;
    }

    public void setSightsView(Long sightsView) {
        this.sightsView = sightsView;
    }

    public Long getSightsCollect() {
        return sightsCollect;
    }

    public void setSightsCollect(Long sightsCollect) {
        this.sightsCollect = sightsCollect;
    }

    public Double getSightsScore() {
        return sightsScore;
    }

    public void setSightsScore(Double sightsScore) {
        this.sightsScore = sightsScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
