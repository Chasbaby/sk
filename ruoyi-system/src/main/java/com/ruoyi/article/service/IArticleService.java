package com.ruoyi.article.service;

import com.ruoyi.article.domain.Article;
import com.ruoyi.article.domain.ArticleRecord;
import com.ruoyi.article.domain.dto.*;
import com.ruoyi.sights.domain.DTO.SightsStatisticTopDTO;

import java.util.List;


/**
 * 文章Service接口
 * 
 * @author ruoyi
 * @date 2023-03-05
 */
public interface IArticleService 
{
    public void xxx();
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
    public int articleAddView(ArticleRecord record);

    public int articleAddView(Long articleId);

    /**
     * 获取某用户的收藏信息
     * @param userId 用户id
     * @return 收藏列表
     */
    public List<ArticleReturnDTO> getAllArticleCollect(Long userId);

    /**
     * 获取某用户点赞信息
     * @param userId 用户
     * @return 文章列表
     */
    public List<ArticleReturnDTO> getAllArticleLike(Long userId);

    /**
     * 获取用户浏览记录
     * @param userId 用户id
     * @return 浏览列表
     */
    public List<ArticleReturnDTO> getAllArticleView(Long userId);

    /**
     * 获取某用户所有文章列表
     *
     * 可以未通过审核
     *
     * 0 是别人  1是自己
     *
     * @return
     */
    public List<ArticleHomeDTO> getAllArticleByUserId(Long userId,int way);

    /**
     * 获取文章详细信息
     * 包括的内容很多
     * @return
     */
    public ArticleDetail getArticleDetail(Long articleId,Long userId);

    /**
     * 获取文章稿件
     */
    public List<ArticleReturnDTO> getDraft(Long userId);

    /**
     * 获取各类文件
     * @param userId
     * @param ways
     * @return
     */
    public List<ArticleStatusDTO> getUserAllArticleByWays(Long userId,Integer ways);

    /**
     * 批量删除文章
     * @return
     */
    public int deleteArticlesByUser(Long[] articleIds);

    public ArticleCreateDTO reEditArticle(Long articleId);

    public Double getArticleRate();

    public ArticleStatisticPie getArticleData();

    public Long[] getJudgeData();

    public Long[] getArticleDMY();

    public List<ArticleTopDTO> getArticleTop();

    public ArticleVoiceDTO transReturn(Long id,Integer position,Long audioId);

    public List<ArticleHotDTO> simpleHotArticle();

    public List<ArticleShowDTO> getShowArticles();






}
