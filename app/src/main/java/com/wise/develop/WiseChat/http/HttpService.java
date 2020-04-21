package com.wise.develop.WiseChat.http;

import com.wise.develop.WiseChat.base.BaseResponse;
import com.wise.develop.WiseChat.bean.ApplyCountBean;
import com.wise.develop.WiseChat.bean.FriendApplyListBean;
import com.wise.develop.WiseChat.bean.FriendListBean;
import com.wise.develop.WiseChat.bean.MessageListBean;
import com.wise.develop.WiseChat.bean.RecentContactListBean;
import com.wise.develop.WiseChat.bean.UserInfoBean;
import com.wise.develop.WiseChat.bean.UserListBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface HttpService {
    @Streaming
    @GET
    Flowable<ResponseBody> downloadFile(@Url String fileUrl);

    @Multipart
    @POST("File/UploadSampleImage")
    Flowable<ResponseBody> uploadFiles(@Part MultipartBody.Part file);

    @Multipart
    @POST()
    Flowable<ResponseBody> uploadFiles(@Url String url, @Part MultipartBody.Part file);

    @Multipart
    @POST()
    Flowable<ResponseBody> UploadCompleteImage(@Url String url, @Part MultipartBody.Part file);

    @Multipart
    @POST("File/UploadSampleImage")
    Flowable<ResponseBody> uploadFiles(@Part List<MultipartBody.Part> parts);

    @POST("user/login")
    Flowable<UserInfoBean> login(@Body Map<String, Object> params);

    @POST("user/register")
    Flowable<UserInfoBean> register(@Body Map<String, Object> params);

    @POST("friend/getFriendApplyList")
    Flowable<FriendApplyListBean> getFriendApplyList();

    @POST("friend/getFriendApplyCount")
    Flowable<ApplyCountBean> getFriendApplyCount();

    @POST("user/searchUser")
    Flowable<UserListBean> searchUser(@Body Map<String, String> params);

    @POST("friend/applyFriend")
    Flowable<BaseResponse> applyFriend(@Body Map<String, Object> params);

    @POST("friend/agreeFriend")
    Flowable<BaseResponse> agreeFriend(@Body Map<String, Object> params);

    @POST("friend/getFriendList")
    Flowable<FriendListBean> getFriendList();

    @POST("message/sendMessage")
    Flowable<BaseResponse> sendMessage(@Body Map<String, Object> params);

    @POST("message/getMessageHistory")
    Flowable<MessageListBean> getMessageHistory(@Body Map<String, Object> params);

    @POST("friend/getRecentContactList")
    Flowable<RecentContactListBean> getRecentContactList();

    @POST("user/getUserInfo")
    Flowable<UserInfoBean> getUserInfo();

    @POST("message/clearUnReadMsgCount")
    Flowable<BaseResponse> clearUnReadMsgCount(@Body Map<String, Object> map);
}
