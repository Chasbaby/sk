package com.ruoyi.article.mapper;

import java.util.List;

import com.ruoyi.article.domain.Article;
import com.ruoyi.article.domain.dto.*;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 文章Mapper接口
 * 
 * @author chas ruoyi
 * @date 2023-03-05
 */
public interface ArticleMapper
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
     * 个人信息中获取文章列表
     */
    public List<ArticleHomeDTO> selectArticleInPersonPage(@Param("userId") Long userId , @Param("way") Integer way);

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
    public List<ArticleReturnDTO> selectArticleCollectByUserId(Long userId);

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

    /**
     * 获取用户草稿文章
     * @param userId
     * @return
     */
    public List<ArticleReturnDTO> getArticleDraft(Long userId);

    /**
     * 获取用户所有的文章
     * @param userId
     * @param ways
     * @return
     */
    public List<ArticleStatusDTO> getAllArticlesByWays(@Param("userId") Long userId, @Param("ways") Integer ways);

    /**
     * 批量删除(逻辑删除)
     * @param articleIds
     * @return
     */
    public int deleteArticleBatches(Long[] articleIds);

    /**
     * 获取文章可视化数据 获取当天文章通过率
     * @return
     */
    public List<Article> getArticleData();

    @Select("select COUNT(0) from article where YEAR(create_time) = YEAR(curdate())")
    // 今年累计发布
    public Long getYearArtData();

    @Select("select COUNT(0) from article where DATE_FORMAT(create_time,'%Y-%m')= DATE_FORMAT(curdate(),'%Y-%m')")
    // 本月累计发布
    public Long getMonthArtData();

    @Select("select COUNT(0) from article where DATE_FORMAT(create_time,'YYYY-MM-DD') = curdate()")
    // 本日累计发布
    public Long getDayArtData();

    @Select("select COUNT(0) from article where YEAR(create_time) = YEAR(curdate()) and is_ok ='Y' ")
    // 年通过
    public Long getYearOKArtData();

    @Select("select COUNT(0) from article where YEAR(create_time) = YEAR(curdate()) and is_ok ='N'")
    // 年不通过
    public Long getYearNOArtData();


    /**
     * top15
     * @return
     */
    public List<Article> getArticleTop();

    public List<Article> simpleHotArticle();

    public List<ArticleShowDTO> selectRandomArticlesShowList();


}
