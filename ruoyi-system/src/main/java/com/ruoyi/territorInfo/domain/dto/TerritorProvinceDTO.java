package com.ruoyi.territorInfo.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 身份dto
 * @date 2023-6-10
 */
@Data
public class TerritorProvinceDTO implements Serializable {
    /** 区域id */
    private Integer territorId;
    private String province;
    private String cityGeocode;
    private String city;
}
