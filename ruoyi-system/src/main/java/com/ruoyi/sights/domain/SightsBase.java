package com.ruoyi.sights.domain;

import cn.easyes.annotation.*;
import cn.easyes.annotation.rely.Analyzer;
import cn.easyes.annotation.rely.FieldStrategy;
import cn.easyes.annotation.rely.FieldType;
import cn.easyes.annotation.rely.IdType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.sights.service.ISightsHotService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

import static com.ruoyi.common.constant.ESConstant.POST_TAG;
import static com.ruoyi.common.constant.ESConstant.PRE_TAG;


/**
 * ES:
 * @IndexName 索引名注解    value：指定索引名；shardsNum：分片数；replicasNum：副本数
 * @IndexId ES主键注解    type：指定注解类型，CUSTOMIZE表示自定义
 * @IndexField ES字段注解    fieldType：字段在索引中的类型；analyzer：索引文档时用的分词器；nestedClass：嵌套类
 * @Score 得分注解    decimalPlaces：得分保留小数位，实体类中被作为ES查询得分返回的字段使用
 */

/**
 * 景点基本信息对象 sights_base
 * 
 * @author ruoyi chas
 * @date 2022-10-25 2023-5-31
 */
@Data
@EqualsAndHashCode
@IndexName(value = "sights",shardsNum = 3,replicasNum = 2)
public class SightsBase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @IndexId(type = IdType.CUSTOMIZE)
    @Excel(name = "景点ID",type = Excel.Type.ALL,cellType= Excel.ColumnType.NUMERIC)
    /** 景点ID */
    private Long sightsId;

    @IndexField(exist = false)
    @Excel(name = "点赞量",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    /** 景点点赞量*/
    private Long sightsLike;

    @IndexField(exist = false)
    @Excel(name = "点击量",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    /**景点点击量*/
    private Long sightsHits;

    @IndexField(exist = false)
    @Excel(name = "浏览量",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    /**景点浏览量*/
    private Long sightsView;

    @IndexField(exist = false)
    @Excel(name = "收藏",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    /** 收藏量**/
    private Long sightsCollect;

    @IndexField(exist = false)
    @Excel(name = "平均分",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    /**景点平均分*/
    private Double sightsScore;

    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    /** 景点名称 */
    @Excel(name = "景点名称",type = Excel.Type.ALL)
    private String sightsName;

    @IndexField(exist = false)
    @Excel(name = "热度",type = Excel.Type.EXPORT,cellType= Excel.ColumnType.NUMERIC)
    /** 景点热度*/
    private Long sightsHot;

    @IndexField(exist = false)
    @Excel(name = "联系电话",type = Excel.Type.ALL)
    /** 景点联系电话*/
    private String sightsTelephone;

//    @IndexField(fieldType = FieldType.KEYWORD ,fieldData = true,strategy = FieldStrategy.NOT_EMPTY)
    @Excel(name = "英文",type = Excel.Type.ALL)
    /** 景点英文*/
    private String sightsEng;

    @IndexField(exist = false)
    @Excel(name = "开放信息",type = Excel.Type.ALL)
    /** 景点开放信息*/
    private String sightsOpen;

    @IndexField(exist = false)
    @Excel(name = "景点位置",type = Excel.Type.ALL)
    /** 景点位置(文字描述) */
    private String sightsLocation;

    @IndexField(exist = false)
    @Excel(name = "经度",type = Excel.Type.ALL,cellType= Excel.ColumnType.NUMERIC,scale=3)
    /** 景点经度 */
    private Double sightsLongitude;

    @IndexField(exist = false)
    @Excel(name="纬度",type = Excel.Type.ALL,cellType= Excel.ColumnType.NUMERIC,scale = 3)
    /** 景点纬度 */
    private Double sightsLatitude;

    @IndexField(strategy = FieldStrategy.NOT_EMPTY,fieldType = FieldType.TEXT)
    @Excel(name="图片",type = Excel.Type.ALL,cellType= Excel.ColumnType.IMAGE)
    /** 景点图片url  最多5张*/
    private String sightsImage;
    /*** 视频 */
    private String sightsVideo;
    /** 缩略图 */
    private String sightsCover;

    @IndexField(exist = false)
    @Excel(name = "二维码",type = Excel.Type.ALL,cellType = Excel.ColumnType.IMAGE)
    /**景点购物二维码*/
    private String sightsCode;

    @HighLight(postTag =POST_TAG ,preTag =PRE_TAG,mappingField = "highlightIntro")
    @IndexField(fieldType = FieldType.KEYWORD_TEXT,fieldData = true,analyzer = Analyzer.IK_SMART,searchAnalyzer = Analyzer.IK_MAX_WORD,strategy = FieldStrategy.NOT_EMPTY)
    /** 景点简介 */
    @Excel(name = "景点简介",type = Excel.Type.ALL)
    private String sightsIntro;

    @HighLight(postTag =POST_TAG ,preTag =PRE_TAG,mappingField = "highlightDetail")
    @IndexField(fieldType = FieldType.TEXT,fieldData = true,analyzer = Analyzer.IK_SMART,searchAnalyzer = Analyzer.IK_MAX_WORD)
    @Excel(name = "景点详细",type = Excel.Type.ALL)
    /** 景点的细节 */
    private String sightsDetail;
    /**高亮*/
    private String highlightDetail;

    private String highlightIntro;

    @IndexField(exist = false)
    /** 景点的种类 */
    @Excel(name = "景点的种类",type = Excel.Type.ALL,dictType = "sights_category")
    private String sightsCategory;

    @IndexField(exist = false)
    /** 景点国家 */
    @Excel(name = "景点国家",type = Excel.Type.ALL)
    private String sightsNation;

    /** 编码ID*/
    private String territorId;

    @IndexField(exist = false)
    /** 景点城市 */
    @Excel(name = "景点城市",type = Excel.Type.ALL)
    private String sightsCity;

    @IndexField(exist = false)
    private Date lastUpdated;

    private String sightsTop;

    /** decimalPlaces：得分保留小数位，实体类中被作为ES查询得分返回的字段使用 **/
    @Score
    private Float score;


    public synchronized void addView(){
        sightsView ++;
        refresh();
    }

    public synchronized void addLike(){
        sightsLike ++;
        refresh();
    }

    public synchronized void reduceLike(){
        sightsLike--;
    }

    public synchronized void addCollect(){
        sightsCollect ++;
        refresh();
    }

    public synchronized void reduceCollect(){
        sightsCollect--;
    }

    public synchronized void addHits(){
        sightsHits ++;
        refresh();
    }

    public synchronized void addScore(Double score){
        this.sightsScore = (score + this.sightsScore) / 2.0;
        refresh();
    }

    private void refresh(){
        this.lastUpdated = new Date();
        calculateHot();

    }

    private void calculateHot(){
        // 这个变化可能有点大  暂定
        this.sightsHot = this.sightsHot + new Double(sightsLike * 0.3 + sightsHits * 0.1
                + sightsCollect * 0.4 + sightsView * 0.2 + sightsScore * 10).longValue();
//                / new Double(time(lastUpdated,getUpdateTime())).intValue();
    }

    private double time(Date now,Date updated){
        return Math.exp(now.getTime() - updated.getTime());
    }


    /**
     * 更新景点热度 在定时任务
     */
    public static void hotTimeReduce(){
        SpringUtils.getBean(ISightsHotService.class).updateHot();
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sightsId", getSightsId())
            .append("sightsLike",getSightsLike())
            .append("sightsHits",getSightsHits())
            .append("sightsView",getSightsView())
            .append("sightsCollect",getSightsCollect())
            .append("sightsScore",getSightsScore())
            .append("hot",getSightsHot())
            .append("tele",getSightsTelephone())
            .append("eng",getSightsEng())
             .append("open",getSightsOpen())
            .append("sightsName", getSightsName())
            .append("sightsLocation", getSightsLocation())
            .append("sightsLongitude", getSightsLongitude())
            .append("sightsLatitude", getSightsLatitude())
            .append("sightsCode",getSightsCode())
            .append("sightsImage", getSightsImage())
            .append("sightsIntro", getSightsIntro())
            .append("sightsDetail", getSightsDetail())
            .append("sightsCategory", getSightsCategory())
            .append("sightsNation", getSightsNation())
            .append("sightsCity", getSightsCity())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
