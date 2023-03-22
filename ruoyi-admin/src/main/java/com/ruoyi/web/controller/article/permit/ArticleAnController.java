package com.ruoyi.web.controller.article.permit;


import com.ruoyi.article.domain.Article;
import com.ruoyi.article.domain.ArticleRecord;
import com.ruoyi.article.domain.dto.*;
import com.ruoyi.article.service.IArticleService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.SearchCaseType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.concerns.service.IConcernsService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.List;

import static com.ruoyi.common.utils.BeanCopyUtils.copyObject;
import static com.ruoyi.framework.config.SensitiveConfig.filter;

/**
 * 面向游客的文章操作
 * @author Chas
 * @data 2023-3
 */
@Api("面向游客的文章操作")
@RestController
@RequestMapping("/article")
public class ArticleAnController extends BaseController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private IConcernsService concernsService;



    /**
     * 文章点赞(点与取消)
     * 1. 判断是否有游客身份
     *  (我们默认登录之后才是游客，没有登录只能查看基本性质的信息)
     * 2. 传 articleId 即可 完成点赞事件
     *
     * 记的修改主文章的内容 可以定时任务
     *
     * @return yes or no
     */
    @ApiOperation("文章点赞")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/like/{articleId}")
    public AjaxResult articleLike(@PathVariable Long articleId){
        // 在拥有 游客 角色的用户 即存在了id
        ArticleRecord record = getArticleRecord(articleId);
        int flag = articleService.articleAddCancelLike(record);
        if (flag==1){
            return AjaxResult.success("取消成功");
        }else {
            return AjaxResult.success("点赞成功");
        }
    }

    /**
     * 浏览是不需要 游客 这个身份的 谁都能浏览
     * 浏览里面有一套东西
     * 点击即可触发
     *
     * 如果是登录的就到这个入口
     * 如果没有登录就直接获取信息看就是了
     * @param articleId
     * @return null
     */
    @ApiOperation("文章浏览")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/view/{articleId}")
    public AjaxResult articleView(@PathVariable Long articleId){
        ArticleRecord record = getArticleRecord(articleId);
        articleService.articleAddView(record);
        return AjaxResult.success();
    }

    /**
     * 非登录用户 浏览
     * @param articleId
     * @return
     */
    @ApiOperation("文章浏览(非登录)")
    @Anonymous
    @GetMapping("/view/anonymous/{articleId}")
    public AjaxResult articleViewAnonymous(@PathVariable Long articleId){
        articleService.articleAddView(articleId);
        return AjaxResult.success();
    }

    /**
     * 文章收藏 要有游客身份哦
     * @param articleId
     * @return yes or no
     */
    @ApiOperation("文章收藏")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/collect/{articleId}")
    public AjaxResult articleCollect(@PathVariable Long articleId){
        ArticleRecord record = getArticleRecord(articleId);
        int flag = articleService.articleAddCancelCollect(record);
        if (flag==1){
            return AjaxResult.success("取消成功");
        }else {
            return AjaxResult.success("收藏成功");
        }
    }

    /**
     *  创作文章 我们建议鼓励发表关于旅游的文章信息
     *  前端应该有一些基本的数据检测 数据的有效性
     * @return yes or no
     */
    @ApiOperation("文章创造")
    @PreAuthorize("@ss.hasRole('common')")
    @PostMapping("/create")
    public AjaxResult createArticle(@RequestBody ArticleCreateDTO article){
        // 复制对象
        Article articleHandle = new Article();
        BeanUtils.copyBeanProp(articleHandle,article);
        articleHandle.setArticleContent(filter(article.getArticleContent()));
        // 设置用户信息
        articleHandle.setUserId(getUserId());
        // 存入数据库
        articleService.insertArticle(articleHandle);
        // 消息推送
        concernsService.setRemind(getUserId());
        
        return AjaxResult.success("创建成功，等待审核即可发表");
    }

    /**
     *  文章编辑
     * @param articleId
     * @return
     */
    @ApiOperation("文章编辑")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/edit/{articleId}")
    public AjaxResult editArticle(@PathVariable Long articleId){
        ArticleCreateDTO createDTO = articleService.reEditArticle(articleId);
        return AjaxResult.success(createDTO);
    }

    /**
     * 再次提交
     * @param article
     * @return
     */
    @ApiOperation("再次提交")
    @PreAuthorize("@ss.hasRole('common')")
    @PostMapping("/ReCreate")
    public AjaxResult reSubmitArticle(@RequestBody ArticleCreateDTO article){
        Article articleHandle = new Article();
        BeanUtils.copyBeanProp(articleHandle,article);
        articleHandle.setArticleContent(filter(article.getArticleContent()));
        articleHandle.setIsOk("U");
        articleService.updateArticle(articleHandle);
        // 消息推送
        concernsService.setRemind(getUserId());
        return AjaxResult.success("修改成功，等待审核即可发表");
    }

    /**
     *  获取某用户收藏列表
     * @return 收藏列表
     */
    @ApiOperation("文章收藏")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/collect/getAll")
    public TableDataInfo readArticleCollect(){
        startPage();
        List<ArticleReturnDTO> articles = articleService.getAllArticleCollect(getUserId());
//        ArticleReturnCore ArticlesCore = new ArticleReturnCore();
//        ArticlesCore.setArticles(articles);
//        ArticlesCore.setRoute(SearchCaseType.ARTICLE.getRoute());
        return getDataTable(articles);
    }

    /**
     * 获取历史点赞记录
     * @return 点赞列表
     */
    @ApiOperation("获取历史点赞记录")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/like/getAll")
    public TableDataInfo readArticleLike(){
        List<ArticleReturnDTO> articles = articleService.getAllArticleLike(getUserId());
        return getDataTable(articles);
    }

    /**
     * 获取历史浏览记录
     * @return
     */
    @ApiOperation("获取历史浏览数据")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/view/getAll")
    public TableDataInfo readArticleView(){
        startPage();
        return getDataTable(articleService.getAllArticleView(getUserId()));
    }

    /**
     * 分页获取某用户所有文章
     * 默认按照时间排序
     * @return 文章列表
     */
    @ApiOperation("分页获取某用户所有文章")
    @PreAuthorize("@ss.hasRole('common')")
    @PostMapping("/getAllArticle/{userId}")
    public TableDataInfo getAllArticle(@PathVariable Long userId){
        startPage();
        if (userId == -1 || userId == getUserId()){
            return getDataTable(articleService.getAllArticleByUserId(getUserId(),1));
        }
        List<ArticleHomeDTO> articles = articleService.getAllArticleByUserId(userId, 0);
        return getDataTable(articles);
    }

    /**
     * 获取文章的详细信息
     * 不管你是不是人都可以到这里来哦
     * @return 信息
     */
    @ApiOperation("获取文章的详细信息")
    @Anonymous
    @PostMapping("/getDetail/{articleId}")
    public AjaxResult getArticleDetail(@PathVariable Long articleId){
        ArticleDetail detail = articleService.getArticleDetail(articleId);
        return AjaxResult.success(detail);
    }

    /**
     * 获取文章稿件
     * @return list
     */
    @GetMapping("/getDraft")
    @ApiOperation("获取文章稿件")
    @PreAuthorize("@ss.hasRole('common')")
    public TableDataInfo getDraft(){
        startPage();
        List<ArticleReturnDTO> drafts = articleService.getDraft(getUserId());
        return getDataTable(drafts);
    }

    /**
     * 获取某用户的所有文章 分类
     * @param num
     * @return
     */
    @PreAuthorize("@ss.hasRole('common')")
    @ApiOperation("获取某用户的所有文章")
    @GetMapping("/person/{num}")
    public TableDataInfo getUserAllArticle(@PathVariable Integer num){
        if (num == null){
            return errorMsg("类型错误，请联系管理员");
        }
        startPage();
        Long userId = getUserId();
        List<ArticleStatusDTO> articles = articleService.getUserAllArticleByWays(userId, num);
        return getDataTable(articles);
    }

    @ApiOperation("批量删除")
    @PreAuthorize("@ss.hasRole('common')")
    @DeleteMapping("/delete/{articleIds}")
    public AjaxResult remove(@PathVariable Long[] articleIds)
    {
        int i = articleService.deleteArticlesByUser(articleIds);
        return AjaxResult.success("成功删除"+i+"篇文章");
    }
    /**
     *  创建记录
     * @param articleId
     * @return ArticleRecord
     */
    private ArticleRecord getArticleRecord(Long articleId) {
        Long userId = getUserId();
        ArticleRecord record = new ArticleRecord();
        record.setArticleId(articleId);
        record.setUserId(userId);
        record.setCreateTime(DateUtils.getNowDate());
        return record;
    }
}
