package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysSensitive;
import com.ruoyi.system.mapper.SysSensitiveMapper;
import com.ruoyi.system.service.ISysSensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chas
 * @introduction
 * @data
 */
@Service
public class SysSensitiveServiceImpl implements ISysSensitiveService {

    @Autowired
    private SysSensitiveMapper sysSensitiveMapper;

    @Override
    public List<SysSensitive> selectAll() {
        return sysSensitiveMapper.selectAllSen();
    }

    /**
     * 查询敏感词
     *
     * @param sensitiveId 敏感词主键
     * @return 敏感词
     */
    @Override
    public SysSensitive selectSysSensitiveBySensitiveId(Long sensitiveId)
    {
        return sysSensitiveMapper.selectSysSensitiveBySensitiveId(sensitiveId);
    }

    /**
     * 查询敏感词列表
     *
     * @param sysSensitive 敏感词
     * @return 敏感词
     */
    @Override
    public List<SysSensitive> selectSysSensitiveList(SysSensitive sysSensitive)
    {
        return sysSensitiveMapper.selectSysSensitiveList(sysSensitive);
    }

    /**
     * 新增敏感词
     *
     * @param sysSensitive 敏感词
     * @return 结果
     */
    @Override
    public int insertSysSensitive(SysSensitive sysSensitive)
    {
        return sysSensitiveMapper.insertSysSensitive(sysSensitive);
    }

    /**
     * 修改敏感词
     *
     * @param sysSensitive 敏感词
     * @return 结果
     */
    @Override
    public int updateSysSensitive(SysSensitive sysSensitive)
    {
        return sysSensitiveMapper.updateSysSensitive(sysSensitive);
    }

    /**
     * 批量删除敏感词
     *
     * @param sensitiveIds 需要删除的敏感词主键
     * @return 结果
     */
    @Override
    public int deleteSysSensitiveBySensitiveIds(Long[] sensitiveIds)
    {
        return sysSensitiveMapper.deleteSysSensitiveBySensitiveIds(sensitiveIds);
    }

    /**
     * 删除敏感词信息
     *
     * @param sensitiveId 敏感词主键
     * @return 结果
     */
    @Override
    public int deleteSysSensitiveBySensitiveId(Long sensitiveId)
    {
        return sysSensitiveMapper.deleteSysSensitiveBySensitiveId(sensitiveId);
    }
}
