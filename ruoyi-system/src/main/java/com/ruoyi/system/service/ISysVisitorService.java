package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.DTO.UserVisitorDTO;
import com.ruoyi.common.core.domain.entity.SysVisitor;

/**
 * @author chas
 * @introduction
 * @data 2023-3
 */
public interface ISysVisitorService {

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
    public int insertVisitor(Long userId);

    /**
     * 获取订阅文创个人信息
     * @param userId
     * @return
     */
    public UserVisitorDTO getCreateIntro(Long userId);

}
