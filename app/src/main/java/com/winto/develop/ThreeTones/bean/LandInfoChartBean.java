package com.winto.develop.ThreeTones.bean;

import com.winto.develop.ThreeTones.base.BaseResponse;

public class LandInfoChartBean extends BaseResponse {

    /**
     * dlbm : 1006
     * firstCategory : 10
     * area : 4095.6057
     * dlmc : 农村道路
     */

    private String dlbm;
    private String firstCategory;
    private double area;
    private String dlmc;

    public String getDlbm() {
        return dlbm;
    }

    public void setDlbm(String dlbm) {
        this.dlbm = dlbm;
    }

    public String getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(String firstCategory) {
        this.firstCategory = firstCategory;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getDlmc() {
        return dlmc;
    }

    public void setDlmc(String dlmc) {
        this.dlmc = dlmc;
    }
}
