package com.ruoyi.article.service;

import com.ruoyi.article.domain.Article;
import com.ruoyi.article.domain.ArticleRecord;

import java.util.List;


/**
 * 文章Service接口
 * 
 * @author ruoyi
 * @date 2023-03-05
 */
public interface IArticleService 
{
    /**
     * 查询文章
     * 
     * @param articleId 文章主键
     * @return 文章
     */
    public Article selectArticleByArticleId(Long articleId);

    /**
     * 查询文章列表
     * 
     * @param article 文章
     * @return 文章集合
     */
    public List<Article> selectArticleList(Article article);

    /**
     * 新增文章
     * 
     * @param article 文章
     * @return 结果
     */
    public int insertArticle(Article article);

    /**
     * 修改文章
     * 
     * @param article 文章
     * @return 结果
     */
    public int updateArticle(Article article);

    /**
     * 批量删除文章
     * 
     * @param articleIds 需要删除的文章主键集合
     * @return 结果
     */
    public int deleteArticleByArticleIds(Long[] articleIds);

    /**
     * 删除文章信息
     * 
     * @param articleId 文章主键
     * @return 结果
     */
    public int deleteArticleByArticleId(Long articleId);

    /**
     * 点赞
     * @param record
     * @return 0 取消 / 1 点赞
     */
    public int articleAddCancelLike(ArticleRecord record);

    /**
     * 收藏
     * @param  record
     * @return 0 取消 / 1 收藏
     */
    public int articleAddCancelCollect(ArticleRecord record);

    /**
     * 增加浏览量
     * @return int
     */
    public int articleAddView(Long articleId);


}
