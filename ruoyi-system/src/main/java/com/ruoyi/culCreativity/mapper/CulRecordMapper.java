package com.ruoyi.culCreativity.mapper;

import com.ruoyi.culCreativity.domain.CulRecord;
import org.apache.ibatis.annotations.Insert;

/**
 * @author chas
 * @introduction 点赞 浏览 收藏
 * @data 2023-3
 */
public interface CulRecordMapper {

    @Insert("insert into ")
    public int addLikeCul(CulRecord record);



}
