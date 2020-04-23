package com.wise.develop.WiseChat.bean;

import com.wise.develop.WiseChat.base.BaseResponse;

import java.util.List;

public class RecentContactListBean extends BaseResponse {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * friendId : 12
         * remarkName : 景天
         * userName : null
         * nickname : 499[28Y12l
         * userHeader : null
         * lastMessage : 急急急
         * sendTime : 2020-04-21 16:34:06
         * unReadCount : 0
         */

        private int friendId;
        private String remarkName;
        private String userName;
        private String nickname;
        private String userHeader;
        private String lastMessage;
        private String sendTime;
        private int unReadCount;

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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUserHeader() {
            return userHeader;
        }

        public void setUserHeader(String userHeader) {
            this.userHeader = userHeader;
        }

        public String getLastMessage() {
            return lastMessage;
        }

        public void setLastMessage(String lastMessage) {
            this.lastMessage = lastMessage;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public int getUnReadCount() {
            return unReadCount;
        }

        public void setUnReadCount(int unReadCount) {
            this.unReadCount = unReadCount;
        }
    }
}
