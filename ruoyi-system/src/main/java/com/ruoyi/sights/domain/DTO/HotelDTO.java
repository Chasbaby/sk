package com.ruoyi.sights.domain.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction hotel dto
 * @date 2023-4
 */
@Data
public class HotelDTO implements Serializable {
    private Long hotelId;
    private Long sightsId;
    private String hotelName;
    private String hotelHotline;
    private String hotelAddress;
    private String hotelPhoto;
    private String hotelIntro;
}
