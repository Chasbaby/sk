package com.ruoyi.recommend.service;

import com.ruoyi.sights.domain.DTO.SightsCountDTO;

import java.util.List;

/**
 * @author chas
 * @introduction 景点推荐服务
 * @date 2023-4
 */
public interface SightsRecommendService {

    public List<SightsCountDTO> SimilarAttractions(Long sightsId);
    public List<SightsCountDTO> onlineAttractions(Long userId);
    public List<SightsCountDTO> getRandomAttractions();
}
