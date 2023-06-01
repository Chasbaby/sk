package com.ruoyi.article.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 创作 DTO
 * @author Chas
 * @data 2023-3
 */
@Data
public class ArticleCreateDTO  implements Serializable {

    /** 文章id */
    private Long articleId;

    /** 作者 */
    private Long userId;

    /** 文章分类 */
    private String articleCategory;

    /** 文章缩略图 */
    private String articleCover;

    /** 文章标题 */
    private String articleTitle;

    /** 文章内容 */
    private String articleContent;

    /** 文章类型 1原创 2转载 3翻译 */
    private String articleType;

    /** 原文链接 */
    private String originalUrl;

    /** 状态值 1公开 2私密 3评论可见 */
    private String status;

    private String[] articleTags;

}
