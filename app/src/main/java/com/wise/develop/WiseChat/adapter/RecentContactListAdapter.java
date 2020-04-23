package com.wise.develop.WiseChat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.bean.RecentContactListBean;
import com.wise.develop.WiseChat.util.DateUtil;
import com.wise.develop.WiseChat.util.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RecentContactListAdapter extends RecyclerView.Adapter<RecentContactListAdapter.RecentContactViewHolder> {
    private Context context;
    private List<RecentContactListBean.DataBean> contactList;
    private OnItemClickListener listener;

    public RecentContactListAdapter(Context context, List<RecentContactListBean.DataBean> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public RecentContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecentContactViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recent_contact_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecentContactViewHolder holder, int position) {
        RecentContactListBean.DataBean contact = getItem(position);
        GlideUtil.displayRoundImage(context, contact.getUserHeader(), holder.iv_header, 5);
        holder.tv_user_name.setText(contact.getRemarkName());
        holder.tv_content.setText(contact.getLastMessage());
        holder.tv_time.setText(DateUtil.friendlyTime(contact.getSendTime()));
        int count = contact.getUnReadCount();
        if (count == 0) {
            holder.tv_unread_count.setVisibility(View.GONE);
            holder.tv_content.setTextColor(ContextCompat.getColor(context, R.color.secondtextcolor));
        } else {
            holder.tv_unread_count.setVisibility(View.VISIBLE);
            holder.tv_unread_count.setText(String.valueOf(count));
            holder.tv_content.setTextColor(ContextCompat.getColor(context, R.color.green));
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAddClick(v, contact);
                holder.tv_unread_count.setVisibility(View.GONE);
                holder.tv_content.setTextColor(ContextCompat.getColor(context, R.color.secondtextcolor));
            }
        });
    }

    private RecentContactListBean.DataBean getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    static class RecentContactViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_header;
        private TextView tv_user_name;
        private TextView tv_content;
        private TextView tv_unread_count;
        private TextView tv_time;

        RecentContactViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_header = itemView.findViewById(R.id.iv_header);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_unread_count = itemView.findViewById(R.id.tv_unread_count);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }

    public void setItemStatus(int position) {

    }

    public interface OnItemClickListener {
        void onAddClick(View view, RecentContactListBean.DataBean contact);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}