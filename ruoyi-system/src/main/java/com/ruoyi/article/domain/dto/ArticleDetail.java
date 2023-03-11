package com.ruoyi.article.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 文章详细信息
 * @data 2023-3
 */
public class ArticleDetail implements Serializable {
    /** 文章id */
    private Long articleId;

    /** 作者 */
    private Long userId;

    /** 作者部分信息 */
    private SysUser user;

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

    /** 点赞数 */
    private Long articleLike;

    /** 浏览量 */
    private Long articleView;

    /** 收藏量 */
    private Long articleCollect;

}
