package com.ruoyi.album.mapper;

import java.util.List;
import com.ruoyi.album.domain.CulAlbum;
import com.ruoyi.album.domain.dto.AlbumCulDTO;
import org.apache.ibatis.annotations.Param;

/**
 * 文创专栏Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public interface CulAlbumMapper {
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
     * 删除文创专栏
     * @param albumId 文创专栏主键
     * @return 结果
     */
    public int deleteCulAlbumByAlbumId(Long albumId);

    /**
     * 批量删除文创专栏
     * @param albumIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCulAlbumByAlbumIds(Long[] albumIds);

    public List<CulAlbum> getAlbumSwiper();

    public List<AlbumCulDTO> getCulByAlbumId(Long albumId);

    public CulAlbum getAlbumInfo(Long albumId);

    public List<Long> getIdByAlbum(Long albumId);

    public int judgeCulIfAlbum(@Param("albumId") Long albumId, @Param("culId") Long culId);

    public int delCulInAlbum(@Param("albumId") Long albumId, @Param("culId") Long culId);

    public int addCulInAlbum(@Param("albumId") Long albumId, @Param("culId") Long culId,@Param("createBy") String createBy);
}
