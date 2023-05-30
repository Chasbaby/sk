package com.ruoyi.culCreativity.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.DTO.UserDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction
 * @data
 */
@Data
public class CulLazyDTO implements Serializable {
    private Long culCreativityId;
    private Long userId;
    private String culCreativityTitle;
    private String culCreativityIntro;
    private Long culCreativityLike;
    private Long culCreativityCollection;
    private Long culCreativityHits;
    private Long culCreativityView;
    private String culCreativityImage;
    private UserDTO user;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
