package com.ruoyi.web.controller.sights;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.baidu.domain.IP.Geocoder;
import com.ruoyi.common.utils.baidu.domain.IP.GeocoderResultMap;
import com.ruoyi.common.utils.baidu.domain.IP.ParaGeo;
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
import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.sights.service.ISightsBaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import static com.ruoyi.common.utils.baidu.BaiduUtils.getGeocoder;

/**
 * 景点基本信息Controller
 * 
 * @author ruoyi
 * @date 2022-10-25
 */
@RestController
@RequestMapping("/sightBase/sights")
public class SightsBaseController extends BaseController {
    @Autowired
    private ISightsBaseService sightsBaseService;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    /**
     * 查询景点基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('sightBase:sights:list')")
    @GetMapping("/list")
    public TableDataInfo list(SightsBase sightsBase)
    {
        startPage();
        List<SightsBase> list = sightsBaseService.selectSightsBaseList(sightsBase);
        return getDataTable(list);
    }

    /**
     *导出模板
     */
    @PostMapping("/importSightsTemplate")
    public void importTemplate(HttpServletResponse response){
        ExcelUtil<SightsBase> util = new ExcelUtil<>(SightsBase.class);
        util.importTemplateExcel(response,"景点数据");
    }


    /**
     * 导出景点基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('sightBase:sights:export')")
    @Log(title = "景点基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SightsBase sightsBase)
    {
        List<SightsBase> list = sightsBaseService.selectSightsBaseList(sightsBase);
        ExcelUtil<SightsBase> util = new ExcelUtil<SightsBase>(SightsBase.class);
        util.exportExcel(response, list, "景点基本信息数据");
    }


    /**
     * 获取景点基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('sightBase:sights:query')")
    @GetMapping(value = "/{sightsId}")
    public AjaxResult getInfo(@PathVariable("sightsId") Long sightsId)
    {

        return AjaxResult.success(sightsBaseService.selectSightsBaseBySightsId(sightsId));
    }

    /**
     * 新增景点基本信息
     */
    @PreAuthorize("@ss.hasPermi('sightBase:sights:add')")
    @Log(title = "景点基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SightsBase sightsBase)
    {
        ParaGeo paraGeo = new ParaGeo();
        paraGeo.setAddress(sightsBase.getSightsLocation().trim());
        Geocoder geocoder = getGeocoder(paraGeo);

        GeocoderResultMap result = geocoder.getResult();

        sightsBase.setSightsLatitude(result.getLocation().getLat());
        sightsBase.setSightsLongitude(result.getLocation().getLng());
        sightsBase.setCreateBy(getUsername());
        sightsBase.setCreateTime(new Date());
        return toAjax(sightsBaseService.insertSightsBase(sightsBase));
    }



    /**
     * 修改景点基本信息
     */
    @PreAuthorize("@ss.hasPermi('sightBase:sights:edit')")
    @Log(title = "景点基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SightsBase sightsBase)
    {
        sightsBase.setUpdateBy(getUsername());
        sightsBase.setUpdateTime(new Date());
        return toAjax(sightsBaseService.updateSightsBase(sightsBase));
    }

    /**
     * 删除景点基本信息
     */
    @PreAuthorize("@ss.hasPermi('sightBase:sights:remove')")
    @Log(title = "景点基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sightsIds}")
    public AjaxResult remove(@PathVariable Long[] sightsIds)
    {
        return toAjax(sightsBaseService.deleteSightsBaseBySightsIds(sightsIds));
    }
}
