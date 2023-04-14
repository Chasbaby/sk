package com.ruoyi.sights.domain.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 推荐DTO
 * @date 2023-4
 */
@Data
public class SightsCountDTO implements Serializable {
    private Long sightsId;
    private Double sightsScore;
    private String sightsEng;
    private String sightsName;
    private String sightsCategory;
}
