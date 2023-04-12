package com.ruoyi.article.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction article dto
 * @date 2023-4
 */
@Data
public class ArticleVoiceDTO implements Serializable {
    private Long articleId;
    private String articleContentOUT;
    private String speakTTS;
}
