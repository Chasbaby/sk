package com.ruoyi.home.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 新闻专栏
 * @date 2023-5-26
 */
@Data
public class newsColumnDTO implements Serializable {
    private Long newsId;
    private String newsTitle;
}
