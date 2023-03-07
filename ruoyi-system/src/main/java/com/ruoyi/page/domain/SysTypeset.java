package com.ruoyi.page.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 动态排版对象 sys_typeset
 * 
 * @author ruoyi
 * @date 2023-02-04
 */
public class SysTypeset extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 排版ID */
    private Long typesetId;

    /** 展示图片 */
    @Excel(name = "展示图片")
    private String typesetImage;

    /** 页面 */
    @Excel(name = "页面")
    private Integer typesetPage;

    /** 位置 */
    @Excel(name = "位置")
    private Integer typesetPosition;

    /** 介绍 */
    @Excel(name = "介绍")
    private String typesetIntroduction;

    /** 富文本 */
    private String typesetText;

    /** 附加文字 */
    @Excel(name = "附加文字")
    private String typesetAttachment;

    /** 路由 */
    @Excel(name = "路由")
    private String typesetRoute;

    /** 是否打开 */
    @Excel(name = "是否打开")
    private String flag;

    /** 更新者 */
    private String updayeBy;

    public void setTypesetId(Long typesetId) 
    {
        this.typesetId = typesetId;
    }

    public Long getTypesetId() 
    {
        return typesetId;
    }
    public void setTypesetImage(String typesetImage) 
    {
        this.typesetImage = typesetImage;
    }

    public String getTypesetImage() 
    {
        return typesetImage;
    }
    public void setTypesetPage(Integer typesetPage) 
    {
        this.typesetPage = typesetPage;
    }

    public Integer getTypesetPage() 
    {
        return typesetPage;
    }
    public void setTypesetPosition(Integer typesetPosition) 
    {
        this.typesetPosition = typesetPosition;
    }

    public Integer getTypesetPosition() 
    {
        return typesetPosition;
    }
    public void setTypesetIntroduction(String typesetIntroduction) 
    {
        this.typesetIntroduction = typesetIntroduction;
    }

    public String getTypesetIntroduction() 
    {
        return typesetIntroduction;
    }
    public void setTypesetText(String typesetText) 
    {
        this.typesetText = typesetText;
    }

    public String getTypesetText() 
    {
        return typesetText;
    }
    public void setTypesetAttachment(String typesetAttachment) 
    {
        this.typesetAttachment = typesetAttachment;
    }

    public String getTypesetAttachment() 
    {
        return typesetAttachment;
    }
    public void setTypesetRoute(String typesetRoute) 
    {
        this.typesetRoute = typesetRoute;
    }

    public String getTypesetRoute() 
    {
        return typesetRoute;
    }
    public void setFlag(String flag) 
    {
        this.flag = flag;
    }

    public String getFlag() 
    {
        return flag;
    }
    public void setUpdayeBy(String updayeBy) 
    {
        this.updayeBy = updayeBy;
    }

    public String getUpdayeBy() 
    {
        return updayeBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("typesetId", getTypesetId())
            .append("typesetImage", getTypesetImage())
            .append("typesetPage", getTypesetPage())
            .append("typesetPosition", getTypesetPosition())
            .append("typesetIntroduction", getTypesetIntroduction())
            .append("typesetText", getTypesetText())
            .append("typesetAttachment", getTypesetAttachment())
            .append("typesetRoute", getTypesetRoute())
            .append("flag", getFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updayeBy", getUpdayeBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
