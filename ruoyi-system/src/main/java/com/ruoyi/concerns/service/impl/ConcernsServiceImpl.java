package com.ruoyi.concerns.service.impl;

import com.ruoyi.common.core.domain.entity.DTO.UserFCDTO;
import com.ruoyi.concerns.mapper.ConcernsMapper;
import com.ruoyi.concerns.service.IConcernsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<UserFCDTO> showFans(Long PriorUser) {
        return null;
    }

    @Override
    public List<UserFCDTO> showConcerns(Long mainUser) {
        return null;
    }

    @Override
    public int setRemind(Long priorUser) {
        return 0;
    }


}
