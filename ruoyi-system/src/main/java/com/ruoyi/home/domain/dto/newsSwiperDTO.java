package com.ruoyi.home.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction swiper
 * @date 2023-6-25
 */
@Data
public class newsSwiperDTO implements Serializable {
    private Long newsId;
    private String imageId;
    private String newsAuthor;
    private String newsTitle;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String newsIntroduction;
}
