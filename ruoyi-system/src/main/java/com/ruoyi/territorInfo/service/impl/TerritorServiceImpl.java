package com.ruoyi.territorInfo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.territorInfo.domain.dto.TerritorAreaDTO;
import com.ruoyi.territorInfo.domain.dto.TerritorCityDTO;
import com.ruoyi.territorInfo.domain.dto.TerritorProvinceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.territorInfo.mapper.TerritorMapper;
import com.ruoyi.territorInfo.domain.Territor;
import com.ruoyi.territorInfo.service.ITerritorService;

/**
 * 国内数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@Service
public class TerritorServiceImpl implements ITerritorService {
    @Autowired
    private TerritorMapper territorMapper;

    /**
     * 查询国内数据
     * 
     * @param territorId 国内数据主键
     * @return 国内数据
     */
    @Override
    public Territor selectTerritorByTerritorId(Integer territorId)
    {
        return territorMapper.selectTerritorByTerritorId(territorId);
    }

    /**
     * 查询国内数据列表
     * 
     * @param territor 国内数据
     * @return 国内数据
     */
    @Override
    public List<Territor> selectTerritorList(Territor territor)
    {
        return territorMapper.selectTerritorList(territor);
    }

    /**
     * 新增国内数据
     * 
     * @param territor 国内数据
     * @return 结果
     */
    @Override
    public int insertTerritor(Territor territor)
    {
        return territorMapper.insertTerritor(territor);
    }

    /**
     * 修改国内数据
     * 
     * @param territor 国内数据
     * @return 结果
     */
    @Override
    public int updateTerritor(Territor territor)
    {
        return territorMapper.updateTerritor(territor);
    }

    /**
     * 批量删除国内数据
     * 
     * @param territorIds 需要删除的国内数据主键
     * @return 结果
     */
    @Override
    public int deleteTerritorByTerritorIds(Integer[] territorIds)
    {
        return territorMapper.deleteTerritorByTerritorIds(territorIds);
    }

    /**
     * 删除国内数据信息
     * 
     * @param territorId 国内数据主键
     * @return 结果
     */
    @Override
    public int deleteTerritorByTerritorId(Integer territorId)
    {
        return territorMapper.deleteTerritorByTerritorId(territorId);
    }

    /**
     * 获取 中国所有省级信息
     * @return
     */
    @Override
    public List<TerritorProvinceDTO> getProvinceInfo() {
        List<Territor> province = territorMapper.getChinaProvince();
        return province.stream().map(item->{
            TerritorProvinceDTO provinceDTO = new TerritorProvinceDTO();
            BeanUtils.copyBeanProp(provinceDTO,item);
            return provinceDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TerritorCityDTO> getCityInfo(String province) {
        List<Territor> city = territorMapper.getChinaCity(province);

        return city.stream().map(item->{
            TerritorCityDTO cityDTO = new TerritorCityDTO();
            BeanUtils.copyBeanProp(cityDTO,item);
            return cityDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TerritorAreaDTO> getAreaInfo(String cityGeocode) {
        List<Territor> area = territorMapper.getCityArea(cityGeocode);
        return area.stream().map(item->{
            TerritorAreaDTO areaDTO = new TerritorAreaDTO();
            BeanUtils.copyBeanProp(areaDTO,item);
            return areaDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TerritorProvinceDTO> searchProvinceInfo(String province) {

        return null;
    }

    @Override
    public List<TerritorCityDTO> searchCityInfo(String city,String cityGeocode) {
        return null;
    }


}
