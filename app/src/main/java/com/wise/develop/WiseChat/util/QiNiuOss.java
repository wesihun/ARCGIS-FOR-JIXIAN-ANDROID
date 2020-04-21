package com.wise.develop.WiseChat.util;

import android.util.Log;

import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.wise.develop.WiseChat.base.BaseObserver;
import com.wise.develop.WiseChat.base.BaseResponse;
import com.wise.develop.WiseChat.bean.QiNiuTokenBean;
import com.wise.develop.WiseChat.http.HttpAction;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class QiNiuOss {

    private static QiNiuOss qiNiuOss;

    private UploadManager uploadManager;

    public static QiNiuOss getInstance() {
        if (qiNiuOss == null) {
            qiNiuOss = new QiNiuOss();
        }
        return qiNiuOss;
    }

    public void uploadFile(String name, String filePath) {
        getUploadToken(name, filePath);
    }

    private void getUploadToken(String name, String filePath) {
        HttpAction.getInstance().getUploadToken().subscribe(new BaseObserver<QiNiuTokenBean>() {
            @Override
            public void onSuccess(QiNiuTokenBean bean) {
                initUploadManager();
                upload(name, filePath, bean.getData().getToken());
            }

            @Override
            public void onError(String message) {
                Log.e("TAG", "onError: " + message);
            }
        });
    }

    private void initUploadManager() {
        Configuration config = new Configuration.Builder()
                .connectTimeout(10)           // 链接超时。默认10秒
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                .build();
        uploadManager = new UploadManager(config, 3);
    }

    private void upload(String name, String filePath, String token) {

        uploadManager.put(filePath, name, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                //res包含hash、key等信息，具体字段取决于上传策略的设置
                if (info.isOK()) {
                    Log.e("qiniu", "Upload Success");
                } else {
                    Log.e("qiniu", "Upload Fail");
                    //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                }
                Log.e("qiniu=====", key + ",\r\n " + info + ",\r\n " + res);
            }
        }, new UploadOptions(null, null, false, new UpProgressHandler() {
            @Override
            public void progress(String key, double percent) {
                Log.e("qiniu", key + ": " + percent);
            }
        }, null));
    }
}
