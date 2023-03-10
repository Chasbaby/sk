package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysPartnerMapper;
import com.ruoyi.system.domain.SysPartner;
import com.ruoyi.system.service.ISysPartnerService;

/**
 * 合作伙伴Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
@Service
public class SysPartnerServiceImpl implements ISysPartnerService 
{
    @Autowired
    private SysPartnerMapper sysPartnerMapper;

    /**
     * 查询合作伙伴
     * 
     * @param partnerId 合作伙伴主键
     * @return 合作伙伴
     */
    @Override
    public SysPartner selectSysPartnerByPartnerId(Long partnerId)
    {
        return sysPartnerMapper.selectSysPartnerByPartnerId(partnerId);
    }

    /**
     * 查询合作伙伴列表
     * 
     * @param sysPartner 合作伙伴
     * @return 合作伙伴
     */
    @Override
    public List<SysPartner> selectSysPartnerList(SysPartner sysPartner)
    {
        return sysPartnerMapper.selectSysPartnerList(sysPartner);
    }

    /**
     * 新增合作伙伴
     * 
     * @param sysPartner 合作伙伴
     * @return 结果
     */
    @Override
    public int insertSysPartner(SysPartner sysPartner)
    {
        sysPartner.setCreateTime(DateUtils.getNowDate());
        return sysPartnerMapper.insertSysPartner(sysPartner);
    }

    /**
     * 修改合作伙伴
     * 
     * @param sysPartner 合作伙伴
     * @return 结果
     */
    @Override
    public int updateSysPartner(SysPartner sysPartner)
    {
        sysPartner.setUpdateTime(DateUtils.getNowDate());
        return sysPartnerMapper.updateSysPartner(sysPartner);
    }

    /**
     * 批量删除合作伙伴
     * 
     * @param partnerIds 需要删除的合作伙伴主键
     * @return 结果
     */
    @Override
    public int deleteSysPartnerByPartnerIds(Long[] partnerIds)
    {
        return sysPartnerMapper.deleteSysPartnerByPartnerIds(partnerIds);
    }

    /**
     * 删除合作伙伴信息
     * 
     * @param partnerId 合作伙伴主键
     * @return 结果
     */
    @Override
    public int deleteSysPartnerByPartnerId(Long partnerId)
    {
        return sysPartnerMapper.deleteSysPartnerByPartnerId(partnerId);
    }
}
