package com.ruoyi.web.controller.system.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysPartner;
import com.ruoyi.system.service.ISysPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chas
 * @introduction  合作方
 * @data 2023-3
 */
@RestController
@RequestMapping("/partner")
public class PartnerAnController extends BaseController {
    @Autowired
    private ISysPartnerService sysPartnerService;

    /**
     * 随机获取5个合作方 展示
     * @return 列表
     */
    @Anonymous
    @PostMapping("/getShow")
    public AjaxResult getShow(){
        List<SysPartner> newList = new ArrayList<>();
        List<SysPartner> partners = sysPartnerService.selectSysPartnerList(new SysPartner());
        for (int i = 0; i < 5; i++) {
            int size = partners.size();
            Random random = new Random();
            int num = random.nextInt(size);
            SysPartner partner = partners.get(num);
            partners.remove(num);
            newList.add(partner);
        }
        return AjaxResult.success(newList);
    }

    @Anonymous
    @PostMapping("/getAll")
    public TableDataInfo getAll(Integer pageNum,Integer pageSize){
        startPage();
        return getDataTable(sysPartnerService.selectSysPartnerList(new SysPartner()));
    }
}
