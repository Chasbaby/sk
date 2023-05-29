package com.ruoyi.recommend.es.context;


import com.ruoyi.article.domain.dto.ArticleSearchPersonDTO;
import com.ruoyi.article.domain.dto.ArticleTopDTO;
import com.ruoyi.common.enums.SearchCaseType;
import com.ruoyi.recommend.es.SearchStrategy;
import com.ruoyi.recommend.es.domain.MultiSearchDTO;
import com.ruoyi.recommend.es.domain.SearchAjaxDTO;
import com.ruoyi.search.SightsSearchDTO;
import com.ruoyi.sights.domain.DTO.SightsSearchPersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ruoyi.common.enums.SearchModeType.getStrategy;

/**
 * 搜索策略上下文
 *
 * @author Chas
 * @date 2023-2
 */
@Service
public class SightsStrategyContext {

    @Value("${search.mode}")
    private String searchMode;

    @Autowired
    private Map<String, SearchStrategy> searchStrategyMap;

    /**
     * 执行搜索策略
     *
     * @param keywords 关键字
     * @return 搜索到的一切
     */
    public List<SearchAjaxDTO> executeSearchStrategy(String keywords){

        // 这里的关键字是按照整个的  应该要分词 在Mysql角度就比较不行 但是 es 肯定okk
        List<SearchAjaxDTO> listMap = searchStrategyMap
                .get(getStrategy(searchMode))
                .searchAll(keywords);
        return listMap;
    }

    /**
     * 执行展示一切
     * @param keywords
     * @return 展示一切
     */
    public List<MultiSearchDTO> executeShowAll(String keywords){
        List<MultiSearchDTO> multiSearch = searchStrategyMap
                .get(getStrategy(searchMode))
                .showAllSearch(keywords);
        return multiSearch;
    }

    /**
     * 文章单独搜素
     * @param keywords
     * @return
     */
    public List<ArticleSearchPersonDTO> executeSearchArticle(String keywords){
        List<ArticleSearchPersonDTO> dtos = searchStrategyMap
                .get(getStrategy(searchMode))
                .showArticleSearch(keywords);
        return dtos;
    }

    /**
     * 景点单独搜索
     * @param keywords
     * @return
     */
    public List<SightsSearchPersonDTO> executeSearchSights(String keywords){
        List<SightsSearchPersonDTO> dtos = searchStrategyMap
                .get(getStrategy(searchMode))
                .showSightsSearch(keywords);
        return dtos;
    }


}
