package com.ruoyi.web.controller.person;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chas
 * @introduction 主页信息展示
 * @data 2023-03
 */
@RestController
@RequestMapping("/person")
public class PersonDataController extends BaseController {

    @Autowired
    private ISysUserService userService;


    @PreAuthorize("@ss.hasRole('common')")
    @PostMapping("/user/{userId}")
    public AjaxResult getUser(@PathVariable Long userId){
        SysUser sysUser = userService.selectUserById(userId);
        return AjaxResult.success(sysUser);
    }
}
