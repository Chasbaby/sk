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
        List<SightsReturnDTO> returnDTOS = new ArrayList<>();
        bases.forEach(item->{
            SightsReturnDTO returnDTO = new SightsReturnDTO();
            BeanUtils.copyBeanProp(returnDTO,item);
            returnDTOS.add(returnDTO);
        });
        return returnDTOS;
    }
}
