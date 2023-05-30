package com.ruoyi.system.domain.domainVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.DTO.UserCommentDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chas
 * @introduction 返回接口
 * @data 2023-3
 */
@Data
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long commentId;
    private Long parentId;
    private Long userId;
    private UserCommentDTO user;
    private String fatherName;
    private String commentContent;
    private Long objectId;
    private Long id;
    private Integer commentView;
    private Integer commentHits;
    private Integer commentLike;
    private String commentSource;
    private String commentIp;
    private String visableStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
}
