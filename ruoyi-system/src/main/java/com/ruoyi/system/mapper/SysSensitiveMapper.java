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
    /**
     * 查询敏感词
     *
     * @param sensitiveId 敏感词主键
     * @return 敏感词
     */
    public SysSensitive selectSysSensitiveBySensitiveId(Long sensitiveId);

    /**
     * 查询敏感词列表
     *
     * @param sysSensitive 敏感词
     * @return 敏感词集合
     */
    public List<SysSensitive> selectSysSensitiveList(SysSensitive sysSensitive);

    /**
     * 新增敏感词
     *
     * @param sysSensitive 敏感词
     * @return 结果
     */
    public int insertSysSensitive(SysSensitive sysSensitive);

    /**
     * 修改敏感词
     *
     * @param sysSensitive 敏感词
     * @return 结果
     */
    public int updateSysSensitive(SysSensitive sysSensitive);

    /**
     * 删除敏感词
     *
     * @param sensitiveId 敏感词主键
     * @return 结果
     */
    public int deleteSysSensitiveBySensitiveId(Long sensitiveId);

    /**
     * 批量删除敏感词
     *
     * @param sensitiveIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysSensitiveBySensitiveIds(Long[] sensitiveIds);


}
