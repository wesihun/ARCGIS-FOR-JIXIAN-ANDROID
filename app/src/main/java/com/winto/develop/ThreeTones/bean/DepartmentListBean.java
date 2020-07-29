package com.winto.develop.ThreeTones.bean;

import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class DepartmentListBean extends BaseResponse {

    /**
     * departmentid : 1
     * departmentname : 生产
     * parentid : 0
     * createtime : 2020-04-08T16:00:00.000+0000
     * subDepartment : []
     */

    private int departmentid;
    private String departmentname;
    private int parentid;
    private String createtime;
    private List<?> subDepartment;

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public List<?> getSubDepartment() {
        return subDepartment;
    }

    public void setSubDepartment(List<?> subDepartment) {
        this.subDepartment = subDepartment;
    }
}
