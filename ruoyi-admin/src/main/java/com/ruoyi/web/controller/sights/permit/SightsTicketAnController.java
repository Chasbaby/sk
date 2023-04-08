package com.ruoyi.web.controller.sights.permit;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sights.domain.DTO.TicketBackDTO;
import com.ruoyi.sights.domain.SightsTicket;
import com.ruoyi.sights.service.ISightsTicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @introduction 购票服务
 * @author Chas
 * @date 2023-4
 */
@Api("面向用户购票")
@RestController
@RequestMapping("/tickets")
public class SightsTicketAnController {

    @Autowired
    private ISightsTicketService sightsTicketService;

    @ApiOperation("购票")
    @PreAuthorize("@ss.hasRole('common')")
    @PostMapping("/bug")
    public AjaxResult submitInfo(@RequestBody TicketBackDTO ticket){
        Integer num = ticket.getBugNum();
        if ( num == 0){
            return AjaxResult.error("请选择购买数量,至少为1张");
        }
        if (num > 5){
            return AjaxResult.error("一次性至多购买5张门票,请重新选择");
        }
        Long ticketId = ticket.getTicketId();
        SightsTicket sightsTicket = sightsTicketService.selectSightsTicketByTicketId(ticketId);
        if (sightsTicket.getTicketNum() < num){
            return AjaxResult.error("购买数量不足，请等待补充后购买");
        }

        return AjaxResult.success(true);
    }


}
