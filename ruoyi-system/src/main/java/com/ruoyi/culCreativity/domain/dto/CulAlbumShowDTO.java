package com.ruoyi.culCreativity.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction album 搜索条件
 * @date 2023-6-14
 */
@Data
public class CulAlbumShowDTO implements Serializable{
    private Long albumId;
    private Long culCreativityId;
    private Long sightsId;
    private Long userId;
    private String culCreativityTitle;
    private String culCreativityIntro;
    private String culCreativityCategory;
    private String culCreativityType;
    private String culCreativityTags;
    private Boolean IfChoice;
}
