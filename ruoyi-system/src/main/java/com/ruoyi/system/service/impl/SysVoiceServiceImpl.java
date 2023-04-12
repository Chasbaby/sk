package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysVoiceMapper;
import com.ruoyi.system.domain.SysVoice;
import com.ruoyi.system.service.ISysVoiceService;

/**
 * 动态播报Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
@Service
public class SysVoiceServiceImpl implements ISysVoiceService 
{
    @Autowired
    private SysVoiceMapper sysVoiceMapper;

    /**
     * 查询动态播报
     * 
     * @param voiceId 动态播报主键
     * @return 动态播报
     */
    @Override
    public SysVoice selectSysVoiceByVoiceId(Long voiceId)
    {
        return sysVoiceMapper.selectSysVoiceByVoiceId(voiceId);
    }

    /**
     * 查询动态播报列表
     * 
     * @param sysVoice 动态播报
     * @return 动态播报
     */
    @Override
    public List<SysVoice> selectSysVoiceList(SysVoice sysVoice)
    {
        return sysVoiceMapper.selectSysVoiceList(sysVoice);
    }

    /**
     * 新增动态播报
     * 
     * @param sysVoice 动态播报
     * @return 结果
     */
    @Override
    public int insertSysVoice(SysVoice sysVoice)
    {
        sysVoice.setCreateTime(DateUtils.getNowDate());
        return sysVoiceMapper.insertSysVoice(sysVoice);
    }

    /**
     * 修改动态播报
     * 
     * @param sysVoice 动态播报
     * @return 结果
     */
    @Override
    public int updateSysVoice(SysVoice sysVoice)
    {
        sysVoice.setUpdateTime(DateUtils.getNowDate());
        return sysVoiceMapper.updateSysVoice(sysVoice);
    }

    /**
     * 批量删除动态播报
     * 
     * @param voiceIds 需要删除的动态播报主键
     * @return 结果
     */
    @Override
    public int deleteSysVoiceByVoiceIds(Long[] voiceIds)
    {
        return sysVoiceMapper.deleteSysVoiceByVoiceIds(voiceIds);
    }

    /**
     * 删除动态播报信息
     * 
     * @param voiceId 动态播报主键
     * @return 结果
     */
    @Override
    public int deleteSysVoiceByVoiceId(Long voiceId)
    {
        return sysVoiceMapper.deleteSysVoiceByVoiceId(voiceId);
    }
}
