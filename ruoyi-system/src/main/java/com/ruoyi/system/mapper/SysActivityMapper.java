package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysActivity;

/**
 * 活动Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-10
 */
public interface SysActivityMapper 
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
     * 删除活动
     * 
     * @param activityId 活动主键
     * @return 结果
     */
    public int deleteSysActivityByActivityId(Long activityId);

    /**
     * 批量删除活动
     * 
     * @param activityIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysActivityByActivityIds(Long[] activityIds);

    public List<SysActivity> getPersonSwiper();
}
