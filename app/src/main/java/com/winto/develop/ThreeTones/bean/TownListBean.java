package com.winto.develop.ThreeTones.bean;

import com.winto.develop.ThreeTones.base.BaseResponse;

import java.util.List;

public class TownListBean extends BaseResponse {

    /**
     * id : 169
     * subAdministrations : [{"id":181,"subAdministrations":[],"name":"东荣村","parentId":169,"treeCode":"2305211002020000000"},{"id":182,"subAdministrations":[],"name":"东兴村","parentId":169,"treeCode":"2305211002030000000"},{"id":183,"subAdministrations":[],"name":"青山村","parentId":169,"treeCode":"2305211002040000000"},{"id":184,"subAdministrations":[],"name":"双丰村","parentId":169,"treeCode":"2305211002050000000"},{"id":185,"subAdministrations":[],"name":"清泉村","parentId":169,"treeCode":"2305211002070000000"},{"id":186,"subAdministrations":[],"name":"先锋村","parentId":169,"treeCode":"2305211002080000000"},{"id":187,"subAdministrations":[],"name":"长征村","parentId":169,"treeCode":"2305211002090000000"},{"id":188,"subAdministrations":[],"name":"胜利村","parentId":169,"treeCode":"2305211002100000000"},{"id":189,"subAdministrations":[],"name":"东发村","parentId":169,"treeCode":"2305211002110000000"},{"id":190,"subAdministrations":[],"name":"金星村","parentId":169,"treeCode":"2305211002120000000"},{"id":191,"subAdministrations":[],"name":"新发村","parentId":169,"treeCode":"2305211002130000000"},{"id":192,"subAdministrations":[],"name":"红联村","parentId":169,"treeCode":"2305211002140000000"},{"id":193,"subAdministrations":[],"name":"安邦村","parentId":169,"treeCode":"2305211002150000000"},{"id":194,"subAdministrations":[],"name":"东辉村","parentId":169,"treeCode":"2305211002160000000"},{"id":195,"subAdministrations":[],"name":"高丰村","parentId":169,"treeCode":"2305211002170000000"},{"id":196,"subAdministrations":[],"name":"永久村","parentId":169,"treeCode":"2305211002180000000"},{"id":197,"subAdministrations":[],"name":"福胜农工贸有限公司","parentId":169,"treeCode":"2305211002320000000"},{"id":198,"subAdministrations":[],"name":"农丰农工贸有限公司","parentId":169,"treeCode":"2305211004240000000"},{"id":199,"subAdministrations":[],"name":"福新农工贸有限公司集体","parentId":169,"treeCode":"2305211004280000000"},{"id":200,"subAdministrations":[],"name":"太平林场","parentId":169,"treeCode":"2305211004420000000"},{"id":201,"subAdministrations":[],"name":"七星林场","parentId":169,"treeCode":"2305211004430000000"},{"id":202,"subAdministrations":[],"name":"丰乐林场","parentId":169,"treeCode":"2305211004450000000"},{"id":203,"subAdministrations":[],"name":"峻山林场","parentId":169,"treeCode":"2305211004460000000"},{"id":336,"subAdministrations":[],"name":"五四农工贸有限公司集体","parentId":169,"treeCode":"2305211004300000000"}]
     * name : 福利镇
     * parentId : 0
     * treeCode : 2305211000000000000
     */

    private int id;
    private String name;
    private int parentId;
    private String treeCode;
    private List<SubAdministrationsBean> subAdministrations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getTreeCode() {
        return treeCode;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode;
    }

    public List<SubAdministrationsBean> getSubAdministrations() {
        return subAdministrations;
    }

    public void setSubAdministrations(List<SubAdministrationsBean> subAdministrations) {
        this.subAdministrations = subAdministrations;
    }

    public static class SubAdministrationsBean  extends BaseResponse{
        /**
         * id : 181
         * subAdministrations : []
         * name : 东荣村
         * parentId : 169
         * treeCode : 2305211002020000000
         */

        private int id;
        private String name;
        private int parentId;
        private String treeCode;
        private List<?> subAdministrations;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getTreeCode() {
            return treeCode;
        }

        public void setTreeCode(String treeCode) {
            this.treeCode = treeCode;
        }

        public List<?> getSubAdministrations() {
            return subAdministrations;
        }

        public void setSubAdministrations(List<?> subAdministrations) {
            this.subAdministrations = subAdministrations;
        }
    }
}
