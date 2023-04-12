package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 语音映射对象 sys_audio
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
public class SysAudio extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long audioId;

    /** speak标签 */
    @Excel(name = "speak标签")
    private String speakLabel;

    /** 百度标签 */
    @Excel(name = "百度标签")
    private String baiduLabel;

    /** 中文 */
    @Excel(name = "中文")
    private String langZh;

    /** 英文 */
    @Excel(name = "英文")
    private String langEn;

    /** 是否开启 */
    @Excel(name = "是否开启")
    private String flag;

    public void setAudioId(Long audioId) 
    {
        this.audioId = audioId;
    }

    public Long getAudioId() 
    {
        return audioId;
    }
    public void setSpeakLabel(String speakLabel) 
    {
        this.speakLabel = speakLabel;
    }

    public String getSpeakLabel() 
    {
        return speakLabel;
    }
    public void setBaiduLabel(String baiduLabel) 
    {
        this.baiduLabel = baiduLabel;
    }

    public String getBaiduLabel() 
    {
        return baiduLabel;
    }
    public void setLangZh(String langZh) 
    {
        this.langZh = langZh;
    }

    public String getLangZh() 
    {
        return langZh;
    }
    public void setLangEn(String langEn) 
    {
        this.langEn = langEn;
    }

    public String getLangEn() 
    {
        return langEn;
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
            .append("audioId", getAudioId())
            .append("speakLabel", getSpeakLabel())
            .append("baiduLabel", getBaiduLabel())
            .append("langZh", getLangZh())
            .append("langEn", getLangEn())
            .append("flag", getFlag())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
