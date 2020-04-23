package com.wise.develop.WiseChat.bean;

import com.wise.develop.WiseChat.base.BaseResponse;

import java.io.Serializable;

public class UserInfoBean extends BaseResponse {
    /**
     * data : {"id":11,"userName":"李逍遥","nickname":"54Be62g2e3","userToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODc3MDU4MTAsInVzZXJJZCI6MTF9.4Ue5l3VFirATDSdxgVW1EjNcjTUBryDZiZUncAW0dHU","password":"1234","age":0,"sex":0,"desc":null,"userHeader":null,"userLocation":null}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 11
         * userName : 李逍遥
         * nickname : 54Be62g2e3
         * userToken : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODc3MDU4MTAsInVzZXJJZCI6MTF9.4Ue5l3VFirATDSdxgVW1EjNcjTUBryDZiZUncAW0dHU
         * password : 1234
         * age : 0
         * sex : 0
         * desc : null
         * userHeader : null
         * userLocation : null
         */

        private int id;
        private String userName;
        private String nickName;
        private String userToken;
        private String password;
        private int age;
        private int sex;
        private String desc;
        private String userHeader;
        private String userLocation;
        private String birth;

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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public String getUserHeader() {
            return userHeader;
        }

        public void setUserHeader(String userHeader) {
            this.userHeader = userHeader;
        }

        public String getUserLocation() {
            return userLocation;
        }

        public void setUserLocation(String userLocation) {
            this.userLocation = userLocation;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }
    }
}
