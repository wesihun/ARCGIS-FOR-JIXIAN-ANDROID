package com.wise.develop.WiseChat.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wise.develop.WiseChat.MainApplication;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.activity.UserEditActivity;
import com.wise.develop.WiseChat.base.BaseFragment;
import com.wise.develop.WiseChat.base.BaseObserver;
import com.wise.develop.WiseChat.bean.UserInfoBean;
import com.wise.develop.WiseChat.http.HttpAction;
import com.wise.develop.WiseChat.util.GlideUtil;
import com.wise.develop.WiseChat.util.ToastUtil;

public class MyFragment extends BaseFragment {

    private TextView tv_user_name;
    private TextView tv_desc;
    private ImageView iv_header;
    private UserInfoBean.DataBean user;

    @Override
    protected void initView() {
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_desc = findViewById(R.id.tv_desc);
        iv_header = findViewById(R.id.iv_header);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {
        tv_user_name.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("userInfo", user);
            toClass(context, UserEditActivity.class, bundle);
        });

        iv_header.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("userInfo", user);
            toClass(context, UserEditActivity.class, bundle);
        });
    }

    private void getUserInfo() {
        HttpAction.getInstance().getUserInfo().subscribe(new BaseObserver<UserInfoBean>() {
            @Override
            public void onSuccess(UserInfoBean bean) {
                user = bean.getData();
                MainApplication.getContext().setToken(user.getUserToken());
                MainApplication.getContext().setUId(user.getId());
                setPage();
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private void setPage() {
        GlideUtil.displayRoundImage(context, user.getUserHeader(), iv_header, 5);
        tv_user_name.setText(user.getUserName());
        tv_desc.setText(user.getDesc());
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserInfo();
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }
}
