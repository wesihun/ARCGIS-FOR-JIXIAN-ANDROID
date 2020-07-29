package com.winto.develop.ThreeTones.bean;


import com.winto.develop.ThreeTones.base.BaseResponse;

public class BaseResponseBean extends BaseResponse {
    /**
     * count : 0
     * code : 0
     * msg : 查询成功
     */

    private int count;
    private int code;
    private String msg;

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
}
