package com.wise.develop.WiseChat.listener;

import java.io.File;

public interface UploadCallBack {

    void onProgress(int progress);

    void onCompleted(File file);

    void onError(String msg);

}
