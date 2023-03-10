package com.ruoyi.article.mapper;

import com.ruoyi.article.domain.ArticleRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    public int addLikeArticle(ArticleRecord articleRecord);

    /**
     * 取消点赞
     * @param articleRecord
     * @return
     */
    @Delete("delete from article_record_like " +
            "where article_id = #{articleId} and user_id = #{userId} limit 1")
    public int deleteLikeArticle(ArticleRecord articleRecord);

    /**
     * 判断是否唯一
     * @param articleRecord
     * @return
     */
    @Select("select 1 from article_record_like " +
            "where article_id = #{articleId} and user_id = #{userId} limit 1")
    public int judgeOnlyOneLikeArticle(ArticleRecord articleRecord);

    /**
     * 增加收藏
     * @param articleRecord
     * @return
     */
    @Insert("insert into article_record_collect(article_id,user_id,create_time) " +
            "values (#{articleId},#{userId},#{createTime}) ")
    public int addCollectArticle(ArticleRecord articleRecord);

    /**
     * 取消收藏
     * @param articleRecord
     * @return
     */
    @Delete("delete from article_record_collect " +
            "where article_id = #{articleId} and user_id = #{userId} limit 1 ")
    public int deleteCollectArticle(ArticleRecord articleRecord);
    /**
     * 判断是否唯一
     * @param articleRecord
     * @return
     */
    @Select("select 1 from article_record_collect " +
            "where article_id = #{articleId} and user_id = #{userId} limit 1")
    public int judgeOnlyOneCollectArticle(ArticleRecord articleRecord);

    /**
     * 增加浏览量
     * @param articleId
     * @return
     */
    @Update("update article set article_view = article_view + 1 " +
            "where articleId = #{articleId} limit 1 ")
    public int addViewArticle(Long articleId);






}
