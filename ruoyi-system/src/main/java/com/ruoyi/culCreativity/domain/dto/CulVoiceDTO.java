package com.ruoyi.culCreativity.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction cul voice
 * @date 2023-4
 */
@Data
public class CulVoiceDTO implements Serializable {
    private Long culCreativityId;
    private String culCreativityIntroOUT;
    private String culCreativityContentOUT;
    private String speakTTS;
}
