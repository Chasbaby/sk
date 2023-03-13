package com.ruoyi.sights.SightsAround;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 餐馆对象 sights_around_restaurant
 * 
 * @author ruoyi
 * @date 2023-01-05
 */
public class SightsAroundRestaurant extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 景点周边餐馆id */
    private Long restaurantId;

    /** 景点id */
    @Excel(name = "景点id")
    private Long sightsId;

    /** 餐馆名称 */
    @Excel(name = "餐馆名称")
    private String restaurantName;

    /** 餐馆地址 */
    @Excel(name = "餐馆地址")
    private String restaurantAddress;

    /** 餐馆管理者 */
    @Excel(name = "餐馆管理者")
    private String restaurantManager;

    /** 订购电话 */
    @Excel(name = "订购电话")
    private String restaurantHotline;

    /** 餐馆简介 */
    @Excel(name = "餐馆简介")
    private String restaurantIntro;

    /** 详细介绍 */
    private String restaurantDetail;

    /** 餐馆菜的风格(菜系) */
    @Excel(name = "餐馆菜的风格(菜系)")
    private String restaurantStyle;

    /** 对外通告 */
    private String restaurantNotice;

    /** 餐馆图片 */
    private String restaurantPhoto;

    /** 餐馆主打菜介绍 */
    private String restaurantMaindish;

    /** 荣誉合格证明 */
    private String restaurantCertificate;

    /** 点评均分 */
    @Excel(name = "点评均分")
    private BigDecimal restaurantScore;

    /** 点评人数 */
    private Integer restaurantReviewers;

    /** 显示资格(是否) */
    @Excel(name = "显示资格(是否)")
    private String showFlag;

    /** 合同 */
    private String contract;

    public void setRestaurantId(Long restaurantId) 
    {
        this.restaurantId = restaurantId;
    }

    public Long getRestaurantId() 
    {
        return restaurantId;
    }
    public void setSightsId(Long sightsId) 
    {
        this.sightsId = sightsId;
    }

    public Long getSightsId() 
    {
        return sightsId;
    }
    public void setRestaurantName(String restaurantName) 
    {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() 
    {
        return restaurantName;
    }
    public void setRestaurantAddress(String restaurantAddress) 
    {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantAddress() 
    {
        return restaurantAddress;
    }
    public void setRestaurantManager(String restaurantManager) 
    {
        this.restaurantManager = restaurantManager;
    }

    public String getRestaurantManager() 
    {
        return restaurantManager;
    }
    public void setRestaurantHotline(String restaurantHotline) 
    {
        this.restaurantHotline = restaurantHotline;
    }

    public String getRestaurantHotline() 
    {
        return restaurantHotline;
    }
    public void setRestaurantIntro(String restaurantIntro) 
    {
        this.restaurantIntro = restaurantIntro;
    }

    public String getRestaurantIntro() 
    {
        return restaurantIntro;
    }
    public void setRestaurantDetail(String restaurantDetail) 
    {
        this.restaurantDetail = restaurantDetail;
    }

    public String getRestaurantDetail() 
    {
        return restaurantDetail;
    }
    public void setRestaurantStyle(String restaurantStyle) 
    {
        this.restaurantStyle = restaurantStyle;
    }

    public String getRestaurantStyle() 
    {
        return restaurantStyle;
    }
    public void setRestaurantNotice(String restaurantNotice) 
    {
        this.restaurantNotice = restaurantNotice;
    }

    public String getRestaurantNotice() 
    {
        return restaurantNotice;
    }
    public void setRestaurantPhoto(String restaurantPhoto) 
    {
        this.restaurantPhoto = restaurantPhoto;
    }

    public String getRestaurantPhoto() 
    {
        return restaurantPhoto;
    }
    public void setRestaurantMaindish(String restaurantMaindish) 
    {
        this.restaurantMaindish = restaurantMaindish;
    }

    public String getRestaurantMaindish() 
    {
        return restaurantMaindish;
    }
    public void setRestaurantCertificate(String restaurantCertificate) 
    {
        this.restaurantCertificate = restaurantCertificate;
    }

    public String getRestaurantCertificate() 
    {
        return restaurantCertificate;
    }
    public void setRestaurantScore(BigDecimal restaurantScore) 
    {
        this.restaurantScore = restaurantScore;
    }

    public BigDecimal getRestaurantScore() 
    {
        return restaurantScore;
    }
    public void setRestaurantReviewers(Integer restaurantReviewers) 
    {
        this.restaurantReviewers = restaurantReviewers;
    }

    public Integer getRestaurantReviewers() 
    {
        return restaurantReviewers;
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
            .append("restaurantId", getRestaurantId())
            .append("sightsId", getSightsId())
            .append("restaurantName", getRestaurantName())
            .append("restaurantAddress", getRestaurantAddress())
            .append("restaurantManager", getRestaurantManager())
            .append("restaurantHotline", getRestaurantHotline())
            .append("restaurantIntro", getRestaurantIntro())
            .append("restaurantDetail", getRestaurantDetail())
            .append("restaurantStyle", getRestaurantStyle())
            .append("restaurantNotice", getRestaurantNotice())
            .append("restaurantPhoto", getRestaurantPhoto())
            .append("restaurantMaindish", getRestaurantMaindish())
            .append("restaurantCertificate", getRestaurantCertificate())
            .append("restaurantScore", getRestaurantScore())
            .append("restaurantReviewers", getRestaurantReviewers())
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
