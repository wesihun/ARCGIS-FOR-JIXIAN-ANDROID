package com.wise.develop.WiseChat.bean;

import com.wise.develop.WiseChat.base.BaseResponse;

import java.util.List;

public class MessageListBean extends BaseResponse {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * messageStatus : 0
         * time : 2020-04-17 10:00:53
         * userName : 邢育森
         * content : 你好吗
         */

        private int messageStatus;
        private int sendOrReceive;
        private String time;
        private String userName;
        private String userHeader;
        private String content;

        public int getMessageStatus() {
            return messageStatus;
        }

        public void setMessageStatus(int messageStatus) {
            this.messageStatus = messageStatus;
        }

        public int getSendOrReceive() {
            return sendOrReceive;
        }

        public void setSendOrReceive(int sendOrReceive) {
            this.sendOrReceive = sendOrReceive;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserHeader() {
            return userHeader;
        }

        public void setUserHeader(String userHeader) {
            this.userHeader = userHeader;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
