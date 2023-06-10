package com.ruoyi.territorInfo.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction  区域市记录
 * @date 2023-6-10
 */
@Data
public class TerritorCityDTO implements Serializable {
    private Integer territorId;
    private String city;
    private String province;
    private String district;
    private String cityGeocode;
}
