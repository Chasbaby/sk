package com.ruoyi.sights.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

/**
 * @author chas
 * @introduction 综合记录
 * @data 2023-3
 */
public interface SightsRecordMapper {


    /**
     * 插入浏览记录
     * @param userId
     * @param sightsId
     * @return
     */
    @Insert("insert into sights_record_history(user_id,sights_id) values ( #{userId},#{sightsId}) ")
    public int addView(@Param("userId") Long userId, @Param("sightsId") Long sightsId);

    /**
     * 插入点击记录
     * @param userId
     * @param sightsId
     * @return
     */
    @Insert("insert into sights_record_hits(user_id,sights_id) values ( #{userId},#{sightsId})")
    public int addHits(@Param("userId") Long userId, @Param("sightsId") Long sightsId);


    /**
     * 插入点赞记录
     * @param userId
     * @param sightsId
     * @return
     */
    @Insert("insert into sights_record_like(user_id,sights_id) values ( #{userId},#{sightsId})")
    public int addLike(@Param("userId") Long userId, @Param("sightsId") Long sightsId);

    /**
     * 删除点赞
     * @param userId
     * @param sightsId
     * @return
     */
    @Delete("delete from sights_record_like where user_id = #{userId} and sights_id = #{sightsId} limit 1")
    public int deleteLike(@Param("userId") Long userId, @Param("sightsId") Long sightsId);
    /**
     * 插入评分记录
     * @param userId
     * @param sightsId
     * @param score
     * @return
     */
    @Insert("insert into sights_record_score(user_id,sights_id,score) values ( #{userId},#{sightsId},#{score})")
    public int addScore(@Param("userId") Long userId, @Param("sightsId") Long sightsId,@Param("score") Double score);

    /**
     * 更新评分
     * @param score
     * @param userId
     * @param sightsId
     * @return
     */
    @Update("update sights_record_score set score = #{score} where user_id = #{userId} and sights_id = #{sightId}")
    public int updateScore(@Param("score") Double score, @Param("userId") Long userId, @Param("sightsId") Long sightsId);

    /**
     * 增加收藏记录
     * @param userId
     * @param sightsId
     * @return
     */
    @Insert("insert into sights_user_collect(user_id,sights_id) values (#{userId},#{sightsId})")
    public int addCollect(@Param("userId") Long userId, @Param("sightsId") Long sightsId);

    @Delete("delete from sights_user_collect where where user_id = #{userId} and sights_id = #{sightsId} limit 1")
    public int deleteCollect(@Param("userId") Long userId, @Param("sightsId") Long sightsId);
    /**
     * 判断是否点赞
     * @param userId
     * @param sightsId
     * @return
     */
    @Select("select count(0) from sights_record_like where user_id = #{userId} and sights_id = #{sightsId} limit 1")
    public int judgeLike(@Param("userId") Long userId, @Param("sightsId") Long sightsId);

    /**
     * 判断是否评分
     * @param userId
     * @param sightsId
     * @return
     */
    @Select("select count(0) from sights_record_score where user_id = #{userId} and sights_id = #{sightsId} limit 1")
    public int judgeScore(@Param("userId") Long userId, @Param("sightsId") Long sightsId);

    /**
     * 判断是否收藏
     * @param userId
     * @param sightsId
     * @return
     */
    @Select("select count(0) from sights_user_collect where user_id = #{userId} and sights_id = #{sightsId} limit 1")
    public int judgeCollect(@Param("userId") Long userId, @Param("sightsId") Long sightsId);


}
