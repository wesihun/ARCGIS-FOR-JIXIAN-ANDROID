package com.winto.develop.ThreeTones.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.winto.develop.ThreeTones.MainApplication;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.adapter.BrowseCensusListAdapter;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.BrowseCensusListBean;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.StatusBarHelper;
import com.winto.develop.ThreeTones.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:浏览统计
 */

public class BrowseCensusActivity extends BaseActivity {

    private ImageView iv_back;
    private RelativeLayout rl_title;
    private TextView tv_login_count;
    private RecyclerView rv_list;
    private SmartRefreshLayout srl_refresh;

    private View view_empty;

    private BrowseCensusListAdapter adapter;
    private List<BrowseCensusListBean.DataBean> browseList;
    private int page = 1;

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        rl_title = findViewById(R.id.rl_title);
        tv_login_count = findViewById(R.id.tv_login_count);
        rv_list = findViewById(R.id.rv_list);
        srl_refresh = findViewById(R.id.srl_refresh);
        view_empty = findViewById(R.id.view_empty);
        srl_refresh.setEnableAutoLoadMore(false);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl_title.getLayoutParams();
        params.topMargin = StatusBarHelper.getStatusBarHeight();
        rl_title.setLayoutParams(params);
    }

    @Override
    protected void initData() {
        getBrowseList();
    }

    @Override
    protected void initAdapter() {
        browseList = new ArrayList<>();
        adapter = new BrowseCensusListAdapter(context, browseList);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        rv_list.setAdapter(adapter);
    }

    @Override
    protected void initClick() {
        iv_back.setOnClickListener(v -> finish());

        srl_refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            getBrowseList();
        });

        srl_refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            getBrowseList();
        });
    }

    private void getBrowseList() {
        Map<String, Object> params = new HashMap<>();
        params.put("userid", MainApplication.getContext().getUserUId());
        params.put("page", page);
        params.put("limit", "20");
        HttpAction.getInstance().getBrowseCensus(params).subscribe(new BaseObserver<BrowseCensusListBean>() {
            @Override
            public void onSuccess(BrowseCensusListBean bean) {
                if (bean.getCode() != 0) {
                    showError("网络请求失败");
                    showEmptyView(true);
                    return;
                }
                if (page == 1) {
                    addHeader(bean.getData());
                    srl_refresh.finishRefresh();
                } else {
                    addFooter(bean.getData());
                    srl_refresh.finishLoadMore();
                }
                tv_login_count.setText(String.format("今日登陆次数：%s", bean.getLoginCount()));
            }

            @Override
            public void onError(String message) {
                showEmptyView(true);
                showError(message);
            }
        });
    }

    private void addHeader(List<BrowseCensusListBean.DataBean> data) {
        browseList.clear();
        if (data == null || data.size() == 0) {
            showEmptyView(true);
            return;
        }
        browseList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    private void addFooter(List<BrowseCensusListBean.DataBean> data) {
        browseList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    private void showError(String message) {
        ToastUtil.show(context, message);
        srl_refresh.finishRefresh();
        srl_refresh.finishLoadMore();
    }

    private void showEmptyView(boolean isShow) {
        if (isShow) {
            srl_refresh.setVisibility(View.GONE);
            view_empty.setVisibility(View.VISIBLE);
        } else {
            srl_refresh.setVisibility(View.VISIBLE);
            view_empty.setVisibility(View.GONE);
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_browse_census;
    }
}