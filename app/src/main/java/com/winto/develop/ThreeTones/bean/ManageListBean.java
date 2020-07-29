package com.winto.develop.ThreeTones.bean;


import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class ManageListBean extends BaseResponse {

    /**
     * count : 3
     * code : 0
     * msg : 查询成功
     * data : [{"applyid":41,"name":"小罗伯特·唐尼","depname":"生产","postname":"工程师","applyreason":null,"phone":"18800000000","applytime":"2020-06-16T08:47:33","states":0},{"applyid":38,"name":"小罗伯特·唐尼","depname":"调度计划","postname":"工程师","applyreason":"13213123453453","phone":"18800000000","applytime":"2020-06-03T15:20:49","states":0},{"applyid":37,"name":"小罗伯特·唐尼","depname":"调度计划","postname":"工程师","applyreason":null,"phone":"18800000000","applytime":"2020-06-03T14:03:59","states":0}]
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
         * applyid : 41
         * name : 小罗伯特·唐尼
         * depname : 生产
         * postname : 工程师
         * applyreason : null
         * phone : 18800000000
         * applytime : 2020-06-16T08:47:33
         * states : 0
         */

        private int applyid;
        private String name;
        private String depname;
        private String postname;
        private String applyreason;
        private String phone;
        private String applytime;
        private int states;

        public int getApplyid() {
            return applyid;
        }

        public void setApplyid(int applyid) {
            this.applyid = applyid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepname() {
            return depname;
        }

        public void setDepname(String depname) {
            this.depname = depname;
        }

        public String getPostname() {
            return postname;
        }

        public void setPostname(String postname) {
            this.postname = postname;
        }

        public String getApplyreason() {
            return applyreason;
        }

        public void setApplyreason(String applyreason) {
            this.applyreason = applyreason;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getApplytime() {
            return applytime;
        }

        public void setApplytime(String applytime) {
            this.applytime = applytime;
        }

        public int getStates() {
            return states;
        }

        public void setStates(int states) {
            this.states = states;
        }
    }
}
