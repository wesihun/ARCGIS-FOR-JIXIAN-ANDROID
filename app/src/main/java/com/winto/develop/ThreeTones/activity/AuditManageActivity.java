package com.winto.develop.ThreeTones.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.adapter.MyPagerAdapter;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.base.BaseFragment;
import com.winto.develop.ThreeTones.fragment.WaitAuditFragment;
import com.winto.develop.ThreeTones.util.StatusBarHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:审核管理
 */

public class AuditManageActivity extends BaseActivity {

    private ImageView iv_back;
    private RelativeLayout rl_title;
    private TabLayout tl_tab;
    private ViewPager vp_pager;

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        rl_title = findViewById(R.id.rl_title);
        tl_tab = findViewById(R.id.tl_tab);
        vp_pager = findViewById(R.id.vp_pager);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl_title.getLayoutParams();
        params.topMargin = StatusBarHelper.getStatusBarHeight();
        rl_title.setLayoutParams(params);
        initFragment();
    }

    private void initFragment() {
        List<BaseFragment> fragmentList = new ArrayList<>();
        List<String> listTitle = new ArrayList<>();
        WaitAuditFragment waitAuditFragment = new WaitAuditFragment();
        WaitAuditFragment auditedFragment = new WaitAuditFragment();
        WaitAuditFragment returnedFragment = new WaitAuditFragment();

        Bundle waitBundle = new Bundle();
        waitBundle.putInt("state", 0);
        Bundle auditedBundle = new Bundle();
        auditedBundle.putInt("state", 1);
        Bundle returnedBundle = new Bundle();
        returnedBundle.putInt("state", -1);

        waitAuditFragment.setArguments(waitBundle);
        auditedFragment.setArguments(auditedBundle);
        returnedFragment.setArguments(returnedBundle);
        fragmentList.add(waitAuditFragment);
        fragmentList.add(auditedFragment);
        fragmentList.add(returnedFragment);
        listTitle.add("待审核");
        listTitle.add("已审核");
        listTitle.add("已退回");
        vp_pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), context, fragmentList, listTitle));
        tl_tab.setupWithViewPager(vp_pager);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initClick() {
        iv_back.setOnClickListener(v -> finish());
    }


    @Override
    protected int setLayout() {
        return R.layout.activity_audit_manage;
    }
}