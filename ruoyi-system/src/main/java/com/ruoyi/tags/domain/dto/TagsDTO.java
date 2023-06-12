package com.ruoyi.tags.domain.dto;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 标签显示
 * @date 2023-6-12
 */
@Data
public class TagsDTO implements Serializable {
    /** 标签id */
    private Long tagsId;

    /** 标签内容 */
    @Excel(name = "标签内容")
    private String tagsContent;
}
