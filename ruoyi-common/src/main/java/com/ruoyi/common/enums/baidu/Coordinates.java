package com.ruoyi.common.enums.baidu;

/**
 * 地理坐标类型
 *
 * @author Chas
 * @date 2023-1
 */
public enum Coordinates {
    GCJ02II("gcj02ll","国测局坐标"),
    BD09MC("bd09mc","百度墨卡托坐标");

    private final String code;
    private final String info;

    Coordinates(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public String getCode() {
        return code;
    }
}
