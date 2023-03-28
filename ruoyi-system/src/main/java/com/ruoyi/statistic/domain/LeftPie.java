package com.ruoyi.statistic.domain;

import com.ruoyi.article.domain.dto.ArticleStatisticPie;
import com.ruoyi.culCreativity.domain.dto.CulStatisticPie;

import java.io.Serializable;

/**
 * @author chas
 * @introduction 左饼图
 * @data 2023-3
 */
public class LeftPie implements Serializable {
   private ArticleStatisticPie articlePie;
   private CulStatisticPie culPie;
   private Long unJudge;
   private Long judged;
   private Long failed;
   private Long sum;

    public ArticleStatisticPie getArticlePie() {
        return articlePie;
    }

    public void setArticlePie(ArticleStatisticPie articlePie) {
        this.articlePie = articlePie;
    }

    public CulStatisticPie getCulPie() {
        return culPie;
    }

    public void setCulPie(CulStatisticPie culPie) {
        this.culPie = culPie;
    }

    public Long getUnJudge() {
        return unJudge;
    }

    public void setUnJudge(Long unJudge) {
        this.unJudge = unJudge;
    }

    public Long getJudged() {
        return judged;
    }

    public void setJudged(Long judged) {
        this.judged = judged;
    }

    public Long getFailed() {
        return failed;
    }

    public void setFailed(Long failed) {
        this.failed = failed;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }
}
