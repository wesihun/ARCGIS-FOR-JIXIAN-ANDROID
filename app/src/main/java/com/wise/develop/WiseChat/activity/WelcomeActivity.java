package com.wise.develop.WiseChat.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;

import com.wise.develop.WiseChat.MainApplication;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.base.BaseActivity;
import com.wise.develop.WiseChat.util.ToastUtil;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:欢迎页
 */

public class WelcomeActivity extends BaseActivity {
    private String[] permissionList = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onStart() {
        super.onStart();
        initPermission();
    }

    @Override
    protected void initView() {
        new Handler().postDelayed(this::gotoActivity, 2000);
    }

    @Override
    protected void initData() {

    }

    private void gotoActivity() {
        String token = MainApplication.getContext().getToken();
        if (TextUtils.isEmpty(token)) {
            //未登录 跳转登录页面
            toClass(context, LoginActivity.class);
        } else {
            //已登录 跳转首页
            toClass(context, MainActivity.class);
        }
        finish();
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission(permissionList[0]) ||
                    checkPermission(permissionList[1]) ||
                    checkPermission(permissionList[2]) ||
                    checkPermission(permissionList[3]) ||
                    checkPermission(permissionList[4]) ||
                    checkPermission(permissionList[5])) {
                ActivityCompat.requestPermissions(this, permissionList, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                if (shouldShowRequestPermissionRationale(permissions[0])) {
                    // 循环申请权限
                    initPermission();
                } else {
                    ToastUtil.show(context, "请给与app相应权限，否则将无法正常使用");
                    finish();
                }
            } else {
                new Handler().postDelayed(this::gotoActivity, 2000);
            }
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }
}
