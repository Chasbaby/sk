package com.ruoyi.sights.service.impl;

import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.sights.domain.DTO.SightsReturnDTO;
import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.sights.mapper.SightsBaseMapper;
import com.ruoyi.sights.mapper.SightsRecordLikeMapper;
import com.ruoyi.sights.service.ISightsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chas
 * @introduction
 * @date
 */
@Service
public class SightsRecordService implements ISightsRecordService {
    @Autowired
    private SightsBaseMapper baseMapper;

    @Autowired
    private SightsRecordLikeMapper likeMapper;

    @Override
    public List<SightsReturnDTO> getUserCollectRecord(Long userId) {
        List<SightsBase> bases = likeMapper.getUserSightsCollect(userId);

        List<SightsReturnDTO> returnDTOS = getSightsReturnDTOS(bases);
        return returnDTOS;
    }

    @Override
    public List<SightsReturnDTO> getUserLikeRecord(Long userId) {
        List<SightsBase> like = likeMapper.getUserSightsLike(userId);
        List<SightsReturnDTO> dtos = getSightsReturnDTOS(like);
        return dtos;
    }

    @Override
    public List<SightsReturnDTO> getUserViewRecord(Long userId) {
        List<SightsBase> view = likeMapper.getUserSightsView(userId);
        List<SightsReturnDTO> dtos = getSightsReturnDTOS(view);
        return dtos;
    }

    @Override
    public List<SightsReturnDTO> getUserHitsRecord(Long userId) {
        List<SightsBase> hits = likeMapper.getUserSightsHits(userId);
        List<SightsReturnDTO> dtos = getSightsReturnDTOS(hits);
        return dtos;
    }

    private List<SightsReturnDTO> getSightsReturnDTOS(List<SightsBase> bases) {
        List<SightsReturnDTO> returnDTOS = new ArrayList<>();
        bases.forEach(item->{
            SightsReturnDTO returnDTO = new SightsReturnDTO();
            BeanUtils.copyBeanProp(returnDTO,item);
            returnDTOS.add(returnDTO);
        });
        return returnDTOS;
    }


}
