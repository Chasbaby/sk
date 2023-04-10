package com.ruoyi.sights.domain.Excel;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.apache.poi.sl.usermodel.Sheet;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author chas
 * @introduction 景点用户行为数据统计 存入excel
 * @data 2023-3
 */
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
        cacheList.add(behavior);
        // 删除redis中key数据
        SpringUtils.getBean(RedisCache.class).deleteObject(SUKEY);
        // 重新设置
        SpringUtils.getBean(RedisCache.class).setCacheList(SUKEY,cacheList);

//        List<SightsUserBehavior> cacheList1 = SpringUtils.getBean(RedisCache.class).getCacheList(SUKEY);
    }

    /**
     * 将redis上的信息存入excel
     */
    public static void redisToExcelSightsUser(){
        List<SightsUserBehavior> cacheList = SpringUtils.getBean(RedisCache.class).getCacheList(SUKEY);
        ExcelUtil<SightsUserBehavior> behaviorExcelUtil = new ExcelUtil<>(SightsUserBehavior.class);
        behaviorExcelUtil.init(cacheList,"sightsUserData",null, Excel.Type.EXPORT);
        behaviorExcelUtil.exportExcel();
        SpringUtils.getBean(RedisCache.class).deleteObject(SUKEY);
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
