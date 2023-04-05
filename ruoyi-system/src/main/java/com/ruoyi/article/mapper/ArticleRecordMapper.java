package com.ruoyi.article.mapper;

import com.ruoyi.article.domain.ArticleRecord;
import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * @author chas
 * @introduction 点赞 浏览 收藏
 * @data 2023-3
 */
public interface ArticleRecordMapper{

    /**
     * 点赞
     * @param articleRecord
     * @return
     */
    @Insert("insert into article_record_like(article_id,user_id,create_time) " +
            "values (#{articleId},#{userId},#{createTime})")
    @ResultMap("com.ruoyi.article.mapper.ArticleMapper.ArticleRecordResult")
    public int addLikeArticle(ArticleRecord articleRecord);

    /**
     * 取消点赞
     * @param articleRecord
     * @return
     */
    @Delete("delete from article_record_like " +
            "where article_id = #{articleId} and user_id = #{userId} limit 1")
    @ResultMap("com.ruoyi.article.mapper.ArticleMapper.ArticleRecordResult")
    public int deleteLikeArticle(ArticleRecord articleRecord);

    /**
     * 判断是否唯一
     * @param articleRecord
     * @return
     */
    @Select("select count(0) from article_record_like " +
            "where article_id = #{articleId} and user_id = #{userId} limit 1")
    @ResultMap("com.ruoyi.article.mapper.ArticleMapper.ArticleRecordResult")
    public int judgeOnlyOneLikeArticle(ArticleRecord articleRecord);

    /**
     * 增加收藏
     * @param articleRecord
     * @return
     */
    @Insert("insert into article_record_collect(article_id,user_id,create_time) " +
            "values (#{articleId},#{userId},#{createTime}) ")
    @ResultMap("com.ruoyi.article.mapper.ArticleMapper.ArticleRecordResult")
    public int addCollectArticle(ArticleRecord articleRecord);

    /**
     * 取消收藏
     * @param articleRecord
     * @return
     */
    @Delete("delete from article_record_collect " +
            "where article_id = #{articleId} and user_id = #{userId} limit 1 ")
    @ResultMap("com.ruoyi.article.mapper.ArticleMapper.ArticleRecordResult")
    public int deleteCollectArticle(ArticleRecord articleRecord);
    /**
     * 判断是否唯一
     * @param articleRecord
     * @return
     */
    @Select("select count(0) from article_record_collect " +
            "where article_id = #{articleId} and user_id = #{userId} limit 1")
    @ResultMap("com.ruoyi.article.mapper.ArticleMapper.ArticleRecordResult")
    public int judgeOnlyOneCollectArticle(ArticleRecord articleRecord);

    /**
     * 增加浏览量
     * @param articleId
     * @return
     */
    @Insert("insert into article_record_view(article_id,user_id,create_time) " +
            "values (#{articleId},#{userId},#{createTime})")
    @ResultMap("com.ruoyi.article.mapper.ArticleMapper.ArticleRecordResult")
    public int addViewArticle(Long articleId);

    @Select("select count(0) from article_record_view " +
            "where article_id = #{articleId} and user_id = #{userId} limit 1")
    @ResultMap("com.ruoyi.article.mapper.ArticleMapper.ArticleRecordResult")
    public int judgeOnlyOneViewArticle(ArticleRecord articleRecord);

    @Update("update article_record_view set create_time = #{createTime} " +
            "where article_id = #{articleId} and user_id = #{userId} limit 1")
    @ResultMap("com.ruoyi.article.mapper.ArticleMapper.ArticleRecordResult")
    public int updateViewTime(Date createTime);






}
