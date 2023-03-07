package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 政策对象 sys_policy
 * 
 * @author ruoyi
 * @date 2022-08-09
 */
public class SysPolicy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 政策ID */
    private Long policyId;

    /** 政策主题 */
    @Excel(name = "政策主题")
    private String policyTitle;

    /** 政策内容 */
    private String policyContent;

    /** 政策类型 */
    @Excel(name = "政策类型")
    private Integer policyType;

    /** 适用范围 */
    @Excel(name = "适用范围")
    private Integer policyScope;

    /** 重要性程度(1-5) */
    @Excel(name = "重要性程度(1-5)")
    private Integer policyImportant;

    /** 法律支撑 */
    private String policyLaw;

    /** 开始执行时间 */
    private Date policyExecuteTime;

    /** 结束执行时间 */
    private Date policyEndingTime;

    public void setPolicyId(Long policyId) 
    {
        this.policyId = policyId;
    }

    public Long getPolicyId() 
    {
        return policyId;
    }
    public void setPolicyTitle(String policyTitle) 
    {
        this.policyTitle = policyTitle;
    }

    public String getPolicyTitle() 
    {
        return policyTitle;
    }
    public void setPolicyContent(String policyContent) 
    {
        this.policyContent = policyContent;
    }

    public String getPolicyContent() 
    {
        return policyContent;
    }
    public void setPolicyType(Integer policyType) 
    {
        this.policyType = policyType;
    }

    public Integer getPolicyType() 
    {
        return policyType;
    }
    public void setPolicyScope(Integer policyScope) 
    {
        this.policyScope = policyScope;
    }

    public Integer getPolicyScope() 
    {
        return policyScope;
    }
    public void setPolicyImportant(Integer policyImportant) 
    {
        this.policyImportant = policyImportant;
    }

    public Integer getPolicyImportant() 
    {
        return policyImportant;
    }
    public void setPolicyLaw(String policyLaw) 
    {
        this.policyLaw = policyLaw;
    }

    public String getPolicyLaw() 
    {
        return policyLaw;
    }
    public void setPolicyExecuteTime(Date policyExecuteTime) 
    {
        this.policyExecuteTime = policyExecuteTime;
    }

    public Date getPolicyExecuteTime() 
    {
        return policyExecuteTime;
    }
    public void setPolicyEndingTime(Date policyEndingTime) 
    {
        this.policyEndingTime = policyEndingTime;
    }

    public Date getPolicyEndingTime() 
    {
        return policyEndingTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("policyId", getPolicyId())
            .append("policyTitle", getPolicyTitle())
            .append("policyContent", getPolicyContent())
            .append("policyType", getPolicyType())
            .append("policyScope", getPolicyScope())
            .append("policyImportant", getPolicyImportant())
            .append("policyLaw", getPolicyLaw())
            .append("policyExecuteTime", getPolicyExecuteTime())
            .append("policyEndingTime", getPolicyEndingTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
