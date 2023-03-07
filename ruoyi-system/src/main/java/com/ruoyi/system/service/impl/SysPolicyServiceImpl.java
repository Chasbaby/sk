package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysPolicyMapper;
import com.ruoyi.system.domain.SysPolicy;
import com.ruoyi.system.service.ISysPolicyService;

/**
 * 政策Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-09
 */
@Service
public class SysPolicyServiceImpl implements ISysPolicyService 
{
    @Autowired
    private SysPolicyMapper sysPolicyMapper;

    /**
     * 查询政策
     * 
     * @param policyId 政策主键
     * @return 政策
     */
    @Override
    public SysPolicy selectSysPolicyByPolicyId(Long policyId)
    {
        return sysPolicyMapper.selectSysPolicyByPolicyId(policyId);
    }

    /**
     * 查询政策列表
     * 
     * @param sysPolicy 政策
     * @return 政策
     */
    @Override
    public List<SysPolicy> selectSysPolicyList(SysPolicy sysPolicy)
    {
        return sysPolicyMapper.selectSysPolicyList(sysPolicy);
    }

    /**
     * 新增政策
     * 
     * @param sysPolicy 政策
     * @return 结果
     */
    @Override
    public int insertSysPolicy(SysPolicy sysPolicy)
    {
        sysPolicy.setCreateTime(DateUtils.getNowDate());
        return sysPolicyMapper.insertSysPolicy(sysPolicy);
    }

    /**
     * 修改政策
     * 
     * @param sysPolicy 政策
     * @return 结果
     */
    @Override
    public int updateSysPolicy(SysPolicy sysPolicy)
    {
        sysPolicy.setUpdateTime(DateUtils.getNowDate());
        return sysPolicyMapper.updateSysPolicy(sysPolicy);
    }

    /**
     * 批量删除政策
     * 
     * @param policyIds 需要删除的政策主键
     * @return 结果
     */
    @Override
    public int deleteSysPolicyByPolicyIds(Long[] policyIds)
    {
        return sysPolicyMapper.deleteSysPolicyByPolicyIds(policyIds);
    }

    /**
     * 删除政策信息
     * 
     * @param policyId 政策主键
     * @return 结果
     */
    @Override
    public int deleteSysPolicyByPolicyId(Long policyId)
    {
        return sysPolicyMapper.deleteSysPolicyByPolicyId(policyId);
    }
}
