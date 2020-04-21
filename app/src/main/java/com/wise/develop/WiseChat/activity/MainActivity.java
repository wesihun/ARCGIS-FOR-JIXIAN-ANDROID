package com.wise.develop.WiseChat.activity;

import android.content.Intent;
import android.widget.RadioGroup;

import com.wise.develop.WiseChat.MainApplication;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.base.BaseActivity;
import com.wise.develop.WiseChat.base.BaseFragment;
import com.wise.develop.WiseChat.base.BaseObserver;
import com.wise.develop.WiseChat.bean.UserInfoBean;
import com.wise.develop.WiseChat.constant.ConstantData;
import com.wise.develop.WiseChat.fragment.FindFragment;
import com.wise.develop.WiseChat.fragment.FriendFragment;
import com.wise.develop.WiseChat.fragment.MessageFragment;
import com.wise.develop.WiseChat.fragment.MyFragment;
import com.wise.develop.WiseChat.http.HttpAction;
import com.wise.develop.WiseChat.socket.ChatSocketClient;

import java.net.URI;

import androidx.fragment.app.FragmentTransaction;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:欢迎页
 */

public class MainActivity extends BaseActivity {
    public static final String ACTION_TAG = "action.refresh_message";

    private RadioGroup rg_main;
    private BaseFragment[] fragmentArray;
    private int currentIndex;
    private ChatSocketClient socketClient;

    @Override
    protected void initView() {
        rg_main = findViewById(R.id.rg_main);
        initFragment();
        initSocket();
    }

    private void initSocket() {
        String rul = ConstantData.WEB_SOCKET_URL + MainApplication.getContext().getUId();

        URI uri = URI.create(rul);
        socketClient = new ChatSocketClient(uri) {
            @Override
            public void onMessage(String message) {
                //message就是接收到的消息
                Intent intent = new Intent();
                intent.setAction(ACTION_TAG);
                intent.putExtra("messageBody", message);
                sendBroadcast(intent);

            }
        };

        try {
            socketClient.connectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initFragment() {
        fragmentArray = new BaseFragment[]{
                new MessageFragment(),
                new FriendFragment(),
                new FindFragment(),
                new MyFragment()
        };

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //设置为默认界面 MessageFragment
        ft.replace(R.id.fl_container, fragmentArray[0]);
        ft.commit();
    }

    @Override
    protected void initData() {
        rg_main.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_msg:
                    setIndexSelected(0);
                    break;
                case R.id.rb_friend:
                    setIndexSelected(1);
                    break;
                case R.id.rb_find:
                    setIndexSelected(2);
                    break;
                case R.id.rb_my:
                    setIndexSelected(3);
                    break;
            }
        });
    }

    //设置Fragment页面
    private void setIndexSelected(int index) {
        if (currentIndex == index) {
            return;
        }
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(fragmentArray[currentIndex]);
        //判断Fragment是否已经添加
        if (!fragmentArray[index].isAdded()) {
            ft.add(R.id.fl_container, fragmentArray[index]).show(fragmentArray[index]);
        } else {
            //显示新的Fragment
            ft.show(fragmentArray[index]);
        }
        ft.commit();
        currentIndex = index;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserInfo();
    }

    private void getUserInfo() {
        HttpAction.getInstance().getUserInfo().subscribe(new BaseObserver<UserInfoBean>() {
            @Override
            public void onSuccess(UserInfoBean bean) {
                UserInfoBean.DataBean user = bean.getData();
                MainApplication.getContext().setToken(user.getUserToken());
                MainApplication.getContext().setUId(user.getId());
                MainApplication.getContext().setUserInfo(user);
            }

            @Override
            public void onError(String message) {

            }
        });
    }
}