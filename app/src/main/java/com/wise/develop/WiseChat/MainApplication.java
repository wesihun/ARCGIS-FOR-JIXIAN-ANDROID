package com.wise.develop.WiseChat;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.wise.develop.WiseChat.bean.UserInfoBean;

import androidx.annotation.NonNull;

public class MainApplication extends Application {
    private static MainApplication application;
    private SharedPreferences preferences = null;

    public final static String TOKEN = "Token";
    public final static String USER_ID = "userId";
    private UserInfoBean.DataBean userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        preferences = getSharedPreferences("wiseChat", MODE_PRIVATE);
    }

    public String getToken() {
        return preferences.getString(TOKEN, "");
    }

    public void setToken(@NonNull String token) {
        preferences.edit().putString(TOKEN, token).apply();
    }

    public int getUId() {
        return preferences.getInt(USER_ID, 0);
    }

    public void setUId(int userId) {
        preferences.edit().putInt(USER_ID, userId).apply();
    }

    public static MainApplication getContext() {
        return application;
    }

    public UserInfoBean.DataBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean.DataBean userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
