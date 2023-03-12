package com.ruoyi.sights.mapper;

import com.ruoyi.culCreativity.domain.SightsCulCreativity;
import com.ruoyi.sights.domain.SightsCulCreativityUserCollect;

import java.util.List;

public interface SightsCulCreativityUserCollectMapper {
    public int addSightsCulCreativityCollection(SightsCulCreativityUserCollect sightsCulCreativityUserCollect);
    public int cancelSightsCulCreativityCollection(SightsCulCreativityUserCollect sightsCulCreativityUserCollect);
    public List<SightsCulCreativity> selectCollectSightsCulCreativityRecord(Long userId);

}
