package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 敏感词
 * @author Chas
 * @data 2023.3
 */
public class SysSensitive extends BaseEntity {

    /** 敏感词ID */
    private Long sensitiveId;
    /** 敏感词汇 **/
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


}
