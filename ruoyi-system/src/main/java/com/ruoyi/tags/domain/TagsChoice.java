package com.ruoyi.tags.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 标签选择对象 tags_choice
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public class TagsChoice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标签id */
    private Long tagsId;

    /** 标签内容 */
    @Excel(name = "标签内容")
    private String tagsContent;

    /** 标签类型 */
    @Excel(name = "标签类型")
    private String tagsType;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String ifDel;

    /** 是否禁用 */
    @Excel(name = "是否禁用")
    private String ifBan;

    public void setTagsId(Long tagsId) 
    {
        this.tagsId = tagsId;
    }

    public Long getTagsId() 
    {
        return tagsId;
    }
    public void setTagsContent(String tagsContent) 
    {
        this.tagsContent = tagsContent;
    }

    public String getTagsContent() 
    {
        return tagsContent;
    }
    public void setTagsType(String tagsType) 
    {
        this.tagsType = tagsType;
    }

    public String getTagsType() 
    {
        return tagsType;
    }
    public void setIfDel(String ifDel) 
    {
        this.ifDel = ifDel;
    }

    public String getIfDel() 
    {
        return ifDel;
    }
    public void setIfBan(String ifBan) 
    {
        this.ifBan = ifBan;
    }

    public String getIfBan() 
    {
        return ifBan;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tagsId", getTagsId())
            .append("tagsContent", getTagsContent())
            .append("tagsType", getTagsType())
            .append("ifDel", getIfDel())
            .append("ifBan", getIfBan())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
