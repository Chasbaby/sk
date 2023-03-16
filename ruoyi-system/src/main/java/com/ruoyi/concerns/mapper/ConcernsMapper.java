package com.ruoyi.concerns.mapper;

import com.ruoyi.concerns.domain.Concerns;
import org.apache.ibatis.annotations.Insert;
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
    @Insert(" ")
    public int addConcerns(Long[] userIds);
    /**
     * 减少关注
     */
    @Insert("")
    public int reduceConcerns(Long[] userIds);
    /**
     * 查询粉丝列表
     */
    @Select("")
    public List<Concerns> searchCountFans(Long userId);
    /**
     * 查询关注列表
     */
    @Select(" ")
    public List<Concerns> searchCountCon(Long userId);
}

