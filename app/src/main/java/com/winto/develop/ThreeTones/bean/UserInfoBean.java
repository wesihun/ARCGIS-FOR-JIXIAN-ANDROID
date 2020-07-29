package com.winto.develop.ThreeTones.bean;

import com.winto.develop.ThreeTones.base.BaseResponse;

public class UserInfoBean extends BaseResponse {

    /**
     * count : 0
     * code : 0
     * msg : 查询成功
     * data : {"userid":1,"departmentid":1,"postid":1,"username":"tom","password":"123","realname":"小罗伯特·唐尼","gender":"男","telephone":"18800000000","createtime":"2020-04-09T00:00:00","state":1,"age":30,"iDcard":"230100000000000000","mail":"1234567890@qq.com","xAreaId":"174","cAreaId":"287","postname":null}
     */

    private int count;
    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean extends BaseResponse {
        /**
         * userid : 1
         * departmentid : 1
         * postid : 1
         * username : tom
         * password : 123
         * realname : 小罗伯特·唐尼
         * gender : 男
         * telephone : 18800000000
         * createtime : 2020-04-09T00:00:00
         * state : 1
         * age : 30
         * iDcard : 230100000000000000
         * mail : 1234567890@qq.com
         * xAreaId : 174
         * cAreaId : 287
         * postname : null
         */

        private int userid;
        private int departmentid;
        private int postid;
        private String username;
        private String password;
        private String realname;
        private String gender;
        private String telephone;
        private String createtime;
        private int state;
        private int age;
        private String iDcard;
        private String mail;
        private String xAreaId;
        private String cAreaId;
        private String postname;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getDepartmentid() {
            return departmentid;
        }

        public void setDepartmentid(int departmentid) {
            this.departmentid = departmentid;
        }

        public int getPostid() {
            return postid;
        }

        public void setPostid(int postid) {
            this.postid = postid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getIDcard() {
            return iDcard;
        }

        public void setIDcard(String iDcard) {
            this.iDcard = iDcard;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getXAreaId() {
            return xAreaId;
        }

        public void setXAreaId(String xAreaId) {
            this.xAreaId = xAreaId;
        }

        public String getCAreaId() {
            return cAreaId;
        }

        public void setCAreaId(String cAreaId) {
            this.cAreaId = cAreaId;
        }

        public String getPostname() {
            return postname;
        }

        public void setPostname(String postname) {
            this.postname = postname;
        }
    }
}
