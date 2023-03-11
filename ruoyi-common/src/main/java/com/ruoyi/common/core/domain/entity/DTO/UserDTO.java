package com.ruoyi.common.core.domain.entity.DTO;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 传给前端的用户数据
 * @data 2023-3
 */
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String nickName;
    private String avatar;

}
