package com.wise.develop.WiseChat.bean;

import com.wise.develop.WiseChat.base.BaseResponse;

public class UserInfoBean extends BaseResponse {

    private static final long serialVersionUID = 2215720662752575817L;
    /**
     * data : {"userId":0,"userName":"zyp","nickname":"zyp","userToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjYyOTczMyIsImV4cCI6MTU3ODczODc1OSwidXNlcm5hbWUiOiJ6eXAifQ.KDWtOE9lKrEJ0dxuNbAZbnoNNx0ud2ArUxpboZyqUlk","password":"629733","age":0,"sex":null,"desc":null}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 0
         * userName : zyp
         * nickname : zyp
         * userToken : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjYyOTczMyIsImV4cCI6MTU3ODczODc1OSwidXNlcm5hbWUiOiJ6eXAifQ.KDWtOE9lKrEJ0dxuNbAZbnoNNx0ud2ArUxpboZyqUlk
         * password : 629733
         * age : 0
         * sex : null
         * desc : null
         */

        private int id;
        private String userName;
        private String nickname;
        private String userToken;
        private String password;
        private int age;
        private int sex;
        private Object desc;

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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public Object getDesc() {
            return desc;
        }

        public void setDesc(Object desc) {
            this.desc = desc;
        }
    }
}
