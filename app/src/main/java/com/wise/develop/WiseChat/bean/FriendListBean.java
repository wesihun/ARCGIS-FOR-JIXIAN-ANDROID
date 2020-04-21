package com.wise.develop.WiseChat.bean;

import com.wise.develop.WiseChat.base.BaseResponse;

import java.util.List;

public class FriendListBean extends BaseResponse {

    /**
     * code : 200
     * message : SUCCESS
     * data : [{"id":17,"friendId":6,"remarkName":"薇恩","userName":"薇恩","age":0,"sex":0,"desc":null}]
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
         * id : 17
         * friendId : 6
         * remarkName : 薇恩
         * userName : 薇恩
         * age : 0
         * sex : 0
         * desc : null
         */

        private int id;
        private int friendId;
        private String remarkName;
        private String userName;
        private int age;
        private int sex;
        private Object desc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFriendId() {
            return friendId;
        }

        public void setFriendId(int friendId) {
            this.friendId = friendId;
        }

        public String getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(String remarkName) {
            this.remarkName = remarkName;
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

        public Object getDesc() {
            return desc;
        }

        public void setDesc(Object desc) {
            this.desc = desc;
        }
    }
}
