package com.ruoyi.excel.service.impl;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.excel.domain.UserVisualizationRecord;
import com.ruoyi.excel.domain.excelRecord;
import com.ruoyi.excel.mapper.excelRecordMapper;
import com.ruoyi.excel.service.UserVisualizationService;
import com.ruoyi.sights.domain.Excel.SightsUserBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.ruoyi.common.utils.file.FileUtils.isValidFilename;

/**
 * @author chas
 * @introduction  每日数据存储 与 信息可视化
 * @date 2023-4
 */
@Service
public class UserVisualizationServiceImpl implements UserVisualizationService {

    @Autowired
    private excelRecordMapper recordMapper;


    /**
     * 从excel种获取昨日数据并统计     这里计算的是景点单日统计数据
     */
    @Override
    public void countDataToday() {
        List<excelRecord> records = recordMapper.selectDayExcel();
        records.stream().forEach(item->{
            String fileName = item.getExcelName();
            boolean valid = isValidFilename(fileName);
            if (valid){
                // String sheetName = item.getSheetName();
                ExcelUtil<SightsUserBehavior> data = new ExcelUtil<>(SightsUserBehavior.class);
                FileInputStream inputStream = null;
                try {
                     inputStream = new FileInputStream(fileName);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                List<SightsUserBehavior> list = new ArrayList<>();
                try {
                     list = data.importExcel(inputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                UserVisualizationRecord record = new UserVisualizationRecord();

//                list.stream().collect(Collectors.groupingBy(SightsUserBehavior::getUserId,))

            }
        });
    }
}
