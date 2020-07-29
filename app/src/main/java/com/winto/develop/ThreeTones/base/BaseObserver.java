package com.winto.develop.ThreeTones.base;


import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;

public abstract class BaseObserver<T> implements FlowableSubscriber<T> {

    @Override
    public void onNext(T t) {
        try {
            onSuccess(t);
        } catch (Exception e) {
            onError("网络请求失败");
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable t) {
        try {
            t.printStackTrace();
            //可自定义错误类型
            onError("网络请求失败");
        } catch (Exception e) {
            e.printStackTrace();
            onError("网络请求失败");
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Integer.MAX_VALUE);
    }

    public abstract void onSuccess(T bean);

    public abstract void onError(String message);
}
