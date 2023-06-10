package com.ruoyi.territorInfo.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction  区域 区记录
 * @date 2023-6-10
 */
@Data
public class TerritorAreaDTO implements Serializable {
    private Integer territorId;
    private String district;
    private String province;
}
