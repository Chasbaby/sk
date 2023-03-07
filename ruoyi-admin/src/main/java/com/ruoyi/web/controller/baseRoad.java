package com.ruoyi.web.controller;

public class baseRoad {
    String A;
    String B;
    Double distance;

    public baseRoad(String a, String b, Double distance) {
        A = a;
        B = b;
        this.distance = distance;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "baseRoad{" +
                "begin='" + A + '\'' +
                ", over='" + B + '\'' +
                ", distance=" + distance +
                '}';
    }
}
