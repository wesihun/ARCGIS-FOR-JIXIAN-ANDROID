package com.winto.develop.ThreeTones.bean;


import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class UserTownListBean extends BaseResponse {

    /**
     * count : 0
     * code : 0
     * msg : 查询成功
     * data : [{"id":169,"parentId":0,"name":"福利镇","treeCode":"2305211000000000000","remark":null},{"id":170,"parentId":0,"name":"集贤镇","treeCode":"2305211010000000000","remark":null},{"id":171,"parentId":0,"name":"升昌镇","treeCode":"2305211020000000000","remark":null},{"id":172,"parentId":0,"name":"丰乐镇","treeCode":"2305211030000000000","remark":null},{"id":173,"parentId":0,"name":"太平镇","treeCode":"2305211040000000000","remark":null},{"id":174,"parentId":0,"name":"腰屯乡","treeCode":"2305212010000000000","remark":null},{"id":175,"parentId":0,"name":"兴安乡","treeCode":"2305212020000000000","remark":null},{"id":176,"parentId":0,"name":"永安乡","treeCode":"2305212040000000000","remark":null},{"id":177,"parentId":0,"name":"黑龙江省笔架山监狱","treeCode":"2305215800000000000","remark":null}]
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
         * id : 169
         * parentId : 0
         * name : 福利镇
         * treeCode : 2305211000000000000
         * remark : null
         */

        private int id;
        private int parentId;
        private String name;
        private String treeCode;
        private Object remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTreeCode() {
            return treeCode;
        }

        public void setTreeCode(String treeCode) {
            this.treeCode = treeCode;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }
    }
}
