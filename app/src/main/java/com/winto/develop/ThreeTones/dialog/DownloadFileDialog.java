package com.winto.develop.ThreeTones.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.bean.DownloadManageListBean;
import com.winto.develop.ThreeTones.constant.ConstantData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by zyp on 2018/3/12.c
 * note:文件下载dialog
 */
public class DownloadFileDialog extends Dialog {

    private ProgressBar pb_progress;

    public DownloadFileDialog(Context context) {
        super(context);
        initDialog();
    }

    private void initDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_download_file, null);
        pb_progress = mView.findViewById(R.id.pb_progress);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(mView);
    }

    public void downloadFile(DownloadManageListBean.DataBean bean, final OnDownloadListener listener) {
        Request request = new Request.Builder().url(ConstantData.HOST4 + "/resource/" + bean.getUrl()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 下载失败
                listener.onDownloadFailed();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len;
                FileOutputStream fos = null;
                // 储存下载文件的目录
//                String savePath = FileUtil.isExistDir("/download");
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
                    Log.e("TAG", "onResponse: "+file.getAbsolutePath());
                    fos = new FileOutputStream(file + "/" + bean.getResourcename());
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        listener.onDownloading(progress);
                        pb_progress.setProgress(progress);
                    }
                    fos.flush();
                    // 下载完成
                    listener.onDownloadSuccess();
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onDownloadFailed();
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
    }

    public interface OnDownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess();

        /**
         * @param progress 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
    }
}