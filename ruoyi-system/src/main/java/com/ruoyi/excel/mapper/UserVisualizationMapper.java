package com.ruoyi.excel.mapper;

import com.ruoyi.excel.domain.UserVisualizationRecord;

import java.util.List;

/**
 * @author chas
 * @introduction 每日统计 将excel文件的数据 计算后统计保存于此，用于echarts的展示
 * @date 2023-3
 */
public interface UserVisualizationMapper{

    /** 插入每次记录信息*/
    public int insertVis(UserVisualizationRecord record);

    /** 获取星期数据 */
    public List<UserVisualizationRecord> getWeekRecordByUserId(Long userId);

    /** 获取本月数据  */
    public List<UserVisualizationRecord>  getMonthRecordByUserId(Long userId);

    /** 获取本年数据  */
    public List<UserVisualizationRecord> getYearRecordByUserId(Long userId);



}
