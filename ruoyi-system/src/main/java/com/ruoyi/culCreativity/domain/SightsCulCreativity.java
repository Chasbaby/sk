package com.ruoyi.culCreativity.domain;

import com.alibaba.excel.annotation.format.NumberFormat;
import com.ruoyi.common.annotation.Excels;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文创对象 sights_cul_creativity
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
public class SightsCulCreativity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文创ID */
    private Long culCreativityId;

    /** 景点ID */
    private Long sightsId;

    /** 用户ID*/
    private Long userId;

    /** 文创标题 */
    @Excel(name = "文创标题")
    private String culCreativityTitle;

    /**图片*/
    private String culCreativityImage;

    /** 文创简介 */
    @Excel(name = "文创简介")
    private String culCreativityIntro;
    /** 文创内容*/
    private String culCreativityContent;

    /** 文创类别 */
    @Excel(name = "文创类别")
    private String culCreativityCategory;

    @Excel(name = "文创类型")
    private String culCreativityType;

    /** 标签 */
    @Excel(name = "标签")
    private String culCreativityTags;

    /** 关键字 */
    @Excel(name = "关键字")
    private String culCreativityKey;

    /** 点赞量 */
    @Excel(name = "点赞量")
    private Long culCreativityLike;

    /** 不喜欢量 */
    @Excel(name = "不喜欢量")
    private Long culCreativityDislike;
    @NumberFormat
    /** 收藏量 */
    @Excel(name = "收藏量")
    private Long culCreativityCollection;

    /** 点击量 */
    @Excel(name = "点击量")
    private Long culCreativityHits;
    /**浏览量*/
    private Long culCreativityView;

    /** 审核者 */
    private String judgeBy;
    /** 审核时间 */
    private String judgeTime;

    /** 是否删除(Y表示已删除N表示未删除) */
    @Excel(name = "是否删除(Y表示已删除N表示未删除)")
    private String delFlag;

    /** 是否置顶(Y表示置顶N表示未置顶) */
    @Excel(name = "是否置顶(Y表示置顶N表示未置顶)")
    private String topFlag;

    public String getCulCreativityContent() {
        return culCreativityContent;
    }

    public void setCulCreativityContent(String culCreativityContent) {
        this.culCreativityContent = culCreativityContent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCulCreativityView() {
        return culCreativityView;
    }

    public void setCulCreativityView(Long culCreativityView) {
        this.culCreativityView = culCreativityView;
    }

    public Long getCulCreativityId() {
        return culCreativityId;
    }

    public void setCulCreativityId(Long culCreativityId) {
        this.culCreativityId = culCreativityId;
    }

    public Long getSightsId() {
        return sightsId;
    }

    public void setSightsId(Long sightsId) {
        this.sightsId = sightsId;
    }

    public String getCulCreativityTitle() {
        return culCreativityTitle;
    }

    public void setCulCreativityTitle(String culCreativityTitle) {
        this.culCreativityTitle = culCreativityTitle;
    }

    public String getCulCreativityImage() {
        return culCreativityImage;
    }

    public void setCulCreativityImage(String culCreativityImage) {
        this.culCreativityImage = culCreativityImage;
    }

    public String getCulCreativityIntro() {
        return culCreativityIntro;
    }

    public void setCulCreativityIntro(String culCreativityIntro) {
        this.culCreativityIntro = culCreativityIntro;
    }

    public String getCulCreativityCategory() {
        return culCreativityCategory;
    }

    public void setCulCreativityCategory(String culCreativityCategory) {
        this.culCreativityCategory = culCreativityCategory;
    }

    public String getCulCreativityTags() {
        return culCreativityTags;
    }

    public void setCulCreativityTags(String culCreativityTags) {
        this.culCreativityTags = culCreativityTags;
    }

    public String getCulCreativityKey() {
        return culCreativityKey;
    }

    public void setCulCreativityKey(String culCreativityKey) {
        this.culCreativityKey = culCreativityKey;
    }

    public Long getCulCreativityLike() {
        return culCreativityLike;
    }

    public void setCulCreativityLike(Long culCreativityLike) {
        this.culCreativityLike = culCreativityLike;
    }

    public Long getCulCreativityDislike() {
        return culCreativityDislike;
    }

    public void setCulCreativityDislike(Long culCreativityDislike) {
        this.culCreativityDislike = culCreativityDislike;
    }

    public Long getCulCreativityCollection() {
        return culCreativityCollection;
    }

    public void setCulCreativityCollection(Long culCreativityCollection) {
        this.culCreativityCollection = culCreativityCollection;
    }

    public Long getCulCreativityHits() {
        return culCreativityHits;
    }

    public void setCulCreativityHits(Long culCreativityHits) {
        this.culCreativityHits = culCreativityHits;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getTopFlag() {
        return topFlag;
    }

    public void setTopFlag(String topFlag) {
        this.topFlag = topFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("culCreativityId", getCulCreativityId())
            .append("sightsId", getSightsId())
            .append("culCreativityTitle", getCulCreativityTitle())
            .append("culCreativityIntro", getCulCreativityIntro())
            .append("culCreativityImage",getCulCreativityImage())
            .append("culCreativityCategory", getCulCreativityCategory())
            .append("culCreativityTags", getCulCreativityTags())
            .append("culCreativityKey", getCulCreativityKey())
            .append("culCreativityLike", getCulCreativityLike())
            .append("culCreativityDislike", getCulCreativityDislike())
            .append("culCreativityCollection", getCulCreativityCollection())
            .append("culCreativityHits", getCulCreativityHits())
                .append("culCreativityView",getCulCreativityView())
                .append("culCreativityContent",getCulCreativityContent())
            .append("delFlag", getDelFlag())
            .append("topFlag", getTopFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
