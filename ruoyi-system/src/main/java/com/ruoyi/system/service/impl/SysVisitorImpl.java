package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.DTO.UserVisitorDTO;
import com.ruoyi.common.core.domain.entity.DTO.VisitorDTO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.entity.SysVisitor;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.mapper.SysUserMapper;
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

    private SysUserMapper userMapper;
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

    /**
     * 获取展示资料
     * @param userId
     * @return
     */
    @Override
    public UserVisitorDTO getCreateIntro(Long userId) {
        UserVisitorDTO visitorDTO = new UserVisitorDTO();
        SysUser sysUser = userMapper.selectUserById(userId);
        BeanUtils.copyBeanProp(visitorDTO,sysUser);
        SysVisitor sysVisitor = visitorMapper.selectVisitorById(userId);
        VisitorDTO visitor = new VisitorDTO();
        BeanUtils.copyBeanProp(visitor,sysVisitor);
        visitorDTO.setVisitor(visitor);
        return visitorDTO;
    }
}
