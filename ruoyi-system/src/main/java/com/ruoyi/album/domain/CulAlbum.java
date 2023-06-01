package com.ruoyi.album.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文创专栏对象 cul_album
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public class CulAlbum extends BaseEntity
{
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

    public void setAlbumId(Long albumId) 
    {
        this.albumId = albumId;
    }

    public Long getAlbumId() 
    {
        return albumId;
    }
    public void setAlbumName(String albumName) 
    {
        this.albumName = albumName;
    }

    public String getAlbumName() 
    {
        return albumName;
    }
    public void setAlbumImage(String albumImage) 
    {
        this.albumImage = albumImage;
    }

    public String getAlbumImage() 
    {
        return albumImage;
    }
    public void setAlbumTitle(String albumTitle) 
    {
        this.albumTitle = albumTitle;
    }

    public String getAlbumTitle() 
    {
        return albumTitle;
    }
    public void setAlbumSlogan(String albumSlogan) 
    {
        this.albumSlogan = albumSlogan;
    }

    public String getAlbumSlogan() 
    {
        return albumSlogan;
    }
    public void setAlbumIntroduce(String albumIntroduce) 
    {
        this.albumIntroduce = albumIntroduce;
    }

    public String getAlbumIntroduce() 
    {
        return albumIntroduce;
    }
    public void setIfBan(String ifBan) 
    {
        this.ifBan = ifBan;
    }

    public String getIfBan() 
    {
        return ifBan;
    }

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
