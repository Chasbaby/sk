package com.ruoyi.web.controller.album.permit;

import com.ruoyi.album.service.ICulAlbumService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chas
 * @introduction 面向前台专辑
 * @date 2023-6-13
 */

@Api("面向前台专辑")
@RestController
@RequestMapping("/album")
public class CulAlbumAnController extends BaseController {

    @Autowired
    private ICulAlbumService albumService;

    @Anonymous
    @ApiOperation("专辑轮播")
    @GetMapping("/swiper")
    public AjaxResult showAlbumSwiper(){
        return AjaxResult.success(albumService.geAlbumSwiper());
    }

    @Anonymous
    @ApiOperation("获取专辑Cul信息")
    @GetMapping("/info/{albumId}")
    public TableDataInfo getAlbumCulInfo(@PathVariable Long albumId){
        startPage();
        return getDataTable(albumService.getCulByAlbum(albumId));
    }

    @Anonymous
    @ApiOperation("获取专栏本身信息")
    @GetMapping("/albumInfo/{albumId}")
    public AjaxResult getAlbum(@PathVariable Long albumId){
        return AjaxResult.success(albumService.getAlbumInfo(albumId));
    }
}
