package com.ruoyi.article.domain;

import java.util.Date;

import cn.easyes.annotation.IndexField;
import cn.easyes.annotation.IndexId;
import cn.easyes.annotation.IndexName;
import cn.easyes.annotation.rely.Analyzer;
import cn.easyes.annotation.rely.FieldStrategy;
import cn.easyes.annotation.rely.FieldType;
import cn.easyes.annotation.rely.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章对象 article
 * 
 * @author ruoyi
 * @date 2023-03-05
 */
@Data
@IndexName(value = "article",shardsNum = 3,replicasNum = 2)
public class Article extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @IndexId(type = IdType.CUSTOMIZE)
    @Excel(name = "文章id",type = Excel.Type.EXPORT,cellType = Excel.ColumnType.NUMERIC)
    /** 文章id */
    private Long articleId;

    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    @Excel(name = "作者",type = Excel.Type.ALL,cellType = Excel.ColumnType.NUMERIC )
    /** 作者id */
    private Long userId;

    @IndexField(fieldType = FieldType.KEYWORD,fieldData = true,strategy = FieldStrategy.NOT_EMPTY)
    /** 文章分类 */
    @Excel(name = "文章分类")
    private String articleCategory;

    @IndexField(strategy = FieldStrategy.NOT_EMPTY,fieldType = FieldType.TEXT)
    /** 文章缩略图 */
    @Excel(name = "文章缩略图",type = Excel.Type.ALL,cellType = Excel.ColumnType.IMAGE)
    private String articleCover;

    /** 文章标题 */
    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    @Excel(name = "文章标题")
    private String articleTitle;

    /** 文章内容 */
    @IndexField(fieldType = FieldType.KEYWORD_TEXT,
            fieldData = true,
            analyzer = Analyzer.IK_SMART,
            searchAnalyzer = Analyzer.IK_MAX_WORD,
            strategy = FieldStrategy.NOT_EMPTY)
    @Excel(name = "文章内容")
    private String articleContent;

    @IndexField(fieldType = FieldType.TEXT)
    /** 文章类型 1原创 2转载 3翻译 */
    @Excel(name = "文章类型",dictType = "article_type")
    private String articleType;

    @IndexField(fieldType = FieldType.TEXT)
    /** 原文链接 */
    @Excel(name = "原文链接",type = Excel.Type.ALL,cellType = Excel.ColumnType.STRING)
    private String originalUrl;

    /** 点赞数 */
    @IndexField(exist = false)
    @Excel(name = "点赞数",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    private Long articleLike;

    /** 浏览量 */
    @IndexField(exist = false)
    @Excel(name = "浏览量",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    private Long articleView;

    /** 收藏量 */

    @IndexField(exist = false)
    @Excel(name = "收藏量",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    private Long articleCollect;

    @IndexField(exist = false)
    /** 是否置顶 0否 1是 */
    private String isTop;


    /** 是否删除 0否 1是 */
    @Excel(name = "删除标志",dictType = "sys_yes_no",type = Excel.Type.EXPORT)
    private String isDelete;

    /** 审核是否通过 */
    @Excel(name = "审核标志",dictType = "all-judge_type",type = Excel.Type.EXPORT)
    private String isOk;


    /** 状态值 1公开 2私密 3评论可见 */

    @Excel(name = "状态值",dictType = "article_state",type = Excel.Type.ALL)
    private String status;

    @Excel(name = "tags")
    private String articleTags;

    @IndexField(exist = false)
    /** 审核者 */
    @Excel(name = "审核者",cellType = Excel.ColumnType.STRING,type = Excel.Type.EXPORT)
    private String judgeBy;
    @IndexField(exist = false)
    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd",type = Excel.Type.EXPORT)
    private Date judgeTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("articleId", getArticleId())
            .append("userId", getUserId())
            .append("articleCategory", getArticleCategory())
            .append("articleCover", getArticleCover())
            .append("articleTitle", getArticleTitle())
            .append("articleContent", getArticleContent())
            .append("articleType", getArticleType())
            .append("originalUrl", getOriginalUrl())
            .append("articleLike", getArticleLike())
            .append("articleView", getArticleView())
            .append("articleCollect", getArticleCollect())
            .append("isTop", getIsTop())
            .append("isDelete", getIsDelete())
            .append("isOk", getIsOk())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("judgeBy", getJudgeBy())
            .append("judgeTime", getJudgeTime())
            .toString();
    }
}
