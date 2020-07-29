package com.winto.develop.ThreeTones.bean;


import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class UserDepartmentListBean extends BaseResponse {

    /**
     * count : 0
     * code : 0
     * msg : 查询成功
     * data : [{"subMenue":[{"subMenue":[],"menueid":3,"menuename":"调度计划","parentmenueid":2},{"subMenue":[],"menueid":4,"menuename":"调度一处","parentmenueid":2}],"menueid":2,"menuename":"调度","parentmenueid":0},{"subMenue":[],"menueid":5,"menuename":"11","parentmenueid":0}]
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
         * subMenue : [{"subMenue":[],"menueid":3,"menuename":"调度计划","parentmenueid":2},{"subMenue":[],"menueid":4,"menuename":"调度一处","parentmenueid":2}]
         * menueid : 2
         * menuename : 调度
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

        public static class SubMenueBean {
            /**
             * subMenue : []
             * menueid : 3
             * menuename : 调度计划
             * parentmenueid : 2
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
