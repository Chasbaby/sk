package com.ruoyi.system.domain.domainVo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction list
 * @date 2023-4
 */
@Data
public class ActivityListDTO implements Serializable {
    /** 活动id */
    private Long activityId;

    /** 景点id */
    private Long sightsId;

    /** 用户id */
    private Long userId;

    private String source;

    private String activityTitle;

    private String activityIntroduction;

    private Long activityHot;

    private String activityPublisher;
}
