package com.ruoyi.web.controller.tags;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chas
 * @introduction 对外标签选择
 * @date 2023-6-1
 */
@Api("标签选择")
@RestController
@RequestMapping("/tags")
public class TagsAnController extends BaseController {

    @ApiOperation("获取标签展示")
    @GetMapping("/show/{position}")
    public AjaxResult getTagsChoice(@PathVariable Integer position){
        return null;
    }

}
