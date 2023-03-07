package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysSensitive;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 敏感词
 * @author Chas
 * @data 2023-3
 */
public interface SysSensitiveMapper {

    @Select("select * from sys_sensitive")
    public List<SysSensitive> selectAllSen();

}
