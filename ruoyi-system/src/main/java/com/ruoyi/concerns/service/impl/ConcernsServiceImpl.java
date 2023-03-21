package com.ruoyi.concerns.service.impl;

import com.ruoyi.common.core.domain.entity.DTO.UserFCDTO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.concerns.mapper.ConcernsMapper;
import com.ruoyi.concerns.service.IConcernsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chas
 * @introduction 关注服务
 * @data 2023-3
 *
 * 未优化
 * 规定:  main  关注  prior
 */
@Service
public class ConcernsServiceImpl implements IConcernsService {

    @Autowired
    private ConcernsMapper concernsMapper;

    @Transactional
    @Override
    public int addConcerns(Long mainUser, Long priorUser) {
        // 判断是否已经关注 如果是1 说明已经关注了
        int flag = concernsMapper.judgeIfConcerns(mainUser, priorUser);
        if (flag==1){
            // 取消关注
            concernsMapper.reduceConcerns(mainUser,priorUser);
        }else {
            // 增加关注
            concernsMapper.addConcerns(mainUser,priorUser);
        }
        return flag;
    }

    /**
     * 展示粉丝列表
     * @param PriorUser
     * @return
     */
    @Override
    public List<UserFCDTO> showFans(Long PriorUser) {
        List<SysUser> sysUsers = concernsMapper.searchCountFans(PriorUser);
        List<UserFCDTO> userFCDTOS = new ArrayList<>();
        sysUsers.stream().forEach(item->{
            UserFCDTO userFCDTO = new UserFCDTO();
            BeanUtils.copyProperties(userFCDTO,item);
            userFCDTOS.add(userFCDTO);
        });
        return userFCDTOS;
    }

    /**
     * 展示关注列表
     * @param mainUser
     * @return
     */
    @Override
    public List<UserFCDTO> showConcerns(Long mainUser) {
        List<SysUser> sysUsers = concernsMapper.searchCountCon(mainUser);
        List<UserFCDTO> userFCDTOS = new ArrayList<>();
        sysUsers.stream().forEach(item->{
            UserFCDTO userFCDTO = new UserFCDTO();
            BeanUtils.copyProperties(userFCDTO,item);
            userFCDTOS.add(userFCDTO);
        });
        return userFCDTOS;
    }

    /**
     * 重置是否提醒
     * @param priorUser
     * @return
     */
    @Override
    public int setRemind(Long priorUser) {


        return 0;
    }


}
