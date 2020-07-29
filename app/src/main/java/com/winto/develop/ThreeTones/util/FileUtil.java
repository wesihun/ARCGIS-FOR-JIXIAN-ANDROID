package com.winto.develop.ThreeTones.util;

import com.winto.develop.ThreeTones.MainApplication;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    /**
     * @param saveDir
     * @return
     * @throws IOException 判断下载目录是否存在
     */
    public static String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(MainApplication.getContext().getExternalFilesDir(null), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        return downloadFile.getAbsolutePath();
    }
}
