package com.ruoyi.concerns.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.DTO.UserFCDTO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.concerns.mapper.ConcernsMapper;
import com.ruoyi.concerns.service.IConcernsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
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

    @Autowired
    private RedisCache redisCache;

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
        Collection<String> keys = redisCache.keys(Constants.LOGIN_TOKEN_KEY + "*");
        List<SysUser> sysUsers = concernsMapper.searchCountFans(PriorUser);
        List<UserFCDTO> userFCDTOS = new ArrayList<>();
        sysUsers.stream().forEach(item->{
            UserFCDTO userFCDTO = new UserFCDTO();
            BeanUtils.copyProperties(item,userFCDTO);
            setIfLogin(keys, userFCDTOS, item, userFCDTO);
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
        Collection<String> keys = redisCache.keys(Constants.LOGIN_TOKEN_KEY + "*");
        List<SysUser> sysUsers = concernsMapper.searchCountCon(mainUser);
        List<UserFCDTO> userFCDTOS = new ArrayList<>();
        sysUsers.stream().forEach(item->{
            UserFCDTO userFCDTO = new UserFCDTO();
            BeanUtils.copyProperties(item,userFCDTO);
            userFCDTO.setFlag(true);
            setIfLogin(keys, userFCDTOS, item, userFCDTO);
        });
        return userFCDTOS;
    }

    /**
     * 在线用户高亮
     * @param keys
     * @param userFCDTOS
     * @param item
     * @param userFCDTO
     */
    private void setIfLogin(Collection<String> keys, List<UserFCDTO> userFCDTOS, SysUser item, UserFCDTO userFCDTO) {
        userFCDTO.setIfLoginIng(false);
        for (String key : keys) {
            LoginUser user = redisCache.getCacheObject(key);
            if (item.getUserName().equals(user.getUser().getUserName())){
                userFCDTO.setIfLoginIng(true);
                break;
            }
        }
        userFCDTOS.add(userFCDTO);
        userFCDTOS.sort((o1, o2) -> o1.getIfLoginIng() ? -1 : 1);
    }

    /**
     * 重置是否提醒
     * @param priorUser
     * @return
     */
    @Override
    public int setRemind(Long priorUser) {
        concernsMapper.setRemind(priorUser);
        return 0;
    }

    /**
     * 查询是否有提醒
     * @param mainUser
     * @return
     */
    @Override
    public int checkUserRemind(Long mainUser) {
        int i = concernsMapper.checkUserRemind(mainUser);
        return i;
    }

    /**
     * 核心
     * 获取主页用户信息 和 相关状态信息
     * @param userId
     */
    public void getMainPageUserInfo(Long userId){}


}
