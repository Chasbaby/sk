package com.ruoyi.home.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction new dto
 * @date 2023-5-25
 */
@Data
public class newsDTO implements Serializable {
    private Long newsId;
    private String newsTitle;
    private Integer newsType;
    private String newsContent;
    private String newsAuthor;
    private String imageId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
