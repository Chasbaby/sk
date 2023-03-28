package com.ruoyi.culCreativity.domain.dto;

import java.io.Serializable;

/**
 * @author chas
 * @introduction Cul Pie
 * @data 2023-3
 */
public class CulStatisticPie implements Serializable {
    private Long culCreativityLike;
    private Long culCreativityCollection;
    private Long culCreativityView;

    public Long getCulCreativityLike() {
        return culCreativityLike;
    }

    public void setCulCreativityLike(Long culCreativityLike) {
        this.culCreativityLike = culCreativityLike;
    }

    public Long getCulCreativityCollection() {
        return culCreativityCollection;
    }

    public void setCulCreativityCollection(Long culCreativityCollection) {
        this.culCreativityCollection = culCreativityCollection;
    }

    public Long getCulCreativityView() {
        return culCreativityView;
    }

    public void setCulCreativityView(Long culCreativityView) {
        this.culCreativityView = culCreativityView;
    }
}
