package com.ruoyi.territorInfo.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 国内数据对象 territor
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public class Territor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 区域id */
    private Integer territorId;

    /** 区域编码 */
    @Excel(name = "区域编码")
    private String areacode;

    /** 独立编码 */
    @Excel(name = "独立编码")
    private String districtcode;

    /** 城市地理编码 */
    @Excel(name = "城市地理编码")
    private String cityGeocode;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 独立地理编码 */
    @Excel(name = "独立地理编码")
    private String districtGeocode;

    /** 地区 */
    @Excel(name = "地区")
    private String district;

    /** 经度 */
    private BigDecimal lon;

    /** 纬度 */
    private BigDecimal lat;

    /** sta_fc */
    private String staFc;

    /** sta_rt */
    private String staRt;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** fc_lon */
    private BigDecimal fcLon;

    /** fc_lat */
    private BigDecimal fcLat;

    /** rt_lon */
    private BigDecimal rtLon;

    /** rt_lat */
    private BigDecimal rtLat;

    /** 原区域编码 */
    @Excel(name = "原区域编码")
    private String originAreacode;

    /** 主区 */
    @Excel(name = "主区")
    private Integer exclude;

    public void setTerritorId(Integer territorId) 
    {
        this.territorId = territorId;
    }

    public Integer getTerritorId() 
    {
        return territorId;
    }
    public void setAreacode(String areacode) 
    {
        this.areacode = areacode;
    }

    public String getAreacode() 
    {
        return areacode;
    }
    public void setDistrictcode(String districtcode) 
    {
        this.districtcode = districtcode;
    }

    public String getDistrictcode() 
    {
        return districtcode;
    }
    public void setCityGeocode(String cityGeocode) 
    {
        this.cityGeocode = cityGeocode;
    }

    public String getCityGeocode() 
    {
        return cityGeocode;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setDistrictGeocode(String districtGeocode) 
    {
        this.districtGeocode = districtGeocode;
    }

    public String getDistrictGeocode() 
    {
        return districtGeocode;
    }
    public void setDistrict(String district) 
    {
        this.district = district;
    }

    public String getDistrict() 
    {
        return district;
    }
    public void setLon(BigDecimal lon) 
    {
        this.lon = lon;
    }

    public BigDecimal getLon() 
    {
        return lon;
    }
    public void setLat(BigDecimal lat) 
    {
        this.lat = lat;
    }

    public BigDecimal getLat() 
    {
        return lat;
    }
    public void setStaFc(String staFc) 
    {
        this.staFc = staFc;
    }

    public String getStaFc() 
    {
        return staFc;
    }
    public void setStaRt(String staRt) 
    {
        this.staRt = staRt;
    }

    public String getStaRt() 
    {
        return staRt;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setFcLon(BigDecimal fcLon) 
    {
        this.fcLon = fcLon;
    }

    public BigDecimal getFcLon() 
    {
        return fcLon;
    }
    public void setFcLat(BigDecimal fcLat) 
    {
        this.fcLat = fcLat;
    }

    public BigDecimal getFcLat() 
    {
        return fcLat;
    }
    public void setRtLon(BigDecimal rtLon) 
    {
        this.rtLon = rtLon;
    }

    public BigDecimal getRtLon() 
    {
        return rtLon;
    }
    public void setRtLat(BigDecimal rtLat) 
    {
        this.rtLat = rtLat;
    }

    public BigDecimal getRtLat() 
    {
        return rtLat;
    }
    public void setOriginAreacode(String originAreacode) 
    {
        this.originAreacode = originAreacode;
    }

    public String getOriginAreacode() 
    {
        return originAreacode;
    }
    public void setExclude(Integer exclude) 
    {
        this.exclude = exclude;
    }

    public Integer getExclude() 
    {
        return exclude;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("territorId", getTerritorId())
            .append("areacode", getAreacode())
            .append("districtcode", getDistrictcode())
            .append("cityGeocode", getCityGeocode())
            .append("city", getCity())
            .append("districtGeocode", getDistrictGeocode())
            .append("district", getDistrict())
            .append("lon", getLon())
            .append("lat", getLat())
            .append("staFc", getStaFc())
            .append("staRt", getStaRt())
            .append("province", getProvince())
            .append("fcLon", getFcLon())
            .append("fcLat", getFcLat())
            .append("rtLon", getRtLon())
            .append("rtLat", getRtLat())
            .append("originAreacode", getOriginAreacode())
            .append("exclude", getExclude())
            .append("remark", getRemark())
            .toString();
    }
}
