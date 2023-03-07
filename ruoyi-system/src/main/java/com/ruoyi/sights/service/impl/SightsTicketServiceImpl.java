package com.ruoyi.sights.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sights.mapper.SightsTicketMapper;
import com.ruoyi.sights.domain.SightsTicket;
import com.ruoyi.sights.service.ISightsTicketService;

/**
 * 门票Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-04
 */
@Service
public class SightsTicketServiceImpl implements ISightsTicketService 
{
    @Autowired
    private SightsTicketMapper sightsTicketMapper;

    /**
     * 查询门票
     * 
     * @param ticketId 门票主键
     * @return 门票
     */
    @Override
    public SightsTicket selectSightsTicketByTicketId(Long ticketId)
    {
        return sightsTicketMapper.selectSightsTicketByTicketId(ticketId);
    }

    /**
     * 查询门票列表
     * 
     * @param sightsTicket 门票
     * @return 门票
     */
    @Override
    public List<SightsTicket> selectSightsTicketList(SightsTicket sightsTicket)
    {
        return sightsTicketMapper.selectSightsTicketList(sightsTicket);
    }

    /**
     * 新增门票
     * 
     * @param sightsTicket 门票
     * @return 结果
     */
    @Override
    public int insertSightsTicket(SightsTicket sightsTicket)
    {
        sightsTicket.setCreateTime(DateUtils.getNowDate());
        return sightsTicketMapper.insertSightsTicket(sightsTicket);
    }

    /**
     * 修改门票
     * 
     * @param sightsTicket 门票
     * @return 结果
     */
    @Override
    public int updateSightsTicket(SightsTicket sightsTicket)
    {
        sightsTicket.setUpdateTime(DateUtils.getNowDate());
        return sightsTicketMapper.updateSightsTicket(sightsTicket);
    }

    /**
     * 批量删除门票
     * 
     * @param ticketIds 需要删除的门票主键
     * @return 结果
     */
    @Override
    public int deleteSightsTicketByTicketIds(Long[] ticketIds)
    {
        //
        return sightsTicketMapper.deleteSightsTicketByTicketIds(ticketIds);
    }

    /**
     * 删除门票信息
     * 
     * @param ticketId 门票主键
     * @return 结果
     */
    @Override
    public int deleteSightsTicketByTicketId(Long ticketId)
    {
        return sightsTicketMapper.deleteSightsTicketByTicketId(ticketId);
    }
}
