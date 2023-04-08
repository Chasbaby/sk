package com.ruoyi.sights.domain.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction 购票信息
 * @date 2023-4
 */
@Data
public class TicketBackDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long ticketId;
    private Long sightsId;
    private Integer bugNum;
    private Date bugTime;

}
