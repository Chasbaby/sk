package com.ruoyi.recommend.es.domain;

import com.ruoyi.common.enums.SearchCaseType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 搜素返回类型
 * @author Chas
 * @date 2023-3
 */
public class SearchAjaxDTO implements Serializable {

    private String type;
    private String route;
    /** 搜素返回值**/
    private List<?> searchReturns;





    public SearchAjaxDTO(List<?> searchReturns) {
        this.searchReturns = searchReturns;
    }

    public SearchAjaxDTO() {
    }

    public List<?> getSearchReturns() {
        return searchReturns;
    }

    public void setSearchReturns(List<?> searchReturns) {
        this.searchReturns = searchReturns;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
