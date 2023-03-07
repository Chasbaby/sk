package com.ruoyi.recommend.es.domain;

import java.io.Serializable;


/**
 * 多元搜索
 * @author Chas
 * @date 2023-3
 */
public class MultiSearchDTO implements Serializable {

    /** 多元id */
    private Long multipleId;

    /** 多元物种id**/
    private Long multipleItemId;

    /** 多元名字/主题**/
    private String multipleName;

    /** 多元内容 */
    private String multipleContent;

    /** 多元路由 **/
    private String multipleRoute;

    /** 多元类型 **/
    private String multipleType;

    /** 多元 点击量 **/
    private Long multipleHits;

    /** 多元 点赞量**/
    private Long multipleLike;

    /** 多元浏览量 */
    private Long multipleView;

    /** 多元收藏量*/
    private Long multipleCollect;

    /** 多元图片**/
    private String multipleImage;

    /** 多元分类*/
    private String multipleCategory;

    /** 多元得分**/
    private Double multipleScore;

    /** 多元作者id **/
    private Long multipleWork;

    public Long getMultipleId() {
        return multipleId;
    }

    public void setMultipleId(Long multipleId) {
        this.multipleId = multipleId;
    }

    public Long getMultipleItemId() {
        return multipleItemId;
    }

    public void setMultipleItemId(Long multipleItemId) {
        this.multipleItemId = multipleItemId;
    }


    public String getMultipleName() {
        return multipleName;
    }

    public void setMultipleName(String multipleName) {
        this.multipleName = multipleName;
    }

    public String getMultipleContent() {
        return multipleContent;
    }

    public void setMultipleContent(String multipleContent) {
        this.multipleContent = multipleContent;
    }

    public String getMultipleRoute() {
        return multipleRoute;
    }

    public void setMultipleRoute(String multipleRoute) {
        this.multipleRoute = multipleRoute;
    }

    public String getMultipleType() {
        return multipleType;
    }

    public void setMultipleType(String multipleType) {
        this.multipleType = multipleType;
    }

    public Long getMultipleHits() {
        return multipleHits;
    }

    public void setMultipleHits(Long multipleHits) {
        this.multipleHits = multipleHits;
    }

    public Long getMultipleLike() {
        return multipleLike;
    }

    public void setMultipleLike(Long multipleLike) {
        this.multipleLike = multipleLike;
    }

    public Long getMultipleView() {
        return multipleView;
    }

    public void setMultipleView(Long multipleView) {
        this.multipleView = multipleView;
    }

    public Long getMultipleCollect() {
        return multipleCollect;
    }

    public void setMultipleCollect(Long multipleCollect) {
        this.multipleCollect = multipleCollect;
    }

    public String getMultipleImage() {
        return multipleImage;
    }

    public void setMultipleImage(String multipleImage) {
        this.multipleImage = multipleImage;
    }

    public String getMultipleCategory() {
        return multipleCategory;
    }

    public void setMultipleCategory(String multipleCategory) {
        this.multipleCategory = multipleCategory;
    }

    public Double getMultipleScore() {
        return multipleScore;
    }

    public void setMultipleScore(Double multipleScore) {
        this.multipleScore = multipleScore;
    }

    public Long getMultipleWork() {
        return multipleWork;
    }

    public void setMultipleWork(Long multipleWork) {
        this.multipleWork = multipleWork;
    }
}
