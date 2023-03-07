package com.ruoyi.common.utils.baidu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 经纬度坐标
 * @author Chas
 * @date 2023-1
 */
public class location implements Serializable {
    /**
     * 纬度值
     */
    private double lat;
    /**
     * 经度值
     */
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public location(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public location() {
    }
    @Override
    public String toString(){
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("lat",getLat())
                .append("lng",getLng())
                .toString();
    }



}
