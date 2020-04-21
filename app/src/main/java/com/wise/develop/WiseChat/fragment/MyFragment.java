package com.wise.develop.WiseChat.fragment;

import android.widget.TextView;

import com.wise.develop.WiseChat.MainApplication;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.base.BaseFragment;

public class MyFragment extends BaseFragment {

    private TextView tv_user_name;

    @Override
    protected void initView() {
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_user_name.setText(MainApplication.getContext().getUserInfo().getUserName());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }
}
