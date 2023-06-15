package com.ruoyi.album.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.album.domain.dto.AlbumCulDTO;
import com.ruoyi.album.domain.dto.AlbumInfoDTO;
import com.ruoyi.album.domain.dto.AlbumSwiperDTO;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.album.mapper.CulAlbumMapper;
import com.ruoyi.album.domain.CulAlbum;
import com.ruoyi.album.service.ICulAlbumService;

/**
 * 文创专栏Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@Service
public class CulAlbumServiceImpl implements ICulAlbumService {
    @Autowired
    private CulAlbumMapper culAlbumMapper;

    /**
     * 查询文创专栏
     * 
     * @param albumId 文创专栏主键
     * @return 文创专栏
     */
    @Override
    public CulAlbum selectCulAlbumByAlbumId(Long albumId)
    {
        return culAlbumMapper.selectCulAlbumByAlbumId(albumId);
    }

    /**
     * 查询文创专栏列表
     * 
     * @param culAlbum 文创专栏
     * @return 文创专栏
     */
    @Override
    public List<CulAlbum> selectCulAlbumList(CulAlbum culAlbum)
    {
        return culAlbumMapper.selectCulAlbumList(culAlbum);
    }

    /**
     * 新增文创专栏
     * 
     * @param culAlbum 文创专栏
     * @return 结果
     */
    @Override
    public int insertCulAlbum(CulAlbum culAlbum) {
        culAlbum.setCreateTime(DateUtils.getNowDate());
        return culAlbumMapper.insertCulAlbum(culAlbum);
    }

    /**
     * 修改文创专栏
     * 
     * @param culAlbum 文创专栏
     * @return 结果
     */
    @Override
    public int updateCulAlbum(CulAlbum culAlbum) {
        culAlbum.setUpdateTime(DateUtils.getNowDate());
        return culAlbumMapper.updateCulAlbum(culAlbum);
    }

    /**
     * 批量删除文创专栏
     * 
     * @param albumIds 需要删除的文创专栏主键
     * @return 结果
     */
    @Override
    public int deleteCulAlbumByAlbumIds(Long[] albumIds)
    {
        return culAlbumMapper.deleteCulAlbumByAlbumIds(albumIds);
    }

    /**
     * 删除文创专栏信息
     * 
     * @param albumId 文创专栏主键
     * @return 结果
     */
    @Override
    public int deleteCulAlbumByAlbumId(Long albumId)
    {
        return culAlbumMapper.deleteCulAlbumByAlbumId(albumId);
    }

    @Override
    public List<AlbumSwiperDTO> geAlbumSwiper() {
        List<CulAlbum> swiper = culAlbumMapper.getAlbumSwiper();
        return swiper.stream().map(item->{
            AlbumSwiperDTO swiperDTO = new AlbumSwiperDTO();
            BeanUtils.copyBeanProp(swiperDTO,item);
            return swiperDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<AlbumCulDTO> getCulByAlbum(Long albumId) {
        return culAlbumMapper.getCulByAlbumId(albumId);
    }

    @Override
    public AlbumInfoDTO getAlbumInfo(Long albumId){
        CulAlbum info = culAlbumMapper.getAlbumInfo(albumId);
        AlbumInfoDTO infoDTO = new AlbumInfoDTO();
        BeanUtils.copyBeanProp(infoDTO,info);
        infoDTO.setAlbumImage(infoDTO.getAlbumImage().split(",")[1]);
        return infoDTO;
    }

    @Override
    public int AddCulToAlbum(Long albumId, Long culId,String createBy) {
        // 判断是否存在
        int i = culAlbumMapper.judgeCulIfAlbum(albumId, culId);
        if (i>0){
            // 则删除
            culAlbumMapper.delCulInAlbum(albumId,culId);
        }else {
            // 加入
            culAlbumMapper.addCulInAlbum(albumId,culId,createBy);
        }
        return i;
    }
}
