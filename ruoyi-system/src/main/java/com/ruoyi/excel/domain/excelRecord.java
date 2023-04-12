package com.ruoyi.excel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction excel存储
 * @date 2023-3
 */
@Data
public class excelRecord implements Serializable {
    private Long excelId;
    private String excelName;
    // 类型(景点0 文创1 文章2)
    private String type;
    private Integer titleName;
    private String sheetName;
    private String ifOk;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
