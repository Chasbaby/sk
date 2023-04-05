package com.ruoyi.sights.service;

import com.ruoyi.sights.domain.DTO.SightsReturnDTO;

import java.util.List;

/**
 * @author chas
 * @introduction
 * @date
 */
public interface ISightsRecordService {

    public List<SightsReturnDTO> getUserCollectRecord(Long userId);

    public List<SightsReturnDTO> getUserLikeRecord(Long userId);

    public List<SightsReturnDTO> getUserViewRecord(Long userId);

    public List<SightsReturnDTO> getUserHitsRecord(Long userId);
}
