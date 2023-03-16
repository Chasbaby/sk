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
    private Long main_user;
    /** 副用户*/
    private Long prior_user;

    /** 关注时间*/
    private Date createTime;

    /** 是否提醒*/
    private String ifRemind;
}
