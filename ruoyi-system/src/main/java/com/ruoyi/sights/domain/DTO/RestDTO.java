package com.ruoyi.sights.domain.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction rest
 * @date 2023-4
 */
@Data
public class RestDTO implements Serializable {
    private Long restaurantId;
    private Long sightsId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantHotline;
    private String restaurantIntro;
    private String restaurantPhoto;
}
