package com.ruoyi.home.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction
 * @date
 */
@Data
public class newsRandomDTO implements Serializable {
    private Long newsId;
    private String newsTitle;
    private Integer newsType;
}
