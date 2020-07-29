package com.winto.develop.ThreeTones.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.winto.develop.ThreeTones.MainApplication;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.adapter.TechnicalStandardAdapter;
import com.winto.develop.ThreeTones.base.BaseFragment;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.BaseResponseBean;
import com.winto.develop.ThreeTones.bean.DocTabListBean;
import com.winto.develop.ThreeTones.bean.TechnicalStandardListBean;
import com.winto.develop.ThreeTones.dialog.DownloadFileDialog;
import com.winto.develop.ThreeTones.dialog.FileDownloadApplyDialog;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TechnicalStandardFragment extends BaseFragment {
    private List<TechnicalStandardListBean.DataBean> list;
    private TechnicalStandardAdapter adapter;
    private SmartRefreshLayout srl_refresh;
    private RecyclerView rv_list;
    private View view_empty;

    private DownloadFileDialog downloadFileDialog;
    private DocTabListBean.DataBean.SubMenueBean subMenu;
    private int page = 1;

    private FileDownloadApplyDialog dialog;

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            subMenu = (DocTabListBean.DataBean.SubMenueBean) bundle.getSerializable("menu");
        }

        srl_refresh = findViewById(R.id.srl_refresh);
        rv_list = findViewById(R.id.rv_list);
        view_empty = findViewById(R.id.view_empty);
        srl_refresh.setEnableAutoLoadMore(false);
    }

    @Override
    protected void initAdapter() {
        list = new ArrayList<>();

        adapter = new TechnicalStandardAdapter(context, list);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        rv_list.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        getDocList();
    }

    @Override
    protected void initClick() {
        adapter.setOnItemClickListener(new TechnicalStandardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TechnicalStandardListBean.DataBean bean) {
                showDialog(bean);
            }
        });

        srl_refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            getDocList();
        });

        srl_refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            getDocList();
        });
    }

    private void showDialog(TechnicalStandardListBean.DataBean bean) {
        if (dialog == null) {
            dialog = new FileDownloadApplyDialog(context);
        }
        dialog.show();
        dialog.setOnClickRateDialog(opinion -> applyDownload(bean, opinion));
    }

    private void applyDownload(TechnicalStandardListBean.DataBean bean, String opinion) {
        Map<String, String> params = new HashMap<>();
        params.put("resourceid", String.valueOf(bean.getResourceid()));
        params.put("userid", String.valueOf(MainApplication.getContext().getUserUId()));
        params.put("username", MainApplication.getContext().getUserName());
        params.put("depid", String.valueOf(MainApplication.getContext().getDepId()));
        params.put("depname", MainApplication.getContext().getDepName());
        params.put("applyreason", opinion);

        HttpAction.getInstance().applyDownload(params).subscribe(new BaseObserver<BaseResponseBean>() {
            @Override
            public void onSuccess(BaseResponseBean bean) {
                if (bean.getCode() != 0) {
                    ToastUtil.show(context, bean.getMsg());
                    return;
                }
                dialog.dismiss();
                ToastUtil.show(context, "申请成功");
            }

            @Override
            public void onError(String message) {
                dialog.dismiss();
                ToastUtil.show(context, message);
            }
        });
    }

    private void getDocList() {
        Map<String, Object> params = new HashMap<>();
        params.put("typeid", subMenu.getMenueid());
        params.put("page", page);
        params.put("limit", 20);
        HttpAction.getInstance().getDocList(params).subscribe(new BaseObserver<TechnicalStandardListBean>() {
            @Override
            public void onSuccess(TechnicalStandardListBean bean) {
                finishRefresh();
                if (bean.getCode() != 0) {
                    ToastUtil.show(context, bean.getMsg());
                    showEmptyView(true);
                    return;
                }
                if (page == 1) {
                    addHeader(bean.getData());
                } else {
                    addFooter(bean.getData());
                }
                showEmptyView(false);
            }

            @Override
            public void onError(String message) {
                finishRefresh();
                showEmptyView(true);
                ToastUtil.show(context, message);
            }
        });
    }

    private void addHeader(List<TechnicalStandardListBean.DataBean> data) {
        list.clear();
        if (data == null || data.size() == 0) {
            showEmptyView(true);
            return;
        }
        list.addAll(data);
        adapter.notifyDataSetChanged();

    }

    private void addFooter(List<TechnicalStandardListBean.DataBean> data) {
        list.addAll(data);
        adapter.notifyDataSetChanged();
    }

    private void finishRefresh() {
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
        return R.layout.fragment_technical_standard;
    }
}