package com.winto.develop.ThreeTones.bean;

import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class StatisticalAnalysisMenuListBean extends BaseResponse {

    /**
     * id : 20
     * parentid : 0
     * menuename : 专题更新统计分析
     * type : polyline
     * createtime : 2020-06-17 00:00:00
     * subAnalysisMenue : [{"id":21,"parentid":20,"menuename":"定位基础","type":"polyline","createtime":"2020-06-17 00:00:00","subAnalysisMenue":[{"id":22,"parentid":21,"menuename":"测量控制点更新","type":"polyline","createtime":"2020-06-17 00:00:00","subAnalysisMenue":[],"tablename":null},{"id":23,"parentid":21,"menuename":"数字正射影像图纠正控制点更新","type":"polyline","createtime":"2020-06-17 00:00:00","subAnalysisMenue":[],"tablename":null}],"tablename":null},{"id":24,"parentid":20,"menuename":"境界与政区","type":"polygon","createtime":"2020-06-17 00:00:00","subAnalysisMenue":[{"id":25,"parentid":24,"menuename":"行政区更新","type":"polygon","createtime":"2020-06-17 00:00:00","subAnalysisMenue":[],"tablename":null},{"id":26,"parentid":24,"menuename":"行政区界线更新","type":"polygon","createtime":"2020-06-17 00:00:00","subAnalysisMenue":[],"tablename":null},{"id":27,"parentid":24,"menuename":"行政区界线更新","type":"polygon","createtime":"2020-06-17 00:00:00","subAnalysisMenue":[],"tablename":null}]}]
     * isExpend
     */

    private int id;
    private int parentid;
    private String menuename;
    private String type;
    private String createtime;
    private String tablename;
    private boolean isExpend;
    private List<StatisticalAnalysisMenuListBean> subAnalysisMenue;

    public boolean isExpend() {
        return isExpend;
    }

    public void setExpend(boolean expend) {
        isExpend = expend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getMenuename() {
        return menuename;
    }

    public void setMenuename(String menuename) {
        this.menuename = menuename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public List<StatisticalAnalysisMenuListBean> getSubAnalysisMenue() {
        return subAnalysisMenue;
    }

    public void setSubAnalysisMenue(List<StatisticalAnalysisMenuListBean> subAnalysisMenue) {
        this.subAnalysisMenue = subAnalysisMenue;
    }
}
