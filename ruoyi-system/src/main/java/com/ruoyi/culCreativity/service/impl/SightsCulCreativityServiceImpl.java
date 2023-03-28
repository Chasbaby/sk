package com.ruoyi.culCreativity.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import com.ruoyi.article.service.impl.ArticleServiceImpl;
import com.ruoyi.common.core.domain.entity.DTO.UserDTO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.culCreativity.domain.*;
import com.ruoyi.culCreativity.domain.dto.CulDetail;
import com.ruoyi.culCreativity.domain.dto.CulHomeDTO;
import com.ruoyi.culCreativity.domain.dto.CulLazyDTO;
import com.ruoyi.culCreativity.domain.dto.CulStatisticPie;
import com.ruoyi.culCreativity.mapper.CulRecordMapper;
import com.ruoyi.sights.domain.*;
import com.ruoyi.sights.domain.DTO.SightsCulDTO;
import com.ruoyi.sights.mapper.*;
import com.ruoyi.system.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.culCreativity.ISightsCulCreativityService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文创Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@Service
public class SightsCulCreativityServiceImpl implements ISightsCulCreativityService 
{
    private static final Logger log = LoggerFactory.getLogger(SightsCulCreativityServiceImpl.class);
    @Autowired
    private SightsCulCreativityMapper sightsCulCreativityMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SightsBaseMapper baseMapper;
    @Autowired
    private CulRecordMapper recordMapper;



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
     * @return
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

        return 0;
    }

    /**
     * 增加 浏览
     * @param record
     * @return
     */
    @Transactional
    @Override
    public int addCulView(CulRecord record) {
        return 0;
    }

    /**
     * 非登录增加浏览
     * @param culId
     * @return
     */
    @Override
    public int addCulViewAnonymous(Long culId) {
        return 0;
    }

    /**
     * 增加 取消 收藏
     * @param record
     * @return
     */
    @Transactional
    @Override
    public int addCulCollect(CulRecord record) {
        return 0;
    }

    /**
     * 用户主页展示
     * @param userId
     * @param way
     * @return
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
     * 获取收藏记录
     * @param userId
     * @return
     */
    @Override
    public List<CulHomeDTO> getAllCulCollect(Long userId) {
        List<SightsCulCreativity> creativities = sightsCulCreativityMapper.selectCulCollectByUserId(userId);
        List<CulHomeDTO> culHomeDTOS = new ArrayList<>();
        creativities.stream().forEach(item->{
            CulHomeDTO culHomeDTO = new CulHomeDTO();
            BeanUtils.copyBeanProp(culHomeDTO,item);
            culHomeDTOS.add(culHomeDTO);
        });
        return culHomeDTOS;
    }


    /**
     * 懒加载获取数据
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
     * 文创通过率
     * @return
     */
    @Override
    public Double getCulRate() {
        Double aDouble = sightsCulCreativityMapper.selectCulRate();
        return aDouble;
    }
    /**
     * 获取文创可视化数据
     * @return
     */
    @Override
    public CulStatisticPie getCulData() {
        return null;
    }

    /**
     * 获取审核状态可视化
     * @return
     */
    @Override
    public Long[] getJudgeData() {
        Long[] data = new Long[4];
        List<SightsCulCreativity> culJudgeData = sightsCulCreativityMapper.getCulJudgeData();
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


}
