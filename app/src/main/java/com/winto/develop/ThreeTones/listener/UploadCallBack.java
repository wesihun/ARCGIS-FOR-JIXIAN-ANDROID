package com.winto.develop.ThreeTones.listener;

public interface UploadCallBack {

    void onProgress(int progress);

    void onCompleted(String imageUrl);

    void onError(String msg);

}
