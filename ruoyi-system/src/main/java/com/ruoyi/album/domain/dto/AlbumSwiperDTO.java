package com.ruoyi.album.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 专辑轮播
 * @date 2023-6-13
 */
@Data
public class AlbumSwiperDTO implements Serializable {
    private Long albumId;
    private String albumImage;
    private String albumIntroduce;
    private String albumName;
}
