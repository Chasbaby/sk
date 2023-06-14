package com.ruoyi.culCreativity.domain;

//import com.alibaba.excel.annotation.format.NumberFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excels;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 文创对象 sights_cul_creativity
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@Data
public class SightsCulCreativity extends BaseEntity {
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
//    @NumberFormat
    /** 收藏量 */
    @Excel(name = "收藏量")
    private Long culCreativityCollection;

    /** 点击量 */
    @Excel(name = "点击量")
    private Long culCreativityHits;
    /**浏览量*/
    private Long culCreativityView;

    /*** 审核结果*/
    private String isOk;
    /** 审核者 */
    private String judgeBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    /** 审核时间 */
    private Date judgeTime;

    /** 状态*/
    private String status;

    /** 是否删除(Y表示已删除N表示未删除) */
    @Excel(name = "是否删除(Y表示已删除N表示未删除)")
    private String delFlag;

    /** 是否置顶(Y表示置顶N表示未置顶) */
    @Excel(name = "是否置顶(Y表示置顶N表示未置顶)")
    private String topFlag;

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
