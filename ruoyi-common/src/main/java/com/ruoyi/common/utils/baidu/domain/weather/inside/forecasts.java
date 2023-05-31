package com.ruoyi.common.utils.baidu.domain.weather.inside;

import java.io.Serializable;

/**
 * date	String	日期，北京时区	data_type=fc/all	-	基础字段
 * week	String	星期，北京时区	data_type=fc/all	-	基础字段
 * high	Int	最高温度(℃)	data_type=fc/all	999999	基础字段
 * low	Int	最低温度(℃)	data_type=fc/all	999999	基础字段
 * wc_day	String	白天风力	data_type=fc/all	暂无	基础字段
 * wc_night	String	晚上风力	data_type=fc/all	暂无	基础字段
 * wd_day	String	白天风向	data_type=fc/all	暂无	基础字段
 * wd_night	String	晚上风向	data_type=fc/all	暂无	基础字段
 * text_day	String	白天天气现象	data_type=fc/all	暂无	基础字段
 * text_night	String	晚上天气现象	data_type=fc/all	暂无	基础字段
 */

/**
 * @author chas
 * @introduction 预报数据
 * @date 2023-5-31
 */
public class forecasts implements Serializable {
    private  String date;
    private  String week;
    private  Integer high;
    private Integer low;
    private  String wcDay;
    private String wcNight;
    private String wdDay;
    private String wdNight;
    private String textDay;
    private String textNight;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public String getWcDay() {
        return wcDay;
    }

    public void setWcDay(String wcDay) {
        this.wcDay = wcDay;
    }

    public String getWcNight() {
        return wcNight;
    }

    public void setWcNight(String wcNight) {
        this.wcNight = wcNight;
    }

    public String getWdDay() {
        return wdDay;
    }

    public void setWdDay(String wdDay) {
        this.wdDay = wdDay;
    }

    public String getWdNight() {
        return wdNight;
    }

    public void setWdNight(String wdNight) {
        this.wdNight = wdNight;
    }

    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    public String getTextNight() {
        return textNight;
    }

    public void setTextNight(String textNight) {
        this.textNight = textNight;
    }

    @Override
    public String toString() {
        return "forecasts{" +
                "date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", high=" + high +
                ", low=" + low +
                ", wcDay='" + wcDay + '\'' +
                ", wcNight='" + wcNight + '\'' +
                ", wdDay='" + wdDay + '\'' +
                ", wdNight='" + wdNight + '\'' +
                ", textDay='" + textDay + '\'' +
                ", textNight='" + textNight + '\'' +
                '}';
    }
}
