package com.ruoyi.recommend.es.impl;

import com.ruoyi.article.domain.Article;
import com.ruoyi.search.ArticleSearchDTO;
import com.ruoyi.search.CulCreativitySearchDTO;
import com.ruoyi.search.SightsSearchDTO;
import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.culCreativity.domain.SightsCulCreativity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ruoyi.common.constant.ESConstant.POST_TAG;
import static com.ruoyi.common.constant.ESConstant.PRE_TAG;


/**
 * 搜索处理
 *
 * @author Chas
 * @date 2023-3
 */
public class common {

    /**
     * 景点信息高亮处理
     * @param searchList
     * @param keywords
     * @return
     */
    public static List<SightsSearchDTO> addSights(List<SightsBase> searchList,String keywords){
        if (searchList.isEmpty()){
            return new ArrayList<>();
        }
        return searchList.stream().map(item -> {
            //获取关键字第一次出现的未知
            String sightsDetail = item.getSightsDetail();
            int index = item.getSightsDetail().indexOf(keywords);
            if (index != -1) {
                //获取关键词前面的文字  这里的 25 应该要改动
                int preIndex = index > 25 ? index - 25 : 0;
                String preText = item.getSightsDetail().substring(preIndex, index);
                //获取关键词后面的文字
                int last = index + keywords.length();
                int postLength = item.getSightsDetail().length() - last;
                int postIndex = postLength > 175 ? last + 175 : last + postLength;
                String postText = item.getSightsDetail().substring(index, postIndex);
                //内容高亮
                sightsDetail = (preText + postText).replaceAll(keywords, PRE_TAG + keywords + POST_TAG);
            }
            String intro = item.getSightsIntro().replaceAll(keywords, PRE_TAG + keywords + POST_TAG);
            String name = item.getSightsName().replaceAll(keywords, PRE_TAG + keywords + POST_TAG);
            String location = item.getSightsLocation().replaceAll(keywords, PRE_TAG + keywords + POST_TAG);
            return new SightsSearchDTO(item.getSightsId(), name,
                    location, intro, sightsDetail);
        }).collect(Collectors.toList());
    }

    /**
     * 文创信息高亮处理
     * @param searchList
     * @param keywords
     * @return
     */
    public static List<CulCreativitySearchDTO> addSightsCulCreativity(List<SightsCulCreativity> searchList, String keywords){
        if (searchList.isEmpty()){
            return new ArrayList<>();
        }
        return searchList.stream().map(item->{
            String culDetail = item.getCulCreativityIntro();
            int index = item.getCulCreativityIntro().indexOf(keywords);
            if (index!=-1){
                //获取关键词前面的文字  这里的 25 应该要改动
                int preIndex = index > 25 ? index - 25 : 0;
                String preText = item.getCulCreativityIntro().substring(preIndex, index);
                //获取关键词后面的文字
                int last = index + keywords.length();
                int postLength = item.getCulCreativityIntro().length() - last;
                int postIndex = postLength > 175 ? last + 175 : last + postLength;
                String postText = item.getCulCreativityIntro().substring(index, postIndex);
                //内容高亮
                culDetail = (preText+postText).replaceAll(keywords,PRE_TAG + keywords + POST_TAG);
            }
            String title = item.getCulCreativityTitle().replaceAll(keywords, PRE_TAG + keywords + POST_TAG);
            return new CulCreativitySearchDTO(item.getCulCreativityId(),item.getSightsId(),title,
                    item.getCulCreativityImage(),culDetail,item.getCulCreativityTags(),item.getCulCreativityKey());
        }).collect(Collectors.toList());

    }

    /**
     * 文章信息高亮处理
     * @param articleList
     * @param keywords
     * @return
     */
    public static List<ArticleSearchDTO> addArticle(List<Article> articleList,String keywords){
        if (articleList.isEmpty()){
            return new ArrayList<>();
        }
        return articleList.stream().map(item->{
            String articleContent = item.getArticleContent();
            int index = item.getArticleContent().indexOf(keywords);
            if (index !=-1){
                //获取关键词前面的文字  这里的 25 应该要改动
                int preIndex = index > 25 ? index - 25 : 0;
                String preText = item.getArticleContent().substring(preIndex, index);
                //获取关键词后面的文字
                int last = index + keywords.length();
                int postLength = item.getArticleContent().length() - last;
                int postIndex = postLength > 175 ? last + 175 : last + postLength;
                String postText = item.getArticleContent().substring(index, postIndex);
                //内容高亮
                articleContent = (preText+postText).replaceAll(keywords,PRE_TAG + keywords + POST_TAG);
            }
            String title = item.getArticleTitle().replaceAll(keywords, PRE_TAG + keywords + POST_TAG);
            return new ArticleSearchDTO(item.getArticleId(),item.getUserId(),item.getArticleCategory(),
                    item.getArticleCover(),title,articleContent,item.getOriginalUrl());
        }).collect(Collectors.toList());
    }

}
