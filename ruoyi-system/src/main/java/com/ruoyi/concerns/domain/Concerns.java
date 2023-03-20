package com.ruoyi.concerns.domain;

import java.util.Date;

/**
 * @author chas
 * @introduction 关注   主 关注 副
 * @data 2023-3
 */
public class Concerns {
    private static final long serialVersionUID = 1L;

    /** 主用户*/
    private Long mainUser;
    /** 副用户*/
    private Long priorUser;

    /** 关注时间*/
    private Date createTime;

    /** 是否提醒*/
    private String ifRemind;


    public Long getMainUser() {
        return mainUser;
    }

    public void setMainUser(Long mainUser) {
        this.mainUser = mainUser;
    }

    public Long getPriorUser() {
        return priorUser;
    }

    public void setPriorUser(Long priorUser) {
        this.priorUser = priorUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIfRemind() {
        return ifRemind;
    }

    public void setIfRemind(String ifRemind) {
        this.ifRemind = ifRemind;
    }
}
