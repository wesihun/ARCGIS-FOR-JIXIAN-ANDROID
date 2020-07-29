package com.winto.develop.ThreeTones.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.adapter.WaitAuditListAdapter;
import com.winto.develop.ThreeTones.base.BaseFragment;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.BaseResponseBean;
import com.winto.develop.ThreeTones.bean.ManageListBean;
import com.winto.develop.ThreeTones.dialog.FillAuditOpinionDialog;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:已审核
 */

public class WaitAuditFragment extends BaseFragment {

    private SmartRefreshLayout srl_refresh;
    private RecyclerView rv_list;

    private WaitAuditListAdapter adapter;
    private List<ManageListBean.DataBean> auditedList;
    private FillAuditOpinionDialog dialog;

    private int page = 1;
    private int state = 0;

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            state = getArguments().getInt("state", 0);
        }
        srl_refresh = findViewById(R.id.srl_refresh);
        rv_list = findViewById(R.id.rv_list);
        srl_refresh.setEnableAutoLoadMore(false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAdapter() {
        auditedList = new ArrayList<>();
        adapter = new WaitAuditListAdapter(context, auditedList);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        rv_list.setAdapter(adapter);
    }

    @Override
    protected void initClick() {
        adapter.setOnBtnClickListener(new WaitAuditListAdapter.OnBtnClickListener() {
            @Override
            public void onClick(ManageListBean.DataBean manage) {
                switch (manage.getStates()) {
                    case 0:
                        showAuditDialog(manage);
                        break;
                    case 1:

                        break;
                    case -1:

                        break;
                }
            }
        });

        srl_refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            getManageList();
        });

        srl_refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            getManageList();
        });
    }

    private void getManageList() {
        Map<String, Object> params = new HashMap<>();
        params.put("states", state);
        params.put("page", page);
        params.put("limit", 20);
        HttpAction.getInstance().getManageList(params).subscribe(new BaseObserver<ManageListBean>() {
            @Override
            public void onSuccess(ManageListBean bean) {
                finishRefresh();
                if (bean.getCode() != 0) {
                    ToastUtil.show(context, bean.getMsg());
                    return;
                }
                if (page == 1) {
                    addHeader(bean.getData());
                } else {
                    addFooter(bean.getData());
                }
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
                finishRefresh();
            }
        });
    }

    private void addHeader(List<ManageListBean.DataBean> data) {
        auditedList.clear();
        auditedList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    private void addFooter(List<ManageListBean.DataBean> data) {
        auditedList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    private void finishRefresh() {
        srl_refresh.finishRefresh();
        srl_refresh.finishLoadMore();
    }

    private void showAuditDialog(ManageListBean.DataBean manage) {
        if (dialog == null) {
            dialog = new FillAuditOpinionDialog(context);
        }
        dialog.show();
        dialog.setOnClickRateDialog(new FillAuditOpinionDialog.OnClickRateDialog() {
            @Override
            public void onClickLeft(String opinion) {
                auditOpinion(manage.getApplyid(), -1, opinion);
            }

            @Override
            public void onClickRight(String opinion) {
                auditOpinion(manage.getApplyid(), 1, opinion);
            }
        });
    }

    private void auditOpinion(int applyId, int state, String opinion) {
        Map<String, String> params = new HashMap<>();
        params.put("applyid", String.valueOf(applyId));
        params.put("states", String.valueOf(state));
        params.put("reson", opinion);
        HttpAction.getInstance().examine(params).subscribe(new BaseObserver<BaseResponseBean>() {
            @Override
            public void onSuccess(BaseResponseBean bean) {
                if (bean.getCode() != 0) {
                    ToastUtil.show(context, bean.getMsg());
                    return;
                }
                if (state == 1) {
                    ToastUtil.show(context, "申请已通过");
                } else if (state == -1) {
                    ToastUtil.show(context, "申请已退回");
                }
                getManageList();
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getManageList();
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_wait_audit;
    }
}