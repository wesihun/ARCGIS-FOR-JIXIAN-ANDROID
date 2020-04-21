package com.wise.develop.WiseChat.http;


import com.wise.develop.WiseChat.base.BaseResponse;
import com.wise.develop.WiseChat.bean.ApplyCountBean;
import com.wise.develop.WiseChat.bean.FriendApplyListBean;
import com.wise.develop.WiseChat.bean.FriendListBean;
import com.wise.develop.WiseChat.bean.MessageListBean;
import com.wise.develop.WiseChat.bean.RecentContactListBean;
import com.wise.develop.WiseChat.bean.UserInfoBean;
import com.wise.develop.WiseChat.bean.UserListBean;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class HttpAction {

    public static HttpAction getInstance() {
        return HttpActionHolder.instance;
    }

    private static class HttpActionHolder {
        private static HttpAction instance = new HttpAction();
    }

    private <T> Flowable<T> applySchedulers(Flowable<T> responseFlowable) {
        return responseFlowable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<UserInfoBean> login(Map<String, Object> map) {
        return applySchedulers(HttpClient.getHttpService().login(map));
    }

    public Flowable<UserInfoBean> register(Map<String, Object> map) {
        return applySchedulers(HttpClient.getHttpService().register(map));
    }

    public Flowable<ApplyCountBean> getFriendApplyCount() {
        return applySchedulers(HttpClient.getHttpService().getFriendApplyCount());
    }

    public Flowable<FriendApplyListBean> getFriendApplyList() {
        return applySchedulers(HttpClient.getHttpService().getFriendApplyList());
    }

    public Flowable<UserListBean> searchUser(Map<String, String> map) {
        return applySchedulers(HttpClient.getHttpService().searchUser(map));
    }

    public Flowable<BaseResponse> applyFriend(Map<String, Object> map) {
        return applySchedulers(HttpClient.getHttpService().applyFriend(map));
    }

    public Flowable<BaseResponse> agreeFriend(Map<String, Object> map) {
        return applySchedulers(HttpClient.getHttpService().agreeFriend(map));
    }

    public Flowable<FriendListBean> getFriendList() {
        return applySchedulers(HttpClient.getHttpService().getFriendList());
    }

    public Flowable<BaseResponse> sendMessage(Map<String, Object> map) {
        return applySchedulers(HttpClient.getHttpService().sendMessage(map));
    }

    public Flowable<MessageListBean> getMessageHistory(Map<String, Object> map) {
        return applySchedulers(HttpClient.getHttpService().getMessageHistory(map));
    }

    public Flowable<RecentContactListBean> getRecentContactList() {
        return applySchedulers(HttpClient.getHttpService().getRecentContactList());
    }

    public Flowable<UserInfoBean> getUserInfo() {
        return applySchedulers(HttpClient.getHttpService().getUserInfo());
    }

    public Flowable<BaseResponse> clearUnReadMsgCount(Map<String, Object> map) {
        return applySchedulers(HttpClient.getHttpService().clearUnReadMsgCount(map));
    }
}
