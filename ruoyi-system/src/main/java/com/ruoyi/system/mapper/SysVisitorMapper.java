package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysVisitor;
import org.apache.ibatis.annotations.Insert;

/**
 * @author chas
 * @introduction
 * @data
 */
public interface SysVisitorMapper {



    public SysVisitor selectVisitorById(Long userId);

    @Insert("insert into sys_visitor(user_id) values (#{useId})")
    public int insertVisitor(Long userId);


}
