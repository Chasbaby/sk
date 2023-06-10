package com.ruoyi.territorInfo.service;

import java.util.List;
import com.ruoyi.territorInfo.domain.Territor;
import com.ruoyi.territorInfo.domain.dto.TerritorAreaDTO;
import com.ruoyi.territorInfo.domain.dto.TerritorCityDTO;
import com.ruoyi.territorInfo.domain.dto.TerritorProvinceDTO;

/**
 * 国内数据Service接口
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public interface ITerritorService 
{
    /**
     * 查询国内数据
     * 
     * @param territorId 国内数据主键
     * @return 国内数据
     */
    public Territor selectTerritorByTerritorId(Integer territorId);

    /**
     * 查询国内数据列表
     * 
     * @param territor 国内数据
     * @return 国内数据集合
     */
    public List<Territor> selectTerritorList(Territor territor);

    /**
     * 新增国内数据
     * 
     * @param territor 国内数据
     * @return 结果
     */
    public int insertTerritor(Territor territor);

    /**
     * 修改国内数据
     * 
     * @param territor 国内数据
     * @return 结果
     */
    public int updateTerritor(Territor territor);

    /**
     * 批量删除国内数据
     * 
     * @param territorIds 需要删除的国内数据主键集合
     * @return 结果
     */
    public int deleteTerritorByTerritorIds(Integer[] territorIds);

    /**
     * 删除国内数据信息
     * 
     * @param territorId 国内数据主键
     * @return 结果
     */
    public int deleteTerritorByTerritorId(Integer territorId);

    /**
     * 获取中国所有省信息
     */
    public List<TerritorProvinceDTO> getProvinceInfo();

    /**
     * 根据省份获取市
     */
    public List<TerritorCityDTO> getCityInfo(String province);

    /**
     * 根据市 获取区
     */
    public List<TerritorAreaDTO> getAreaInfo(String cityGeocode);


    public List<TerritorProvinceDTO> searchProvinceInfo(String province);

    public List<TerritorCityDTO> searchCityInfo(String city,String cityGeocode);
}
