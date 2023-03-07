package com.ruoyi.web.controller.mail;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysEmail;
import com.ruoyi.system.service.ISysEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xin.altitude.cms.common.util.EntityUtils;


import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/system/email")
public class EmailController {

    @Autowired
    ISysEmailService iSysEmailService;

    public AjaxResult send(SysEmail sysEmail, MultipartFile file){
        return null;
    }

}
