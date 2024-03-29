package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysActivity;
import com.ruoyi.system.domain.domainVo.ActivityDetailDTO;
import com.ruoyi.system.domain.domainVo.ActivityListDTO;
import com.ruoyi.system.domain.domainVo.ActivityPersonDTO;

/**
 * 活动Service接口
 * 
 * @author ruoyi
 * @date 2023-04-10
 */
public interface ISysActivityService 
{
    /**
     * 查询活动
     * 
     * @param activityId 活动主键
     * @return 活动
     */
    public SysActivity selectSysActivityByActivityId(Long activityId);

    /**
     * 查询活动列表
     * 
     * @param sysActivity 活动
     * @return 活动集合
     */
    public List<SysActivity> selectSysActivityList(SysActivity sysActivity);

    /**
     * 新增活动
     * 
     * @param sysActivity 活动
     * @return 结果
     */
    public int insertSysActivity(SysActivity sysActivity);

    /**
     * 修改活动
     * 
     * @param sysActivity 活动
     * @return 结果
     */
    public int updateSysActivity(SysActivity sysActivity);

    /**
     * 批量删除活动
     * 
     * @param activityIds 需要删除的活动主键集合
     * @return 结果
     */
    public int deleteSysActivityByActivityIds(Long[] activityIds);

    /**
     * 删除活动信息
     * 
     * @param activityId 活动主键
     * @return 结果
     */
    public int deleteSysActivityByActivityId(Long activityId);

    /**
     * 首部轮播活动
     */
    public List<ActivityPersonDTO> getPersonSwiper();

    /**
     * 活动列表
     * @return
     */
    public List<ActivityListDTO> getActList();

    /**
     * 分页获取活动列表
     * @return
     */
    public List<SysActivity> getAllList();

    /**
     * 定时任务 提高热度
     */
    public void addHot();

    /**
     * 定时任务 减少活动热度
     */
    public void reduceHot();

    public ActivityDetailDTO getDetail(Long activityId);
}
