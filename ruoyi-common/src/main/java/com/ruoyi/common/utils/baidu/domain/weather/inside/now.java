package com.ruoyi.common.utils.baidu.domain.weather.inside;

import java.io.Serializable;
/**
 * temp	Int	温度（℃）	始终返回	999999	基础字段
 * feels_like	Int	体感温度(℃)	data_type=now/all	999999	基础字段
 * rh	Int	相对湿度(%)	data_type=now/all	999999	基础字段
 * wind_class	String	风力等级	data_type=now/all	暂无	基础字段
 * wind_dir	String	风向描述	data_type=now/all	暂无	基础字段
 * text	String	天气现象	data_type=now/all	暂无	基础字段
 * prec_1h	Double	1小时累计降水量(mm)	data_type=now/all	999999	高级字段
 * clouds	Int	云量(%)	data_type=now/all	999999	高级字段
 * vis	Int	能见度(m)	data_type=now/all	999999	高级字段
 * aqi	Int	空气质量指数数值	data_type=now/all	999999	高级字段
 * pm25	Int	pm2.5浓度(μg/m3)	data_type=now/all	999999	高级字段
 * pm10	Int	pm10浓度(μg/m3)	data_type=now/all	999999	高级字段
 * no2	Int	二氧化氮浓度(μg/m3)	data_type=now/all	999999	高级字段
 * so2	Int	二氧化硫浓度(μg/m3)	data_type=now/all	999999	高级字段
 * o3	Int	臭氧浓度(μg/m3)	data_type=now/all	999999	高级字段
 * co	Double	一氧化碳浓度(mg/m3)	data_type=now/all	999999	高级字段
 * uptime	String	数据更新时间，北京时间	data_type=now/all	-	基础字段
 */

/**
 * @author chas
 * @introduction 实况数据
 * @date 2023-5-31
 */
public class now implements Serializable {
    private Integer temp;
    private  Integer feelsLike;
    private Integer rh;
    private String windClass;
    private  String windDir;
    private    String text;
    private  String prec_1h;
    private  Integer clouds;
    private  Integer vis;
    private   Integer aqi;
    private Integer pm25;
    private Integer pm10;
    private  Integer no2;
    private  Integer so2;
    private   Integer o3;
    private   Double co;
    private  String uptime;



    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Integer feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Integer getRh() {
        return rh;
    }

    public void setRh(Integer rh) {
        this.rh = rh;
    }

    public String getWindClass() {
        return windClass;
    }

    public void setWindClass(String windClass) {
        this.windClass = windClass;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPrec_1h() {
        return prec_1h;
    }

    public void setPrec_1h(String prec_1h) {
        this.prec_1h = prec_1h;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Integer getVis() {
        return vis;
    }

    public void setVis(Integer vis) {
        this.vis = vis;
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public Integer getPm25() {
        return pm25;
    }

    public void setPm25(Integer pm25) {
        this.pm25 = pm25;
    }

    public Integer getPm10() {
        return pm10;
    }

    public void setPm10(Integer pm10) {
        this.pm10 = pm10;
    }

    public Integer getNo2() {
        return no2;
    }

    public void setNo2(Integer no2) {
        this.no2 = no2;
    }

    public Integer getSo2() {
        return so2;
    }

    public void setSo2(Integer so2) {
        this.so2 = so2;
    }

    public Integer getO3() {
        return o3;
    }

    public void setO3(Integer o3) {
        this.o3 = o3;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    @Override
    public String toString() {
        return "now{" +
                "temp=" + temp +
                ", feelsLike=" + feelsLike +
                ", rh=" + rh +
                ", windClass='" + windClass + '\'' +
                ", windDir='" + windDir + '\'' +
                ", text='" + text + '\'' +
                ", prec_1h='" + prec_1h + '\'' +
                ", clouds=" + clouds +
                ", vis=" + vis +
                ", aqi=" + aqi +
                ", pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", no2=" + no2 +
                ", so2=" + so2 +
                ", o3=" + o3 +
                ", co=" + co +
                ", uptime='" + uptime + '\'' +
                '}';
    }
}
