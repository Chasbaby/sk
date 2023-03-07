package com.ruoyi.recommend.domain;

import java.io.Serializable;

public class UserProductRec implements Serializable {
    private Integer userId ;
    private Integer productId;
    private Double score;

    public UserProductRec() {}

    public UserProductRec(Integer userId, Integer productId, Double score) {
        this.userId = userId;
        this.productId = productId;
        this.score = score;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
