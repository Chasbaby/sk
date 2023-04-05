package com.ruoyi.web.controller.CulCreativity.permit;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.constant.KafkaTopicsConstant;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.concerns.service.IConcernsService;
import com.ruoyi.culCreativity.domain.CulRecord;
import com.ruoyi.culCreativity.domain.SightsCulCreativity;
import com.ruoyi.culCreativity.domain.dto.CulCreateDTO;
import com.ruoyi.culCreativity.domain.dto.CulDetail;
import com.ruoyi.culCreativity.domain.dto.CulHomeDTO;
import com.ruoyi.culCreativity.ISightsCulCreativityService;
import com.ruoyi.culCreativity.domain.dto.CulLazyDTO;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.ISysVisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.framework.config.SensitiveConfig.filter;

/**
 * @author chas
 * @introduction 文创服务
 * @date 2023-3
 */
@Api("文创游客板块")
@RestController
@RequestMapping("/creativity")
public class CulCreativityAnController extends BaseController {

    @Autowired
    private ISightsCulCreativityService creativityService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysVisitorService visitorService;

    @Autowired
    private IConcernsService concernsService;

    @Autowired
    private KafkaTemplate kafkaTemplate;


    /**
     * 分页获取某用户所有文创 完成
     * @param userId
     * @return
     */
    @ApiOperation("分页获取某用户所有文创")
    @PreAuthorize("@ss.hasRole('common')")
    @PostMapping("/getAllCul/{userId}")
    public TableDataInfo getAllCul(@PathVariable Long userId){
        startPage();
        if (userId == -1 || userId == getUserId()){

            return getDataTable(creativityService.getAllCulByUserId(getUserId(),1));
        }
        List<CulHomeDTO> culHomeDTOList = creativityService.getAllCulByUserId(userId,0);
        return getDataTable(culHomeDTOList);
    }

    /**
     * 文创点赞
     * @param culCreativityId id
     * @return  状态信息
     */
    @ApiOperation("文创点赞")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/like/{culCreativityId}")
    public AjaxResult CreativityLike(@PathVariable Long culCreativityId){
        CulRecord record = createRecord(culCreativityId);
        int i = creativityService.addCulLike(record);
        return AjaxResult.success();
    }

    /**
     * 文创浏览 登陆性浏览
     * @param culCreativityId id
     * @return 状态信息
     */
    @ApiOperation("登录浏览")
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
    @ApiOperation("非登录浏览")
    @Anonymous
    @GetMapping("/view/anonymous/{culCreativityId}")
    public AjaxResult CreativityViewAnonymous(@PathVariable Long culCreativityId){
        kafkaTemplate.send(KafkaTopicsConstant.CUL_VIEW_ANONYMOUS,culCreativityId);
        return null;
    }

    /**
     * 获得文创详细资料
     * @param culCreativityId
     * @return
     */
    @ApiOperation("获得文创详细资料")
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
    @ApiOperation("订阅用户")
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
    @ApiOperation("用户创作文创")
    @PreAuthorize("@ss.hasRole('common')")
    @PostMapping("/create")
    public AjaxResult create(@RequestBody CulCreateDTO createDTO){

        SightsCulCreativity culCreativity = new SightsCulCreativity();
        BeanUtils.copyBeanProp(culCreativity,createDTO);
        culCreativity.setCulCreativityContent(filter(createDTO.getCulCreativityContent()));
        culCreativity.setUserId(getUserId());
        creativityService.insertSightsCulCreativity(culCreativity);
        // 消息推送开启
        concernsService.setRemind(getUserId());
        return AjaxResult.success("创建成功，等待审核即可发表");
    }

    @ApiOperation("文创再编辑")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/edit/{culCreativityId}")
    public AjaxResult editCul(@PathVariable Long culCreativityId){
        CulCreateDTO culCreateDTO = creativityService.reEditCul(culCreativityId);
        return AjaxResult.success(culCreateDTO);
    }

    @ApiOperation("再次提交")
    @PreAuthorize("@ss.hasRole('common')")
    @PostMapping("/ReCreate")
    public AjaxResult reSubmitCul(@RequestBody CulCreateDTO createDTO){
        SightsCulCreativity creativity = new SightsCulCreativity();
        BeanUtils.copyBeanProp(creativity,createDTO);
        creativity.setCulCreativityContent(filter(creativity.getCulCreativityContent()));
        creativity.setIsOk("U");
        creativityService.updateSightsCulCreativity(creativity);
        //消息推送
        concernsService.setRemind(getUserId());
        return AjaxResult.success("修改成功，等待审核即可发表");
    }


    @ApiOperation("文创收藏")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/collect/{culCreativityId}")
    public AjaxResult culCollect(@PathVariable Long culCreativityId){
        CulRecord record = createRecord(culCreativityId);
        int flag = creativityService.addCulCollect(record);
        if (flag == 1){
            return AjaxResult.success("取消成功");
        }else {
            return AjaxResult.success("收藏成功");
        }
    }

    @ApiOperation("获取文创收藏")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/collect/getAll")
    public TableDataInfo readArticleCollect(){
        startPage();
        List<CulHomeDTO> culCollect = creativityService.getAllCulCollect(getUserId());
        return getDataTable(culCollect);
    }

    @ApiOperation("懒加载")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/lazy")
    public TableDataInfo getConcernsLazyCul(){
        startPage();

        List<CulLazyDTO> concernsLazyCul = creativityService.getConcernsLazyCul(getUserId());

        return getDataTable(concernsLazyCul);
    }

    @ApiOperation("获取历史点赞记录")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/like/getAll")
    public TableDataInfo readCulLike(){
        return null;
    }

    @ApiOperation("获取历史浏览记录")
    @PreAuthorize("@ss.hasRole('common')")
    @GetMapping("/view/getAll")
    public TableDataInfo readCulView(){

        return null;
    }

    @ApiOperation("获取文创稿件")
    @GetMapping("/getDraft")
    @PreAuthorize("@ss.hasRole('common')")
    public TableDataInfo getDraft(){
        return null;
    }

    @ApiOperation("获取某用户发表的全部文创")
    @GetMapping("/person/{num}")
    @PreAuthorize("@ss.hasRole('common')")
    public TableDataInfo getUserAllCul(@PathVariable Integer num){
        return null;
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/delete/{culCreativityIds}")
    public AjaxResult remove(@PathVariable Long[] culCreativityIds ){
        return null;
    }


    private CulRecord createRecord(Long culCreativityId) {
        CulRecord record = new CulRecord();
        record.setUserId(getUserId());
        record.setCulId(culCreativityId);
        record.setCreateTime(DateUtils.getNowDate());
        return record;
    }







}
