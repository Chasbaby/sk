package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysSensitive;
import com.ruoyi.system.mapper.SysSensitiveMapper;
import com.ruoyi.system.service.ISysSensitiveService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chas
 * @introduction
 * @data
 */
public class SysSensitiveServiceImpl implements ISysSensitiveService {

    @Autowired
    private SysSensitiveMapper sysSensitiveMapper;
    @Override
    public List<SysSensitive> selectAll() {
        return sysSensitiveMapper.selectAllSen();
    }
}
