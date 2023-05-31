package com.ruoyi.common.utils.baidu.domain.weather.inside;

import java.io.Serializable;
/**
 * name	String	生活指数中文名称	data_type=index/all	暂无	高级字段
 * brief	String	生活指数概要说明	data_type=index/all	暂无	高级字段
 * detail	String	生活指数详细说明	data_type=index/all	暂无	高级字段
 */

/**
 * @author chas
 * @introduction 生活指数数据
 * @date 2023-5-31
 */
public class indexes implements Serializable {
    private String name;
    private String brief;
    private String detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "indexes{" +
                "name='" + name + '\'' +
                ", brief='" + brief + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
