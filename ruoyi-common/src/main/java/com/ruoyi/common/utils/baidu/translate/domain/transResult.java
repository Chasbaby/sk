package com.ruoyi.common.utils.baidu.translate.domain;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 结果
 * @date 2023-4
 */

public class transResult implements Serializable {
    private String src;
    private String dst;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public transResult() {
    }

    public transResult(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }
}
