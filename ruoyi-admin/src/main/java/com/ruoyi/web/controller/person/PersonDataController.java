package com.ruoyi.web.controller.person;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.DTO.UserChangeDTO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chas
 * @introduction 主页信息展示
 * @data 2023-03
 */
@Api("主页信息展示")
@RestController
@RequestMapping("/person")
public class PersonDataController extends BaseController {

    @Autowired
    private ISysUserService userService;


    /**
     * 个人主页信息
     *
     * 看别人的信息填入id
     * 如果不填 就是看自己的
     *
     * @param userId
     * @return 信息
     */
    @ApiOperation("个人主页信息")
    @PreAuthorize("@ss.hasRole('common')")
    @PostMapping("/user/{userId}")
    public AjaxResult getUser(@PathVariable Long userId){
        UserChangeDTO changeDTOS;
        // 如果没有填入userId  那就是看用户自己的资料
        if (userId == null){
            userId = getUserId();
        }
        changeDTOS =  userService.perInformation(userId);
        return AjaxResult.success(changeDTOS);
    }

    @ApiOperation("更新个人信息")
    @PreAuthorize("@ss.hasRole('common')")
    @PostMapping("/user/update")
    public AjaxResult updateUser(@RequestBody UserChangeDTO user){
        if (user == null){
            return AjaxResult.error("信息不能为空");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyBeanProp(sysUser,user);
        userService.checkUserAllowed(sysUser);

        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(sysUser)))
        {
            return AjaxResult.error("修改用户'" + sysUser.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(sysUser)))
        {
            return AjaxResult.error("修改用户'" + sysUser.getUserName() + "'失败，邮箱账号已存在");
        }
        sysUser.setUpdateBy(getUsername());
        userService.updateUser(sysUser);
        return AjaxResult.success("修改数据成功");
    }


}
