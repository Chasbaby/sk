package com.ruoyi.web.controller.sights.permit;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.sights.domain.DTO.SightsReturnDTO;
import com.ruoyi.sights.service.ISightsRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chas
 * @introduction 景点用户操作
 * @date 2023-4
 */
@Api("景点用户操作")
@RestController
@RequestMapping("/record")
public class SightsRecordController extends BaseController {

    @Autowired
    private ISightsRecordService recordService;

    @ApiOperation("获取用户景点收藏")
    @GetMapping("/collect/getAll")
    @PreAuthorize("@ss.hasRole('common')")
    public TableDataInfo getUserCollectSights(){
        startPage();
        List<SightsReturnDTO> record = recordService.getUserCollectRecord(getUserId());
        return getDataTable(record);
    }

    @ApiOperation("获取用户点赞景点记录")
    @GetMapping("/like/getAll")
    @PreAuthorize("@ss.hasRole('common')")
    public TableDataInfo getUserLikeSights(){
        startPage();
        List<SightsReturnDTO> record = recordService.getUserLikeRecord(getUserId());
        return getDataTable(record);
    }

    @ApiOperation("获取用户点击景点记录")
    @GetMapping("/hits/getAll")
    @PreAuthorize("@ss.hasRole('common')")
    public TableDataInfo getUserHitsSights(){
        startPage();
        List<SightsReturnDTO> record = recordService.getUserHitsRecord(getUserId());
        return getDataTable(record);
    }

    @ApiOperation("获取用户浏览景点记录")
    @GetMapping("/view/getAll")
    @PreAuthorize("@ss.hasRole('common')")
    public TableDataInfo getUserViewSights(){
        startPage();
        List<SightsReturnDTO> record = recordService.getUserViewRecord(getUserId());
        return getDataTable(record);
    }


}
