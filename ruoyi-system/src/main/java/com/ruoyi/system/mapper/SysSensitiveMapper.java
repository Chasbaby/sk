package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.SysSensitive;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 敏感词
 * @author Chas
 * @data 2023-3
 */
public interface SysSensitiveMapper extends BaseMapper<SysSensitive> {


    @Results(id = "SensitiveResult",value = {
            @Result(column = "sensitive_id" ,property = "sensitiveId"),
            @Result(column = "sensitive_word",property = "sensitiveWord"),
    })
    @Select("select sensitive_id , sensitive_word from sys_sensitive")
    public List<SysSensitive> selectAllSen();

//    @UpdateProvider( method="batchUpdate")
//    public void batchUpdate(List<SysSensitive> sysSensitives);

}
