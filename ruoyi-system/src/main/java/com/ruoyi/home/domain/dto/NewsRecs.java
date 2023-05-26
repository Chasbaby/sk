package com.ruoyi.home.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.lucene.spatial3d.geom.SerializableObject;

import java.io.Serializable;

/**
 * @author chas
 * @introduction news silimar result
 * @date 2023-5-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsRecs implements Serializable {
    private Long newsIdOne;
    private Long newsIdTwo;
    private Double newsScore;
}
