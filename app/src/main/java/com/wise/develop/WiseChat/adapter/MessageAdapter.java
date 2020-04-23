package com.wise.develop.WiseChat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.bean.MessageListBean;
import com.wise.develop.WiseChat.bean.UserListBean;
import com.wise.develop.WiseChat.util.DateUtil;
import com.wise.develop.WiseChat.util.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private Context context;
    private List<MessageListBean.DataBean> messageList;
    private OnAddClickListener listener;

    public MessageAdapter(Context context, List<MessageListBean.DataBean> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            //发送
            return new MessageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_send_message_list, parent, false));
        } else {
            //接收
            return new MessageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_receive_message_list, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MessageListBean.DataBean message = getItem(position);
        GlideUtil.displayRoundImage(context, message.getUserHeader(), holder.iv_header, 5);
        if (isShowTimeLabel(position)){
            holder.tv_time.setVisibility(View.VISIBLE);
            holder.tv_time.setText(DateUtil.friendlyTime(message.getTime()));
        } else {
            holder.tv_time.setVisibility(View.GONE);
        }
        holder.tv_content.setText(message.getContent());
    }

    private boolean isShowTimeLabel(int position) {
        if (position == 0) {
            return true;
        }
        String preTime = getItem(position - 1).getTime();
        String currentTime = getItem(position).getTime();
        return DateUtil.isShowTimeLabel(preTime, currentTime);
    }

    private MessageListBean.DataBean getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return messageList.get(position).getSendOrReceive();
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_time;
        private ImageView iv_header;
        private TextView tv_content;

        MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
            iv_header = itemView.findViewById(R.id.iv_header);
            tv_content = itemView.findViewById(R.id.tv_content);
        }
    }

    public interface OnAddClickListener {
        void onAddClick(UserListBean.DataBean user);
    }

    public void setOnAddClickListener(OnAddClickListener listener) {
        this.listener = listener;
    }
}