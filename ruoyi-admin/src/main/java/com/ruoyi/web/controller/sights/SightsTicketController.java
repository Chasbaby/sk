package com.ruoyi.web.controller.sights;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.sights.domain.SightsTicket;
import com.ruoyi.sights.service.ISightsTicketService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 门票Controller
 * 
 * @author ruoyi chas
 * @date 2023-01-04
 */
@RestController
@RequestMapping("/system/ticket")
public class SightsTicketController extends BaseController
{
    @Autowired
    private ISightsTicketService sightsTicketService;

    /**
     * 查询门票列表
     */
    @PreAuthorize("@ss.hasPermi('system:ticket:list')")
    @GetMapping("/list")
    public TableDataInfo list(SightsTicket sightsTicket)
    {
        startPage();
        List<SightsTicket> list = sightsTicketService.selectSightsTicketList(sightsTicket);
        return getDataTable(list);
    }

    /**
     * 导出门票列表
     */
    @PreAuthorize("@ss.hasPermi('system:ticket:export')")
    @Log(title = "门票", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SightsTicket sightsTicket)
    {
        List<SightsTicket> list = sightsTicketService.selectSightsTicketList(sightsTicket);
        ExcelUtil<SightsTicket> util = new ExcelUtil<SightsTicket>(SightsTicket.class);
        util.exportExcel(response, list, "门票数据");
    }

    /**
     * 获取门票详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:ticket:query')")
    @GetMapping(value = "/{ticketId}")
    public AjaxResult getInfo(@PathVariable("ticketId") Long ticketId)
    {
        return AjaxResult.success(sightsTicketService.selectSightsTicketByTicketId(ticketId));
    }

    /**
     * 新增门票
     */
    @PreAuthorize("@ss.hasPermi('system:ticket:add')")
    @Log(title = "门票", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SightsTicket sightsTicket)
    {
        sightsTicket.setCreateTime(new Date());
        sightsTicket.setCreateBy(getUsername());
        return toAjax(sightsTicketService.insertSightsTicket(sightsTicket));
    }

    /**
     * 修改门票
     */
    @PreAuthorize("@ss.hasPermi('system:ticket:edit')")
    @Log(title = "门票", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SightsTicket sightsTicket)
    {
        sightsTicket.setUpdateTime(new Date());
        sightsTicket.setUpdateBy(getUsername());
        return toAjax(sightsTicketService.updateSightsTicket(sightsTicket));
    }

    /**
     * 删除门票
     */
    @PreAuthorize("@ss.hasPermi('system:ticket:remove')")
    @Log(title = "门票", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ticketIds}")
    public AjaxResult remove(@PathVariable Long[] ticketIds)
    {
        return toAjax(sightsTicketService.deleteSightsTicketByTicketIds(ticketIds));
    }

    /**
     * 门票绑定景点
     */
    @PreAuthorize("@ss.hasPermi('system:ticket:edit')")
    @Log(title ="绑定门票",businessType = BusinessType.GRANT)
    @GetMapping("/connect/{sightsId}/{ticketId}")
    public AjaxResult connectSights(@PathVariable Long sightsId,@PathVariable Long ticketId){
        // 判定唯一性

        // 判定存在性
        SightsTicket sightsTicket = new SightsTicket();
        sightsTicket.setSightsId(sightsId);
        sightsTicket.setTicketId(ticketId);
        sightsTicketService.updateSightsTicket(sightsTicket);
        // 成功
        return AjaxResult.success("绑定成功");
    }
}
