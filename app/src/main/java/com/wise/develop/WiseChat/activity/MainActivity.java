package com.wise.develop.WiseChat.activity;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.wise.develop.WiseChat.MainApplication;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.base.BaseActivity;
import com.wise.develop.WiseChat.base.BaseFragment;
import com.wise.develop.WiseChat.base.BaseObserver;
import com.wise.develop.WiseChat.bean.MessageInfoBean;
import com.wise.develop.WiseChat.bean.UserInfoBean;
import com.wise.develop.WiseChat.constant.ConstantData;
import com.wise.develop.WiseChat.fragment.FindFragment;
import com.wise.develop.WiseChat.fragment.FriendFragment;
import com.wise.develop.WiseChat.fragment.MessageFragment;
import com.wise.develop.WiseChat.fragment.MyFragment;
import com.wise.develop.WiseChat.http.HttpAction;
import com.wise.develop.WiseChat.socket.ChatSocketClient;
import com.wise.develop.WiseChat.util.FileUtil;
import com.wise.develop.WiseChat.util.NotificationUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
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
    private Handler timeHandler;

    @Override
    protected void initView() {
        rg_main = findViewById(R.id.rg_main);
        initFragment();
        initSocket();
        initHandler();
        checkEnabledDialog();
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
                NotificationUtil.showNotification(message);
            }
        };

        try {
            socketClient.connectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initHandler() {
        if (timeHandler == null) {
            timeHandler = new Handler();
        }
        timeHandler.postDelayed(timeRunnable, 30 * 1000);
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

    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            //长连接心跳  30s发送一次
            heartBeat();
            timeHandler.postDelayed(timeRunnable, 30 * 1000);
        }
    };

    private void heartBeat() {
        if (socketClient != null) {
            if (socketClient.isClosed()) {
                reconnectWs();
            } else {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("ping", 1559551259897L);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                socketClient.send(jsonObject.toString());
                Log.e("ping", "run: " + jsonObject.toString());
            }
        } else {
            //如果client已为空，重新初始化webSocket
            initSocket();
        }
    }

    private void reconnectWs() {
        new Thread() {
            @Override
            public void run() {
                try {
                    //重连
                    socketClient.reconnectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeConnect();
    }

    public void checkEnabledDialog() {
        if (checkEnabled(context)) {
            return;
        }
        createDialog(context).show();
    }

    public boolean checkEnabled(Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }

    public Dialog createDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("通知权限");
        builder.setMessage("需要获取通知权限，取消设置则无法第一时间接受到新消息，是否设置？");
        builder.setNegativeButton("否", null);
        builder.setPositiveButton("是", (dialogInterface, i) -> {
            String name = context.getPackageName();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", name, null);
            intent.setData(uri);
            context.startActivity(intent);
        });
        return builder.create();
    }

    private void closeConnect() {
        try {
            if (null != socketClient) {
                socketClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socketClient = null;
        }
    }
}