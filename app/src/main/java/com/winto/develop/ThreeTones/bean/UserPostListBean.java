package com.winto.develop.ThreeTones.bean;


import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class UserPostListBean extends BaseResponse {
    /**
     * count : 0
     * code : 0
     * msg : 查询成功
     * data : [{"postid":1,"postname":"工程师","postdetail":"程序员","createtime":"2020-04-09T00:00:00"},{"postid":2,"postname":"经理","postdetail":"项目经理","createtime":"2020-04-10T00:00:00"},{"postid":4,"postname":"11","postdetail":"22","createtime":"2020-06-04T09:08:58"}]
     */

    private int count;
    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseResponse {
        /**
         * postid : 1
         * postname : 工程师
         * postdetail : 程序员
         * createtime : 2020-04-09T00:00:00
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
}
