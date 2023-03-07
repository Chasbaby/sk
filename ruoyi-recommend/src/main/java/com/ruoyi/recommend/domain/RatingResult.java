package com.ruoyi.recommend.domain;

import java.io.Serializable;

public class RatingResult implements Serializable {
    private Integer userId;
    private Integer productId2;
    private Double score;

    public RatingResult() {
    }

    public RatingResult(Integer userId, Integer productId2, Double score) {
        this.userId = userId;
        this.productId2 = productId2;
        this.score = score;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId2() {
        return productId2;
    }

    public void setProductId2(Integer productId2) {
        this.productId2 = productId2;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
