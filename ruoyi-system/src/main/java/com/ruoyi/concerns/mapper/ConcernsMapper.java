package com.ruoyi.concerns.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.concerns.domain.Concerns;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author chas
 * @introduction
 * @data
 */
public interface ConcernsMapper {

    /**
     * 增加关注
     */
    @Insert("insert into concerns(main_user,prior_user) values (#{mainUser},#{priorUser})")
    public int addConcerns(@Param("mainUser") Long mainUser,@Param("priorUser") Long priorUser );
    /**
     * 减少关注
     */
    @Delete("delete from concerns where main_user = #{mainUser} and prior_user = #{priorUser}")
    public int reduceConcerns(@Param("mainUser")Long mainUser ,@Param("priorUser")Long priorUser );

    /**
     * 查询粉丝列表
     */
    public List<SysUser> searchCountFans(Long userId);

    /**
     * 查询关注列表
     */
    public List<SysUser> searchCountCon(Long userId);

    /**
     * 检测是否关注
     * @return 1 0
     */
    @Select("select count(0) from concerns where main_user = #{mainUser} and prior_user = #{priorUser} limit 1")
    public int judgeIfConcerns(@Param("mainUser") Long mainUser, @Param("priorUser") Long priorUser);

    /**
     * 获取关注数量
     * @param mainUser
     * @return
     */
    @Select("select count(0) from concerns where main_user = #{mainUser}")
    public int getConcernsNum(Long mainUser);

    /**
     * 获取粉丝数量
     * @param priorUser
     * @return
     */
    @Select("select count(0) from concerns where prior_user = #{priorUser}")
    public int getFansNum(Long priorUser);

    /**
     * 重置提醒
     * @param priorUser
     * @return
     */
    public int setRemind(Long priorUser);

    /**
     * 查询用户是否有提醒
     * @param mainUser
     * @return
     */
    @Select("select count(0) from concerns where main_user = #{mainUser} and prior_user = 'Y' limit 1")
    public int checkUserRemind(Long mainUser);

    /** 提醒文创*/
    @Update("update concerns set cul_num = cul_num + 1 where prior_user= #{priorUser}")
    public int addCulNum(Long priorUser);

    /** 提醒文章*/
    @Update("update concerns set article_num = article_num + 1 where prior_user = #{priorUser} ")
    public int addArticleNum(Long priorUser);

    public int reduceArticleNum();


}

