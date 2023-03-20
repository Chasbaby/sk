package com.ruoyi.concerns.service;

import com.ruoyi.common.core.domain.entity.DTO.UserFCDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chas
 * @introduction 关注服务
 * @data 2023-3
 */
public interface IConcernsService {


    /**
     *  规定:  main  关注  prior
     * @param mainUser
     * @param priorUser
     * @return
     */
    public int addConcerns(Long mainUser,Long priorUser);


    /**
     * 传我的id
     *
     * 展示粉丝列表
     * @param priorUser
     * @return
     */
    public List<UserFCDTO> showFans(Long priorUser);

    /**
     * 传我的id 作为 副
     *
     * 展示关注列表
     * @param mainUser
     * @return
     */
    public List<UserFCDTO> showConcerns(Long mainUser);

    /**
     * 设置提醒
     * @param priorUser
     * @return
     */
    public int setRemind(Long priorUser);
}
