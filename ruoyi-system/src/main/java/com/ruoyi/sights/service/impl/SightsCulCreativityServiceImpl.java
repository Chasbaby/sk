package com.ruoyi.sights.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.DTO.UserDTO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.culCreativity.domain.*;
import com.ruoyi.culCreativity.domain.dto.CulDetail;
import com.ruoyi.sights.domain.*;
import com.ruoyi.sights.domain.DTO.SightsCulDTO;
import com.ruoyi.sights.mapper.*;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sights.service.ISightsCulCreativityService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文创Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@Service
public class SightsCulCreativityServiceImpl implements ISightsCulCreativityService 
{
    @Autowired
    private SightsCulCreativityMapper sightsCulCreativityMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SightsBaseMapper baseMapper;


    /**
     * 查询文创
     * 
     * @param culCreativityId 文创主键
     * @return 文创
     */
    @Override
    public SightsCulCreativity selectSightsCulCreativityByCulCreativityId(Long culCreativityId)
    {
        return sightsCulCreativityMapper.selectSightsCulCreativityByCulCreativityId(culCreativityId);
    }

    /**
     * 查询文创列表
     * 
     * @param sightsCulCreativity 文创
     * @return 文创
     */
    @Override
    public List<SightsCulCreativity> selectSightsCulCreativityList(SightsCulCreativity sightsCulCreativity)
    {
        return sightsCulCreativityMapper.selectSightsCulCreativityList(sightsCulCreativity);
    }

    /**
     * 新增文创
     * 
     * @param sightsCulCreativity 文创
     * @return 结果
     */
    @Override
    public int insertSightsCulCreativity(SightsCulCreativity sightsCulCreativity)
    {
        sightsCulCreativity.setCreateTime(DateUtils.getNowDate());
        return sightsCulCreativityMapper.insertSightsCulCreativity(sightsCulCreativity);
    }

    /**
     * 修改文创
     * 
     * @param sightsCulCreativity 文创
     * @return 结果
     */
    @Override
    public int updateSightsCulCreativity(SightsCulCreativity sightsCulCreativity)
    {
        sightsCulCreativity.setUpdateTime(DateUtils.getNowDate());
        return sightsCulCreativityMapper.updateSightsCulCreativity(sightsCulCreativity);
    }

    /**
     * 批量删除文创
     * 
     * @param culCreativityIds 需要删除的文创主键
     * @return 结果
     */
    @Override
    public int deleteSightsCulCreativityByCulCreativityIds(Long[] culCreativityIds)
    {
        return sightsCulCreativityMapper.deleteSightsCulCreativityByCulCreativityIds(culCreativityIds);
    }

    /**
     * 删除文创信息
     * 
     * @param culCreativityId 文创主键
     * @return 结果
     */
    @Override
    public int deleteSightsCulCreativityByCulCreativityId(Long culCreativityId)
    {
        return sightsCulCreativityMapper.deleteSightsCulCreativityByCulCreativityId(culCreativityId);
    }

    /**
     * 获取文创详细信息
     * @param culCreativityId id
     * @return
     */
    @Override
    public CulDetail getCulDetail(Long culCreativityId) {
        CulDetail detail = new CulDetail();
        SightsCulCreativity creativity = sightsCulCreativityMapper.selectDetailById(culCreativityId);
        BeanUtils.copyBeanProp(detail,creativity);
        Long userId = creativity.getUserId();
        // 如果是用户发表的文创
        if ( userId != null){
            UserDTO userDTO = new UserDTO();
            SysUser sysUser = userMapper.selectUserById(userId);
            BeanUtils.copyBeanProp(userDTO,sysUser);
            detail.setUser(userDTO);
            return detail;
        }
        Long sightsId = creativity.getSightsId();
        // 如果是景点发布的文创
        if (sightsId !=null){
            SightsCulDTO sightsCulDTO = new SightsCulDTO();
            SightsBase sightsBase = baseMapper.selectSightsBaseBySightsId(sightsId);
            BeanUtils.copyBeanProp(sightsCulDTO,sightsBase);
            detail.setSight(sightsCulDTO);
            return detail;
        }
        return detail;
    }


    /**
     *  增加 取消 点赞
     * @param record
     * @return
     */
    @Transactional
    @Override
    public int addCulLike(CulRecord record) {
        return 0;
    }

    /**
     * 增加 浏览
     * @param record
     * @return
     */
    @Transactional
    @Override
    public int addCulView(CulRecord record) {
        return 0;
    }

    /**
     * 非登录增加浏览
     * @param culId
     * @return
     */
    @Override
    public int addCulViewAnonymous(Long culId) {
        return 0;
    }

    /**
     * 增加 取消 收藏
     * @param record
     * @return
     */
    @Transactional
    @Override
    public int addCulCollect(CulRecord record) {
        return 0;
    }


}
