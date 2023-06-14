package com.ruoyi.album.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 专辑内部信息
 * @date 2023-6-14
 */
@Data
public class AlbumCulDTO implements Serializable {
    private Long culCreativityId;
    private Long sightsId;
    private Long userId;
    private String culCreativityTitle;
    private String culCreativityImage;
}
