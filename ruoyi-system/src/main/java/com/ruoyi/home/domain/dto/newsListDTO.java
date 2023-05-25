package com.ruoyi.home.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction new list dto
 * @date 2023-6-25
 */
@Data
public class newsListDTO implements Serializable {
    private Long newsId;
    private String newsTitle;
    private Integer newsType;
    private String newsAuthor;
    private String imageId;
    private String newsIntroduction;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
