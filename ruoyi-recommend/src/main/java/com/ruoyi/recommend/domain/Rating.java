package com.ruoyi.recommend.domain;

import java.io.Serializable;

public class Rating implements Serializable {
    private Integer userId;
    private Integer productId;
    private Double rating;
    private Long timestamp;


    public Rating() {
    }

    public Rating(Integer userId, Integer productId, Double rating, Long timestamp) {
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.timestamp = timestamp;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
