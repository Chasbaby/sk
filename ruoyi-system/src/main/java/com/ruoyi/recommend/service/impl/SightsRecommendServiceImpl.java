package com.ruoyi.recommend.service.impl;

import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.recommend.mapper.recommendComprehensionMapper;
import com.ruoyi.recommend.service.SightsRecommendService;
import com.ruoyi.sights.domain.DTO.SightsCountDTO;
import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.sights.mapper.SightsBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chas
 * @introduction 景点推荐服务
 * @date 2023-4
 */
@Service
public class SightsRecommendServiceImpl implements SightsRecommendService {

    @Autowired
    private recommendComprehensionMapper comprehension;

    @Autowired
    private SightsBaseMapper baseMapper;


    /**
     * 获取相似景点 (协同过滤的结果)
     * @param sightsId id
     * @return dto
     */
    @Override
    public List<SightsCountDTO> SimilarAttractions(Long sightsId) {
        List<SightsCountDTO> sightsCountDTOS = new ArrayList<>();
        List<SightsBase> sights = comprehension.getItemCFSights(sightsId);
        getReasonSights(sightsCountDTOS, sights);
        return sightsCountDTOS;
    }

    @Override
    public List<SightsCountDTO> onlineAttractions(Long userId){
        List<SightsCountDTO> sightsCountDTOS = new ArrayList<>();
        List<SightsBase> sights = comprehension.getOnlineRecommend(userId);
        getReasonSights(sightsCountDTOS, sights);
        return sightsCountDTOS;
    }

    @Override
    public List<SightsCountDTO> getRandomAttractions() {
        List<SightsCountDTO> sightsCountDTOS = new ArrayList<>();
        List<SightsBase> sights = baseMapper.getRandomSights();
        sights.stream().limit(5).forEach(item->{
            SightsCountDTO countDTO = new SightsCountDTO();
            BeanUtils.copyBeanProp(countDTO,item);
            sightsCountDTOS.add(countDTO);
        });
        return sightsCountDTOS;
    }

    private void getReasonSights(List<SightsCountDTO> sightsCountDTOS, List<SightsBase> sights) {
        int size = sights.size();
        if (size < 5) {
            if (size != 0){
                // 总计
                List<Long> list = sights.stream().map(SightsBase::getSightsId).collect(Collectors.toList());
                List<SightsBase> bases = comprehension.getNoSights(list, 5 - size);
                sights.addAll(bases);
            }else {
                // 是0  就 随机
                List<SightsBase> sightsBases = baseMapper.getRandomSights();
                List<SightsBase> collect = sightsBases.stream().limit(5).collect(Collectors.toList());
                sights.addAll(collect);
            }
        }
        sights.stream().forEach(item -> {
            SightsCountDTO dto = new SightsCountDTO();
            BeanUtils.copyBeanProp(dto, item);
            sightsCountDTOS.add(dto);
        });
    }
}
