package com.winto.develop.ThreeTones.bean;


import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class UserVillageListBean extends BaseResponse {
    /**
     * count : 0
     * code : 0
     * msg : 查询成功
     * data : [{"id":204,"parentId":170,"name":"永发村","treeCode":"2305211012010000000","remark":null},{"id":205,"parentId":170,"name":"双胜村","treeCode":"2305211012020000000","remark":null},{"id":206,"parentId":170,"name":"中兴村","treeCode":"2305211012030000000","remark":null},{"id":207,"parentId":170,"name":"七一村","treeCode":"2305211012040000000","remark":null},{"id":208,"parentId":170,"name":"顺发村","treeCode":"2305211012050000000","remark":null},{"id":209,"parentId":170,"name":"山河村","treeCode":"2305211012060000000","remark":null},{"id":210,"parentId":170,"name":"德胜村","treeCode":"2305211012070000000","remark":null},{"id":211,"parentId":170,"name":"山东村","treeCode":"2305211012080000000","remark":null},{"id":212,"parentId":170,"name":"国庆村","treeCode":"2305211012090000000","remark":null},{"id":213,"parentId":170,"name":"红光村","treeCode":"2305211012100000000","remark":null},{"id":214,"parentId":170,"name":"丰收村","treeCode":"2305211012110000000","remark":null},{"id":215,"parentId":170,"name":"兆林村","treeCode":"2305211012120000000","remark":null},{"id":216,"parentId":170,"name":"同意村","treeCode":"2305211012130000000","remark":null},{"id":217,"parentId":170,"name":"长安村","treeCode":"2305211012140000000","remark":null},{"id":218,"parentId":170,"name":"永富村","treeCode":"2305211012150000000","remark":null},{"id":219,"parentId":170,"name":"福厚村","treeCode":"2305211012170000000","remark":null},{"id":220,"parentId":170,"name":"洪仁村","treeCode":"2305211012180000000","remark":null},{"id":221,"parentId":170,"name":"务正村","treeCode":"2305211012190000000","remark":null},{"id":222,"parentId":170,"name":"城新村","treeCode":"2305211012200000000","remark":null},{"id":223,"parentId":170,"name":"五一村","treeCode":"2305211012210000000","remark":null},{"id":224,"parentId":170,"name":"黎明村","treeCode":"2305211012220000000","remark":null},{"id":225,"parentId":170,"name":"德祥村","treeCode":"2305211012230000000","remark":null},{"id":334,"parentId":170,"name":"平原村","treeCode":"2305211012160000000","remark":null}]
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
         * id : 204
         * parentId : 170
         * name : 永发村
         * treeCode : 2305211012010000000
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
