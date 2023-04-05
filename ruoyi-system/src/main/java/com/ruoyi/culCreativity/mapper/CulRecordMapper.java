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
    @ResultMap("com.ruoyi.sights.mapper.SightsCulCreativityMapper.CulRecordResult")
    public int addLikeCul(CulRecord record);

    /**
     * 取消
     */
    @Delete("delete from cul_record_like " +
            "where user_id = #{userId} and cul_creativity_id=#{culId} limit 1")
    @ResultMap("com.ruoyi.sights.mapper.SightsCulCreativityMapper.CulRecordResult")
    public int deleteLikeCul(CulRecord record);

    /**
     * 判断唯一
     */
    @Select("select count(0) from cul_record_like " +
            "where user_id = #{userId} and cul_creativity_id=#{culId} limit 1 ")
    @ResultMap("com.ruoyi.sights.mapper.SightsCulCreativityMapper.CulRecordResult")
    public int judgeOnlyOneLikeCul(CulRecord record);

    /**
     * 下面是 收藏操作
     */
    @Insert("insert into cul_record_collect(user_id,cul_creativity_id)" +
            " values (#{userId},#{culId})")
    @ResultMap("com.ruoyi.sights.mapper.SightsCulCreativityMapper.CulRecordResult")
    public int addCollectCul(CulRecord record);

    @Delete("delete from cul_record_collect" +
            " where user_id = #{userId} and cul_creativity_id=#{culId} limit 1")
    @ResultMap("com.ruoyi.sights.mapper.SightsCulCreativityMapper.CulRecordResult")
    public int deleteCollectCul(CulRecord record);

    @Select("select count(0) from cul_record_collect " +
            "where user_id = #{userId} and cul_creativity_id=#{culId} limit 1")
    @ResultMap("com.ruoyi.sights.mapper.SightsCulCreativityMapper.CulRecordResult")
    public int judgeOnlyOneCollectCul(CulRecord record);

    /**
     * 下面是浏览操作
     */
    @Insert("insert into cul_record_view(user_id,cul_creativity_id) values (#{userId},#{culId}) ")
    @ResultMap("com.ruoyi.sights.mapper.SightsCulCreativityMapper.CulRecordResult")
    public int addViewCul(CulRecord record);

    @Update("update cul_record_view set create_time = #{createTime} " +
            "where user_id = #{userId} and cul_creativity_id=#{culId} limit 1")
    @ResultMap("com.ruoyi.sights.mapper.SightsCulCreativityMapper.CulRecordResult")
    public int updateViewTime(Date createTime);

    @Select("select count(0) from cul_record_view where user_id = #{userId} and cul_creativity_id=#{culId} limit 1")
    @ResultMap("com.ruoyi.sights.mapper.SightsCulCreativityMapper.CulRecordResult")
    public int judgeOnlyOneViewCul(CulRecord record);
}
