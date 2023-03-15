package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 敏感词
 * @author Chas
 * @data 2023.3
 */
public class SysSensitive{

    /** 敏感词ID */
    private Long sensitiveId;
    /** 敏感词汇 **/
    @Excel(defaultValue = "敏感词")
    private String sensitiveWord;

    public Long getSensitiveId() {
        return sensitiveId;
    }

    public void setSensitiveId(Long sensitiveId) {
        this.sensitiveId = sensitiveId;
    }

    public String getSensitiveWord() {
        return sensitiveWord;
    }

    public void setSensitiveWord(String sensitiveWord) {
        this.sensitiveWord = sensitiveWord;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("sensitiveId", getSensitiveId())
                .append("sensitiveWord", getSensitiveWord())
                .toString();
    }
}
