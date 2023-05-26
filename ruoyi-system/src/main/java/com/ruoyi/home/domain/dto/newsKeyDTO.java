package com.ruoyi.home.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 新闻关键字
 * @date 2023-5-26
 */
@Data
@NoArgsConstructor
public class newsKeyDTO implements Serializable {
    private Long newsId;
    private String newsTitle;
    private String newsKey;

    public newsKeyDTO(Long newsId, String newsTitle, String newsKey) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsKey = newsKey;
    }

}
