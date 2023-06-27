package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysVisitor;
import org.apache.ibatis.annotations.Insert;

/**
 * @author chas
 * @introduction
 * @data
 */
public interface SysVisitorMapper {

    /**
     * 根据id 获取用户Visitor信息
     * @param userId
     * @return
     */
    public SysVisitor selectVisitorById(Long userId);

    /**
     * 为新用户创建初始化信息
     * @param userId id
     * @return
     */
    @Insert("insert into sys_visitor(user_id) values (#{useId})")
    public int insertVisitor(Long userId);




}
