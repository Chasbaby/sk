package com.ruoyi.page.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.page.mapper.SysTypesetMapper;
import com.ruoyi.page.domain.SysTypeset;
import com.ruoyi.page.service.ISysTypesetService;

/**
 * 动态排版Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-04
 */
@Service
public class SysTypesetServiceImpl implements ISysTypesetService 
{
    @Autowired
    private SysTypesetMapper sysTypesetMapper;

    /**
     * 查询动态排版
     * 
     * @param typesetId 动态排版主键
     * @return 动态排版
     */
    @Override
    public SysTypeset selectSysTypesetByTypesetId(Long typesetId)
    {
        return sysTypesetMapper.selectSysTypesetByTypesetId(typesetId);
    }

    /**
     * 查询动态排版列表
     * 
     * @param sysTypeset 动态排版
     * @return 动态排版
     */
    @Override
    public List<SysTypeset> selectSysTypesetList(SysTypeset sysTypeset)
    {
        return sysTypesetMapper.selectSysTypesetList(sysTypeset);
    }

    /**
     * 新增动态排版
     * 
     * @param sysTypeset 动态排版
     * @return 结果
     */
    @Override
    public int insertSysTypeset(SysTypeset sysTypeset)
    {
        sysTypeset.setCreateTime(DateUtils.getNowDate());
        return sysTypesetMapper.insertSysTypeset(sysTypeset);
    }

    /**
     * 修改动态排版
     * 
     * @param sysTypeset 动态排版
     * @return 结果
     */
    @Override
    public int updateSysTypeset(SysTypeset sysTypeset)
    {
        sysTypeset.setUpdateTime(DateUtils.getNowDate());
        return sysTypesetMapper.updateSysTypeset(sysTypeset);
    }

    /**
     * 批量删除动态排版
     * 
     * @param typesetIds 需要删除的动态排版主键
     * @return 结果
     */
    @Override
    public int deleteSysTypesetByTypesetIds(Long[] typesetIds)
    {
        return sysTypesetMapper.deleteSysTypesetByTypesetIds(typesetIds);
    }

    /**
     * 删除动态排版信息
     * 
     * @param typesetId 动态排版主键
     * @return 结果
     */
    @Override
    public int deleteSysTypesetByTypesetId(Long typesetId)
    {
        return sysTypesetMapper.deleteSysTypesetByTypesetId(typesetId);
    }

    @Override
    public List<SysTypeset> getTypesetSights3() {
        return sysTypesetMapper.getTypesetSights3();
    }

    @Override
    public List<SysTypeset> getTypesetCul3() {
        return sysTypesetMapper.getTypesetCul3();
    }
}
