package com.wise.develop.WiseChat.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.adapter.UserListAdapter;
import com.wise.develop.WiseChat.base.BaseActivity;
import com.wise.develop.WiseChat.base.BaseObserver;
import com.wise.develop.WiseChat.base.BaseResponse;
import com.wise.develop.WiseChat.bean.UserListBean;
import com.wise.develop.WiseChat.http.HttpAction;
import com.wise.develop.WiseChat.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchUserActivity extends BaseActivity {
    private ImageView iv_back;
    private EditText et_search;
    private RecyclerView rv_friend_list;
    private TextView tv_search;

    private UserListAdapter adapter;
    private List<UserListBean.DataBean> userList;
    private String key;

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        et_search = findViewById(R.id.et_search);
        rv_friend_list = findViewById(R.id.rv_friend_list);
        tv_search = findViewById(R.id.tv_search);
    }

    @Override
    protected void initAdapter() {
        userList = new ArrayList<>();
        adapter = new UserListAdapter(context, userList);
        rv_friend_list.setLayoutManager(new LinearLayoutManager(context));
        rv_friend_list.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchUser();
                hideSoftInput();
            }
        });

        adapter.setOnAddClickListener(new UserListAdapter.OnAddClickListener() {
            @Override
            public void onAddClick(UserListBean.DataBean user) {
                applyFriend(user.getId());
            }
        });
    }

    private void searchUser() {
        key = et_search.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put("userName", key);
        HttpAction.getInstance().searchUser(params).subscribe(new BaseObserver<UserListBean>() {

            @Override
            public void onSuccess(UserListBean bean) {
                userList.clear();
                userList.addAll(bean.getData());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private void applyFriend(int toUserId) {
        Map<String, Object> params = new HashMap<>();
        params.put("toUserId", toUserId);
        HttpAction.getInstance().applyFriend(params).subscribe(new BaseObserver<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse bean) {
                ToastUtil.show(context, "申请好友成功");
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_search_friend;
    }
}