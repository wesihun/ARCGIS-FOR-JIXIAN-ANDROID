package com.winto.develop.ThreeTones.bean;

import com.winto.develop.ThreeTones.base.BaseResponse;

public class PostListBean extends BaseResponse {

    /**
     * postid : 1
     * postname : 工程师
     * postdetail : 程序员
     * createtime : 2020-04-08T16:00:00.000+0000
     */

    private int postid;
    private String postname;
    private String postdetail;
    private String createtime;

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getPostdetail() {
        return postdetail;
    }

    public void setPostdetail(String postdetail) {
        this.postdetail = postdetail;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
