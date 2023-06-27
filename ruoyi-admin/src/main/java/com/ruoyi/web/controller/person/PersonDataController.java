package com.ruoyi.web.controller.person;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.DTO.UserChangeDTO;
import com.ruoyi.common.core.domain.entity.DTO.UserVisitorDTO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.concerns.service.IConcernsService;
import com.ruoyi.page.domain.SysTypeset;
import com.ruoyi.page.service.ISysTypesetService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.ISysVisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
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

    @Autowired
    private IConcernsService concernsService;

    @Autowired
    private ISysTypesetService sysTypesetService;

    @Autowired
    private ISysVisitorService visitorService;


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
        AjaxResult ajaxResult = new AjaxResult();
        // 如果没有填入userId  或者 userId 是自己的
        if (userId == -1 || userId == getUserId()){
            userId = getUserId();
            changeDTOS =  userService.perInformation(null,userId);
            ajaxResult.put("data",changeDTOS);
            ajaxResult.put("ifSelf",true);
            return ajaxResult;
        }
        // 如果是别人
        changeDTOS = userService.perInformation(getUserId(),userId);
        ajaxResult.put("data",changeDTOS);
        ajaxResult.put("ifSelf",false);
        return ajaxResult;
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

        userService.updateUserInfoByCommon(sysUser);
        return AjaxResult.success("修改数据成功");
    }

    @ApiOperation("获取个人资料")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/user/getInfo")
    public AjaxResult getInfo(){
        UserChangeDTO personInfo = userService.getPersonInfo(getUserId());
        return AjaxResult.success(personInfo);
    }

    @ApiOperation("修改背景图片")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/user/{image}")
    public AjaxResult editBackgroundImage(@PathVariable String image){
        // 解决了 图片路径 与 请求路径的相似性
        int i = Integer.parseInt(image);
        SysTypeset sysTypeset = new SysTypeset();
        sysTypeset.setTypesetPosition(9);
        sysTypeset.setTypesetPage(1);
        List<SysTypeset> sysTypesets = sysTypesetService.selectSysTypesetList(sysTypeset);
        SysTypeset typeset = sysTypesets.get(0);
        String backgroundImage = typeset.getTypesetImage().split(",")[i];
        userService.updateUserBackgroundImage(backgroundImage,getUserId());
        return AjaxResult.success("修改背景图片成功");
    }

    @ApiOperation("获取订阅文创个人信息")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/create/intro")
    public AjaxResult getCreateIntro(){
        Long userId = getUserId();
        UserVisitorDTO intro = visitorService.getCreateIntro(userId);
        return AjaxResult.success(intro);
    }


    @ApiOperation("获取主页信息 和 相关状态")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/remind")
    public AjaxResult getMainIfRemindInfo(){

        return null;
    }

    @ApiOperation("获取创作中心活动图")
    @Anonymous
    @GetMapping("/activity/picture")
    public AjaxResult getActPerson(){
        return AjaxResult.success(sysTypesetService.getPersonPicture());
    }

    @ApiOperation("个人全部数据")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/statistic")
    public AjaxResult statisticDataByPerson(){
        return AjaxResult.success(visitorService.statisticUser(getUserId()));
    }





}
