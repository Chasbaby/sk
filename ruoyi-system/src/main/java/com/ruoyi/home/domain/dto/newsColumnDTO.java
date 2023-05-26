package com.ruoyi.home.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction 新闻专栏
 * @date 2023-5-26
 */
@Data
public class newsColumnDTO implements Serializable {
    private Long newsId;
    private String newsTitle;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
