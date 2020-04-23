package com.wise.develop.WiseChat.bean;

import com.wise.develop.WiseChat.base.BaseResponse;

public class MessageInfoBean extends BaseResponse {

    /**
     * friendId : 14
     * content : 士大夫似的
     * sendTime : 2020-04-20 15:04:37
     */

    private int friendId;
    private String content;
    private String sendTime;
    private String friendName;
    private String friendHeader;

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendHeader() {
        return friendHeader;
    }

    public void setFriendHeader(String friendHeader) {
        this.friendHeader = friendHeader;
    }
}
