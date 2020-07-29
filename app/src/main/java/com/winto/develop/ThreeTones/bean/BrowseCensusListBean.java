package com.winto.develop.ThreeTones.bean;


import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class BrowseCensusListBean extends BaseResponse {

    /**
     * count : 3
     * loginCount : 1
     * code : 0
     * msg : 成功
     * data : [{"logid":1163,"userid":1,"logtitle":"浏览记录","logcontent":"登入系统","createtime":"2020-06-17T07:59:55"},{"logid":1164,"userid":1,"logtitle":"浏览记录","logcontent":"点击资源共享","createtime":"2020-06-17T07:59:58"},{"logid":1165,"userid":1,"logtitle":"浏览记录","logcontent":"点击统计分析","createtime":"2020-06-17T08:00:01"}]
     */

    private int count;
    private int loginCount;
    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
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

    public static class DataBean extends BaseResponse{
        /**
         * logid : 1163
         * userid : 1
         * logtitle : 浏览记录
         * logcontent : 登入系统
         * createtime : 2020-06-17T07:59:55
         */

        private int logid;
        private int userid;
        private String logtitle;
        private String logcontent;
        private String createtime;

        public int getLogid() {
            return logid;
        }

        public void setLogid(int logid) {
            this.logid = logid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getLogtitle() {
            return logtitle;
        }

        public void setLogtitle(String logtitle) {
            this.logtitle = logtitle;
        }

        public String getLogcontent() {
            return logcontent;
        }

        public void setLogcontent(String logcontent) {
            this.logcontent = logcontent;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}
