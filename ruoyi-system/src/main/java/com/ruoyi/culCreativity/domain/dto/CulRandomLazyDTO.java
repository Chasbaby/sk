package com.ruoyi.culCreativity.domain.dto;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction  瀑布流懒加载 ~~~~~~
 * @date  2023-5-30
 */
@Data
public class CulRandomLazyDTO implements Serializable {
    private Long culCreativityId;
    private Long sightsId;
    private Long userId;
    private String culCreativityTitle;
    private String culCreativityImage;
}
