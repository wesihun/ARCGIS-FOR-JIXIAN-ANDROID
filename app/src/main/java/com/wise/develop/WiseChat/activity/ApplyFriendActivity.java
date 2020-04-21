package com.wise.develop.WiseChat.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.adapter.ApplyFriendListAdapter;
import com.wise.develop.WiseChat.base.BaseActivity;
import com.wise.develop.WiseChat.base.BaseObserver;
import com.wise.develop.WiseChat.base.BaseResponse;
import com.wise.develop.WiseChat.bean.FriendApplyListBean;
import com.wise.develop.WiseChat.http.HttpAction;
import com.wise.develop.WiseChat.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:欢迎页
 */

public class ApplyFriendActivity extends BaseActivity {

    private ImageView iv_back;
    private TextView tv_search;
    private RecyclerView rv_friend_list;

    private ApplyFriendListAdapter adapter;
    private List<FriendApplyListBean.DataBean> applyFriendList;

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        tv_search = findViewById(R.id.tv_search);
        rv_friend_list = findViewById(R.id.rv_friend_list);
    }

    @Override
    protected void initAdapter() {
        applyFriendList = new ArrayList<>();
        adapter = new ApplyFriendListAdapter(context, applyFriendList);
        rv_friend_list.setLayoutManager(new LinearLayoutManager(context));
        rv_friend_list.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        getApplyFriendList();
    }

    @Override
    protected void initClick() {
        iv_back.setOnClickListener(view -> finish());

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toClass(context, SearchUserActivity.class);
            }
        });

        adapter.setOnAddClickListener(user -> agreeFriend(user.getId()));
    }

    private void getApplyFriendList() {
        HttpAction.getInstance().getFriendApplyList().subscribe(new BaseObserver<FriendApplyListBean>() {
            @Override
            public void onSuccess(FriendApplyListBean bean) {
                applyFriendList.clear();
                applyFriendList.addAll(bean.getData());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private void agreeFriend(int fromUserId) {
        Map<String, Object> params = new HashMap<>();
        params.put("fromUserId", fromUserId);
        HttpAction.getInstance().agreeFriend(params).subscribe(new BaseObserver<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse bean) {
                ToastUtil.show(context, "已同意");
                getApplyFriendList();
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_apply_friend;
    }
}