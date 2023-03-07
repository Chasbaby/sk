package com.ruoyi.recommend.domain;

import java.io.Serializable;
import java.util.Objects;

public class SightsRecs implements Serializable {
    private Long sightsIdOne;
    private Long sightsIdTwo;
    private Double sightsScore;

    public SightsRecs() {
    }

    public SightsRecs(Long sightsIdOne, Long sightsIdTwo, Double sightsScore) {
        this.sightsIdOne = sightsIdOne;
        this.sightsIdTwo = sightsIdTwo;
        this.sightsScore = sightsScore;
    }

    public Long getSightsIdOne() {
        return sightsIdOne;
    }

    public void setSightsIdOne(Long sightsIdOne) {
        this.sightsIdOne = sightsIdOne;
    }

    public Long getSightsIdTwo() {
        return sightsIdTwo;
    }

    public void setSightsIdTwo(Long sightsIdTwo) {
        this.sightsIdTwo = sightsIdTwo;
    }

    public Double getSightsScore() {
        return sightsScore;
    }

    public void setSightsScore(Double sightsScore) {
        this.sightsScore = sightsScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SightsRecs)) return false;
        SightsRecs that = (SightsRecs) o;
        return sightsIdOne.equals(that.sightsIdOne) && sightsIdTwo.equals(that.sightsIdTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sightsIdOne, sightsIdTwo);
    }
}
