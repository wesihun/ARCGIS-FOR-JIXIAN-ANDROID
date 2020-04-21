package com.wise.develop.WiseChat.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ActionBroadcast extends BroadcastReceiver {

    private ActionListener listener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (listener != null) {
            listener.receive(context, intent);
        }
    }

    public void setListener(ActionListener l) {
        listener = l;
    }

    public interface ActionListener {
        void receive(Context context, Intent intent);
    }
}
