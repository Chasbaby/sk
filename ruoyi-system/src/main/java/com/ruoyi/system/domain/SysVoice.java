package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 动态播报对象 sys_voice
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
public class SysVoice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 播报id */
    private Long voiceId;

    /** 页面(字典类型记得) */
    @Excel(name = "页面(字典类型记得)")
    private Integer voicePage;

    /** 页面定位(字典类型 上中下这类的) */
    @Excel(name = "页面定位(字典类型 上中下这类的)")
    private Integer voicePosition;

    /** 内容介绍 */
    @Excel(name = "内容介绍")
    private String voiceIntroduction;

    /** 播报内容 */
    @Excel(name = "播报内容")
    private String voiceContent;

    /** 音高 */
    @Excel(name = "音高")
    private Integer voiceVolume;

    /** 语言(默认中文) */
    @Excel(name = "语言(默认中文)")
    private String voiceLanguange;

    /** 费率 */
    @Excel(name = "费率")
    private Integer voiceRate;

    /** 音高 */
    @Excel(name = "音高")
    private Integer voicePitch;

    /** 是否开启 */
    @Excel(name = "是否开启")
    private String flag;

    public void setVoiceId(Long voiceId) 
    {
        this.voiceId = voiceId;
    }

    public Long getVoiceId() 
    {
        return voiceId;
    }
    public void setVoicePage(Integer voicePage) 
    {
        this.voicePage = voicePage;
    }

    public Integer getVoicePage() 
    {
        return voicePage;
    }
    public void setVoicePosition(Integer voicePosition) 
    {
        this.voicePosition = voicePosition;
    }

    public Integer getVoicePosition() 
    {
        return voicePosition;
    }
    public void setVoiceIntroduction(String voiceIntroduction) 
    {
        this.voiceIntroduction = voiceIntroduction;
    }

    public String getVoiceIntroduction() 
    {
        return voiceIntroduction;
    }
    public void setVoiceContent(String voiceContent) 
    {
        this.voiceContent = voiceContent;
    }

    public String getVoiceContent() 
    {
        return voiceContent;
    }
    public void setVoiceVolume(Integer voiceVolume) 
    {
        this.voiceVolume = voiceVolume;
    }

    public Integer getVoiceVolume() 
    {
        return voiceVolume;
    }
    public void setVoiceLanguange(String voiceLanguange) 
    {
        this.voiceLanguange = voiceLanguange;
    }

    public String getVoiceLanguange() 
    {
        return voiceLanguange;
    }
    public void setVoiceRate(Integer voiceRate) 
    {
        this.voiceRate = voiceRate;
    }

    public Integer getVoiceRate() 
    {
        return voiceRate;
    }
    public void setVoicePitch(Integer voicePitch) 
    {
        this.voicePitch = voicePitch;
    }

    public Integer getVoicePitch() 
    {
        return voicePitch;
    }
    public void setFlag(String flag) 
    {
        this.flag = flag;
    }

    public String getFlag() 
    {
        return flag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("voiceId", getVoiceId())
            .append("voicePage", getVoicePage())
            .append("voicePosition", getVoicePosition())
            .append("voiceIntroduction", getVoiceIntroduction())
            .append("voiceContent", getVoiceContent())
            .append("voiceVolume", getVoiceVolume())
            .append("voiceLanguange", getVoiceLanguange())
            .append("voiceRate", getVoiceRate())
            .append("voicePitch", getVoicePitch())
            .append("flag", getFlag())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .toString();
    }
}
