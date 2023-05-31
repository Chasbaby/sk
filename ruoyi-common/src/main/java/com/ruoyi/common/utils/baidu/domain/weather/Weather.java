package com.ruoyi.common.utils.baidu.domain.weather;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 天气调用
 * @date 2023-5-31
 */
public class Weather implements Serializable {
    private Integer status;
    private WeatherResult result;
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public WeatherResult getResult() {
        return result;
    }

    public void setResult(WeatherResult result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "status=" + status +
                ", result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}
