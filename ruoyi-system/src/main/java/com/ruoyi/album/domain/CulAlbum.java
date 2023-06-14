package com.ruoyi.album.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文创专栏对象 cul_album
 * 
 * @author ruoyi chas
 * @date 2023-06-01
 */
@Data
public class CulAlbum extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 专栏id */
    private Long albumId;

    /** 专栏名称 */
    @Excel(name = "专栏名称")
    private String albumName;

    /** 专栏图片 */
    @Excel(name = "专栏图片")
    private String albumImage;

    /** 专栏主题 */
    @Excel(name = "专栏主题")
    private String albumTitle;

    /** 专栏口号 */
    @Excel(name = "专栏口号")
    private String albumSlogan;

    /** 专辑介绍 */
    @Excel(name = "专辑介绍")
    private String albumIntroduce;

    /** 是否禁用 */
    @Excel(name = "是否禁用")
    private String ifBan;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("albumId", getAlbumId())
            .append("albumName", getAlbumName())
            .append("albumImage", getAlbumImage())
            .append("albumTitle", getAlbumTitle())
            .append("albumSlogan", getAlbumSlogan())
            .append("albumIntroduce", getAlbumIntroduce())
            .append("ifBan", getIfBan())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
