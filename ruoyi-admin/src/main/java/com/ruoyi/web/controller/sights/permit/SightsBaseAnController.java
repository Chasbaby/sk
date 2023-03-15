package com.ruoyi.web.controller.sights.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.sights.domain.*;
import com.ruoyi.sights.domain.DTO.SightsDTO;
import com.ruoyi.sights.domain.DTO.SightsRecommendDTO;
import com.ruoyi.sights.service.ISightsBaseService;
import com.ruoyi.system.service.ICommentService;
import com.ruoyi.system.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *   景点 一整套
 * @author  Chas
 * @date 2022-10
 */
@Api("景点展示")
@Anonymous
@RestController
@RequestMapping("/sights")
public class SightsBaseAnController extends BaseController {

    @Autowired
    private ISightsBaseService iSightsBaseService;

    @Autowired
    private ISysConfigService iSysConfigService;

    /**
     * 单纯获取一定量景点展示
     */
    @ApiOperation("获取景点")
    @PostMapping("/getAll")
    public AjaxResult getAll(){
        String s = iSysConfigService.selectConfigByKey("sights:all:num");
        List<SightsBase> baseList = iSightsBaseService.selectSightsBaseList(new SightsBase());
        List<SightsBase> newList = new LinkedList<>();
        for (int i = 0; newList.size()!=Convert.toInt(s); i++) {
            int anInt = new Random().nextInt(baseList.size());
            SightsBase sightsBase = baseList.get(anInt);
            newList.add(sightsBase);
            baseList.remove(anInt);
        }
        return AjaxResult.success(newList);
    }

    /**
     * 以下是推荐部分
     */

    /**
     * 获取某用户 实时 推荐列表
     * 采用 spark 等 技术 已经通过定时任务计算好了
     * 所以在此直接从数据库中提取
     * 传的数量在 业务层中 显示
     *
     * 三种情况
     * 1. 不存在用户 那么直接获取 一定数量的景点展示
     * 2. 存在用户 但是推荐列表里面不存在 那么 直接获取一定数量的景点展示
     * 3. 存在用户 推荐列表存在 那么直接拿数据
     */
    @ApiOperation("获取某用户推荐列表")
    @PostMapping("/recommend")
    @PreAuthorize("@ss.hasAnyRoles('common')")
    public TableDataInfo getRecommendSights(){
        startPage();
        List<SightsRecommendDTO> sights = iSightsBaseService.getRecommendSights(getUserId());
        return getDataTable(sights);
    }

    /**
     * 获得单个景点信息
     * 点击景点之后, 也要加入历史记录哦
     */
    @ApiOperation("获取单个景点信息")
    @GetMapping("/{sightsId}")
    public AjaxResult getSightsInfo(@PathVariable Long sightsId){
        // 如果是空的
        if (sightsId == null){
            return AjaxResult.error("请联系官方，没有此景点信息");
        }
        // 景点信息 (有些在缓存中的信息 并没有加入 但是无所谓)
        SightsDTO sightsDTO = iSightsBaseService.selectDetailSightsById(sightsId);
        return AjaxResult.success(sightsDTO);
    }

    /**
     *历史热门景点推荐
     *已经通过 spark 计算完成 直接提取即可
     */
    @Anonymous
    @ApiOperation("获取历史热门景点列表")
    @PostMapping("/historySights")
    public TableDataInfo getHistoryHotSights(){
        startPage();
        List<SightsRecommendDTO> historyHotSights = iSightsBaseService.getHistoryHotSights();
        return getDataTable(historyHotSights);
    }

    /**
     * 近期热门景点推荐
     * 已经通过spark计算获取 直接提取即可
     */
    @Anonymous
    @ApiOperation("获取近期热门列表")
    @PostMapping("/recentSights")
    public TableDataInfo getRecentHotSights(){
        startPage();
        List<SightsRecommendDTO> recentHotSights = iSightsBaseService.getRecentHotSights();
        return getDataTable(recentHotSights);
    }

    /**
     * 优质景点推荐
     * 已经通过spark计算获得 直接提取即可
     */
    @Anonymous
    @ApiOperation("获取优质景点列表")
    @PostMapping("/goodSights")
    public TableDataInfo getGoodSights(){
        startPage();
        List<SightsRecommendDTO> goodSights = iSightsBaseService.getGoodSights();
        return getDataTable(goodSights);
    }

    /**
     * 下面是辅助景点推荐部分
     */

    /**
     * 为景点添加 tags 便于推荐
     * 字数限制  5 字以内 请前端判断一下 谢谢
     * 并加入推荐 用于 内容匹配
     *
     * 标签集合格式   |内容 @ userId|
     * 算法 : 1.提取数据库中对应sightsId的tags
     *       2.tags 2次 分词
     *       3.比较内容 若相同 则tags无需加入
     *       4.         不同 若数量达标则 根据循环队列的做法 从第一个开始覆盖
     * 存入日志采用 kafka 异步
     * @param sightsId
     * @param tag
     * @return ajaxResult
     */
    @ApiOperation("为景点添加tags")
    @PostMapping("/addTags")
    @PreAuthorize("@ss.hasAnyRoles('common')")
    public AjaxResult addSightsTags(@PathVariable Long sightsId,@PathVariable String tag){
        // 1. 检查tags 格式合理性 (手动)
        // 2. 检查tags 内容合理性 (kafka)
        // 3. 加入日志 用于 内容匹配
        // 4. 存入数据库 用于后期展示
        return AjaxResult.success();
    }


    /**
     * 默认展示的 tags 用户可选的tags
     * 直接从数据库中获取即可
     * 限于篇幅 每次只传一定的量 内容是随机的
     */
    @ApiOperation("默认展示tag")
    @PostMapping("/defaultTags")
    public AjaxResult defaultTags(){

        return AjaxResult.success();

    }


    /**
     * 以下是对于景点部分属性
     */

    /**
     * 点击量++   完结
     * 当进入单个具体单个景点的页面时 ，前端传来景点的id 直接实现效果
     * 这里不需要任何判断
     * 无取消这一措施
     * 记得写入 log
     *
     *
     *  (完成) 后面优化用 redis 然后 定时任务 批量增加 现在没必要
     *
     *
     * @return ajaxResult
     */
    @ApiOperation("点击量增加")
    @GetMapping("/hit/{sightsId}")
    public AjaxResult improveHits(@PathVariable Long sightsId){
        iSightsBaseService.updateSightsViaHits(sightsId);
        // TODO  写日志
        return AjaxResult.success();
    }

    /**
     * 点击量排行榜
     * 1.数量后台固定
     * 2.可以传入大数据模块 也可以放到 游客页面展示
     * 3.传过去的数据有规定个数 且 排序 从上至下 越来越低
     * 4.这里传过去的是所有的值 实际上不需要那么多值 前端自己挑选
     * 5.缺点 : 传过去的是全部数据 实际上 只有部分用的到 浪费 时间空间
     * 6.这里实际上要不断发起请求，因为随时都可能变化，
     *   所以 可以选择固定时间 发起请求 但是时间和展示数量最好有所连续
     */
    @Anonymous
    @ApiOperation("点击量排行榜")
    @PostMapping("/hits/rank")
    public AjaxResult getHitRank(){
        // 在这里用懒加载  但是传的个数还是要限制的
        // 获取自定义个数
        String num = iSysConfigService.selectConfigByKey(" ");
        List<SightsBase> baseList = iSightsBaseService.selectSightsTopViaHit(Convert.toInt(num));
        return AjaxResult.success(baseList);
    }

    /**
     * 点赞量 ++ -- 完结
     * 1. 前端传来景点的id 直接实现效果
     * 2. 日志记录
     * 3. 这里 增加 和 取消 同步
     *    如果原本是 无 则 默认是增加点赞量
     *    如果原本是 点赞 则取消点赞
     * 4. 缺点:  这里 点赞 判断 取消  sql语句是分离的
     *          有没有一种方法直接一句sql
     *          但是判断状态值 用了另一个接口 可否使用同一个
     * 5. 希望 : 如果连续短时间内多次发起请求 可以有所反应 拒绝发起
     */
    @ApiOperation("点赞量变化")
    @GetMapping("/like/{sightsId}")
    @PreAuthorize("@ss.hasAnyRoles('common')")
    public AjaxResult changeLike(@PathVariable Long sightsId){
        SightsRecordLike recordLike = new SightsRecordLike();
        recordLike.setSightsId(sightsId);
        recordLike.setUserId(getUserId());
        recordLike.setCreateTime(new Date());
        iSightsBaseService.SightsManageViaLike(recordLike);
        return AjaxResult.success();
    }

    /**
     * 点赞量排行版
     * (数量后台固定)
     * 内容同 点击量排行榜
     */
    @ApiOperation("点赞量排行榜")
    @PostMapping("/like/rank")
    public AjaxResult getLikeRank(){
        String num = iSysConfigService.selectConfigByKey("");
        List<SightsBase> baseList = iSightsBaseService.selectSightsTopViaLike(Convert.toInt(num));
        return AjaxResult.success(baseList);
    }

    /**
     * 流览量 ++ 完结
     */
    @ApiOperation("浏览量++")
    @GetMapping("/view/{sightsId}")
    public AjaxResult improveView(@PathVariable Long sightsId){
        iSightsBaseService.updateSightsViaView(sightsId);
        // TODO 写进日志
        return AjaxResult.success();
    }

    /**
     * 浏览量排行榜
     */
    @ApiOperation("浏览量排行榜")
    @GetMapping("/view/rank")
    public AjaxResult getViewRank(){
        String num = iSysConfigService.selectConfigByKey("");
        List<SightsBase> baseList = iSightsBaseService.selectSightsTopViaView(Convert.toInt(num));
        return AjaxResult.success(baseList);
    }

    /**
     * 以下是收藏 历史记录部分
     */

    /**
     * 收藏景点 完结
     */
    @ApiOperation("收藏取消景点")
    @GetMapping("/collect/{sightsId}")
    @PreAuthorize("@ss.hasAnyRoles('common')")
    public AjaxResult collectSightsId(@PathVariable Long sightsId){
        SightsUserCollect userCollect = new SightsUserCollect();
        userCollect.setUserId(getUserId());
        userCollect.setSightsId(sightsId);
        userCollect.setCollectTime(new Date());
        boolean ifCollect = iSightsBaseService.changeSightsCollection(userCollect);
        if (ifCollect){
            return AjaxResult.success("收藏成功");
        }
        return AjaxResult.success("取消收藏");

    }

//    /**
//     * 取消景点收藏   已经集成到上面的
//     */
//    @ApiOperation("取消收藏景点")
//    @GetMapping("/reverse/collect/{sightsId}")
//    @PreAuthorize("@ss.hasAnyRoles('common')")
//    public AjaxResult reverseSightsCollection(@PathVariable Long sightsId){
//        SightsUserCollect userCollect = new SightsUserCollect();
//        userCollect.setUserId(getUserId());
//        userCollect.setSightsId(sightsId);
//        iSightsBaseService.cancelSightsCollection(userCollect);
//        return AjaxResult.success();
//    }


    /**
     * 获取用户景点 收藏记录 完结
     */
    @ApiOperation("获取用户收藏景点")
    @PostMapping("/collect")
    @PreAuthorize("@ss.hasAnyRoles('common')")
    public TableDataInfo getCollection(){
        List<SightsBase> sightsRecord = iSightsBaseService.selectCollectSightsRecord(getUserId());
        return getDataTable(sightsRecord);
    }

    /**
     * 添加历史景点记录 完结
     * 后面改为 redis 添加效率
     * 这里还需要进行判断 如果已经在库中 那么更新时间即可
     * @param sightsId
     * @return
     */
    @ApiOperation("添加景点历史记录")
    @GetMapping("/history/{sightsId}")
    @PreAuthorize("@ss.hasAnyRoles('common')")
    public AjaxResult setSightsHistory(@PathVariable Long sightsId){
        SightsRecordHistory recordHistory = new SightsRecordHistory();
        recordHistory.setCreateTime(new Date());
        recordHistory.setSightsId(sightsId);
        recordHistory.setUserId(getUserId());
        iSightsBaseService.addSightsHistoryRecord(recordHistory);
        return AjaxResult.success();
    }

    /**
     * 用户景点历史记录 完结
     */
    @ApiOperation("获取用户景点历史记录")
    @PostMapping("/history")
    @PreAuthorize("@ss.hasAnyRoles('common')")
    public TableDataInfo getHistory(){
        List<SightsBase> baseList = iSightsBaseService.selectHistoryRecordByUserId(getUserId());
        return getDataTable(baseList);
    }

    /**
     * 下面是景点 评分部分
     */

    /**
     * 对某景点 (取消)评分  完结
     * 评分 我们单独拿一个表
     * 所以我们要将信息传到那分表然后
     * 通过 kafka 异步监听改变景点平均分
     *
     * 给景点评分 若数据库中已经有了数据 那么就覆盖 没有就加入
     *
     * 用 redis
     */
    @ApiOperation("景点评分操作")
    @GetMapping("/{sightsId}/{score}")
    @PreAuthorize("@ss.hasAnyRoles('common')")
    public AjaxResult changeSightsScore(@PathVariable("sightsId") Long sightsId,@PathVariable("score") Double score){
        SightsRecordScore sightsRecordScore = new SightsRecordScore();
        sightsRecordScore.setSightsId(sightsId);
        sightsRecordScore.setScore(score);
        Long userId = getUserId();
        sightsRecordScore.setUserId(userId);
        sightsRecordScore.setCreateTime(new Date());
        iSightsBaseService.insertUserSightsScore(sightsRecordScore);
        return AjaxResult.success();
    }

    /**
     * 判断某用户是否对景点评分 完结
     */
    @ApiOperation("判断用户是否对某景点评分")
    @GetMapping("/judge/{sightsId}")
    @PreAuthorize("@ss.hasAnyRoles('common')")
    public AjaxResult judgeUserToSightsExistScore(@PathVariable Long sightsId){
        SightsRecordScore recordScore = new SightsRecordScore();
        recordScore.setUserId(getUserId());
        recordScore.setSightsId(sightsId);
        boolean check = iSightsBaseService.checkUserSightsScore(recordScore);
        return AjaxResult.success(check);
    }

    /**
     * 获取景点评分排行榜(数量后台固定)
     */
    @ApiOperation("获取景点评分排行榜")
    @GetMapping("/score/rank")
    public AjaxResult getSightsScoreRank(){
        String s = iSysConfigService.selectConfigByKey("");
        List<SightsBase> baseList = iSightsBaseService.selectSightsTopViaScore(Convert.toInt(s));
        return AjaxResult.success(baseList);
    }


}
