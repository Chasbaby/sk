package com.ruoyi.article.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 文章热度
 * @date 2023-5-28
 */
@Data
public class ArticleHotDTO implements Serializable {
    private Long articleId;
    private Long userId;
    private String articleTitle;
}
