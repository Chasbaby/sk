package com.ruoyi.recommend.es;

import com.ruoyi.common.enums.SearchCaseType;
import com.ruoyi.recommend.es.domain.MultiSearchDTO;
import com.ruoyi.recommend.es.domain.SearchAjaxDTO;
import com.ruoyi.search.SightsSearchDTO;

import java.util.List;
import java.util.Map;

/**
 * 搜索策略
 *
 * @author Chas
 * @date 2023-2
 */
public interface SearchStrategy {

    /**
     * 搜索景点
     *
     * @param keywords 关键字
     * @return 搜索列表
     */
    List<SearchAjaxDTO> searchAll(String keywords);


    /**
     * 展示所有
     * @param keywords 关键字
     * @return 一切
     */
    List<MultiSearchDTO> showAllSearch(String keywords);

}
