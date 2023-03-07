package com.ruoyi.recommend.es.impl;

import com.ruoyi.article.domain.Article;
import com.ruoyi.article.mapper.ArticleMapper;
import com.ruoyi.common.enums.SearchCaseType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.xss.XssValidator;
import com.ruoyi.recommend.es.SearchStrategy;
import com.ruoyi.recommend.es.domain.MultiSearchDTO;
import com.ruoyi.recommend.es.domain.SearchAjaxDTO;
import com.ruoyi.search.ArticleSearchDTO;
import com.ruoyi.search.CulCreativitySearchDTO;
import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.search.SightsSearchDTO;
import com.ruoyi.sights.domain.SightsCulCreativity;
import com.ruoyi.sights.mapper.SightsBaseMapper;
import com.ruoyi.sights.mapper.SightsCulCreativityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.ruoyi.recommend.es.impl.common.*;

/**
 * mysql 搜索策略
 *
 * @author Chas
 * @date 2023-2
 */

@Service("mySqlSearchStrategyImpl")
public class MysqlSearchStrategyImpl implements SearchStrategy {
    private final static String pattern="<(\\S*?)[^>]*>.*?|<.*? />";

    @Autowired
    private SightsBaseMapper sightsBaseMapper;

    @Autowired
    private SightsCulCreativityMapper sightsCulMapper;

    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 全局搜索
     * @param keywords 关键字
     * @return 搜索列表
     */
    @Override
    public List<SearchAjaxDTO> searchAll(String keywords) {
        List<SearchAjaxDTO> allData= new ArrayList<>();
        SearchAjaxDTO sightsS = new SearchAjaxDTO();
        // 搜索景点
        SightsBase sightsBase = new SightsBase();
        sightsBase.setSightsName(keywords);
        sightsBase.setSightsDetail(keywords);
        sightsBase.setSightsIntro(keywords);
        sightsBase.setSightsLocation(keywords);
        List<SightsBase> searchList = sightsBaseMapper.selectSightsInSearchList(sightsBase);
        // 高亮处理
        List<SightsSearchDTO> sightsList = addSights(searchList, keywords);
        sightsS.setSearchReturns(sightsList);
        sightsS.setType(SearchCaseType.SIGHTS.getType());
        sightsS.setRoute(SearchCaseType.SIGHTS.getRoute());
        allData.add(sightsS);

        SightsCulCreativity sightsCulCreativity = new SightsCulCreativity();
        sightsCulCreativity.setCulCreativityIntro(keywords);
        sightsCulCreativity.setCulCreativityTitle(keywords);
        sightsCulCreativity.setCulCreativityKey(keywords);
        sightsCulCreativity.setCulCreativityTags(keywords);
        List<SightsCulCreativity> culCreativityList = sightsCulMapper.selectSightsInSearchList(sightsCulCreativity);
        // 高亮处理
        List<CulCreativitySearchDTO> creativityList = addSightsCulCreativity(culCreativityList, keywords);
        SearchAjaxDTO CulS = new SearchAjaxDTO();
        CulS.setSearchReturns(creativityList);
        CulS.setRoute(SearchCaseType.CREATION.getRoute());
        CulS.setType(SearchCaseType.CREATION.getType());
        allData.add(CulS);

        Article article = new Article();
        article.setArticleContent(keywords);
        article.setArticleTitle(keywords);
//        article.setIsDelete("N");
//        article.setIsOk("Y");
//        article.setStatus("1");
        List<Article> ArticleList = articleMapper.selectArticleInSearchList(article);
        //高亮处理
        List<ArticleSearchDTO> articleList = addArticle(ArticleList, keywords);
        SearchAjaxDTO articleS = new SearchAjaxDTO();
        articleS.setType(SearchCaseType.ARTICLE.getType());
        articleS.setRoute(SearchCaseType.ARTICLE.getRoute());
        articleS.setSearchReturns(articleList);
        allData.add(articleS);
        return allData;
    }

    @Override
    public List<MultiSearchDTO> showAllSearch(String keywords) {
        if (!StringUtils.isEmpty(keywords)){
            List<SightsBase> sightsBases = sightsBaseMapper.selectSightsBaseList(new SightsBase());
            SightsCulCreativity sightsCulCreativity = new SightsCulCreativity();
            sightsCulCreativity.setDelFlag("N");
            List<SightsCulCreativity> sightsCul = sightsCulMapper.selectSightsCulCreativityList(sightsCulCreativity);
            Article article = new Article();
            article.setIsOk("Y");
            article.setIsDelete("N");
            article.setStatus("1");
            List<Article> articles = articleMapper.selectArticleList(new Article());
            AtomicReference<Long> i= new AtomicReference<>(0L);
            List<MultiSearchDTO> allData = new ArrayList<>();

            List<MultiSearchDTO> sightsBaseSearch = sightsBases.stream().map(item -> {
                MultiSearchDTO searchDTO = new MultiSearchDTO();
                //searchDTO.setMultipleWork();  景点没有作者 只有发布者
                searchDTO.setMultipleCategory(item.getSightsCategory());
                searchDTO.setMultipleCollect(item.getSightsCollect());
                searchDTO.setMultipleContent(item.getSightsDetail().replaceAll(pattern,""));
                searchDTO.setMultipleHits(item.getSightsHits());
                // Long andSet = i.getAndSet(i.get() + 1);
                //searchDTO.setMultipleId(andSet);

                searchDTO.setMultipleName(item.getSightsName());
                searchDTO.setMultipleImage(item.getSightsImage());
                searchDTO.setMultipleRoute(SearchCaseType.SIGHTS.getRoute());
                searchDTO.setMultipleType(SearchCaseType.SIGHTS.getType());
                searchDTO.setMultipleScore(item.getSightsScore());
                searchDTO.setMultipleView(item.getSightsView());
                searchDTO.setMultipleLike(item.getSightsLike());
                searchDTO.setMultipleItemId(item.getSightsId());
                return searchDTO;
            }).collect(Collectors.toList());
            List<MultiSearchDTO> sightsCulSearch = sightsCul.stream().map(item -> {
                MultiSearchDTO searchDTO = new MultiSearchDTO();
                searchDTO.setMultipleCategory(item.getCulCreativityCategory());
                searchDTO.setMultipleCollect(item.getCulCreativityCollection());
                searchDTO.setMultipleContent(item.getCulCreativityIntro().replaceAll(pattern,""));
                searchDTO.setMultipleHits(item.getCulCreativityHits());
                //searchDTO.setMultipleId(i.getAndSet(i.get() + 1));
                searchDTO.setMultipleName(item.getCulCreativityTitle());
                searchDTO.setMultipleImage(item.getCulCreativityImage());
                searchDTO.setMultipleRoute(SearchCaseType.CREATION.getRoute());
                searchDTO.setMultipleType(SearchCaseType.CREATION.getType());
                //searchDTO.setMultipleScore();
                searchDTO.setMultipleView(item.getCulCreativityView());
                searchDTO.setMultipleLike(item.getCulCreativityLike());
                searchDTO.setMultipleItemId(item.getCulCreativityId());
                return searchDTO;
            }).collect(Collectors.toList());
            List<MultiSearchDTO> articleSearch = articles.stream().map(item -> {
                MultiSearchDTO searchDTO = new MultiSearchDTO();
                searchDTO.setMultipleWork(item.getUserId());
                searchDTO.setMultipleCategory(item.getArticleCategory());
                searchDTO.setMultipleCollect(item.getArticleCollect());
                searchDTO.setMultipleContent(item.getArticleContent().replaceAll(pattern,""));
                //searchDTO.setMultipleId(i.getAndSet(i.get() + 1));
                searchDTO.setMultipleName(item.getArticleTitle());
                searchDTO.setMultipleImage(item.getArticleCover());
                searchDTO.setMultipleRoute(SearchCaseType.ARTICLE.getRoute());
                searchDTO.setMultipleType(SearchCaseType.ARTICLE.getType());
                searchDTO.setMultipleView(item.getArticleView());
                searchDTO.setMultipleLike(item.getArticleLike());
                searchDTO.setMultipleItemId(item.getArticleId());
                return searchDTO;
            }).collect(Collectors.toList());
            allData.addAll(sightsBaseSearch);
            allData.addAll(sightsCulSearch);
            allData.addAll(articleSearch);
            return allData;
        }else {
            AtomicReference<Long> i = new AtomicReference<>(0L);
            // 搜索景点
            SightsBase sightsBase = new SightsBase();
            sightsBase.setSightsName(keywords);
            sightsBase.setSightsDetail(keywords);
            sightsBase.setSightsIntro(keywords);
            sightsBase.setSightsLocation(keywords);
            List<SightsBase> searchList = sightsBaseMapper.selectSightsInSearchList(sightsBase);
            List<SightsSearchDTO> sightsList = addSights(searchList, keywords);


            SightsCulCreativity sightsCulCreativity = new SightsCulCreativity();
            sightsCulCreativity.setCulCreativityIntro(keywords);
            sightsCulCreativity.setCulCreativityTitle(keywords);
            sightsCulCreativity.setCulCreativityKey(keywords);
            sightsCulCreativity.setCulCreativityTags(keywords);
            List<SightsCulCreativity> culCreativityList = sightsCulMapper.selectSightsInSearchList(sightsCulCreativity);
            List<CulCreativitySearchDTO> creativityList = addSightsCulCreativity(culCreativityList, keywords);


            Article article = new Article();
            article.setArticleContent(keywords);
            article.setArticleTitle(keywords);
            List<Article> ArticleList = articleMapper.selectArticleInSearchList(article);
            List<ArticleSearchDTO> articleList = addArticle(ArticleList, keywords);


        }



        return null;
    }
}
