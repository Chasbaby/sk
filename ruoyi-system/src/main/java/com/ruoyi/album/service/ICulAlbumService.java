package com.ruoyi.album.service;

import java.util.List;
import com.ruoyi.album.domain.CulAlbum;
import com.ruoyi.album.domain.dto.AlbumCulDTO;
import com.ruoyi.album.domain.dto.AlbumInfoDTO;
import com.ruoyi.album.domain.dto.AlbumSwiperDTO;

/**
 * 文创专栏Service接口
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public interface ICulAlbumService {
    /**
     * 查询文创专栏
     * 
     * @param albumId 文创专栏主键
     * @return 文创专栏
     */
    public CulAlbum selectCulAlbumByAlbumId(Long albumId);

    /**
     * 查询文创专栏列表
     * 
     * @param culAlbum 文创专栏
     * @return 文创专栏集合
     */
    public List<CulAlbum> selectCulAlbumList(CulAlbum culAlbum);

    /**
     * 新增文创专栏
     * 
     * @param culAlbum 文创专栏
     * @return 结果
     */
    public int insertCulAlbum(CulAlbum culAlbum);

    /**
     * 修改文创专栏
     * 
     * @param culAlbum 文创专栏
     * @return 结果
     */
    public int updateCulAlbum(CulAlbum culAlbum);

    /**
     * 批量删除文创专栏
     * 
     * @param albumIds 需要删除的文创专栏主键集合
     * @return 结果
     */
    public int deleteCulAlbumByAlbumIds(Long[] albumIds);

    /**
     * 删除文创专栏信息
     * 
     * @param albumId 文创专栏主键
     * @return 结果
     */
    public int deleteCulAlbumByAlbumId(Long albumId);

    public List<AlbumSwiperDTO> geAlbumSwiper();

    public List<AlbumCulDTO> getCulByAlbum(Long albumId);

    public AlbumInfoDTO getAlbumInfo(Long albumId);

    public int AddCulToAlbum(Long albumId,Long culId,String createBy);
}
