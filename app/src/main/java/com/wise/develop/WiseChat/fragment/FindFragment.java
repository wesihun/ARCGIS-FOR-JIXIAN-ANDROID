package com.wise.develop.WiseChat.fragment;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.ToastUtils;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.base.BaseFragment;
import com.wise.develop.WiseChat.http.HttpAction;
import com.wise.develop.WiseChat.listener.UploadCallBack;
import com.wise.develop.WiseChat.util.FileUtil;
import com.wise.develop.WiseChat.util.GlideEngine;
import com.wise.develop.WiseChat.util.GlideUtil;
import com.wise.develop.WiseChat.util.QiNiuOss;
import com.wise.develop.WiseChat.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FindFragment extends BaseFragment {

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_find;
    }
}
