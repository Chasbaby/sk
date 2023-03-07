package com.ruoyi.web.controller.home;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.service.ISysConfigService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.domain.HomeNews;
import com.ruoyi.home.service.IHomeNewsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新闻Controller
 * @author ruoyi
 * @date 2022-08-19
 */
@RestController
@RequestMapping("/home/news")
public class HomeNewsController extends BaseController
{
    @Autowired
    private IHomeNewsService homeNewsService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 返回列表中所有符合条件的数据
     * @return AjaxResult
     */
    @GetMapping("/allNews")
    public AjaxResult getAll(){
        HomeNews homeNews = new HomeNews();
        List<HomeNews> list = homeNewsService.selectHomeNewsList(homeNews);
        return AjaxResult.success(list);
    }

    /**
     * 查询新闻列表
     */
    @PreAuthorize("@ss.hasPermi('home:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(HomeNews homeNews)
    {
        startPage();
        List<HomeNews> list = homeNewsService.selectHomeNewsList(homeNews);
        return getDataTable(list);
    }

    /**
     * 导出新闻列表
     */
    @PreAuthorize("@ss.hasPermi('home:news:export')")
    @Log(title = "新闻", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomeNews homeNews)
    {
        List<HomeNews> list = homeNewsService.selectHomeNewsList(homeNews);
        ExcelUtil<HomeNews> util = new ExcelUtil<HomeNews>(HomeNews.class);
        util.exportExcel(response, list, "新闻数据");
    }

    /**
     * 获取新闻详细信息
     */
    @PreAuthorize("@ss.hasPermi('home:news:query')")
    @GetMapping(value = "/{newsId}")
    public AjaxResult getInfo(@PathVariable("newsId") Long newsId)
    {
        return AjaxResult.success(homeNewsService.selectHomeNewsByNewsId(newsId));
    }

    /**
     * 新增新闻
     */
    @PreAuthorize("@ss.hasPermi('home:news:add')")
    @Log(title = "新闻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomeNews homeNews)
    {
        homeNews.setUpdateTime(new Date());
        homeNews.setCreateBy(getUsername());
        return toAjax(homeNewsService.insertHomeNews(homeNews));
    }

    /**
     * 修改新闻
     */
    @PreAuthorize("@ss.hasPermi('home:news:edit')")
    @Log(title = "新闻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomeNews homeNews)
    {
        homeNews.setUpdateTime(new Date());
        homeNews.setUpdateBy(getUsername());
        return toAjax(homeNewsService.updateHomeNews(homeNews));
    }

    /**
     * 删除新闻
     */
    @PreAuthorize("@ss.hasPermi('home:news:remove')")
    @Log(title = "新闻", businessType = BusinessType.DELETE)
	@DeleteMapping("/{newsIds}")
    public AjaxResult remove(@PathVariable Long[] newsIds)
    {
        return toAjax(homeNewsService.deleteHomeNewsByNewsIds(newsIds));
    }
}
