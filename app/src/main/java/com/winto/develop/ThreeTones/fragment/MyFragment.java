package com.winto.develop.ThreeTones.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winto.develop.ThreeTones.MainApplication;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.activity.AuditManageActivity;
import com.winto.develop.ThreeTones.activity.BrowseCensusActivity;
import com.winto.develop.ThreeTones.activity.DownloadManageActivity;
import com.winto.develop.ThreeTones.activity.LoginActivity;
import com.winto.develop.ThreeTones.activity.ModifyInfoActivity;
import com.winto.develop.ThreeTones.base.BaseFragment;
import com.winto.develop.ThreeTones.util.StatusBarHelper;

public class MyFragment extends BaseFragment {

    private ImageView iv_header;
    private TextView tv_name;
    private TextView tv_bjzl;
    private TextView tv_lltj;
    private TextView tv_xzgl;
    private TextView tv_hsgl;

    @Override
    protected void initView() {
        iv_header = findViewById(R.id.iv_header);
        tv_name = findViewById(R.id.tv_name);
        tv_bjzl = findViewById(R.id.tv_bjzl);
        tv_lltj = findViewById(R.id.tv_lltj);
        tv_xzgl = findViewById(R.id.tv_xzgl);
        tv_hsgl = findViewById(R.id.tv_hsgl);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv_header.getLayoutParams();
        params.topMargin = StatusBarHelper.getStatusBarHeight() + 50;
        iv_header.setLayoutParams(params);

        if (!TextUtils.isEmpty(MainApplication.getContext().getUserName())) {
            tv_name.setText(MainApplication.getContext().getUserName());
        }
        if ("管理员".equals(MainApplication.getContext().getRoleName())) {
            tv_hsgl.setVisibility(View.VISIBLE);
        } else {
            tv_hsgl.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(MainApplication.getContext().getUserName())) {
            tv_name.setText(MainApplication.getContext().getUserName());
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {
        tv_bjzl.setOnClickListener(v -> toClass(context, ModifyInfoActivity.class));

        tv_lltj.setOnClickListener(v -> toClass(context, BrowseCensusActivity.class));

        tv_xzgl.setOnClickListener(v -> toClass(context, DownloadManageActivity.class));

        iv_header.setOnClickListener(v -> {
            if (MainApplication.getContext().getSession() == null) {
                toClass(context, LoginActivity.class);
            }
        });

        tv_hsgl.setOnClickListener(v -> toClass(context, AuditManageActivity.class));
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }
}
