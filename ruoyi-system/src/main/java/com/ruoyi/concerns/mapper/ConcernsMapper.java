package com.ruoyi.concerns.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.concerns.domain.Concerns;
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

    public int addConcerns(Long mainUser,Long priorUser );
    /**
     * 减少关注
     */

    public int reduceConcerns(Long mainUser ,Long priorUser );

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
    @Select("select count(0) from concerns where main_user = #{mainUser} and priorUser = #{priorUser} limit 1")
    public int judgeIfConcerns(@Param("mainUser") Long mainUser, @Param("priorUser") Long priorUser);

    public int setRemind(Long priorUser);
}

