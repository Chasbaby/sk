package com.ruoyi.excel.mapper;

import com.ruoyi.excel.domain.excelRecord;

import java.util.List;

/**
 * @author chas
 * @introduction excel记录
 * @date 2023-4
 */
public interface excelRecordMapper {

    public int insertExcel(excelRecord record);

    public List<excelRecord> selectExcel(excelRecord record);

    /**
     * 获取当天数据 用于数据统计
     * @return 数据
     */
    public List<excelRecord> selectDayExcel();

}
