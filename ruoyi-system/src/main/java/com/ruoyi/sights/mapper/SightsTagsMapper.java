package com.ruoyi.sights.mapper;

import com.ruoyi.sights.domain.SightsTags;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SightsTagsMapper {

    /**
     * 获取所有景点及其对应的tags
     */

    @Select("select sb.sights_id,sb.sights_name,st.tags " +
            "from sights_base sb left join sights_tags st " +
            "on sb.sights_id = st.sights_id")
    public List<SightsTags> selectAllSightsAndTags();
}
