package com.winto.develop.ThreeTones.bean;

import com.winto.develop.ThreeTones.base.BaseResponse;

public class RoleListBean extends BaseResponse {

    /**
     * roleid : 1
     * rolename : 管理员
     * detail : 管理员
     * createtime : 2020-04-08T16:00:00.000+0000
     */

    private int roleid;
    private String rolename;
    private String detail;
    private String createtime;

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
