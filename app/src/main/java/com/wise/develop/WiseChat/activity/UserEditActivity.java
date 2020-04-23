package com.wise.develop.WiseChat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.base.BaseActivity;
import com.wise.develop.WiseChat.base.BaseObserver;
import com.wise.develop.WiseChat.base.BaseResponse;
import com.wise.develop.WiseChat.bean.UserInfoBean;
import com.wise.develop.WiseChat.dialog.ChooseDateDialog;
import com.wise.develop.WiseChat.dialog.ChooseSexDialog;
import com.wise.develop.WiseChat.http.HttpAction;
import com.wise.develop.WiseChat.listener.UploadCallBack;
import com.wise.develop.WiseChat.util.FileUtil;
import com.wise.develop.WiseChat.util.GlideEngine;
import com.wise.develop.WiseChat.util.GlideUtil;
import com.wise.develop.WiseChat.util.QiNiuOss;
import com.wise.develop.WiseChat.util.ToastUtil;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class UserEditActivity extends BaseActivity {

    private ImageView iv_back;
    private ImageView iv_header;
    private EditText et_nike_name;
    private TextView tv_sex;
    private TextView tv_location;
    private TextView tv_birth;
    private EditText et_desc;
    private Button btn_save;

    private UserInfoBean.DataBean user;
    private String headerUrl;

    private ChooseSexDialog sexDialog;
    private ChooseDateDialog dateDialog;

    @Override
    protected void initIntentData() {
        user = (UserInfoBean.DataBean) getIntent().getSerializableExtra("userInfo");
    }

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        iv_header = findViewById(R.id.iv_header);
        et_nike_name = findViewById(R.id.et_nike_name);
        tv_sex = findViewById(R.id.tv_sex);
        tv_location = findViewById(R.id.tv_location);
        tv_birth = findViewById(R.id.tv_birth);
        et_desc = findViewById(R.id.et_desc);
        btn_save = findViewById(R.id.btn_save);
        setUserInfo();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });

        tv_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexDialog();
            }
        });

        tv_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBirthDialog();
            }
        });

        tv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseLocation();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
            }
        });
    }

    private void saveUserInfo() {
        if (TextUtils.isEmpty(headerUrl)) {
            ToastUtil.show(context, "请选择头像");
            return;
        }

        String nickName = et_nike_name.getText().toString().trim();
        if (TextUtils.isEmpty(nickName)) {
            ToastUtil.show(context, "请填写昵称");
            return;
        }

        String birth = tv_birth.getText().toString().trim();
        if (TextUtils.isEmpty(birth)) {
            ToastUtil.show(context, "请选择出生日期");
            return;
        }

        String userLocation = tv_location.getText().toString().trim();
        if (TextUtils.isEmpty(userLocation)) {
            ToastUtil.show(context, "请选择地区");
            return;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("userHeader", headerUrl);
        params.put("nickName", nickName);
        params.put("sex", "男".equals(tv_sex.getText().toString()) ? 1 : 0);
        params.put("userLocation", userLocation);
        params.put("birth", birth);
        params.put("desc", et_desc.getText().toString().trim());
        HttpAction.getInstance().updateUser(params).subscribe(new BaseObserver<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse bean) {
                ToastUtil.show(context, "信息保存成功");
                finish();
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private void setUserInfo() {
        if (user == null) {
            return;
        }
        headerUrl = user.getUserHeader();
        GlideUtil.displayRoundImage(context, user.getUserHeader(), iv_header, 10);
        et_nike_name.setText(user.getNickName());
        tv_sex.setText(user.getSex() == 0 ? "女" : "男");
        tv_birth.setText(user.getBirth());
        tv_location.setText(user.getUserLocation());
        et_desc.setText(user.getDesc());
    }

    private void chooseLocation() {
        List<HotCity> hotCities = new ArrayList<>();
        hotCities.add(new HotCity("北京", "北京", ""));
        hotCities.add(new HotCity("上海", "上海", ""));
        hotCities.add(new HotCity("广州", "广东", ""));
        hotCities.add(new HotCity("深圳", "广东", ""));
        hotCities.add(new HotCity("杭州", "浙江", ""));
        hotCities.add(new HotCity("宁波", "浙江", ""));
        hotCities.add(new HotCity("成都", "四川", ""));
        hotCities.add(new HotCity("武汉", "湖北", ""));
        hotCities.add(new HotCity("哈尔滨", "黑龙江", ""));
        hotCities.add(new HotCity("沈阳", "辽宁", ""));
        hotCities.add(new HotCity("大连", "辽宁", ""));

        CityPicker.getInstance()
                .setFragmentManager(getSupportFragmentManager())  //此方法必须调用
                .enableAnimation(true)  //启用动画效果
                .setAnimationStyle(R.style.pop_win_anim_style)  //自定义动画
                .setHotCities(hotCities)  //指定热门城市
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        if (data != null) {
                            tv_location.setText(String.format("%s  %s", data.getProvince(), data.getName()));
                        }
                    }

                    @Override
                    public void onLocate() {
                    }
                })
                .show();
    }

    private void choosePicture() {
        PictureSelector.create(this).openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())
                .imageSpanCount(4)
                .isCamera(true)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .enableCrop(true)
                .withAspectRatio(1, 1)
                .compress(true)
                .compressQuality(55)
                .freeStyleCropEnabled(true)
                .rotateEnabled(false)
                .isDragFrame(true)
                .openClickSound(false)
                .forResult(PictureConfig.CHOOSE_REQUEST_IMAGE);
    }

    private void showSexDialog() {
        if (sexDialog == null) {
            sexDialog = new ChooseSexDialog(context);
        }
        sexDialog.show();
        sexDialog.setOnBtnClickListener(text -> {
            tv_sex.setText(text);
            sexDialog.dismiss();
        });
    }

    private void showBirthDialog() {
        if (dateDialog == null) {
            dateDialog = new ChooseDateDialog(context);
        }
        dateDialog.show();
        dateDialog.setOnBtnClickListener(text -> {
            tv_birth.setText(text);
        });
    }

    private void uploadImage(String name, String path) {
        String uploadName = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date()) + "_" + name;
        QiNiuOss.getInstance().uploadFile(uploadName, path, new UploadCallBack() {
            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onCompleted(String imageUrl) {
                headerUrl = imageUrl;
                GlideUtil.displayImage(context, headerUrl, iv_header);
            }

            @Override
            public void onError(String msg) {
                ToastUtil.show(context, msg);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST_IMAGE) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (selectList == null || selectList.size() == 0) {
                return;
            }
            LocalMedia localMedia = selectList.get(0);
            uploadImage(localMedia.getFileName(), FileUtil.getCompressPath(localMedia));
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_user_edit;
    }
}