package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysPolicy;

/**
 * 政策Service接口
 * 
 * @author ruoyi
 * @date 2022-08-09
 */
public interface ISysPolicyService 
{
    /**
     * 查询政策
     * 
     * @param policyId 政策主键
     * @return 政策
     */
    public SysPolicy selectSysPolicyByPolicyId(Long policyId);

    /**
     * 查询政策列表
     * 
     * @param sysPolicy 政策
     * @return 政策集合
     */
    public List<SysPolicy> selectSysPolicyList(SysPolicy sysPolicy);

    /**
     * 新增政策
     * 
     * @param sysPolicy 政策
     * @return 结果
     */
    public int insertSysPolicy(SysPolicy sysPolicy);

    /**
     * 修改政策
     * 
     * @param sysPolicy 政策
     * @return 结果
     */
    public int updateSysPolicy(SysPolicy sysPolicy);

    /**
     * 批量删除政策
     * 
     * @param policyIds 需要删除的政策主键集合
     * @return 结果
     */
    public int deleteSysPolicyByPolicyIds(Long[] policyIds);

    /**
     * 删除政策信息
     * 
     * @param policyId 政策主键
     * @return 结果
     */
    public int deleteSysPolicyByPolicyId(Long policyId);
}
