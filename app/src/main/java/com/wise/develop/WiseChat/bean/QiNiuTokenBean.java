package com.wise.develop.WiseChat.bean;

import com.wise.develop.WiseChat.base.BaseResponse;

public class QiNiuTokenBean extends BaseResponse {


    /**
     * data : {"token":"lJQ2Xr5Ja3BwfqrAs0lRx8taPsFKp91L-Rgv1wUW:9eHeUmJcQofsiCngICHp9rMAr6o=:eyJzY29wZSI6Indpc2UtY2hhdCIsImRlYWRsaW5lIjoxNTg3NDYzMTE0fQ=="}
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
         * token : lJQ2Xr5Ja3BwfqrAs0lRx8taPsFKp91L-Rgv1wUW:9eHeUmJcQofsiCngICHp9rMAr6o=:eyJzY29wZSI6Indpc2UtY2hhdCIsImRlYWRsaW5lIjoxNTg3NDYzMTE0fQ==
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
