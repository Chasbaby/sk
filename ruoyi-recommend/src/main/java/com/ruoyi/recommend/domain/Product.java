package com.ruoyi.recommend.domain;


import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer productId ;
    private String productName;
    private String type;
    private String tags;

    public Product(){}
    public Product(Integer productId, String productName, String type, String tags) {
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.tags = tags;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", type='" + type + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
