package com.ruoyi.sights.domain.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction
 * @date 2023-3
 */
@Data
public class SightsRandomDTO implements Serializable {

    private Long sightsId;
    private String sightsName;
    private String sightsLocation;
    private String sightsIntro;
    private String sightsImage;

}
