package com.ruoyi.common.utils.baidu.domain.IP;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 地理编码
 * @author Chas
 * @date 2023-1
 */
public class Geocoder implements Serializable {
    /**
     * 返回结果状态值， 成功返回0，其他值请查看enums中的GeocoderType 返回码状态表。
     */
    private int status;
    /**
     * 映射结果
     */
    private GeocoderResultMap GeocoderResultMap;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public GeocoderResultMap getResult() {
        return GeocoderResultMap;
    }

    public void setResult(GeocoderResultMap GeocoderResultMap) {
        this.GeocoderResultMap = GeocoderResultMap;
    }

    public Geocoder(int status, GeocoderResultMap GeocoderResultMap) {
        this.status = status;
        this.GeocoderResultMap = GeocoderResultMap;
    }

    public Geocoder() {
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("status",getStatus())
                .append("resultMap",getResult())
                .toString();
    }


}
