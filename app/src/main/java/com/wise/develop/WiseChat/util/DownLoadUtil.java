package com.wise.develop.WiseChat.util;

import android.content.Context;
import android.os.Environment;

import com.wise.develop.WiseChat.listener.DownloadCallBack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zyp on 2019/8/14.
 *
 */
public class DownLoadUtil {
    private Context context;
    private DownloadCallBack downloadCallBack;
    private String filePath = Environment.getExternalStorageDirectory() + "/download_LLL/";
    private String urlPath = "";
    private String fileName = "";


    public DownLoadUtil() {
    }

    public void upDataFile(Context context, String urlPath, DownloadCallBack downloadCallBack) {
        this.downloadCallBack = downloadCallBack;
        this.urlPath = urlPath;
        this.fileName = urlPath.substring(urlPath.lastIndexOf("/") + 1);
        this.context = context;
        new Thread(new Runnable() {
            @Override
            public void run() {
                upData();
            }
        }).start();
    }


    private void upData() {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            HttpURLConnection connection = getHttpURLConnection(urlPath);
            // 获取文件大小
            int length = connection.getContentLength();
            // 创建输入流
            is = connection.getInputStream();
            File file = new File(filePath);
            // 判断文件目录是否存在
            if (!file.exists()) {
                file.mkdirs();
            }
            File apkFile = new File(file, fileName);
            if (apkFile.exists()){
                apkFile.delete();
            }
            fos = new FileOutputStream(apkFile);
            //读取长度
            int len = 0;
            // 下载长度大小
            int count = 0;
            // 缓存
            byte buf[] = new byte[1024 * 2];
            while ((len = is.read(buf)) != -1) {
                count += len;
                int progress = (int) (((float) count / length) * 100);
                downloadCallBack.onProgress(progress);
                fos.write(buf, 0, len);
            }

            fos.close();
            is.close();

            installApk();

        } catch (IOException e) {
            e.printStackTrace();
            downloadCallBack.onError("链接有问题");
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }

            } catch (IOException ignored) {
            }

            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }

        }
    }

    /**
     * 对 httpURLConnection 进行配置
     *
     * @param fileUrl
     * @return
     * @throws IOException
     */
    private HttpURLConnection getHttpURLConnection(String fileUrl) throws IOException {
        int ConnectTimeout = 60 * 60 * 3; //连接时间
        int ReadTimeout = 60 * 60 * 3;    //读取时间
        URL url;
        HttpURLConnection httpURLConnection;
        url = new URL(fileUrl);
        //返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
        httpURLConnection = (HttpURLConnection) url.openConnection();
        // 默认为GET
        httpURLConnection.setRequestMethod("GET");
        //不使用缓存
        httpURLConnection.setUseCaches(false);
        //设置超时时间
        httpURLConnection.setConnectTimeout(ConnectTimeout);
        //设置读取超时时间
        httpURLConnection.setReadTimeout(ReadTimeout);
        //设置是否从httpUrlConnection读入，默认情况下是true;
        httpURLConnection.setDoInput(true);
        //很多项目需要传入cookie解开注释（自行修改）
        // connection.setRequestProperty("Cookie", "my_cookie");
        //相应码是否为200
        httpURLConnection.connect();
        return httpURLConnection;
    }


    private void installApk() {
        File apkfile = new File(filePath, fileName);
        if (!apkfile.exists()) {
            return;
        }
        downloadCallBack.onCompleted(apkfile);
    }


}
