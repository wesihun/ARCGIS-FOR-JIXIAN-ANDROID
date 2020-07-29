package com.winto.develop.ThreeTones.bean;


import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class LayerDataListBean extends BaseResponse {
    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean extends BaseResponse {
        /**
         * area : 13022.77
         * bsm : 230521293100000001
         * czcdm : 2305211000000000000
         * czclx : 202
         * czcmc : 福利镇
         * czcmj : 13022.77
         * name : 福利镇
         * objectid : 1
         * shape : E80000002200000008001000E19304009003000001000000A6C29CD1DA3384FBFAC7810692C1119C67ACE217B099068CF81CB4B70D9CB60FB2CB0CAEF209848A089A8F13B8C21F9ADE169A8E4198F10BAC931480E02082F52C9ED2198C881082BD0D90D202D8D4138EFE0ACCE00792D00BCAE8059EB013E69819B0B270D8C40E90D40AE698198CE007C6A255FE9A28C6A255C09B28E69C18C8E805C0D548D0BB01A4C00AEED608A2D041C2B54AACC410D6892188DB06E2B128F48702EEE91BD8F10BF2CF22DA9807D8DD0FFCD804D2FF159E9505F8A41890CD12D88F13B0801FC49E078E8A10805F
         * ysdm : 2099030100
         */

        private double area;
        private String bsm;
        private String czcdm;
        private String czclx;
        private String czcmc;
        private double czcmj;
        private String name = "无";
        private int objectid;
        private String shape;
        private String ysdm;

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        public String getBsm() {
            return bsm;
        }

        public void setBsm(String bsm) {
            this.bsm = bsm;
        }

        public String getCzcdm() {
            return czcdm;
        }

        public void setCzcdm(String czcdm) {
            this.czcdm = czcdm;
        }

        public String getCzclx() {
            return czclx;
        }

        public void setCzclx(String czclx) {
            this.czclx = czclx;
        }

        public String getCzcmc() {
            return czcmc;
        }

        public void setCzcmc(String czcmc) {
            this.czcmc = czcmc;
        }

        public double getCzcmj() {
            return czcmj;
        }

        public void setCzcmj(double czcmj) {
            this.czcmj = czcmj;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getObjectid() {
            return objectid;
        }

        public void setObjectid(int objectid) {
            this.objectid = objectid;
        }

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public String getYsdm() {
            return ysdm;
        }

        public void setYsdm(String ysdm) {
            this.ysdm = ysdm;
        }
    }
}
