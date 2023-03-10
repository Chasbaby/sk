package com.ruoyi.article.domain.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author chas
 * @introduction 返回前端数据
 * @data 2023-3
 */
public class ArticleReturnCore implements Serializable {

    /** 列表*/
    private List<ArticleReturnDTO> articles;

    /** 路由*/
    private String route;

    public List<ArticleReturnDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleReturnDTO> articles) {
        this.articles = articles;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
