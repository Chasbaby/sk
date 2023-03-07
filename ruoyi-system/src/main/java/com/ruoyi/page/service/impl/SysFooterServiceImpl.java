package com.ruoyi.page.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.page.mapper.SysFooterMapper;
import com.ruoyi.page.domain.SysFooter;
import com.ruoyi.page.service.ISysFooterService;

/**
 * 底部展示Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-03
 */
@Service
public class SysFooterServiceImpl implements ISysFooterService 
{
    @Autowired
    private SysFooterMapper sysFooterMapper;

    /**
     * 查询底部展示
     * 
     * @param footerId 底部展示主键
     * @return 底部展示
     */
    @Override
    public SysFooter selectSysFooterByFooterId(Long footerId)
    {
        return sysFooterMapper.selectSysFooterByFooterId(footerId);
    }

    /**
     * 查询底部展示列表
     * 
     * @param sysFooter 底部展示
     * @return 底部展示
     */
    @Override
    public List<SysFooter> selectSysFooterList(SysFooter sysFooter)
    {
        return sysFooterMapper.selectSysFooterList(sysFooter);
    }

    /**
     * 新增底部展示
     * 
     * @param sysFooter 底部展示
     * @return 结果
     */
    @Override
    public int insertSysFooter(SysFooter sysFooter)
    {
        sysFooter.setCreateTime(DateUtils.getNowDate());
        return sysFooterMapper.insertSysFooter(sysFooter);
    }

    /**
     * 修改底部展示
     * 
     * @param sysFooter 底部展示
     * @return 结果
     */
    @Override
    public int updateSysFooter(SysFooter sysFooter)
    {
        sysFooter.setUpdateTime(DateUtils.getNowDate());
        return sysFooterMapper.updateSysFooter(sysFooter);
    }

    /**
     * 批量删除底部展示
     * 
     * @param footerIds 需要删除的底部展示主键
     * @return 结果
     */
    @Override
    public int deleteSysFooterByFooterIds(Long[] footerIds)
    {
        return sysFooterMapper.deleteSysFooterByFooterIds(footerIds);
    }

    /**
     * 删除底部展示信息
     * 
     * @param footerId 底部展示主键
     * @return 结果
     */
    @Override
    public int deleteSysFooterByFooterId(Long footerId)
    {
        return sysFooterMapper.deleteSysFooterByFooterId(footerId);
    }
}
