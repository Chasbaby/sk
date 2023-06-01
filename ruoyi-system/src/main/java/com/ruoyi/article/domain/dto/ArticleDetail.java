package com.ruoyi.article.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.DTO.UserDTO;
import com.ruoyi.common.core.domain.entity.DTO.VisitorDTO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.entity.SysVisitor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction 文章详细信息
 * @data 2023-3   2023-6-1
 */
@Data
public class ArticleDetail implements Serializable {
    /** 文章id */
    private Long articleId;

    /** 作者 */
    private Long userId;

    /** 作者部分信息 */
    private UserDTO user;

    /** 作者部分信息*/
    private VisitorDTO visitor;

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

    private Integer ifLike;

    private Integer ifCollect;

    private String[] articleTags;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
