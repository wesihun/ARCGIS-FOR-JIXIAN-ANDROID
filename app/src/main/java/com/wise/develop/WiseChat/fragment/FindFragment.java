package com.wise.develop.WiseChat.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.ToastUtils;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.base.BaseFragment;
import com.wise.develop.WiseChat.http.HttpAction;
import com.wise.develop.WiseChat.util.GlideEngine;
import com.wise.develop.WiseChat.util.GlideUtil;
import com.wise.develop.WiseChat.util.QiNiuOss;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FindFragment extends BaseFragment {

    @Override
    protected void initView() {
        findViewById(R.id.btn_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });

    }

    private void choosePicture() {
        PictureSelector.create(this).openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())
                .imageSpanCount(4)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .isCamera(true)
                .openClickSound(false)
                .forResult(PictureConfig.CHOOSE_REQUEST_IMAGE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {

    }

    private void uploadImage(String name, String path) {
        String uploadName = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date()) + "_" + name;
        QiNiuOss.getInstance().uploadFile(uploadName,path);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST_IMAGE) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (selectList == null || selectList.size() == 0) {
                return;
            }
            LocalMedia localMedia = selectList.get(0);
            uploadImage(localMedia.getFileName(), localMedia.getPath());

//            GlideUtil.displayImage(context, localMedia.getPath(), findViewById(R.id.iv_image));
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_find;
    }
}
