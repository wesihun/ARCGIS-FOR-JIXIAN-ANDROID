package com.winto.develop.ThreeTones.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.winto.develop.ThreeTones.MainApplication;

import java.lang.reflect.Field;

/**
 * 工具类
 * Created by SmileXie on 2017/6/29.
 */

public final class StatusBarHelper {
    private static Integer statusBarHeight = null;
    /**
     * 设置状态栏全透明
     *
     * @param activity 需要设置的activity
     */
    public static void setTransparent(Activity activity) {
        transparentStatusBar(activity);
    }

    /**
     * 使状态栏透明
     */
    private static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个flag contentView才能延伸到状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //状态栏覆盖在contentView上面，设置透明使contentView的背景透出来
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            //让contentView延伸到状态栏并且设置状态栏颜色透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public static int getStatusBarHeight() {
        if (statusBarHeight != null) return statusBarHeight;

        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = MainApplication.getContext().getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
            statusBarHeight = 0;
        }
        return statusBarHeight;
    }
}
