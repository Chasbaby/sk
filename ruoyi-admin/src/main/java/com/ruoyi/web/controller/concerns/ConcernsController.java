package com.ruoyi.web.controller.concerns;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.DTO.UserFCDTO;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.concerns.service.IConcernsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chas
 * @introduction 用户之间
 * @data 2023-3
 */
@Api("用户之间操作")
@RestController
@RequestMapping("/inter")
public class ConcernsController extends BaseController {

    @Autowired
    private IConcernsService concernsService;

    /**
     * 取消/添加关注
     * 规定:  main  关注  prior
     * @param priorUser id
     * @return 状态信息
     */
    @ApiOperation("取消/增加关注")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/add/{priorUser}")
    public AjaxResult addConcerns(@PathVariable Long priorUser){
        Long mainUser = getUserId();
        int flag = concernsService.addConcerns(mainUser, priorUser);
        if (flag == 1 ){
            return AjaxResult.success("取消关注,期待下次与你的缘分");
        }else {
            return AjaxResult.success("成功关注,快去看看这位伙伴的新鲜事物吧");
        }

    }

    /**
     * 展示粉丝列表
     * @return 列表数据
     */
    @ApiOperation("展示粉丝列表")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/showFans")
    public TableDataInfo showFans(){
        List<UserFCDTO> userFCDTOS = concernsService.showFans(getUserId());
        return getDataTable(userFCDTOS);
    }

    /**
     * 展示关注列表
     * @return 列表数据
     */
    @ApiOperation("展示关注列表")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/showConcerns")
    public TableDataInfo showConcerns(){
        List<UserFCDTO> userFCDTOS = concernsService.showConcerns(getUserId());
        return getDataTable(userFCDTOS);
    }






}
