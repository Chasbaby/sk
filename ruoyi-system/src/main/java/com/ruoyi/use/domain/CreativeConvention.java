package com.ruoyi.use.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 创作公约对象 creative_convention
 * 
 * @author ruoyi
 * @date 2023-01-31
 */
public class CreativeConvention extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long conventionId;

    /** 选项卡标题 */
    @Excel(name = "选项卡标题")
    private String conventionLabel;

    /** 选项卡别名 */
    @Excel(name = "选项卡别名")
    private String conventionName;

    @Excel(name = "具体内容")
    /** 具体内容 */
    private String conventionContent;

    /**图标*/
    private String conventionIcon;
    /** 是否禁用 */
    @Excel(name = "是否禁用")
    private String conventionDisabled;

    /** 标签是否可关闭 */
    @Excel(name = "标签是否可关闭")
    private String conventionClosable;

    /** 标签是否延迟渲染 */
    @Excel(name = "标签是否延迟渲染")
    private String conventionLazy;

    public String getConventionIcon() {
        return conventionIcon;
    }

    public void setConventionIcon(String conventionIcon) {
        this.conventionIcon = conventionIcon;
    }

    public void setConventionId(Long conventionId)
    {
        this.conventionId = conventionId;
    }

    public Long getConventionId() 
    {
        return conventionId;
    }
    public void setConventionLabel(String conventionLabel) 
    {
        this.conventionLabel = conventionLabel;
    }

    public String getConventionLabel() 
    {
        return conventionLabel;
    }
    public void setConventionName(String conventionName) 
    {
        this.conventionName = conventionName;
    }

    public String getConventionName() 
    {
        return conventionName;
    }
    public void setConventionContent(String conventionContent) 
    {
        this.conventionContent = conventionContent;
    }

    public String getConventionContent() 
    {
        return conventionContent;
    }
    public void setConventionDisabled(String conventionDisabled) 
    {
        this.conventionDisabled = conventionDisabled;
    }

    public String getConventionDisabled() 
    {
        return conventionDisabled;
    }
    public void setConventionClosable(String conventionClosable) 
    {
        this.conventionClosable = conventionClosable;
    }

    public String getConventionClosable() 
    {
        return conventionClosable;
    }
    public void setConventionLazy(String conventionLazy) 
    {
        this.conventionLazy = conventionLazy;
    }

    public String getConventionLazy() 
    {
        return conventionLazy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("conventionId", getConventionId())
            .append("conventionLabel", getConventionLabel())
            .append("conventionName", getConventionName())
            .append("conventionIcon",getConventionIcon())
            .append("conventionContent", getConventionContent())
            .append("conventionDisabled", getConventionDisabled())
            .append("conventionClosable", getConventionClosable())
            .append("conventionLazy", getConventionLazy())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
