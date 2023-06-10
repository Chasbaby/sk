package com.ruoyi.common.utils.baidu.domain.weather;

import com.ruoyi.common.utils.StringUtils;

import java.io.Serializable;

/***
 * 参数名称	参数含义	默认值	字段类型	必选
 * district_id 区县的行政区划编码，和location二选一     无   string	否
 * location 经纬度，经度在前纬度在后，逗号分隔。支持类型：bd09mc/bd09ll/wgs84/gcj02。开通高级权限后才能使用  无	double	否
 * ak 开发者密钥，可在API控制台申请获得 无	string	是
 * data_type	请求数据类型。数据类型有：now/fc/index/alert/fc_hour/all，控制返回内容	无	string	是
 * output	返回格式，目前支持json/xml	json	string	否
 * coordtype	支持类型:wgs84/bd09ll/bd09mc/gcj02	wgs84	string	否
 */

/**
 * @author chas
 * @introduction 国内天气预报
 * @date 2023-5-31
 */


public class DomesticWeather implements Serializable{
    String districtId;
    Double location;
    String ak;
    String dataType;
    String output = "json";
    String coordtype;


    public DomesticWeather() {}

    public DomesticWeather(String districtId, Double location, String ak, String dataType, String output, String coordtype) {
        this.districtId = districtId;
        this.location = location;
        this.ak = ak;
        this.dataType = dataType;
        this.output = output;
        this.coordtype = coordtype;
    }

    // todo 待补充
    public String ObjectToPara(){
        return "district_id=" +getDistrictId()
                +"&ak="+getAk()
                +((StringUtils.isEmpty(getOutput()))?"":"&output="+getOutput())
                +((StringUtils.isEmpty(getDataType()))?"":"&data_type="+getDataType())
                ;
    }


    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public Double getLocation() {
        return location;
    }

    public void setLocation(Double location) {
        this.location = location;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getCoordtype() {
        return coordtype;
    }

    public void setCoordtype(String coordtype) {
        this.coordtype = coordtype;
    }

    @Override
    public String toString() {
        return "DomesticWeather{" +
                "districtId='" + districtId + '\'' +
                ", location=" + location +
                ", ak='" + ak + '\'' +
                ", dataType='" + dataType + '\'' +
                ", output='" + output + '\'' +
                ", coordtype='" + coordtype + '\'' +
                '}';
    }
}
