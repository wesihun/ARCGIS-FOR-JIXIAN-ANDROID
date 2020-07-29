package com.winto.develop.ThreeTones.bean;

import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class SpecialMenuListBean extends BaseResponse {

    /**
     * id : 1
     * parentid : 0
     * menuename : 三调专项调查
     * serverpath :
     * createtime : 2020-06-09 00:00:00
     * tablename : null
     * shape : null
     * subSpecialMenue : [{"id":2,"parentid":1,"menuename":"拆除未尽区","serverpath":"/arcgis/rest/services/jixian/CCWJQ_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"ccwjq","shape":"polygon","subSpecialMenue":[{"id":10,"parentid":2,"menuename":"测量控制点更新","serverpath":null,"createtime":"2020-06-09 00:00:00","tablename":null,"shape":null,"subSpecialMenue":[]}]},{"id":3,"parentid":1,"menuename":"城镇村等用地","serverpath":"/arcgis/rest/services/jixian/CZCDYD_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"czcdyd","shape":"polygon","subSpecialMenue":[]},{"id":4,"parentid":1,"menuename":"村籍调查区界线","serverpath":"/arcgis/rest/services/jixian/CJDCQJX_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"cjdcqjx","shape":"polyline","subSpecialMenue":[]},{"id":5,"parentid":1,"menuename":"行政区","serverpath":"/arcgis/rest/services/jixian/XZQ_TAG/MapServer","createtime":"2020-06-09 00:00:00","tablename":"xzq","shape":"polygon","subSpecialMenue":[]},{"id":6,"parentid":1,"menuename":"国家公园","serverpath":"/arcgis/rest/services/jixian/GJGY_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"gjgy","shape":"polygon","subSpecialMenue":[]},{"id":7,"parentid":1,"menuename":"开发园区","serverpath":"/arcgis/rest/services/jixian/KFYA_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"kfyq","shape":"polygon","subSpecialMenue":[]},{"id":8,"parentid":1,"menuename":"临时用地","serverpath":"/arcgis/rest/services/jixian/LSYD_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"lsyd","shape":"polygon","subSpecialMenue":[]},{"id":9,"parentid":1,"menuename":"坡度图","serverpath":"/arcgis/rest/services/jixian/PDT_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"pdt","shape":"polygon","subSpecialMenue":[]},{"id":10,"parentid":1,"menuename":"湿地公园","serverpath":"/arcgis/rest/services/jixian/SDGY_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"sdgy","shape":"polygon","subSpecialMenue":[]},{"id":11,"parentid":1,"menuename":"森林公园","serverpath":"/arcgis/rest/services/jixian/SLGY_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"slgy","shape":"polygon","subSpecialMenue":[]},{"id":12,"parentid":1,"menuename":"生态保护红线","serverpath":"/arcgis/rest/services/jixian/STBHHX_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"stbhhx","shape":"polygon","subSpecialMenue":[]},{"id":13,"parentid":1,"menuename":"推土区","serverpath":"/arcgis/rest/services/jixian/TTQ_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"ttq","shape":"polygon","subSpecialMenue":[]},{"id":14,"parentid":1,"menuename":"行政区界线","serverpath":"/arcgis/rest/services/jixian/XZQJX_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"xzqjx","shape":"polyline","subSpecialMenue":[]},{"id":15,"parentid":1,"menuename":"永久基本农田","serverpath":"/arcgis/rest/services/jixian/YJJBNTTB_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"yjjbnttb","shape":"polygon","subSpecialMenue":[]},{"id":16,"parentid":1,"menuename":"自然保护区","serverpath":"/arcgis/rest/services/jixian/ZRBHQ_D/MapServer","createtime":"2020-06-09 00:00:00","tablename":"zrbhq","shape":"polygon","subSpecialMenue":[]}]
     */

    private int id;
    private int parentid;
    private String menuename;
    private String serverpath;
    private String createtime;
    private Object tablename;
    private Object shape;
    private List<SubSpecialMenueBeanX> subSpecialMenue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getMenuename() {
        return menuename;
    }

    public void setMenuename(String menuename) {
        this.menuename = menuename;
    }

    public String getServerpath() {
        return serverpath;
    }

    public void setServerpath(String serverpath) {
        this.serverpath = serverpath;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Object getTablename() {
        return tablename;
    }

    public void setTablename(Object tablename) {
        this.tablename = tablename;
    }

    public Object getShape() {
        return shape;
    }

    public void setShape(Object shape) {
        this.shape = shape;
    }

    public List<SubSpecialMenueBeanX> getSubSpecialMenue() {
        return subSpecialMenue;
    }

    public void setSubSpecialMenue(List<SubSpecialMenueBeanX> subSpecialMenue) {
        this.subSpecialMenue = subSpecialMenue;
    }

    public static class SubSpecialMenueBeanX extends BaseResponse {
        /**
         * id : 2
         * parentid : 1
         * menuename : 拆除未尽区
         * serverpath : /arcgis/rest/services/jixian/CCWJQ_D/MapServer
         * createtime : 2020-06-09 00:00:00
         * tablename : ccwjq
         * shape : polygon
         * subSpecialMenue : [{"id":10,"parentid":2,"menuename":"测量控制点更新","serverpath":null,"createtime":"2020-06-09 00:00:00","tablename":null,"shape":null,"subSpecialMenue":[]}]
         */

        private int id;
        private int parentid;
        private String menuename;
        private String serverpath;
        private String createtime;
        private String tablename;
        private String shape;
        private List<SubSpecialMenueBean> subSpecialMenue;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentid() {
            return parentid;
        }

        public void setParentid(int parentid) {
            this.parentid = parentid;
        }

        public String getMenuename() {
            return menuename;
        }

        public void setMenuename(String menuename) {
            this.menuename = menuename;
        }

        public String getServerpath() {
            return serverpath;
        }

        public void setServerpath(String serverpath) {
            this.serverpath = serverpath;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getTablename() {
            return tablename;
        }

        public void setTablename(String tablename) {
            this.tablename = tablename;
        }

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public List<SubSpecialMenueBean> getSubSpecialMenue() {
            return subSpecialMenue;
        }

        public void setSubSpecialMenue(List<SubSpecialMenueBean> subSpecialMenue) {
            this.subSpecialMenue = subSpecialMenue;
        }

        public static class SubSpecialMenueBean  extends BaseResponse{
            /**
             * id : 10
             * parentid : 2
             * menuename : 测量控制点更新
             * serverpath : null
             * createtime : 2020-06-09 00:00:00
             * tablename : null
             * shape : null
             * subSpecialMenue : []
             */

            private int id;
            private int parentid;
            private String menuename;
            private Object serverpath;
            private String createtime;
            private Object tablename;
            private Object shape;
            private List<?> subSpecialMenue;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParentid() {
                return parentid;
            }

            public void setParentid(int parentid) {
                this.parentid = parentid;
            }

            public String getMenuename() {
                return menuename;
            }

            public void setMenuename(String menuename) {
                this.menuename = menuename;
            }

            public Object getServerpath() {
                return serverpath;
            }

            public void setServerpath(Object serverpath) {
                this.serverpath = serverpath;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public Object getTablename() {
                return tablename;
            }

            public void setTablename(Object tablename) {
                this.tablename = tablename;
            }

            public Object getShape() {
                return shape;
            }

            public void setShape(Object shape) {
                this.shape = shape;
            }

            public List<?> getSubSpecialMenue() {
                return subSpecialMenue;
            }

            public void setSubSpecialMenue(List<?> subSpecialMenue) {
                this.subSpecialMenue = subSpecialMenue;
            }
        }
    }
}
