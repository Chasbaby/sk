package com.ruoyi.excel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction 每日数据存储
 * @date 2023-4
 */
@Data
public class UserVisualizationRecord implements Serializable {

    private Long visualizationId;
    private Long userId;
    private Long subscribe;
    private Long fans;
    private Long concerns;
    private Long like;
    private Long collect;
    private Long view;
    private Long comments;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date record;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
