package com.ruoyi.sights.domain.DTO;

import com.ruoyi.sights.domain.SightsTicket;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chas
 * @introduction 核心地带 景点DTO
 * @data 2023-3
 */
@Data
public class SightsDTO implements Serializable {
    /** 景点ID */
    private Long sightsId;
    /** 景点点赞量*/
    private Long sightsLike;

    /**景点点击量*/
    private Long sightsHits;

    /**景点浏览量*/
    private Long sightsView;

    /** 收藏量**/
    private Long sightsCollect;

    /**景点平均分*/
    private Double sightsScore;

    private String sightsName;

    /** 景点热度*/
    private Long sightsHot;

    /** 景点联系电话*/
    private String sightsTelephone;

    /** 景点英文*/
    private String sightsEng;

    /** 景点开放信息*/
    private String sightsOpen;

    private String sightsLocation;

    private String sightsImage;

    private String sightsIntro;

    private String sightsDetail;

    private String sightsCategory;

    private String sightsNation;

    private String sightsCity;

    private List<BulletinDTO> bulletin ;

    private List<TicketDTO> tickets;

    private Integer scoreNum;
    /**景点购物二维码*/
    private String sightsCode;
    private Integer CommentNum;

    private Integer ifCollect;
    private Integer ifLike;
    private Double score;
    /*** 视频 */
    private String sightsVideo;
    /** 缩略图 */
    private String sightsCover;


}


