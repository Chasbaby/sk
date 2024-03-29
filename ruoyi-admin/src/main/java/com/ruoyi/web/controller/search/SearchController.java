package com.ruoyi.web.controller.search;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.recommend.es.context.SightsStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 全局搜索
 * @author Chas
 * @date 2023-2
 */

@Api("全局搜索")
@RestController
@RequestMapping("/search")
public class SearchController extends BaseController {

    @Autowired
    private SightsStrategyContext strategyContext;

    /**
     * 搜索一切
     * @param keywords
     * @return 各种数据
     */
    @Anonymous
    @GetMapping("/suggest/{keywords}")
    @ApiOperation("搜索一切")
    public AjaxResult listAllBySearch(@PathVariable(required = false) String keywords){
        if (StringUtils.isEmpty(keywords)){
            return AjaxResult.error("请输入您的搜索内容");
        }
        return AjaxResult.success(strategyContext.executeSearchStrategy(keywords));
    }


    /**
     * 搜索加展示
     * @param keywords 可有可无
     * @return 展示的数据
     */
    @Anonymous
    @GetMapping("/all/{keywords}")
    @ApiOperation("展示一切")
    public AjaxResult showAllBySearch(@PathVariable(required = false) String keywords){

        return AjaxResult.success(strategyContext.executeShowAll(keywords));

    }

    @Anonymous
    @ApiOperation("文章搜索")
    @GetMapping("/article/{keywords}")
    public AjaxResult searchArticle(@PathVariable(required = false) String keywords){
        return AjaxResult.success(strategyContext.executeSearchArticle(keywords));
    }

    @Anonymous
    @ApiOperation("景点搜索")
    @GetMapping("/sights/{keywords}")
    public AjaxResult searchSights(@PathVariable(required = false) String keywords){
        return AjaxResult.success(strategyContext.executeSearchSights(keywords));
    }
}
