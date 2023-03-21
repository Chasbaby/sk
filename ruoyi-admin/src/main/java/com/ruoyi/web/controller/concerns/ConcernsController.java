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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/showFans/{userId}")
    public TableDataInfo showFans(@PathVariable Long userId){
        // 如果是自己
        if (userId == -1 || getUserId() == userId){
            List<UserFCDTO> userFCDTOS = concernsService.showFans(getUserId());
            Map<String,Object> params = new HashMap<>();
            params.put("ifSelf",true);
            return getDataTable(userFCDTOS,"这些都是您忠实的伙伴",params);
        }
        List<UserFCDTO> userFCDTOS = concernsService.showFans(userId);
        Map<String,Object> params = new HashMap<>();
        params.put("ifSelf",false);
        return getDataTable(userFCDTOS,"欢迎您的加入",params);
    }

    /**
     * 展示关注列表
     * @return 列表数据
     */
    @ApiOperation("展示关注列表")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/showConcerns/{userId}")
    public TableDataInfo showConcerns(@PathVariable Long userId){
        if (userId == -1 || getUserId() == userId){
            List<UserFCDTO> userFCDTOS = concernsService.showConcerns(getUserId());
            Map<String,Object> params = new HashMap<>();
            params.put("ifSelf",true);
            return getDataTable(userFCDTOS,null,params);
        }
        List<UserFCDTO> userFCDTOS = concernsService.showConcerns(userId);
        Map<String,Object> params = new HashMap<>();
        params.put("ifSelf",false);
        return getDataTable(userFCDTOS,null,params);
    }






}
