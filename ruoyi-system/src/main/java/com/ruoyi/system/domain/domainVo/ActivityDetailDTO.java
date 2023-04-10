package com.ruoyi.system.domain.domainVo;

import com.ruoyi.common.core.domain.entity.DTO.UserDTO;
import com.ruoyi.sights.domain.DTO.SightsRandomDTO;
import com.ruoyi.system.domain.SysActivity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 详细信息
 * @date 2023-4
 */
@Data
public class ActivityDetailDTO implements Serializable {

    SysActivity activity;
    SightsRandomDTO sights;
    UserDTO user;
}
