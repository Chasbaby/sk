package com.ruoyi.sights.domain.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 景点单独搜索
 * @date 2023-5-29
 */
@Data
public class SightsSearchPersonDTO implements Serializable {
    private Long sightsId;
    private String sightsName;
    private String sightsEng;
    private String sightsLocation;
    private String sightsNation;
    private String sightsCity;
}
