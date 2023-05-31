package com.ruoyi.common.utils.baidu.domain.weather.inside;

import java.io.Serializable;

/**
 *
 * type	String	预警事件类型	data_type=alert/all	暂无	高级字段
 * level	String	预警事件等级	data_type=alert/all	暂无	高级字段
 * title	String	预警标题	data_type=alert/all	-	高级字段
 * desc	String	预警详细提示信息	data_type=alert/all	-	高级字段
 */

/**
 * @author chas
 * @introduction 气象预警数据
 * @date 2023-5-31
 */
public class alert implements Serializable {
    private  String type;
    private  String level;
    private  String title;
    private  String desc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "alert{" +
                "type='" + type + '\'' +
                ", level='" + level + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
