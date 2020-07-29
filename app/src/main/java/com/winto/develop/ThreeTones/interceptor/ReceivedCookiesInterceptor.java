package com.winto.develop.ThreeTones.interceptor;

import com.winto.develop.ThreeTones.MainApplication;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {


   /* Context context;

    public ReceivedCookiesInterceptor(Context context) {
        this.context = context;
    }*/

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (MainApplication.getContext().getSession() == null) {
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                HashSet<String> cookies = new HashSet<>(originalResponse.headers("Set-Cookie"));
                MainApplication.getContext().setSession(cookies);
            }
        }

        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Content-Type", "text/html; charset=UTF-8");
        //return originalResponse;
        return chain.proceed(builder.build());
    }
}