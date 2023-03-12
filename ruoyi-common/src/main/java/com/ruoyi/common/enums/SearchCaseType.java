package com.ruoyi.common.enums;

/**
 * 搜索类型
 * @author Chas
 * @date 2023-3
 */
public enum SearchCaseType {

    /* 景点 */
    SIGHTS("/frontHome/attractions/attraction/","SIGHTS"),
    /* 文章 */
    ARTICLE("/frontHome/articlepage/","ARTICLE"),
    /* 文创 */
    CREATION("/frontHome/culcreation/","CREATION");

    private final String route;

    private final String type;

    SearchCaseType(String route, String type) {
        this.route = route;
        this.type = type;
    }

    public String getRoute() {
        return route;
    }

    public String getType() {
        return type;
    }
}
