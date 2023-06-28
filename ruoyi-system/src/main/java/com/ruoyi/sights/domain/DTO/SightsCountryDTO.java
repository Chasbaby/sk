package com.ruoyi.sights.domain.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 国家分布
 * @date 2023-6-28
 */
@Data
public class SightsCountryDTO implements Serializable {
    private String name;
    private Integer value;
}
