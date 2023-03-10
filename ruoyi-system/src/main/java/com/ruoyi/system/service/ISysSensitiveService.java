package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysSensitive;

import java.util.List;

/**
 @introduction  敏感词服务
 @author chas
 @data 202-3
 */

public interface ISysSensitiveService {

    public List<SysSensitive> selectAll();

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
