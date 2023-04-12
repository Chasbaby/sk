package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysVoice;

/**
 * 动态播报Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
public interface SysVoiceMapper 
{
    /**
     * 查询动态播报
     * 
     * @param voiceId 动态播报主键
     * @return 动态播报
     */
    public SysVoice selectSysVoiceByVoiceId(Long voiceId);

    /**
     * 查询动态播报列表
     * 
     * @param sysVoice 动态播报
     * @return 动态播报集合
     */
    public List<SysVoice> selectSysVoiceList(SysVoice sysVoice);

    /**
     * 新增动态播报
     * 
     * @param sysVoice 动态播报
     * @return 结果
     */
    public int insertSysVoice(SysVoice sysVoice);

    /**
     * 修改动态播报
     * 
     * @param sysVoice 动态播报
     * @return 结果
     */
    public int updateSysVoice(SysVoice sysVoice);

    /**
     * 删除动态播报
     * 
     * @param voiceId 动态播报主键
     * @return 结果
     */
    public int deleteSysVoiceByVoiceId(Long voiceId);

    /**
     * 批量删除动态播报
     * 
     * @param voiceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysVoiceByVoiceIds(Long[] voiceIds);
}
