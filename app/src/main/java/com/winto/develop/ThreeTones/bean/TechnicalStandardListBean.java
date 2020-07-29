package com.winto.develop.ThreeTones.bean;


import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class TechnicalStandardListBean extends BaseResponse {

    /**
     * count : 3
     * code : 0
     * msg : 查询成功
     * data : [{"resourceid":1,"resourcetypeid":2,"resourcedir":"1.jpg","resourcename":"图例1","sender":"机构1","createtime":"2020-05-21T08:56:35","filesize":50,"url":"/images/1.jpg","encryption":0,"resourcetypename":"基本农田"},{"resourceid":2,"resourcetypeid":2,"resourcedir":"1.jpg","resourcename":"图例1","sender":"机构1","createtime":"2020-05-21T08:56:35","filesize":50,"url":"/images/1.jpg","encryption":0,"resourcetypename":"基本农田"},{"resourceid":3,"resourcetypeid":2,"resourcedir":"1.jpg","resourcename":"图例1","sender":"机构1","createtime":"2020-05-21T08:56:35","filesize":50,"url":"/images/1.jpg","encryption":0,"resourcetypename":"基本农田"}]
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
         * resourceid : 1
         * resourcetypeid : 2
         * resourcedir : 1.jpg
         * resourcename : 图例1
         * sender : 机构1
         * createtime : 2020-05-21T08:56:35
         * filesize : 50
         * url : /images/1.jpg
         * encryption : 0
         * resourcetypename : 基本农田
         */

        private int resourceid;
        private int resourcetypeid;
        private String resourcedir;
        private String resourcename;
        private String sender;
        private String createtime;
        private int filesize;
        private String url;
        private int encryption;
        private String resourcetypename;

        public int getResourceid() {
            return resourceid;
        }

        public void setResourceid(int resourceid) {
            this.resourceid = resourceid;
        }

        public int getResourcetypeid() {
            return resourcetypeid;
        }

        public void setResourcetypeid(int resourcetypeid) {
            this.resourcetypeid = resourcetypeid;
        }

        public String getResourcedir() {
            return resourcedir;
        }

        public void setResourcedir(String resourcedir) {
            this.resourcedir = resourcedir;
        }

        public String getResourcename() {
            return resourcename;
        }

        public void setResourcename(String resourcename) {
            this.resourcename = resourcename;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getFilesize() {
            return filesize;
        }

        public void setFilesize(int filesize) {
            this.filesize = filesize;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getEncryption() {
            return encryption;
        }

        public void setEncryption(int encryption) {
            this.encryption = encryption;
        }

        public String getResourcetypename() {
            return resourcetypename;
        }

        public void setResourcetypename(String resourcetypename) {
            this.resourcetypename = resourcetypename;
        }
    }
}
