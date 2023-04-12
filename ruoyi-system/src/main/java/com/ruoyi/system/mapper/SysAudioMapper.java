package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysAudio;

/**
 * 语音映射Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
public interface SysAudioMapper 
{
    /**
     * 查询语音映射
     * 
     * @param audioId 语音映射主键
     * @return 语音映射
     */
    public SysAudio selectSysAudioByAudioId(Long audioId);

    /**
     * 查询语音映射列表
     * 
     * @param sysAudio 语音映射
     * @return 语音映射集合
     */
    public List<SysAudio> selectSysAudioList(SysAudio sysAudio);

    /**
     * 新增语音映射
     * 
     * @param sysAudio 语音映射
     * @return 结果
     */
    public int insertSysAudio(SysAudio sysAudio);

    /**
     * 修改语音映射
     * 
     * @param sysAudio 语音映射
     * @return 结果
     */
    public int updateSysAudio(SysAudio sysAudio);

    /**
     * 删除语音映射
     * 
     * @param audioId 语音映射主键
     * @return 结果
     */
    public int deleteSysAudioByAudioId(Long audioId);

    /**
     * 批量删除语音映射
     * 
     * @param audioIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysAudioByAudioIds(Long[] audioIds);
}
