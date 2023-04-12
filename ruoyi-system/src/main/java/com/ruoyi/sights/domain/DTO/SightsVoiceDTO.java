package com.ruoyi.sights.domain.DTO;

import lombok.Data;

/**
 * @author chas
 * @introduction 景点文章dto
 * @date 2023-4
 */
@Data
public class SightsVoiceDTO {
    private Long sightsId;

    private String sightsDetailOUT;

    private String sightsIntroOUT;

    private String speakTTS;
}
