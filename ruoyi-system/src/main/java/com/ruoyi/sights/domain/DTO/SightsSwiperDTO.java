package com.ruoyi.sights.domain.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 景点轮播展示
 * @date 2023 - 5 - 29
 */
@Data
public class SightsSwiperDTO implements Serializable {
    private Long sightsId;
    private String sightsName;
    private String sightsEng;
    private String sightsLocation;
    private Double sightsLongitude;
    private Double sightsLatitude;
    private String sightsImage;
    private String sightsNation;
    private String sightsCity;
}
