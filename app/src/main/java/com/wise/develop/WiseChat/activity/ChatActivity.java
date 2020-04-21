package com.wise.develop.WiseChat.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wise.develop.WiseChat.MainApplication;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.adapter.MessageAdapter;
import com.wise.develop.WiseChat.base.BaseActivity;
import com.wise.develop.WiseChat.base.BaseObserver;
import com.wise.develop.WiseChat.base.BaseResponse;
import com.wise.develop.WiseChat.bean.MessageInfoBean;
import com.wise.develop.WiseChat.bean.MessageListBean;
import com.wise.develop.WiseChat.broadcast.ActionBroadcast;
import com.wise.develop.WiseChat.http.HttpAction;
import com.wise.develop.WiseChat.util.DateUtil;
import com.wise.develop.WiseChat.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.wise.develop.WiseChat.activity.MainActivity.ACTION_TAG;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:欢迎页
 */

public class ChatActivity extends BaseActivity {
    private ActionBroadcast broadcast = new ActionBroadcast();
    private ImageView iv_back;
    private TextView tv_title;
    private RecyclerView rv_message_list;
    private EditText et_content;
    private TextView tv_send;

    private String userName;
    private int userId;
    private MessageAdapter adapter;
    private List<MessageListBean.DataBean> messageList;

    @Override
    protected void initIntentData() {
        userName = getIntent().getStringExtra("userName");
        userId = getIntent().getIntExtra("userId", 0);
    }

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        rv_message_list = findViewById(R.id.rv_message_list);
        et_content = findViewById(R.id.et_content);
        tv_send = findViewById(R.id.tv_send);

        tv_title.setText("与" + userName + "聊天中");

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_TAG);
        registerReceiver(broadcast, filter);
    }

    @Override
    protected void initAdapter() {
        messageList = new ArrayList<>();
        adapter = new MessageAdapter(context, messageList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setStackFromEnd(true);
        rv_message_list.setLayoutManager(manager);
        rv_message_list.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        getMessageHistory();
    }

    @Override
    protected void initClick() {
        iv_back.setOnClickListener(v -> finish());

        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        broadcast.setListener(new ActionBroadcast.ActionListener() {
            @Override
            public void receive(Context context, Intent intent) {
                String messageBody = intent.getStringExtra("messageBody");
                MessageInfoBean message = new Gson().fromJson(messageBody, MessageInfoBean.class);
                if (message.getFriendId() == userId) {
                    refreshMessage(userName, message.getContent(), message.getSendTime(), 1);
                }
            }
        });
    }

    private void getMessageHistory() {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        HttpAction.getInstance().getMessageHistory(params).subscribe(new BaseObserver<MessageListBean>() {
            @Override
            public void onSuccess(MessageListBean bean) {
                messageList.clear();
                messageList.addAll(bean.getData());
                adapter.notifyDataSetChanged();
                rv_message_list.scrollToPosition(messageList.size() - 1);
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void sendMessage() {
        String content = et_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtil.show(context, "不能为空");
            return;
        }

        et_content.setText("");
        refreshMessage(MainApplication.getContext().getUserInfo().getUserName(), content, DateUtil.getCurrentTimeStr(DateUtil.YYYY_MM_DD_HH_MM_SS), 0);

        Map<String, Object> params = new HashMap<>();
        params.put("content", content);
        params.put("toUserId", userId);
        HttpAction.getInstance().sendMessage(params).subscribe(new BaseObserver<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse bean) {

            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private void refreshMessage(String userName, String content, String sendTime, int sendOrReceive) {
        MessageListBean.DataBean message = new MessageListBean.DataBean();
        message.setContent(content);
        message.setTime(sendTime);
        message.setSendOrReceive(sendOrReceive);
        message.setUserName(userName);
        messageList.add(message);
        adapter.notifyItemInserted(adapter.getItemCount());
        rv_message_list.scrollToPosition(messageList.size() - 1);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_chat;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcast);
    }
}