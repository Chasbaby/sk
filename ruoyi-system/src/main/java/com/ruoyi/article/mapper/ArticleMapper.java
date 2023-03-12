package com.ruoyi.article.mapper;

import java.util.List;

import com.ruoyi.article.domain.Article;
import com.ruoyi.article.domain.dto.ArticleCreateDTO;
import com.ruoyi.common.core.mapper.BaseMapperPlus;

/**
 * 文章Mapper接口
 * 
 * @author chas ruoyi
 * @date 2023-03-05
 */
public interface ArticleMapper extends BaseMapperPlus<ArticleMapper,Article, ArticleCreateDTO>
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
     * 删除文章
     * 
     * @param articleId 文章主键
     * @return 结果
     */
    public int deleteArticleByArticleId(Long articleId);

    /**
     * 批量删除文章
     * @param articleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArticleByArticleIds(Long[] articleIds);

    /**
     * 全局搜索文章
     * @param article 文章
     * @return 列表
     */
    public List<Article> selectArticleInSearchList(Article article);

    /**
     * 获取用户收藏列表
     * @param userId
     * @return list
     */
    public List<Article> selectArticleCollectByUserId(Long userId);

    /**
     * 获取用户点赞列表
     * @param userId
     * @return list
     */
    public List<Article> selectArticleLikeByUserId(Long userId);

    /**
     * 获取用户浏览记录
     * @param userId
     * @return list
     */
    public List<Article> selectArticleViewByUserId(Long userId);

    /**
     * 增加景点 浏览量
     * @param article
     * @return
     */
    public int updateArticleView(Long article);

}
