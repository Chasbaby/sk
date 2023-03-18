package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.SysVisitor;
import com.ruoyi.system.mapper.SysVisitorMapper;
import com.ruoyi.system.service.ISysVisitorService;
import org.springframework.stereotype.Service;

/**
 * @author chas
 * @introduction
 * @data
 */
@Service
public class SysVisitorImpl implements ISysVisitorService {

    private SysVisitorMapper visitorMapper;
    /**
     * 根据id 获取用户Visitor信息
     * @param userId
     * @return
     */
    @Override
    public SysVisitor selectVisitorById(Long userId) {
        return visitorMapper.selectVisitorById(userId);
    }
    /**
     * 为新用户创建初始化信息
     * @param userId id
     * @return
     */
    @Override
    public int insertVisitor(Long userId) {
        return visitorMapper.insertVisitor(userId);
    }
}
