package com.ruoyi.sights.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 门票对象 sights_ticket
 * 
 * @author ruoyi
 * @date 2023-01-04
 */
public class SightsTicket extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 门票ID */
    private Long ticketId;

    /** 景点id */
    @Excel(name = "景点id")
    private Long sightsId;

    /** 门票数量 */
    @Excel(name = "门票数量")
    private Integer ticketNum;

    /** 门票价格 */
    @Excel(name = "门票价格")
    private BigDecimal ticketPrice;

    /** 门票独立编码开头 */
    private String ticketCode;

    /** 折扣 */
    @Excel(name = "折扣")
    private BigDecimal discount;

    /** 专享服务介绍 */
    private String selfService;

    /** 票状态(0可购买 1不可购买 2已购完) */
    @Excel(name = "票状态(0可购买 1不可购买 2已购完)")
    private String ticketStatus;

    /** 门票开始使用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "门票开始使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ticketBegintime;

    /** 门票的截止使用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "门票的截止使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ticketEndtime;

    public void setTicketId(Long ticketId) 
    {
        this.ticketId = ticketId;
    }

    public Long getTicketId() 
    {
        return ticketId;
    }
    public void setSightsId(Long sightsId) 
    {
        this.sightsId = sightsId;
    }

    public Long getSightsId() 
    {
        return sightsId;
    }
    public void setTicketNum(Integer ticketNum) 
    {
        this.ticketNum = ticketNum;
    }

    public Integer getTicketNum() 
    {
        return ticketNum;
    }
    public void setTicketPrice(BigDecimal ticketPrice) 
    {
        this.ticketPrice = ticketPrice;
    }

    public BigDecimal getTicketPrice() 
    {
        return ticketPrice;
    }
    public void setTicketCode(String ticketCode) 
    {
        this.ticketCode = ticketCode;
    }

    public String getTicketCode() 
    {
        return ticketCode;
    }
    public void setDiscount(BigDecimal discount)
    {
        this.discount = discount;
    }

    public BigDecimal getDiscount()
    {
        return discount;
    }
    public void setSelfService(String selfService) 
    {
        this.selfService = selfService;
    }

    public String getSelfService() 
    {
        return selfService;
    }
    public void setTicketStatus(String ticketStatus) 
    {
        this.ticketStatus = ticketStatus;
    }

    public String getTicketStatus() 
    {
        return ticketStatus;
    }
    public void setTicketBegintime(Date ticketBegintime) 
    {
        this.ticketBegintime = ticketBegintime;
    }

    public Date getTicketBegintime() 
    {
        return ticketBegintime;
    }
    public void setTicketEndtime(Date ticketEndtime) 
    {
        this.ticketEndtime = ticketEndtime;
    }

    public Date getTicketEndtime() 
    {
        return ticketEndtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ticketId", getTicketId())
            .append("sightsId", getSightsId())
            .append("ticketNum", getTicketNum())
            .append("ticketPrice", getTicketPrice())
            .append("ticketCode", getTicketCode())
            .append("discount", getDiscount())
            .append("selfService", getSelfService())
            .append("ticketStatus", getTicketStatus())
            .append("ticketBegintime", getTicketBegintime())
            .append("ticketEndtime", getTicketEndtime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
