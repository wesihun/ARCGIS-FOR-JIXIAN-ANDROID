package com.winto.develop.ThreeTones.bean;

import com.winto.develop.ThreeTones.base.BaseResponse;

public class UserInfoListBean extends BaseResponse {

    /**
     * userid : 1
     * role : {"roleid":2,"rolename":"经理","detail":"经理1","createtime":null}
     * privilege : {"privilegeid":2,"privilegecode":"101","privilegename":"普通用户","createtime":null}
     * department : {"departmentid":1,"departmentname":"生产","parentid":0,"createtime":null,"subDepartment":null}
     * post : {"postid":1,"postname":"工程师","postdetail":"程序员","createtime":null}
     * username : tom
     * password : 123
     * realname : 小罗伯特·唐尼
     * gender : 男
     * telephone : 18800000000
     * createtime : 2020-04-09 00:00:00
     * state : 1
     */

    private int userid;
    private RoleBean role;
    private PrivilegeBean privilege;
    private DepartmentBean department;
    private PostBean post;
    private String username;
    private String password;
    private String realname;
    private String gender;
    private String telephone;
    private String createtime;
    private int state;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public RoleBean getRole() {
        return role;
    }

    public void setRole(RoleBean role) {
        this.role = role;
    }

    public PrivilegeBean getPrivilege() {
        return privilege;
    }

    public void setPrivilege(PrivilegeBean privilege) {
        this.privilege = privilege;
    }

    public DepartmentBean getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentBean department) {
        this.department = department;
    }

    public PostBean getPost() {
        return post;
    }

    public void setPost(PostBean post) {
        this.post = post;
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

    public static class RoleBean extends BaseResponse{
        /**
         * roleid : 2
         * rolename : 经理
         * detail : 经理1
         * createtime : null
         */

        private int roleid;
        private String rolename;
        private String detail;
        private Object createtime;

        public int getRoleid() {
            return roleid;
        }

        public void setRoleid(int roleid) {
            this.roleid = roleid;
        }

        public String getRolename() {
            return rolename;
        }

        public void setRolename(String rolename) {
            this.rolename = rolename;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }
    }

    public static class PrivilegeBean extends BaseResponse{
        /**
         * privilegeid : 2
         * privilegecode : 101
         * privilegename : 普通用户
         * createtime : null
         */

        private int privilegeid;
        private String privilegecode;
        private String privilegename;
        private Object createtime;

        public int getPrivilegeid() {
            return privilegeid;
        }

        public void setPrivilegeid(int privilegeid) {
            this.privilegeid = privilegeid;
        }

        public String getPrivilegecode() {
            return privilegecode;
        }

        public void setPrivilegecode(String privilegecode) {
            this.privilegecode = privilegecode;
        }

        public String getPrivilegename() {
            return privilegename;
        }

        public void setPrivilegename(String privilegename) {
            this.privilegename = privilegename;
        }

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }
    }

    public static class DepartmentBean extends BaseResponse{
        /**
         * departmentid : 1
         * departmentname : 生产
         * parentid : 0
         * createtime : null
         * subDepartment : null
         */

        private int departmentid;
        private String departmentname;
        private int parentid;
        private Object createtime;
        private Object subDepartment;

        public int getDepartmentid() {
            return departmentid;
        }

        public void setDepartmentid(int departmentid) {
            this.departmentid = departmentid;
        }

        public String getDepartmentname() {
            return departmentname;
        }

        public void setDepartmentname(String departmentname) {
            this.departmentname = departmentname;
        }

        public int getParentid() {
            return parentid;
        }

        public void setParentid(int parentid) {
            this.parentid = parentid;
        }

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }

        public Object getSubDepartment() {
            return subDepartment;
        }

        public void setSubDepartment(Object subDepartment) {
            this.subDepartment = subDepartment;
        }
    }

    public static class PostBean extends BaseResponse{
        /**
         * postid : 1
         * postname : 工程师
         * postdetail : 程序员
         * createtime : null
         */

        private int postid;
        private String postname;
        private String postdetail;
        private Object createtime;

        public int getPostid() {
            return postid;
        }

        public void setPostid(int postid) {
            this.postid = postid;
        }

        public String getPostname() {
            return postname;
        }

        public void setPostname(String postname) {
            this.postname = postname;
        }

        public String getPostdetail() {
            return postdetail;
        }

        public void setPostdetail(String postdetail) {
            this.postdetail = postdetail;
        }

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }
    }
}
