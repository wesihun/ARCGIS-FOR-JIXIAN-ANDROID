package com.wise.develop.WiseChat.listener;

import java.io.File;

/**
 * 创建时间：2018/3/7
 * 编写人：czw
 * 功能描述 ：
 */

public interface DownloadCallBack {

    void onProgress(int progress);

    void onCompleted(File file);

    void onError(String msg);

}
