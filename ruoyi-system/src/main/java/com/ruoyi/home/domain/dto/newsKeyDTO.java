package com.ruoyi.home.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 新闻关键字
 * @date 2023-5-26
 */
@Data
public class newsKeyDTO implements Serializable {
    private Long newsId;
    private String newsTitle;
    private String newsKey;
}
