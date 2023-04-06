package com.ruoyi.sights.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction
 * @date 2023-4
 */
@Data
public class SightsReturnDTO implements Serializable {
    private Long sightsId;
    private Long sightsLike;
    private Long sightsHits;
    private Long sightsView;
    private Long sightsCollect;
    private Double sightsScore;
    private String sightsName;
    private String sightsEng;
    private String sightsIntro;
    private String sightsImage;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
