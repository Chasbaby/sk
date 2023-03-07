package com.ruoyi.web.controller.search;

import com.ruoyi.common.core.domain.BaseEntity;

public class searchNumber extends BaseEntity {
    private String keywords;

    public searchNumber(String keywords) {
        this.keywords = keywords;
    }

    public searchNumber() {
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
