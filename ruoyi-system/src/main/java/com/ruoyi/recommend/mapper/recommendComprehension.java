package com.ruoyi.recommend.mapper;

import com.ruoyi.sights.domain.DTO.SightsCountDTO;
import com.ruoyi.sights.domain.SightsBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface recommendComprehension {

    public List<SightsBase> getHistoryHotSights();

    public List<SightsBase>  getItemCFSights(Long sightsId);

    public List<SightsBase> getNoSights(@Param("sightsId") List<Long> sightsId, @Param("num") Integer num);
}
