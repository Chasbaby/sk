package com.ruoyi.common.utils.baidu.domain.weather.inside;

import java.io.Serializable;

/**
 * @author chas
 * @introduction  地理位置信息
 * @date 2023-5-31
 */
public class address implements Serializable{
    /** 国家*/
    private String country;
    /** 省份*/
    private String province;
    /** 城市*/
    private String city;
    /** 区县*/
    private String name;
    /** 区县id*/
    private String id;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "address{" +
                "country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
