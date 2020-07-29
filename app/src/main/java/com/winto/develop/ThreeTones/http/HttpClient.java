package com.winto.develop.ThreeTones.http;

import android.util.Log;

import com.winto.develop.ThreeTones.constant.ConstantData;
import com.winto.develop.ThreeTones.interceptor.AddCookiesInterceptor;
import com.winto.develop.ThreeTones.interceptor.ReceivedCookiesInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class HttpClient {
    /**
     * 构建OkHttp
     */
    private static final class OKHttpHolder {

        private static final int TIME_OUT = 60;

        private static final OkHttpClient OK_HTTP_CLIENT = createOkHttpClientBuilder().build();

        private static OkHttpClient.Builder createOkHttpClientBuilder() {

            final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            /*builder.addInterceptor(chain -> {
                Request.Builder request = chain.request().newBuilder();
                String token = "";
                if (!TextUtils.isEmpty(token)) {
                    request.addHeader("Authorization", token);
                }
                request.build();

                return chain.proceed(request.build());
            });*/
            //设置连接超时
            builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
            //设置从主机读信息超时
            builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
            //设置写信息超时
            builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
            builder.addInterceptor(new HttpLoggingInterceptor(message -> Log.e("======>", message)).setLevel(HttpLoggingInterceptor.Level.BODY));
            /*builder.cookieJar(new CookieJar() {
                @Override
                public void saveFromResponse(HttpUrl httpUrl, List<Cookie> cookies) {
                    cookieStore.put(httpUrl.host(), cookies);
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                    List<Cookie> cookies = cookieStore.get(httpUrl.host());
                    return cookies != null ? cookies : new ArrayList<>();
                }
            });*/
            builder.addInterceptor(new AddCookiesInterceptor());
            builder.addInterceptor(new ReceivedCookiesInterceptor());
            return builder;
        }

    }

    // 构建全局Retrofit客户端
    private static final class RetrofitHolder {

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(ConstantData.HOST1)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
//                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * HttpService
     */
    private static final class HttpServiceHolder {
        private static final HttpService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(HttpService.class);
    }

    static HttpService getHttpService() {
        return HttpServiceHolder.REST_SERVICE;
    }
}

