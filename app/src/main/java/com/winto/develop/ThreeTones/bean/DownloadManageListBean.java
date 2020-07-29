package com.winto.develop.ThreeTones.bean;


import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class DownloadManageListBean extends BaseResponse {

    /**
     * count : 3
     * code : 0
     * msg : 查询成功
     * data : [{"resourcename":"图例1","reson":null,"applytime":"2020-06-03T14:03:59","url":"/images/1.jpg","applyid":37,"depname":"调度计划"},{"resourcename":"图例1","reson":null,"applytime":"2020-06-03T15:20:49","url":"/images/1.jpg","applyid":38,"depname":"调度计划"},{"resourcename":"技术文档2","reson":null,"applytime":"2020-06-16T08:47:33","url":"/document/word/testword.docx","applyid":41,"depname":"生产"}]
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

    public static class DataBean extends BaseResponse{
        /**
         * resourcename : 图例1
         * reson : null
         * applytime : 2020-06-03T14:03:59
         * url : /images/1.jpg
         * applyid : 37
         * depname : 调度计划
         */

        private String resourcename;
        private Object reson;
        private String applytime;
        private String url;
        private int applyid;
        private String depname;

        public String getResourcename() {
            return resourcename;
        }

        public void setResourcename(String resourcename) {
            this.resourcename = resourcename;
        }

        public Object getReson() {
            return reson;
        }

        public void setReson(Object reson) {
            this.reson = reson;
        }

        public String getApplytime() {
            return applytime;
        }

        public void setApplytime(String applytime) {
            this.applytime = applytime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getApplyid() {
            return applyid;
        }

        public void setApplyid(int applyid) {
            this.applyid = applyid;
        }

        public String getDepname() {
            return depname;
        }

        public void setDepname(String depname) {
            this.depname = depname;
        }
    }
}
