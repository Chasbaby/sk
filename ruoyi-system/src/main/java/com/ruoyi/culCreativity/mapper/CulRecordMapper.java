package com.ruoyi.culCreativity.mapper;

import com.ruoyi.culCreativity.domain.CulRecord;
import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * @author chas
 * @introduction 点赞 浏览 收藏
 * @data 2023-3
 */
public interface CulRecordMapper {

    /**
     * 点赞
     */
    @Insert("insert into cul_record_like(user_id,cul_creativity_id) " +
            "values (#{userId},#{culId})")
    public int addLikeCul(@Param("userId") Long userId,@Param("culId") Long culId);

    /**
     * 取消
     */
    @Delete("delete from cul_record_like " +
            "where user_id = #{userId} and cul_creativity_id=#{culId} limit 1")
    public int deleteLikeCul(@Param("userId") Long userId,@Param("culId") Long culId);

    /**
     * 判断唯一
     */
    @Select("select count(0) from cul_record_like " +
            "where user_id = #{userId} and cul_creativity_id=#{culId} limit 1 ")
    public int judgeOnlyOneLikeCul(@Param("userId") Long userId,@Param("culId") Long culId);

    /**
     * 下面是 收藏操作
     */
    @Insert("insert into cul_record_collect(user_id,cul_creativity_id)" +
            " values (#{userId},#{culId})")
    public int addCollectCul(@Param("userId") Long userId,@Param("culId") Long culId);

    @Delete("delete from cul_record_collect" +
            " where user_id = #{userId} and cul_creativity_id=#{culId} limit 1")
    public int deleteCollectCul(@Param("userId") Long userId,@Param("culId") Long culId);

    @Select("select count(0) from cul_record_collect " +
            "where user_id = #{userId} and cul_creativity_id = #{culId} limit 1")
    public int judgeOnlyOneCollectCul(@Param("userId") Long userId,@Param("culId") Long culId);

    /**
     * 下面是浏览操作
     */
    @Insert("insert into cul_record_view(user_id,cul_creativity_id) values (#{userId},#{culId}) ")
    public int addViewCul(@Param("userId") Long userId,@Param("culId") Long culId);

    @Update("update cul_record_view set create_time = #{createTime} " +
            "where user_id = #{userId} and cul_creativity_id=#{culId} limit 1")
    public int updateViewTime(@Param("createTime") Date createTime,@Param("userId") Long userId,@Param("culId") Long culId);

    @Select("select count(0) from cul_record_view where user_id = #{userId} and cul_creativity_id=#{culId} limit 1")
    public int judgeOnlyOneViewCul(@Param("userId") Long userId,@Param("culId") Long culId);
}
