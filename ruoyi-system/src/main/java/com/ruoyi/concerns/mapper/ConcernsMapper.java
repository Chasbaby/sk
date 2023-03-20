package com.ruoyi.concerns.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.concerns.domain.Concerns;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    @Insert("insert into concerns(main_user,prior_user) values (mainUser,priorUser)")
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

    public int setRemind(Long priorUser);
}

