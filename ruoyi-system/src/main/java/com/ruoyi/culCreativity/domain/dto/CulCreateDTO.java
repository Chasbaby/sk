package com.ruoyi.culCreativity.domain.dto;

import com.ruoyi.common.utils.validate.AddGroup;
import com.ruoyi.common.utils.validate.EditGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author chas
 * @introduction 创作CUL
 * @date 2023-3
 */
@Data
public class CulCreateDTO implements Serializable {
    //@NotBlank(message = "标题不能为空",groups = {AddGroup.class, EditGroup.class})
    private String culCreativityTitle;
    //@NotBlank(message = "简介不能为空",groups = {AddGroup.class, EditGroup.class})
    private String culCreativityIntro;
    //@NotBlank(message = "内容不能为空",groups = {AddGroup.class, EditGroup.class})
    private String culCreativityContent;

    //private String culCreativityTags;

    private String culCreativityImage;

    private String culCreativityCategory;

    private String culCreativityType;
    //@NotBlank(message = "状态不能为空",groups = {AddGroup.class, EditGroup.class})
    private String status;
}
