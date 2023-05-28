package com.ruoyi.article.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 各自检索
 * @date 2023-5-28
 */
@Data
public class ArticleSearchPersonDTO implements Serializable {
    private Long articleId;
    private Long userId;
    private String articleTitle;
    private Long articleView;

}
