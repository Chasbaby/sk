package com.ruoyi.sights.domain.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chas
 * @introduction dto
 * @data 2023-3
 */
public class TicketDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long ticketId;
    private Long sightsId;
    private BigDecimal ticketPrice;
    private BigDecimal discount;
    private String selfService;
    private String ticketStatus;
    private Date ticketBegintime;
    private Date ticketEndtime;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getSightsId() {
        return sightsId;
    }

    public void setSightsId(Long sightsId) {
        this.sightsId = sightsId;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getSelfService() {
        return selfService;
    }

    public void setSelfService(String selfService) {
        this.selfService = selfService;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Date getTicketBegintime() {
        return ticketBegintime;
    }

    public void setTicketBegintime(Date ticketBegintime) {
        this.ticketBegintime = ticketBegintime;
    }

    public Date getTicketEndtime() {
        return ticketEndtime;
    }

    public void setTicketEndtime(Date ticketEndtime) {
        this.ticketEndtime = ticketEndtime;
    }
}
