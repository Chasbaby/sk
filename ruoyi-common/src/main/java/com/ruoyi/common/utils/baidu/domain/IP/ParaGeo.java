package com.ruoyi.common.utils.baidu.domain.IP;

import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 地理编码请求参数
 * @author Chas
 * @date 2023-1
 */
public class ParaGeo implements Serializable {

    /**
     * 待解析的地址。最多支持84个字节。
     * 可以输入两种样式的值，分别是：
     * 1、标准的结构化地址信息，如北京市海淀区上地十街十号 【推荐，地址结构越完整，解析精度越高】
     * 2、支持“*路与*路交叉口”描述方式，如北一环路和阜阳路的交叉路口
     * 第二种方式并不总是有返回结果，只有当地址库中存在该地址描述时才有返回。
     *
     * eg: 北京市海淀区上地十街10号
     */
    @NotNull
    private String address;

    /**
     * 地址所在的城市名。用于指定上述地址所在的城市，当多个城市都有上述地址时，
     * 该参数起到过滤作用，但不限制坐标召回城市。
     * eg: 	北京市
     */
    private String city;

    /**
     * 可选参数，添加后返回国测局经纬度坐标或百度米制坐标
     * enum 中 Coordinates
     */
    private String retCoordtype;

    /**
     * 用户申请注册的key，自v2开始参数修改为“ak”
     */
    @NotNull
    //@Value("${baidu.map.ak}")
    private String ak;

    /**
     * 若用户所用ak的校验方式为sn校验时该参数必须
     */
    private String sn;

    /**
     * json  / xml
     */
    private String output = "json";

    /**
     * 将json格式的返回值通过callback函数返回以实现jsonp功能
     *
     * eg:callback=showLocation(JavaScript函数名)
     */
    private String callback;

    /**
     * 是否触发解析到最小地址结构功能
     * extension_analys_level=1或true时，触发analys_level;字段返回参数；
     * extension_analys_level=0或false时；
     * analys_level字段不返回参数；
     */
    private String extensionAnalysLevel;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRetCoordtype() {
        return retCoordtype;
    }

    public void setRetCoordtype(String retCoordtype) {
        this.retCoordtype = retCoordtype;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getExtensionAnalysLevel() {
        return extensionAnalysLevel;
    }

    public void setExtensionAnalysLevel(String extensionAnalysLevel) {
        this.extensionAnalysLevel = extensionAnalysLevel;
    }

    public ParaGeo() {
    }

    public ParaGeo(String address, String city, String retCoordtype, String ak, String sn, String output,
                   String callback, String extensionAnalysLevel) {
        this.address = address;
        this.city = city;
        this.retCoordtype = retCoordtype;
        this.ak = ak;
        this.sn = sn;
        this.output = output;
        this.callback = callback;
        this.extensionAnalysLevel = extensionAnalysLevel;
    }

    /**
     * 百度地图请求参数
     * @return 请求参数
     */
    public String ObjectToPara(){
        return "address="+getAddress()
                +"&ak="+getAk()
                +(StringUtils.isEmpty(getOutput())?"":"&output="+getOutput())
                +(StringUtils.isEmpty(getCity())?"":"&city="+getCity())
                +(StringUtils.isEmpty(getRetCoordtype())?"":"&ret_coordtype="+getRetCoordtype())
                +(StringUtils.isEmpty(getCallback())?"":"&callback="+getCallback())
                +(StringUtils.isEmpty(getSn())?"":"&sn="+getSn())
                +(StringUtils.isEmpty(getExtensionAnalysLevel())?"":"&extension_analys_level="+getExtensionAnalysLevel());
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("address",getAddress())
                .append("city",getCity())
                .append("retCoordtype",getRetCoordtype())
                .append("ak",getAk())
                .append("sn",getSn())
                .append("output",getOutput())
                .append("callback",getCallback())
                .append("extensionAnalysLevel",getExtensionAnalysLevel())
                .toString();
    }
}
