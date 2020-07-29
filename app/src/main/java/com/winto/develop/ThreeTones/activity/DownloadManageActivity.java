package com.winto.develop.ThreeTones.activity;

import android.Manifest;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.winto.develop.ThreeTones.MainApplication;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.adapter.DownloadManageListAdapter;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.DownloadManageListBean;
import com.winto.develop.ThreeTones.dialog.DownloadFileDialog;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.StatusBarHelper;
import com.winto.develop.ThreeTones.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:下载管理
 */
public class DownloadManageActivity extends BaseActivity {
    private String[] permissionList = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private ImageView iv_back;
    private RelativeLayout rl_title;
    private RecyclerView rv_list;
    private SmartRefreshLayout srl_refresh;
    private View view_empty;
    private DownloadManageListAdapter adapter;
    private List<DownloadManageListBean.DataBean> downloadList;
    private int page = 1;

    private DownloadFileDialog downloadFileDialog;

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        rl_title = findViewById(R.id.rl_title);
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
        downloadList = new ArrayList<>();
        adapter = new DownloadManageListAdapter(context, downloadList);
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

        adapter.setOnItemClickListener(bean -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkPermission(permissionList[0]) || checkPermission(permissionList[1])) {
                    initPermission();
                } else {
                    showDownloadDialog(bean);
                }
            } else {
                showDownloadDialog(bean);
            }
        });
    }

    private void showDownloadDialog(DownloadManageListBean.DataBean bean) {
        if (downloadFileDialog == null) {
            downloadFileDialog = new DownloadFileDialog(context);
        }
        downloadFileDialog.show();
        downloadFileDialog.downloadFile(bean, new DownloadFileDialog.OnDownloadListener() {
            @Override
            public void onDownloadSuccess() {
                downloadFileDialog.dismiss();
                runOnUiThread(() -> ToastUtil.show(context, "下载成功"));
            }

            @Override
            public void onDownloading(int progress) {

            }

            @Override
            public void onDownloadFailed() {
                downloadFileDialog.dismiss();
                runOnUiThread(() -> ToastUtil.show(context, "下载失败"));
            }
        });
    }

    private void getBrowseList() {
        Map<String, Object> params = new HashMap<>();
        params.put("userid", MainApplication.getContext().getUserUId());
        params.put("page", page);
        params.put("limit", "20");
        HttpAction.getInstance().getDownloadList(params).subscribe(new BaseObserver<DownloadManageListBean>() {
            @Override
            public void onSuccess(DownloadManageListBean bean) {
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
            }

            @Override
            public void onError(String message) {
                showEmptyView(true);
                showError(message);
            }
        });
    }

    private void addHeader(List<DownloadManageListBean.DataBean> data) {
        downloadList.clear();
        if (data == null || data.size() == 0) {
            showEmptyView(true);
            return;
        }
        downloadList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    private void addFooter(List<DownloadManageListBean.DataBean> data) {
        downloadList.addAll(data);
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

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission(permissionList[0]) || checkPermission(permissionList[1])) {
                ActivityCompat.requestPermissions(this, permissionList, 1);
            }
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_download_manage;
    }
}