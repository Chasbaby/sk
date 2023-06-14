package com.ruoyi.album.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction album info
 * @date 2023-6-14
 */
@Data
public class AlbumInfoDTO implements Serializable {
    private Long albumId;
    private String albumName;
    private String albumImage;
    private String albumTitle;
    private String albumSlogan;
    private String albumIntroduce;
}
