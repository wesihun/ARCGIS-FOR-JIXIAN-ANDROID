package com.wise.develop.WiseChat.fragment;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.activity.ChatActivity;
import com.wise.develop.WiseChat.adapter.RecentContactListAdapter;
import com.wise.develop.WiseChat.base.BaseFragment;
import com.wise.develop.WiseChat.base.BaseObserver;
import com.wise.develop.WiseChat.base.BaseResponse;
import com.wise.develop.WiseChat.bean.RecentContactListBean;
import com.wise.develop.WiseChat.broadcast.ActionBroadcast;
import com.wise.develop.WiseChat.http.HttpAction;
import com.wise.develop.WiseChat.util.GlideEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.wise.develop.WiseChat.activity.MainActivity.ACTION_TAG;

public class MessageFragment extends BaseFragment {

    private RecyclerView rv_friend_list;
    private SmartRefreshLayout srl_refresh;
    private ActionBroadcast broadcast = new ActionBroadcast();

    private List<RecentContactListBean.DataBean> contactList;
    private RecentContactListAdapter adapter;
    private int friendId = 0;

    private boolean isRefreshMsg = true;

    @Override
    public void onPause() {
        super.onPause();
        isRefreshMsg = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        isRefreshMsg = true;
        clearUnReadCount();
    }

    @Override
    protected void initView() {
        rv_friend_list = findViewById(R.id.rv_friend_list);
        srl_refresh = findViewById(R.id.srl_refresh);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_TAG);

        context.registerReceiver(broadcast, filter);
    }

    @Override
    protected void initAdapter() {
        contactList = new ArrayList<>();
        adapter = new RecentContactListAdapter(context, contactList);
        rv_friend_list.setLayoutManager(new LinearLayoutManager(context));
        rv_friend_list.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        getRecentContactList();
    }

    @Override
    protected void initClick() {
        srl_refresh.setOnRefreshListener(refreshLayout -> getRecentContactList());

        broadcast.setListener((context, intent) -> {
            if (isRefreshMsg) {
                getRecentContactList();
            }
        });

        adapter.setOnItemClickListener((view, contact) -> {
            friendId = contact.getFriendId();
            Bundle bundle = new Bundle();
            bundle.putString("userName", contact.getRemarkName());
            bundle.putInt("userId", friendId);
            toClass(context, ChatActivity.class, bundle);
        });
    }

    private void getRecentContactList() {
        HttpAction.getInstance().getRecentContactList().subscribe(new BaseObserver<RecentContactListBean>() {
            @Override
            public void onSuccess(RecentContactListBean bean) {
                contactList.clear();
                contactList.addAll(bean.getData());
                adapter.notifyDataSetChanged();
                srl_refresh.finishRefresh();
            }

            @Override
            public void onError(String message) {
                srl_refresh.finishRefresh();
            }
        });
    }

    private void clearUnReadCount() {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", friendId);
        HttpAction.getInstance().clearUnReadMsgCount(params).subscribe(new BaseObserver<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse bean) {
                getRecentContactList();
            }

            @Override
            public void onError(String message) {
                getRecentContactList();
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_message;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(broadcast);
    }
}
