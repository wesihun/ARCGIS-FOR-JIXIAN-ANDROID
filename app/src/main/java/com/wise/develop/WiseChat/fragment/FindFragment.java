package com.wise.develop.WiseChat.fragment;

import android.app.Activity;
import android.view.View;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.base.BaseFragment;
import com.wise.develop.WiseChat.util.GlideEngine;

public class FindFragment extends BaseFragment {

    @Override
    protected void initView() {
        findViewById(R.id.btn_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PictureSelector.create((Activity) context).openGallery(PictureMimeType.ofImage())
                        .loadImageEngine(GlideEngine.createGlideEngine())
                        .imageSpanCount(4)
                        .selectionMode(PictureConfig.SINGLE)
                        .previewImage(true)
                        .isCamera(true)
                        .openClickSound(false)
                        .forResult(PictureConfig.CHOOSE_REQUEST_IMAGE);
            }
        });

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
