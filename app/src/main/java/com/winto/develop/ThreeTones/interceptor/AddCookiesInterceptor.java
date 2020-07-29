package com.winto.develop.ThreeTones.interceptor;

import com.winto.develop.ThreeTones.MainApplication;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    /*Context context;

    public AddCookiesInterceptor(Context context) {
        this.context = context;
    }*/

    @Override
    public Response intercept(Chain chain) throws IOException {
        /*设置cookie*/
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet<String>) MainApplication.getContext().getSession();
        if (preferences != null) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
                builder.addHeader("Content-Type", "text/html; charset=UTF-8");
            }
        }
        chain.proceed(chain.request());
        return chain.proceed(builder.build());
    }
}
