package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysPartner;

/**
 * 合作伙伴Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
public interface SysPartnerMapper 
{
    /**
     * 查询合作伙伴
     * 
     * @param partnerId 合作伙伴主键
     * @return 合作伙伴
     */
    public SysPartner selectSysPartnerByPartnerId(Long partnerId);

    /**
     * 查询合作伙伴列表
     * 
     * @param sysPartner 合作伙伴
     * @return 合作伙伴集合
     */
    public List<SysPartner> selectSysPartnerList(SysPartner sysPartner);

    /**
     * 新增合作伙伴
     * 
     * @param sysPartner 合作伙伴
     * @return 结果
     */
    public int insertSysPartner(SysPartner sysPartner);

    /**
     * 修改合作伙伴
     * 
     * @param sysPartner 合作伙伴
     * @return 结果
     */
    public int updateSysPartner(SysPartner sysPartner);

    /**
     * 删除合作伙伴
     * 
     * @param partnerId 合作伙伴主键
     * @return 结果
     */
    public int deleteSysPartnerByPartnerId(Long partnerId);

    /**
     * 批量删除合作伙伴
     * 
     * @param partnerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysPartnerByPartnerIds(Long[] partnerIds);
}
