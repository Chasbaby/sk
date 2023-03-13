package com.ruoyi.sights.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 酒店对象 sights_around_hotel
 * 
 * @author ruoyi
 * @date 2023-01-05
 */
public class SightsAroundHotel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 酒店ID */
    private Long hotelId;

    /** 景点id */
    @Excel(name = "景点id")
    private Long sightsId;

    /** 酒店名称 */
    @Excel(name = "酒店名称")
    private String hotelName;

    /** 酒店电话 */
    @Excel(name = "酒店电话")
    private String hotelHotline;

    /** 酒店管理 */
    @Excel(name = "酒店管理")
    private String hotelManager;

    /** 酒店地址 */
    @Excel(name = "酒店地址")
    private String hotelAddress;

    /** 酒店图片 */
    private String hotelPhoto;

    /** 酒店简介 */
    @Excel(name = "酒店简介")
    private String hotelIntro;

    /** 详细介绍 */
    private String hotelDetail;

    /** 对外通告 */
    private String hotelNotice;

    /** 荣誉合格证明 */
    private String hotelCertificate;

    /** 点评人数 */
    private Integer hotelReviewers;

    /** 点评均分 */
    @Excel(name = "点评均分")
    private BigDecimal hotelScore;

    /** 显示资格(是否) */
    @Excel(name = "显示资格(是否)")
    private String showFlag;

    /** 合同 */
    private String contract;

    public void setHotelId(Long hotelId) 
    {
        this.hotelId = hotelId;
    }

    public Long getHotelId() 
    {
        return hotelId;
    }
    public void setSightsId(Long sightsId) 
    {
        this.sightsId = sightsId;
    }

    public Long getSightsId() 
    {
        return sightsId;
    }
    public void setHotelName(String hotelName) 
    {
        this.hotelName = hotelName;
    }

    public String getHotelName() 
    {
        return hotelName;
    }
    public void setHotelHotline(String hotelHotline) 
    {
        this.hotelHotline = hotelHotline;
    }

    public String getHotelHotline() 
    {
        return hotelHotline;
    }
    public void setHotelManager(String hotelManager) 
    {
        this.hotelManager = hotelManager;
    }

    public String getHotelManager() 
    {
        return hotelManager;
    }
    public void setHotelAddress(String hotelAddress) 
    {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelAddress() 
    {
        return hotelAddress;
    }
    public void setHotelPhoto(String hotelPhoto) 
    {
        this.hotelPhoto = hotelPhoto;
    }

    public String getHotelPhoto() 
    {
        return hotelPhoto;
    }
    public void setHotelIntro(String hotelIntro) 
    {
        this.hotelIntro = hotelIntro;
    }

    public String getHotelIntro() 
    {
        return hotelIntro;
    }
    public void setHotelDetail(String hotelDetail) 
    {
        this.hotelDetail = hotelDetail;
    }

    public String getHotelDetail() 
    {
        return hotelDetail;
    }
    public void setHotelNotice(String hotelNotice) 
    {
        this.hotelNotice = hotelNotice;
    }

    public String getHotelNotice() 
    {
        return hotelNotice;
    }
    public void setHotelCertificate(String hotelCertificate) 
    {
        this.hotelCertificate = hotelCertificate;
    }

    public String getHotelCertificate() 
    {
        return hotelCertificate;
    }
    public void setHotelReviewers(Integer hotelReviewers) 
    {
        this.hotelReviewers = hotelReviewers;
    }

    public Integer getHotelReviewers() 
    {
        return hotelReviewers;
    }
    public void setHotelScore(BigDecimal hotelScore) 
    {
        this.hotelScore = hotelScore;
    }

    public BigDecimal getHotelScore() 
    {
        return hotelScore;
    }
    public void setShowFlag(String showFlag) 
    {
        this.showFlag = showFlag;
    }

    public String getShowFlag() 
    {
        return showFlag;
    }
    public void setContract(String contract) 
    {
        this.contract = contract;
    }

    public String getContract() 
    {
        return contract;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("hotelId", getHotelId())
            .append("sightsId", getSightsId())
            .append("hotelName", getHotelName())
            .append("hotelHotline", getHotelHotline())
            .append("hotelManager", getHotelManager())
            .append("hotelAddress", getHotelAddress())
            .append("hotelPhoto", getHotelPhoto())
            .append("hotelIntro", getHotelIntro())
            .append("hotelDetail", getHotelDetail())
            .append("hotelNotice", getHotelNotice())
            .append("hotelCertificate", getHotelCertificate())
            .append("hotelReviewers", getHotelReviewers())
            .append("hotelScore", getHotelScore())
            .append("showFlag", getShowFlag())
            .append("contract", getContract())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
