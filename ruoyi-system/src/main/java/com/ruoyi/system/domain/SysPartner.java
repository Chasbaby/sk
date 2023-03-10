package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合作伙伴对象 sys_partner
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
public class SysPartner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合作伙伴id */
    private Long partnerId;

    /** 伙伴介绍 */
    @Excel(name = "伙伴介绍")
    private String partnerIntro;

    /** 伙伴logo */
    @Excel(name = "伙伴logo")
    private String pertnerLogo;

    /** 合作核心事项 */
    @Excel(name = "合作核心事项")
    private String cooperationCore;

    /** 合作开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合作开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cooperationBegin;

    /** 合作结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合作结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cooperationEnd;

    /** 合作文档 */
    @Excel(name = "合作文档")
    private String cooperationDocument;

    public void setPartnerId(Long partnerId) 
    {
        this.partnerId = partnerId;
    }

    public Long getPartnerId() 
    {
        return partnerId;
    }
    public void setPartnerIntro(String partnerIntro) 
    {
        this.partnerIntro = partnerIntro;
    }

    public String getPartnerIntro() 
    {
        return partnerIntro;
    }
    public void setPertnerLogo(String pertnerLogo) 
    {
        this.pertnerLogo = pertnerLogo;
    }

    public String getPertnerLogo() 
    {
        return pertnerLogo;
    }
    public void setCooperationCore(String cooperationCore) 
    {
        this.cooperationCore = cooperationCore;
    }

    public String getCooperationCore() 
    {
        return cooperationCore;
    }
    public void setCooperationBegin(Date cooperationBegin) 
    {
        this.cooperationBegin = cooperationBegin;
    }

    public Date getCooperationBegin() 
    {
        return cooperationBegin;
    }
    public void setCooperationEnd(Date cooperationEnd) 
    {
        this.cooperationEnd = cooperationEnd;
    }

    public Date getCooperationEnd() 
    {
        return cooperationEnd;
    }
    public void setCooperationDocument(String cooperationDocument) 
    {
        this.cooperationDocument = cooperationDocument;
    }

    public String getCooperationDocument() 
    {
        return cooperationDocument;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("partnerId", getPartnerId())
            .append("partnerIntro", getPartnerIntro())
            .append("pertnerLogo", getPertnerLogo())
            .append("cooperationCore", getCooperationCore())
            .append("cooperationBegin", getCooperationBegin())
            .append("cooperationEnd", getCooperationEnd())
            .append("cooperationDocument", getCooperationDocument())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
