package com.ruoyi.recommend.domain;

import java.io.Serializable;

public class ContentProductRec implements Serializable {
    private Integer productId1;
    private Integer productId2;
    private Double score;

    public ContentProductRec() {
    }

    public ContentProductRec(Integer productId1, Integer productId2, Double score) {
        this.productId1 = productId1;
        this.productId2 = productId2;
        this.score = score;
    }

    public Integer getProductId1() {
        return productId1;
    }

    public void setProductId1(Integer productId1) {
        this.productId1 = productId1;
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

    @Override
    public String toString() {
        return "ContentProductRec{" +
                "productId1=" + productId1 +
                ", productId2=" + productId2 +
                ", score=" + score +
                '}';
    }
}
