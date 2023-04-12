package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysAudioMapper;
import com.ruoyi.system.domain.SysAudio;
import com.ruoyi.system.service.ISysAudioService;

/**
 * 语音映射Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
@Service
public class SysAudioServiceImpl implements ISysAudioService 
{
    @Autowired
    private SysAudioMapper sysAudioMapper;

    /**
     * 查询语音映射
     * 
     * @param audioId 语音映射主键
     * @return 语音映射
     */
    @Override
    public SysAudio selectSysAudioByAudioId(Long audioId)
    {
        return sysAudioMapper.selectSysAudioByAudioId(audioId);
    }

    /**
     * 查询语音映射列表
     * 
     * @param sysAudio 语音映射
     * @return 语音映射
     */
    @Override
    public List<SysAudio> selectSysAudioList(SysAudio sysAudio)
    {
        return sysAudioMapper.selectSysAudioList(sysAudio);
    }

    /**
     * 新增语音映射
     * 
     * @param sysAudio 语音映射
     * @return 结果
     */
    @Override
    public int insertSysAudio(SysAudio sysAudio)
    {
        sysAudio.setCreateTime(DateUtils.getNowDate());
        return sysAudioMapper.insertSysAudio(sysAudio);
    }

    /**
     * 修改语音映射
     * 
     * @param sysAudio 语音映射
     * @return 结果
     */
    @Override
    public int updateSysAudio(SysAudio sysAudio)
    {
        sysAudio.setUpdateTime(DateUtils.getNowDate());
        return sysAudioMapper.updateSysAudio(sysAudio);
    }

    /**
     * 批量删除语音映射
     * 
     * @param audioIds 需要删除的语音映射主键
     * @return 结果
     */
    @Override
    public int deleteSysAudioByAudioIds(Long[] audioIds)
    {
        return sysAudioMapper.deleteSysAudioByAudioIds(audioIds);
    }

    /**
     * 删除语音映射信息
     * 
     * @param audioId 语音映射主键
     * @return 结果
     */
    @Override
    public int deleteSysAudioByAudioId(Long audioId)
    {
        return sysAudioMapper.deleteSysAudioByAudioId(audioId);
    }
}
