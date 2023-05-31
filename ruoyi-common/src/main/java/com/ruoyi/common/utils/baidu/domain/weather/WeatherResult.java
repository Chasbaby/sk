package com.ruoyi.common.utils.baidu.domain.weather;

import com.ruoyi.common.utils.baidu.domain.weather.inside.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author chas
 * @introduction 天气数据结果集
 * @date 2023-5-31
 */
public class WeatherResult implements Serializable {
    private address ADDRESS;
    private now NOW;
    private List<alert> ALERT;
    private List<indexes> INDEXES;
    private List<forecasts> FORECASTS;

    public address getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(address ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public now getNOW() {
        return NOW;
    }

    public void setNOW(now NOW) {
        this.NOW = NOW;
    }

    public List<alert> getALERT() {
        return ALERT;
    }

    public void setALERT(List<alert> ALERT) {
        this.ALERT = ALERT;
    }

    public List<indexes> getINDEXES() {
        return INDEXES;
    }

    public void setINDEXES(List<indexes> INDEXES) {
        this.INDEXES = INDEXES;
    }

    public List<forecasts> getFORECASTS() {
        return FORECASTS;
    }

    public void setFORECASTS(List<forecasts> FORECASTS) {
        this.FORECASTS = FORECASTS;
    }

    @Override
    public String toString() {
        return "WeatherResult{" +
                "ADDRESS=" + ADDRESS +
                ", NOW=" + NOW +
                ", ALERT=" + ALERT +
                ", INDEXES=" + INDEXES +
                ", FORECASTS=" + FORECASTS +
                '}';
    }
}
