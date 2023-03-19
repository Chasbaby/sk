package com.ruoyi.web.controller.CulCreativity.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.culCreativity.domain.CulRecord;
import com.ruoyi.culCreativity.domain.dto.CulCreateDTO;
import com.ruoyi.culCreativity.domain.dto.CulDetail;
import com.ruoyi.sights.service.ISightsCulCreativityService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.ISysVisitorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author chas
 * @introduction 文创服务
 * @data 2023-3
 */
@ApiOperation("文创游客板块")
@RestController
@RequestMapping("/creativity")
public class CulCreativityAnController extends BaseController {

    @Autowired
    private ISightsCulCreativityService creativityService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysVisitorService visitorService;


    /**
     * 文创点赞
     * @param culCreativityId id
     * @return  状态信息
     */
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/like/{culCreativityId}")
    public AjaxResult CreativityLike(@PathVariable Long culCreativityId){
        CulRecord record = createRecord(culCreativityId);
        int i = creativityService.addCulLike(record);
        return AjaxResult.success();
    }

    private CulRecord createRecord(Long culCreativityId) {
        CulRecord record = new CulRecord();
        record.setUserId(getUserId());
        record.setCulId(culCreativityId);
        record.setCreateTime(DateUtils.getNowDate());
        return record;
    }

    /**
     * 文创浏览 登陆性浏览
     * @param culCreativityId id
     * @return 状态信息
     */
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/view/{culCreativityId}")
    public AjaxResult CreativityView(@PathVariable Long culCreativityId){
        CulRecord record = createRecord(culCreativityId);
        int i = creativityService.addCulView(record);
        return null;
    }

    /**
     * 文创非登录浏览
     * @param culCreativityId id
     * @return 状态信息
     */
    @Anonymous
    @GetMapping("/view/anonymous/{culCreativityId}")
    public AjaxResult CreativityViewAnonymous(@PathVariable Long culCreativityId){
        creativityService.addCulViewAnonymous(culCreativityId);
        return null;
    }

    /**
     * 获得文创详细资料
     * @param culCreativityId
     * @return
     */
    @Anonymous
    @GetMapping("/getDetail/{culCreativityId}")
    public AjaxResult getCulDetail(@PathVariable Long culCreativityId){
        CulDetail detail =  creativityService.getCulDetail(culCreativityId);
        return AjaxResult.success(detail);
    }

    /**
     * 订阅用户 -> 文创
     * @param userId id
     * @return 状态信息
     */
    @GetMapping("/subscription/{userId}")
    @PreAuthorize("@ss.hasRole('common')")
    public AjaxResult subscription(@PathVariable Long userId){
        SysUser sysUser = userService.selectUserById(getUserId());

        return null;
    }

    /**
     * 用户创作文创
     * @return 状态信息
     */
    @PreAuthorize("@ss.hasRole('common')")
    @PostMapping("/create")
    public AjaxResult crate(@RequestBody CulCreateDTO createDTO){

        return null;
    }






}
