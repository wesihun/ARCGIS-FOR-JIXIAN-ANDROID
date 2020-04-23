package com.wise.develop.WiseChat.util;

import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import com.wise.develop.WiseChat.base.BaseObserver;
import com.wise.develop.WiseChat.bean.QiNiuTokenBean;
import com.wise.develop.WiseChat.http.HttpAction;
import com.wise.develop.WiseChat.listener.UploadCallBack;

public class QiNiuOss {

    private static QiNiuOss qiNiuOss;
    private String bucket = "http://q9465dta7.bkt.clouddn.com/";
    private UploadManager uploadManager;

    public static QiNiuOss getInstance() {
        if (qiNiuOss == null) {
            qiNiuOss = new QiNiuOss();
        }
        return qiNiuOss;
    }

    public void uploadFile(String name, String filePath, UploadCallBack callBack) {
        getUploadToken(name, filePath, callBack);
    }

    private void getUploadToken(String name, String filePath, UploadCallBack callBack) {
        HttpAction.getInstance().getUploadToken().subscribe(new BaseObserver<QiNiuTokenBean>() {
            @Override
            public void onSuccess(QiNiuTokenBean bean) {
                initUploadManager();
                upload(name, filePath, bean.getData().getToken(), callBack);
            }

            @Override
            public void onError(String message) {
                callBack.onError(message);
            }
        });
    }

    private void initUploadManager() {
        Configuration config = new Configuration.Builder()
                .connectTimeout(10)
                .responseTimeout(60)
                .build();
        uploadManager = new UploadManager(config, 3);
    }

    private void upload(String name, String filePath, String token, UploadCallBack callBack) {
        uploadManager.put(filePath, name, token, (key, info, res) -> {
            if (info.isOK()) {
                String finalUrl = String.format("%s/%s", bucket, key);
                callBack.onCompleted(finalUrl);
            } else {
                callBack.onError(info.toString());
            }
        }, null);
    }
}
