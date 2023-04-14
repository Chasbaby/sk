package com.ruoyi.recommend.service.impl;

import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.recommend.mapper.recommendComprehension;
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
    private recommendComprehension comprehension;


    /**
     * 获取相似景点 (协同过滤的结果)
     * @param sightsId id
     * @return dto
     */
    @Override
    public List<SightsCountDTO> SimilarAttractions(Long sightsId) {
        List<SightsCountDTO> sightsCountDTOS = new ArrayList<>();
        List<SightsBase> sights = comprehension.getItemCFSights(sightsId);
        int size = sights.size();
        if (size < 5){
            List<Long> list = sights.stream().map(SightsBase::getSightsId).collect(Collectors.toList());
            List<SightsBase> bases = comprehension.getNoSights(list, 5 - size);
            sights.addAll(bases);
        }
        sights.stream().forEach(item->{
            SightsCountDTO dto = new SightsCountDTO();
            BeanUtils.copyBeanProp(dto,item);
            sightsCountDTOS.add(dto);
        });
        return sightsCountDTOS;
    }
}
