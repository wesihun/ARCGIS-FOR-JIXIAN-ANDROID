package com.winto.develop.ThreeTones.bean;

import com.winto.develop.ThreeTones.base.BaseResponse;

public class LeafNodeInfoBean extends BaseResponse {

    /**
     * menueid : 10
     * menuename : 水田
     * parentmenueid : 3
     * firstcategory : 01
     * secondcategory : 0101
     * subMenue : null
     * createtime : 2020-04-20 09:02:04
     */

    private int menueid;
    private String menuename;
    private int parentmenueid;
    private String firstcategory;
    private String secondcategory;
    private Object subMenue;
    private String createtime;

    public int getMenueid() {
        return menueid;
    }

    public void setMenueid(int menueid) {
        this.menueid = menueid;
    }

    public String getMenuename() {
        return menuename;
    }

    public void setMenuename(String menuename) {
        this.menuename = menuename;
    }

    public int getParentmenueid() {
        return parentmenueid;
    }

    public void setParentmenueid(int parentmenueid) {
        this.parentmenueid = parentmenueid;
    }

    public String getFirstcategory() {
        return firstcategory;
    }

    public void setFirstcategory(String firstcategory) {
        this.firstcategory = firstcategory;
    }

    public String getSecondcategory() {
        return secondcategory;
    }

    public void setSecondcategory(String secondcategory) {
        this.secondcategory = secondcategory;
    }

    public Object getSubMenue() {
        return subMenue;
    }

    public void setSubMenue(Object subMenue) {
        this.subMenue = subMenue;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
