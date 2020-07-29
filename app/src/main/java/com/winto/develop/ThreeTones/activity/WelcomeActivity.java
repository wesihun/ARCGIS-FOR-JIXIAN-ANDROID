package com.winto.develop.ThreeTones.activity;

import android.os.Handler;

import com.winto.develop.ThreeTones.MainApplication;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.base.BaseActivity;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:欢迎页面
 */

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void initView() {
        new Handler().postDelayed(this::gotoActivity, 2000);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {

    }

    private void gotoActivity() {
        if (MainApplication.getContext().getSession() == null) {
            toClass(context, LoginActivity.class);
            finish();
        } else {
            toClass(context, MainActivity.class);
            finish();
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }
}