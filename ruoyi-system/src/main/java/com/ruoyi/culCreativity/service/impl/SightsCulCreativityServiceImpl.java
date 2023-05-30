package com.ruoyi.culCreativity.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.ruoyi.article.service.impl.ArticleServiceImpl;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.DTO.UserDTO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.culCreativity.domain.*;
import com.ruoyi.culCreativity.domain.dto.*;
import com.ruoyi.culCreativity.mapper.CulRecordMapper;
import com.ruoyi.sights.domain.*;
import com.ruoyi.sights.domain.DTO.SightsCulDTO;
import com.ruoyi.sights.mapper.*;
import com.ruoyi.system.domain.SysAudio;
import com.ruoyi.system.mapper.SysAudioMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.culCreativity.ISightsCulCreativityService;
import org.springframework.transaction.annotation.Transactional;

import static com.ruoyi.common.utils.baidu.TranslateUtils.getTranslateResult;

/**
 * 文创Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@Service
public class SightsCulCreativityServiceImpl implements ISightsCulCreativityService 
{
    private final static String pattern="<(\\S*?)[^>]*>.*?|<.*? />";
    private static final Logger log = LoggerFactory.getLogger(SightsCulCreativityServiceImpl.class);
    @Autowired
    private SightsCulCreativityMapper sightsCulCreativityMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SightsBaseMapper baseMapper;
    @Autowired
    private CulRecordMapper recordMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysAudioMapper audioMapper;




    /**
     * 查询文创
     * 
     * @param culCreativityId 文创主键
     * @return 文创
     */
    @Override
    public SightsCulCreativity selectSightsCulCreativityByCulCreativityId(Long culCreativityId)
    {
        return sightsCulCreativityMapper.selectSightsCulCreativityByCulCreativityId(culCreativityId);
    }

    /**
     * 查询文创列表
     * 
     * @param sightsCulCreativity 文创
     * @return 文创
     */
    @Override
    public List<SightsCulCreativity> selectSightsCulCreativityList(SightsCulCreativity sightsCulCreativity)
    {
        return sightsCulCreativityMapper.selectSightsCulCreativityList(sightsCulCreativity);
    }

    /**
     * 新增文创
     * 
     * @param sightsCulCreativity 文创
     * @return 结果
     */
    @Override
    public int insertSightsCulCreativity(SightsCulCreativity sightsCulCreativity)
    {
        sightsCulCreativity.setCreateTime(DateUtils.getNowDate());
        return sightsCulCreativityMapper.insertSightsCulCreativity(sightsCulCreativity);
    }

    /**
     * 修改文创
     * 
     * @param sightsCulCreativity 文创
     * @return 结果
     */
    @Override
    public int updateSightsCulCreativity(SightsCulCreativity sightsCulCreativity)
    {
        sightsCulCreativity.setUpdateTime(DateUtils.getNowDate());
        return sightsCulCreativityMapper.updateSightsCulCreativity(sightsCulCreativity);
    }

    /**
     * 批量删除文创
     * 
     * @param culCreativityIds 需要删除的文创主键
     * @return 结果
     */
    @Override
    public int deleteSightsCulCreativityByCulCreativityIds(Long[] culCreativityIds)
    {
        return sightsCulCreativityMapper.deleteSightsCulCreativityByCulCreativityIds(culCreativityIds);
    }

    /**
     * 删除文创信息
     * 
     * @param culCreativityId 文创主键
     * @return 结果
     */
    @Override
    public int deleteSightsCulCreativityByCulCreativityId(Long culCreativityId)
    {
        return sightsCulCreativityMapper.deleteSightsCulCreativityByCulCreativityId(culCreativityId);
    }
    /**
     * 获取文创详细信息
     * @param culCreativityId id
     */
    @Override
    public CulDetail getCulDetail(Long culCreativityId) {
        CulDetail detail = new CulDetail();
        SightsCulCreativity creativity = sightsCulCreativityMapper.selectDetailById(culCreativityId);
        BeanUtils.copyBeanProp(detail,creativity);
        Long userId = creativity.getUserId();
        // 如果是用户发表的文创
        if ( userId != null){
            UserDTO userDTO = new UserDTO();
            SysUser sysUser = userMapper.selectUserById(userId);
            BeanUtils.copyBeanProp(userDTO,sysUser);
            detail.setUser(userDTO);
            return detail;
        }
        Long sightsId = creativity.getSightsId();
        // 如果是景点发布的文创
        if (sightsId !=null){
            SightsCulDTO sightsCulDTO = new SightsCulDTO();
            SightsBase sightsBase = baseMapper.selectSightsBaseBySightsId(sightsId);
            BeanUtils.copyBeanProp(sightsCulDTO,sightsBase);
            detail.setSight(sightsCulDTO);
            return detail;
        }
        return detail;
    }


    /**
     *  增加 取消 点赞
     * @param record
     * @return
     */
    @Transactional
    @Override
    public int addCulLike(CulRecord record) {
        int i = recordMapper.judgeOnlyOneLikeCul(record.getUserId(),record.getCulId());
        if (i==0){
            recordMapper.addLikeCul(record.getUserId(),record.getCulId());
        }else {
            recordMapper.deleteLikeCul(record.getUserId(),record.getCulId());
        }

        return i;
    }

    /**
     * 增加 浏览
     * @param record
     * @return
     */
    @Transactional
    @Override
    public int addCulView(CulRecord record) {
        int i = recordMapper.addViewCul(record.getUserId(),record.getCulId());
        return i;
    }

    /**
     * 增加 取消 收藏
     * @param record
     * @return
     */
    @Transactional
    @Override
    public int addCulCollect(CulRecord record) {
        int i = recordMapper.judgeOnlyOneCollectCul(record.getUserId(),record.getCulId());
        if (i==0){
            recordMapper.addCollectCul(record.getUserId(),record.getCulId());
        }else {
            recordMapper.deleteCollectCul(record.getUserId(),record.getCulId());
        }

        return i;
    }

    /**
     * 非登录增加浏览
     * @param culId
     * @return
     */
    @Transactional
    @Override
    public int addCulViewAnonymous(Long culId) {
        // 获取redis上的缓存
        Boolean hasKey = redisCache.hasKey(Constants.CUL_COLLECT_ANONYMOUS);
        // 如果没有
        if (!hasKey){
            // 建立
            List<Long> cul = new ArrayList<>();
            cul.add(culId);
            // 加入缓存
            redisCache.setCacheList(Constants.CUL_COLLECT_ANONYMOUS,cul);
        }
        // 如果有  乐观锁  获取数据 并删除 增加
        redisCache.lock(Constants.CUL_COLLECT_ANONYMOUS);
        List<Long> culList = redisCache.getCacheObject(Constants.CUL_COLLECT_ANONYMOUS);
        redisCache.deleteObject(Constants.CUL_COLLECT_ANONYMOUS);
        culList.add(culId);
        redisCache.setCacheList(Constants.CUL_COLLECT_ANONYMOUS,culList);
        return 0;
    }

    /**
     * 定时任务
     */
    public void addAnonymousByRedis(){
        redisCache.lock(Constants.CUL_COLLECT_ANONYMOUS);// 乐观锁
        List<Long> culList = redisCache.getCacheObject(Constants.CUL_COLLECT_ANONYMOUS); //获取
        redisCache.setCacheList(Constants.CUL_COLLECT_ANONYMOUS,culList);// 删除
        // 批量更新
        sightsCulCreativityMapper.updateAddCulView(culList);
    }

    /**
     * 获取文创稿件
     * @param userId
     * @return
     */
    @Override
    public List<CulHomeDTO> getDraft(Long userId) {
        List<SightsCulCreativity> draft = sightsCulCreativityMapper.getCulDraft(userId);
        List<CulHomeDTO> homeDTOS = new ArrayList<>();
        draft.forEach(item->{
            CulHomeDTO homeDTO = new CulHomeDTO();
            BeanUtils.copyBeanProp(homeDTO,item);
            homeDTOS.add(homeDTO);
        });
        return homeDTOS;
    }

    /** 批量删除*/
    @Override
    public int deleteBatchesCulByIds(Long[] culId) {
        int i = sightsCulCreativityMapper.deleteBatchesCul(culId);
        return i;
    }

    /**
     * ways  0  审核通过
     *       1  审核不通过
     *       2  进行中
      * @param user
     * @param way
     * @return
     */
    @Override
    public List<CulHomeDTO> getUserAllArticleByWays(Long user, Integer way) {
        List<SightsCulCreativity> culCreativities = sightsCulCreativityMapper.getAllCUlByWays(user, way);
        List<CulHomeDTO> collect = culCreativities.stream().map(item -> {
            CulHomeDTO homeDTO = new CulHomeDTO();
            BeanUtils.copyBeanProp(homeDTO, item);
            return homeDTO;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 文创语音
     * @param id  id
     * @param position   0 intro 1 content
     * @param audioId id
     * @return
     */
    @Override
    public CulVoiceDTO transReturnCul(Long id, Integer position, Long audioId) {
        SysAudio audio = audioMapper.selectSysAudioByAudioId(audioId);
        CulVoiceDTO voiceDTO = new CulVoiceDTO();
        SightsCulCreativity culCreativity = sightsCulCreativityMapper.selectSightsCulCreativityByCulCreativityId(id);
        if (position ==0 ){
            String intro = culCreativity.getCulCreativityIntro();
            String s = intro.replaceAll(pattern, "");
            String result = getTranslateResult(s, null, audio.getBaiduLabel());
            voiceDTO.setCulCreativityIntroOUT(result);
        }else {
            String content = culCreativity.getCulCreativityContent();
            String s = content.replaceAll(pattern, "");
            String result = getTranslateResult(s, null, audio.getBaiduLabel());
            voiceDTO.setCulCreativityContentOUT(result);
        }
        voiceDTO.setCulCreativityId(id);
        voiceDTO.setSpeakTTS(audio.getSpeakLabel());
        return voiceDTO;
    }

    /**
     * 用户主页展示 完成
     */
    @Override
    public List<CulHomeDTO> getAllCulByUserId(Long userId, Integer way) {
        List<CulHomeDTO> culHomeDTOS = new ArrayList<>();
        List<SightsCulCreativity> personPage = sightsCulCreativityMapper.selectCulInPersonPage(userId, way);
        personPage.stream().forEach(item->{
            CulHomeDTO culHomeDTO = new CulHomeDTO();
            BeanUtils.copyBeanProp(culHomeDTO,item);
            culHomeDTOS.add(culHomeDTO);
        });
        return culHomeDTOS;
    }

    /**
     * 获取收藏记录 完成
     * @param userId
     * @return
     */
    @Override
    public List<CulHomeDTO> getAllCulCollect(Long userId) {
        List<SightsCulCreativity> creativities = sightsCulCreativityMapper.selectCulCollectByUserId(userId);
        List<CulHomeDTO> culHomeDTOS = getHomeDTOS(creativities);
        return culHomeDTOS;
    }

    private List<CulHomeDTO> getHomeDTOS(List<SightsCulCreativity> creativities) {
        List<CulHomeDTO> culHomeDTOS = new ArrayList<>();
        creativities.stream().forEach(item->{
            CulHomeDTO culHomeDTO = new CulHomeDTO();
            BeanUtils.copyBeanProp(culHomeDTO,item);
            culHomeDTOS.add(culHomeDTO);
        });
        return culHomeDTOS;
    }

    @Override
    public List<CulHomeDTO> getAllCulLike(Long userId) {
        List<SightsCulCreativity> creativities = sightsCulCreativityMapper.selectCulLikeByUserId(userId);
        List<CulHomeDTO> dtos = getHomeDTOS(creativities);
        return dtos;
    }

    @Override
    public List<CulHomeDTO> getAllCulView(Long userId) {
        List<SightsCulCreativity> creativities = sightsCulCreativityMapper.selectCulViewByUserId(userId);
        List<CulHomeDTO> dtos = getHomeDTOS(creativities);
        return dtos;
    }


    /**
     * 懒加载获取数据 待优化
     * @param userId
     * @return
     */
    @Override
    public List<CulLazyDTO> getConcernsLazyCul(Long userId) {
        List<SightsCulCreativity> creativity = sightsCulCreativityMapper.selectLazyCul(userId);
        List<CulLazyDTO> culLazyDTOS = new ArrayList<>();

        creativity.stream().forEach(item->{
            CulLazyDTO culLazyDTO = new CulLazyDTO();
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyBeanProp(culLazyDTO,item);
            Long user = item.getUserId();
            SysUser sysUser = userMapper.selectUserById(user);
            BeanUtils.copyBeanProp(userDTO,sysUser);
            culLazyDTO.setUser(userDTO);
            culLazyDTOS.add(culLazyDTO);
        });;
        return culLazyDTOS;
    }

    /**
     * 文创通过率 待优化 有点错
     * @return
     */
    @Override
    public Double getCulRate() {
        List<SightsCulCreativity> culData = sightsCulCreativityMapper.getCulData();
        if (culData.isEmpty()){
            return 1.0;
        }
        Long ok = 0L;
        Long all = 0L;
        while (culData.iterator().hasNext()) {
            SightsCulCreativity next = culData.iterator().next();
            if (next.getIsOk()=="Y"){
                ok++;
            }
            all++;
        }

        return ok/all.doubleValue();
    }

    /**
     * 获取文创可视化数据 待优化有点错
     * @return
     */
    @Override
    public CulStatisticPie getCulData() {
        CulStatisticPie pie = new CulStatisticPie();
        pie.setCulCreativityView(0L);
        pie.setCulCreativityLike(0L);
        pie.setCulCreativityCollection(0L);

        List<SightsCulCreativity> culData = sightsCulCreativityMapper.getCulData();
        if (culData.isEmpty()) {
            return pie;
        }
        while (culData.iterator().hasNext()) {

            SightsCulCreativity next = culData.iterator().next();
            pie.setCulCreativityLike(pie.getCulCreativityLike() + (next.getCulCreativityLike() == null ? 0L:next.getCulCreativityLike()) );
            pie.setCulCreativityCollection(pie.getCulCreativityCollection()+ (next.getCulCreativityCollection()==null?0L:next.getCulCreativityCollection()));
            pie.setCulCreativityView(pie.getCulCreativityView()+ (next.getCulCreativityView()==null?0L:next.getCulCreativityView()));

        }
        return pie;
    }

    /**
     * 获取审核状态可视化 待优化有错哦
     * @return
     */
    @Override
    public Long[] getJudgeData() {

        Long[] data = new Long[4];
        for (int i = 0; i < data.length; i++) {
            data[i] = 0L ;
        }
        List<SightsCulCreativity> culJudgeData = sightsCulCreativityMapper.getCulData();
        if (culJudgeData.isEmpty() ){
            for (int i = 0; i < data.length; i++) {
                data[i] = 0L;
            }
            return data;
        }
//        这样效率低 虽然可以并发
//        Long sum = culJudgeData.stream().count();
//        Long ok = culJudgeData.stream().filter(item -> item.getIsOk() == "Y").count();
//        Long no = culJudgeData.stream().filter(item -> item.getIsOk() == "N").count();
//        Long unJudge = culJudgeData.stream().filter(item -> item.getIsOk() == "U").count();

        // 下面的效率更高
        Iterator<SightsCulCreativity> iterator = culJudgeData.stream().iterator();
        while(iterator.hasNext()){
            SightsCulCreativity next = iterator.next();
            switch (next.getIsOk()){
                case "U" : data[3]++; data[0] ++; break;
                case "Y" : data[1]++; data[0] ++;break;
                case "N" : data[2]++; data[0] ++;break;
                default: log.warn("审核状态数据存在错误");
            }
        }

        return data;
    }

    /**
     *  5 大数据 完成
     * @return
     */
    @Override
    public Long[] getCuLDMY() {
        Long[] data = new Long[5];
        // 今日累计数据
        data[0] = sightsCulCreativityMapper.getDayCulData();
        // 本月累计数据
        data[1] = sightsCulCreativityMapper.getMonthCulData();
        // 今年累计数据
        data[2] = sightsCulCreativityMapper.getYearCulData();
        // 今年总通
        data[3] = sightsCulCreativityMapper.getYearOKCulData();
        // 今年总未通
        data[4] = sightsCulCreativityMapper.getYearNOCulData();
        return data;
    }

    /**
     * 文创排行 完成
     * @return
     */
    @Override
    public List<CulTopDTO> getTopCul() {
        List<CulTopDTO> culTopDTOS = new ArrayList<>();
        List<SightsCulCreativity> top = sightsCulCreativityMapper.getCulTop();
        top.stream().forEach(item->{
            CulTopDTO dto = new CulTopDTO();
            BeanUtils.copyBeanProp(dto,item);
            culTopDTOS.add(dto);
        });
        return culTopDTOS;
    }

    /**
     * 获取编辑信息 完成
     * @param culId
     * @return
     */
    @Override
    public CulCreateDTO reEditCul(Long culId) {
        SightsCulCreativity creativity = selectSightsCulCreativityByCulCreativityId(culId);
        CulCreateDTO createDTO = new CulCreateDTO();
        BeanUtils.copyBeanProp(createDTO,creativity);
        return createDTO;

    }

}
