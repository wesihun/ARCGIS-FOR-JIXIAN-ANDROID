package com.wise.develop.WiseChat.bean;

import com.wise.develop.WiseChat.base.BaseResponse;

import java.util.List;

public class FriendApplyListBean extends BaseResponse {
    /**
     * code : 200
     * message : SUCCESS
     * data : [{"id":5,"userName":"德莱文","age":0,"sex":null,"desc":null,"addStatus":"1"},{"id":7,"userName":"伊泽瑞尔","age":0,"sex":null,"desc":null,"addStatus":"1"},{"id":8,"userName":"卢锡安","age":0,"sex":null,"desc":null,"addStatus":"1"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5
         * userName : 德莱文
         * age : 0
         * sex : null
         * desc : null
         * addStatus : 1
         */

        private int id;
        private String userName;
        private int age;
        private int sex;
        private String desc;
        private String addStatus;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getAddStatus() {
            return addStatus;
        }

        public void setAddStatus(String addStatus) {
            this.addStatus = addStatus;
        }
    }
}
