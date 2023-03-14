package com.ruoyi.sights.service;

import java.util.List;

import com.ruoyi.sights.domain.DTO.TicketDTO;
import com.ruoyi.sights.domain.SightsTicket;

/**
 * 门票Service接口
 * 
 * @author ruoyi
 * @date 2023-01-04
 */
public interface ISightsTicketService 
{
    /**
     * 查询门票
     * 
     * @param ticketId 门票主键
     * @return 门票
     */
    public SightsTicket selectSightsTicketByTicketId(Long ticketId);

    /**
     * 查询门票列表
     * 
     * @param sightsTicket 门票
     * @return 门票集合
     */
    public List<SightsTicket> selectSightsTicketList(SightsTicket sightsTicket);

    /**
     * 新增门票
     * 
     * @param sightsTicket 门票
     * @return 结果
     */
    public int insertSightsTicket(SightsTicket sightsTicket);

    /**
     * 修改门票
     * 
     * @param sightsTicket 门票
     * @return 结果
     */
    public int updateSightsTicket(SightsTicket sightsTicket);

    /**
     * 批量删除门票
     * 
     * @param ticketIds 需要删除的门票主键集合
     * @return 结果
     */
    public int deleteSightsTicketByTicketIds(Long[] ticketIds);

    /**
     * 删除门票信息
     * 
     * @param ticketId 门票主键
     * @return 结果
     */
    public int deleteSightsTicketByTicketId(Long ticketId);

    /**
     * 获取某景点各种门票
     * @param sightsId
     * @return
     */
    public List<TicketDTO> selectTicketsBySightsId(Long sightsId);
}
