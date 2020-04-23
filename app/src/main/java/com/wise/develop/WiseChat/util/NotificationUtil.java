package com.wise.develop.WiseChat.util;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.wise.develop.WiseChat.MainApplication;
import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.activity.ChatActivity;
import com.wise.develop.WiseChat.bean.MessageInfoBean;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationUtil {

    private static String CALENDAR_ID = MainApplication.getContext().getPackageName();

    public static void showNotification(String messageBody) {
        NotificationManager manager = (NotificationManager) MainApplication.getContext().getSystemService(NOTIFICATION_SERVICE);
        Notification notification = createNotification(messageBody, MainApplication.getContext(), manager);
        if (manager != null) {
            manager.notify(1, notification);
        }
    }

    private static Notification createNotification(String messageBody, Context context, NotificationManager notificationManager) {
        MessageInfoBean message = new Gson().fromJson(messageBody, MessageInfoBean.class);
        Notification notification;
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(CALENDAR_ID, "123", NotificationManager.IMPORTANCE_HIGH);
            // 设置渠道描述
            notificationChannel.setDescription("测试通知组");
            // 是否绕过请勿打扰模式
            notificationChannel.canBypassDnd();
            // 设置绕过请勿打扰模式
            notificationChannel.setBypassDnd(true);
            // 桌面Launcher的消息角标
            notificationChannel.canShowBadge();
            // 设置显示桌面Launcher的消息角标
            notificationChannel.setShowBadge(true);
            // 设置通知出现时声音，默认通知是有声音的
            notificationChannel.setSound(null, null);
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            // 设置通知出现时的震动（如果 android 设备支持的话）
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400,
                    300, 200, 400});
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("friendName", message.getFriendName());
        intent.putExtra("friendHeader", message.getFriendHeader());
        intent.putExtra("friendId", message.getFriendId());
        builder = new NotificationCompat.Builder(context, CALENDAR_ID);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification = builder.setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent)
                .setChannelId(CALENDAR_ID)
                .setContentText(message.getContent())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(message.getFriendName())
                .setAutoCancel(true)
                .setLargeIcon(FileUtil.urlToBitmap(message.getFriendHeader()))
                .build();
        return notification;
    }


}
