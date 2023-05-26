package com.ruoyi.culCreativity.domain.dto;

import com.ruoyi.common.utils.validate.AddGroup;
import com.ruoyi.common.utils.validate.EditGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author chas
 * @introduction 创作CUL
 * @date 2023-3
 */
public class CulCreateDTO implements Serializable {
    @NotBlank(message = "标题不能为空",groups = {AddGroup.class, EditGroup.class})
    private String culCreativityTitle;
    @NotBlank(message = "简介不能为空",groups = {AddGroup.class, EditGroup.class})
    private String culCreativityIntro;
    @NotBlank(message = "内容不能为空",groups = {AddGroup.class, EditGroup.class})
    private String culCreativityContent;

    private String culCreativityImage;

    private String culCreativityCategory;

    private String culCreativityType;
    @NotBlank(message = "状态不能为空",groups = {AddGroup.class, EditGroup.class})
    private String status;

    public String getCulCreativityTitle() {
        return culCreativityTitle;
    }

    public void setCulCreativityTitle(String culCreativityTitle) {
        this.culCreativityTitle = culCreativityTitle;
    }

    public String getCulCreativityIntro() {
        return culCreativityIntro;
    }

    public void setCulCreativityIntro(String culCreativityIntro) {
        this.culCreativityIntro = culCreativityIntro;
    }

    public String getCulCreativityContent() {
        return culCreativityContent;
    }

    public void setCulCreativityContent(String culCreativityContent) {
        this.culCreativityContent = culCreativityContent;
    }

    public String getCulCreativityImage() {
        return culCreativityImage;
    }

    public void setCulCreativityImage(String culCreativityImage) {
        this.culCreativityImage = culCreativityImage;
    }

    public String getCulCreativityCategory() {
        return culCreativityCategory;
    }

    public void setCulCreativityCategory(String culCreativityCategory) {
        this.culCreativityCategory = culCreativityCategory;
    }

    public String getCulCreativityType() {
        return culCreativityType;
    }

    public void setCulCreativityType(String culCreativityType) {
        this.culCreativityType = culCreativityType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
