package com.ruoyi.common.utils.baidu.translate.domain;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 翻译实体
 * @date 2023-4
 */

public class TranslateDomain implements Serializable {
    private String from;
    private transResult[] transResult;
    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public com.ruoyi.common.utils.baidu.translate.domain.transResult[] getTransResult() {
        return transResult;
    }

    public void setTransResult(com.ruoyi.common.utils.baidu.translate.domain.transResult[] transResult) {
        this.transResult = transResult;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public TranslateDomain(String from, com.ruoyi.common.utils.baidu.translate.domain.transResult transResult[], String to) {
        this.from = from;
        this.transResult = transResult;
        this.to = to;
    }

    public TranslateDomain() {
    }
}
