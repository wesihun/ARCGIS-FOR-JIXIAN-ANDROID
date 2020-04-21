package com.wise.develop.WiseChat.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.activity.ApplyFriendActivity;
import com.wise.develop.WiseChat.activity.ChatActivity;
import com.wise.develop.WiseChat.adapter.FriendListAdapter;
import com.wise.develop.WiseChat.base.BaseFragment;
import com.wise.develop.WiseChat.base.BaseObserver;
import com.wise.develop.WiseChat.bean.ApplyCountBean;
import com.wise.develop.WiseChat.bean.FriendListBean;
import com.wise.develop.WiseChat.http.HttpAction;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FriendFragment extends BaseFragment {

    private SmartRefreshLayout srl_refresh;
    private RelativeLayout rl_friend_apply;
    private TextView tv_msg_count;
    private RecyclerView rv_friend_list;
    private List<FriendListBean.DataBean> friendList;
    private FriendListAdapter adapter;

    @Override
    protected void initView() {
        srl_refresh = findViewById(R.id.srl_refresh);
        rl_friend_apply = findViewById(R.id.rl_friend_apply);
        tv_msg_count = findViewById(R.id.tv_msg_count);
        rv_friend_list = findViewById(R.id.rv_friend_list);
    }

    @Override
    protected void initAdapter() {
        friendList = new ArrayList<>();
        adapter = new FriendListAdapter(context, friendList);
        rv_friend_list.setLayoutManager(new LinearLayoutManager(context));
        rv_friend_list.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        getApplyCount();
        getFriendList();
    }

    @Override
    protected void initClick() {
        rl_friend_apply.setOnClickListener(v -> toClass(context, ApplyFriendActivity.class));

        adapter.setOnItemClickListener(friend -> {
            Bundle bundle = new Bundle();
            bundle.putString("userName", friend.getRemarkName());
            bundle.putInt("userId", friend.getFriendId());
            toClass(context, ChatActivity.class, bundle);
        });

        srl_refresh.setOnRefreshListener(refreshLayout -> {
            getApplyCount();
            getFriendList();
        });
    }

    private void getApplyCount() {
        HttpAction.getInstance().getFriendApplyCount().subscribe(new BaseObserver<ApplyCountBean>() {
            @Override
            public void onSuccess(ApplyCountBean bean) {
                int count = bean.getData().getApplyCount();
                if (count == 0) {
                    tv_msg_count.setVisibility(View.GONE);
                } else {
                    tv_msg_count.setVisibility(View.VISIBLE);
                    tv_msg_count.setText(String.valueOf(count));
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void getFriendList() {
        HttpAction.getInstance().getFriendList().subscribe(new BaseObserver<FriendListBean>() {
            @Override
            public void onSuccess(FriendListBean bean) {
                friendList.clear();
                friendList.addAll(bean.getData());
                adapter.notifyDataSetChanged();
                srl_refresh.finishRefresh();
            }

            @Override
            public void onError(String message) {
                srl_refresh.finishRefresh();
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_friend;
    }
}