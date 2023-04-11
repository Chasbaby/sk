package com.ruoyi.excel.mapper;

import com.ruoyi.excel.domain.excelRecord;

import java.util.List;

/**
 * @author chas
 * @introduction excel记录  文件
 * @date 2023-4
 */
public interface excelRecordMapper {

    /**
     * 插入数据
     * @param record
     * @return
     */
    public int insertExcel(excelRecord record);

    /**
     * 按照要求获取数据
     * @param record
     * @return
     */
    public List<excelRecord> selectExcel(excelRecord record);

    /**
     * 获取当天数据 用于数据统计定时任务可能不止一个减轻redis压力
     * @return 数据
     */
    public List<excelRecord> selectDayExcel();

}
