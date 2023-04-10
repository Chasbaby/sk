package com.ruoyi.system.domain.domainVo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction 活动
 * @date 2023-4
 */
@Data
public class ActivityPersonDTO implements Serializable {
    private Long activityId;
    private Long sightsId;
    private Long userId;
    private String source;
    private String activityTitle;
    private String activityIntroduction;
    private String activityImage;
}
