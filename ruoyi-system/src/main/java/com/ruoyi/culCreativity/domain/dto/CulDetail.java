package com.ruoyi.culCreativity.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.DTO.UserDTO;
import com.ruoyi.sights.domain.DTO.SightsCulDTO;
import lombok.Data;

import java.util.Date;

/**
 * @author chas
 * @introduction culdto
 * @data 2023-3
 */
@Data
public class CulDetail {
    private static final long serialVersionUID = 1L;
    private Long culCreativityId;
    private Long sightsId;
    private SightsCulDTO sight;
    private Long userId;
    private UserDTO user;
    private String culCreativityTitle;
    private String culCreativityImage;
    private String culCreativityIntro;
    private String culCreativityContent;
    private String culCreativityCategory;
    private String[] culCreativityTags;
    private Long culCreativityLike;
    private Long culCreativityDislike;
    private Long culCreativityCollection;
    private Long culCreativityHits;
    private Long culCreativityView;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;



}
