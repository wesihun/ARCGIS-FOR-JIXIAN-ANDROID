package com.winto.develop.ThreeTones.activity;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.adapter.StatisticalReportAdapter;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.bean.LandInfoChartBean;
import com.winto.develop.ThreeTones.util.StatusBarHelper;

import java.util.List;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:统计报表
 */

public class StatisticalReportActivity extends BaseActivity {

    private ImageView iv_back;
    private List<LandInfoChartBean> chartInfoList;
    private StatisticalReportAdapter adapter;
    private RelativeLayout rl_title;
    private TextView tv_name;
    private RecyclerView rv_list;
    private String name;

    @Override
    protected void initIntentData() {
        name = getIntent().getStringExtra("name");
        chartInfoList = (List<LandInfoChartBean>) getIntent().getSerializableExtra("chartInfoList");
    }

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        rl_title = findViewById(R.id.rl_title);
        tv_name = findViewById(R.id.tv_name);
        rv_list = findViewById(R.id.rv_list);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl_title.getLayoutParams();
        params.topMargin = StatusBarHelper.getStatusBarHeight();
        rl_title.setLayoutParams(params);

        tv_name.setText(name);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAdapter() {
        adapter = new StatisticalReportAdapter(context, chartInfoList);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        rv_list.setAdapter(adapter);
    }

    @Override
    protected void initClick() {
        iv_back.setOnClickListener(v -> finish());
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_statistical_report;
    }
}