package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.DTO.UserDTO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.sights.domain.DTO.SightsRandomDTO;
import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.sights.mapper.SightsBaseMapper;
import com.ruoyi.system.domain.domainVo.ActivityDetailDTO;
import com.ruoyi.system.domain.domainVo.ActivityListDTO;
import com.ruoyi.system.domain.domainVo.ActivityPersonDTO;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysActivityMapper;
import com.ruoyi.system.domain.SysActivity;
import com.ruoyi.system.service.ISysActivityService;

/**
 * 活动Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-10
 */
@Service
public class SysActivityServiceImpl implements ISysActivityService 
{
    @Autowired
    private SysActivityMapper sysActivityMapper;

    @Autowired
    private SightsBaseMapper baseMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询活动
     * 
     * @param activityId 活动主键
     * @return 活动
     */
    @Override
    public SysActivity selectSysActivityByActivityId(Long activityId)
    {
        return sysActivityMapper.selectSysActivityByActivityId(activityId);
    }

    /**
     * 查询活动列表
     * 
     * @param sysActivity 活动
     * @return 活动
     */
    @Override
    public List<SysActivity> selectSysActivityList(SysActivity sysActivity)
    {
        return sysActivityMapper.selectSysActivityList(sysActivity);
    }

    /**
     * 新增活动
     * 
     * @param sysActivity 活动
     * @return 结果
     */
    @Override
    public int insertSysActivity(SysActivity sysActivity)
    {
        sysActivity.setCreateTime(DateUtils.getNowDate());
        return sysActivityMapper.insertSysActivity(sysActivity);
    }

    /**
     * 修改活动
     * 
     * @param sysActivity 活动
     * @return 结果
     */
    @Override
    public int updateSysActivity(SysActivity sysActivity)
    {
        sysActivity.setUpdateTime(DateUtils.getNowDate());
        return sysActivityMapper.updateSysActivity(sysActivity);
    }

    /**
     * 批量删除活动
     * 
     * @param activityIds 需要删除的活动主键
     * @return 结果
     */
    @Override
    public int deleteSysActivityByActivityIds(Long[] activityIds)
    {
        return sysActivityMapper.deleteSysActivityByActivityIds(activityIds);
    }

    /**
     * 删除活动信息
     * 
     * @param activityId 活动主键
     * @return 结果
     */
    @Override
    public int deleteSysActivityByActivityId(Long activityId)
    {
        return sysActivityMapper.deleteSysActivityByActivityId(activityId);
    }

    /**
     * 活动轮播
     * @return
     */
    @Override
    public List<ActivityPersonDTO> getPersonSwiper() {
        List<SysActivity> swiper = sysActivityMapper.getPersonSwiper();
        List<ActivityPersonDTO> personDTOS = new ArrayList<>();
        swiper.stream().forEach(item->{
            ActivityPersonDTO personDTO = new ActivityPersonDTO();
            BeanUtils.copyBeanProp(personDTO,item);
            personDTOS.add(personDTO);
        });
        return personDTOS;
    }

    /*** 活动列表*/
    @Override
    public List<ActivityListDTO> getActList() {
        List<SysActivity> show = sysActivityMapper.getListShow();
        List<ActivityListDTO> personDTOS = new ArrayList<>();
        show.forEach(item->{
            ActivityListDTO activityListDTO = new ActivityListDTO();
            BeanUtils.copyBeanProp(activityListDTO,item);
            personDTOS.add(activityListDTO);
        });
        return personDTOS;
    }

    /**
     * 分页获取活动列表
     * @return
     */
    @Override
    public List<SysActivity> getAllList() {
        List<SysActivity> list = sysActivityMapper.getActList();
        return list;
    }

    /**
     * 定时增加热点
     */
    @Override
    public void addHot() {
        if (redisCache.hasKey(Constants.ACTIVITY_RECORD)){
            redisCache.lock(Constants.ACTIVITY_RECORD);
            List<Long> cacheList = redisCache.getCacheList(Constants.ACTIVITY_RECORD);
            redisCache.deleteObject(Constants.ACTIVITY_RECORD);
            sysActivityMapper.updateHot(cacheList);
        }
    }

    /**
     * 定时减少热点
     */
    @Override
    public void reduceHot() {
        sysActivityMapper.reduceHot();
    }

    /**
     * 活动详细数据
     * @param activityId
     * @return
     */
    @Override
    public ActivityDetailDTO getDetail(Long activityId) {
        SysActivity activity = selectSysActivityByActivityId(activityId);
        ActivityDetailDTO detailDTO = new ActivityDetailDTO();
        detailDTO.setActivity(activity);
        if ("0".equals(activity.getSource())){
            SightsBase base = baseMapper.selectSightsBaseBySightsId(activity.getSightsId());
            SightsRandomDTO dto = new SightsRandomDTO();
            BeanUtils.copyBeanProp(dto,base);
            detailDTO.setSights(dto);
        }else if ("1".equals(activity.getSource())||"2".equals(activity.getSource())){
            SysUser sysUser = userMapper.selectUserById(activity.getUserId());
            UserDTO dto = new UserDTO();
            BeanUtils.copyBeanProp(dto,sysUser);
            detailDTO.setUser(dto);
        }
        return detailDTO;
    }


}
