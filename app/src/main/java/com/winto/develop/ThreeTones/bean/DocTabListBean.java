package com.winto.develop.ThreeTones.bean;


import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class DocTabListBean extends BaseResponse {

    /**
     * count : 0
     * code : 0
     * msg : 查询成功
     * data : [{"subMenue":[{"subMenue":[],"menueid":2,"menuename":"基本农田","parentmenueid":1},{"subMenue":[{"subMenue":[],"menueid":6,"menuename":"技术标准1","parentmenueid":3},{"subMenue":[],"menueid":7,"menuename":"技术标准2","parentmenueid":3}],"menueid":3,"menuename":"技术标准","parentmenueid":1},{"subMenue":[],"menueid":4,"menuename":"工作报告","parentmenueid":1},{"subMenue":[],"menueid":5,"menuename":"其他文档","parentmenueid":1}],"menueid":1,"menuename":"资源共享","parentmenueid":0}]
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
         * subMenue : [{"subMenue":[],"menueid":2,"menuename":"基本农田","parentmenueid":1},{"subMenue":[{"subMenue":[],"menueid":6,"menuename":"技术标准1","parentmenueid":3},{"subMenue":[],"menueid":7,"menuename":"技术标准2","parentmenueid":3}],"menueid":3,"menuename":"技术标准","parentmenueid":1},{"subMenue":[],"menueid":4,"menuename":"工作报告","parentmenueid":1},{"subMenue":[],"menueid":5,"menuename":"其他文档","parentmenueid":1}]
         * menueid : 1
         * menuename : 资源共享
         * parentmenueid : 0
         */

        private int menueid;
        private String menuename;
        private int parentmenueid;
        private List<SubMenueBean> subMenue;

        public int getMenueid() {
            return menueid;
        }

        public void setMenueid(int menueid) {
            this.menueid = menueid;
        }

        public String getMenuename() {
            return menuename;
        }

        public void setMenuename(String menuename) {
            this.menuename = menuename;
        }

        public int getParentmenueid() {
            return parentmenueid;
        }

        public void setParentmenueid(int parentmenueid) {
            this.parentmenueid = parentmenueid;
        }

        public List<SubMenueBean> getSubMenue() {
            return subMenue;
        }

        public void setSubMenue(List<SubMenueBean> subMenue) {
            this.subMenue = subMenue;
        }

        public static class SubMenueBean extends BaseResponse{
            /**
             * subMenue : []
             * menueid : 2
             * menuename : 基本农田
             * parentmenueid : 1
             */

            private int menueid;
            private String menuename;
            private int parentmenueid;
            private List<?> subMenue;

            public int getMenueid() {
                return menueid;
            }

            public void setMenueid(int menueid) {
                this.menueid = menueid;
            }

            public String getMenuename() {
                return menuename;
            }

            public void setMenuename(String menuename) {
                this.menuename = menuename;
            }

            public int getParentmenueid() {
                return parentmenueid;
            }

            public void setParentmenueid(int parentmenueid) {
                this.parentmenueid = parentmenueid;
            }

            public List<?> getSubMenue() {
                return subMenue;
            }

            public void setSubMenue(List<?> subMenue) {
                this.subMenue = subMenue;
            }
        }
    }
}
