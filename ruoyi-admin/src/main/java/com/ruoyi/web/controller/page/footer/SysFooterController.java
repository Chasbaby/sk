package com.ruoyi.web.controller.page.footer;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.kafka.core.KafkaTemplate;
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
import com.ruoyi.page.domain.SysFooter;
import com.ruoyi.page.service.ISysFooterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import static com.ruoyi.recommend.util.InitKafka.config11;

/**
 * 底部展示Controller
 * 
 * @author ruoyi
 * @date 2023-02-03
 */
@RestController
@RequestMapping("/system/footer")
public class SysFooterController extends BaseController
{
    @Autowired
    private ISysFooterService sysFooterService;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    /**
     * 查询底部展示列表
     */
    @PreAuthorize("@ss.hasPermi('system:footer:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysFooter sysFooter)
    {
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("recommender","我是你爸爸");
        }

        //config11();
        startPage();
        List<SysFooter> list = sysFooterService.selectSysFooterList(sysFooter);
        return getDataTable(list);
    }

    /**
     * 导出底部展示列表
     */
    @PreAuthorize("@ss.hasPermi('system:footer:export')")
    @Log(title = "底部展示", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFooter sysFooter)
    {
        List<SysFooter> list = sysFooterService.selectSysFooterList(sysFooter);
        ExcelUtil<SysFooter> util = new ExcelUtil<SysFooter>(SysFooter.class);
        util.exportExcel(response, list, "底部展示数据");
    }

    /**
     * 获取底部展示详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:footer:query')")
    @GetMapping(value = "/{footerId}")
    public AjaxResult getInfo(@PathVariable("footerId") Long footerId)
    {
        return AjaxResult.success(sysFooterService.selectSysFooterByFooterId(footerId));
    }

    /**
     * 新增底部展示
     */
    @PreAuthorize("@ss.hasPermi('system:footer:add')")
    @Log(title = "底部展示", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFooter sysFooter)
    {
        return toAjax(sysFooterService.insertSysFooter(sysFooter));
    }

    /**
     * 修改底部展示
     */
    @PreAuthorize("@ss.hasPermi('system:footer:edit')")
    @Log(title = "底部展示", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFooter sysFooter)
    {
        return toAjax(sysFooterService.updateSysFooter(sysFooter));
    }

    /**
     * 删除底部展示
     */
    @PreAuthorize("@ss.hasPermi('system:footer:remove')")
    @Log(title = "底部展示", businessType = BusinessType.DELETE)
	@DeleteMapping("/{footerIds}")
    public AjaxResult remove(@PathVariable Long[] footerIds)
    {
        return toAjax(sysFooterService.deleteSysFooterByFooterIds(footerIds));
    }
}
