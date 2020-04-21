package com.wise.develop.WiseChat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.bean.FriendListBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.UserViewHolder> {
    private Context context;
    private List<FriendListBean.DataBean> friendList;
    private OnItemClickListener listener;

    public FriendListAdapter(Context context, List<FriendListBean.DataBean> friendList) {
        this.context = context;
        this.friendList = friendList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_friend_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        FriendListBean.DataBean friend = getItem(position);
        holder.tv_user_name.setText(friend.getUserName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onItemClick(friend);
                }
            }
        });
    }

    public FriendListBean.DataBean getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_header;
        private TextView tv_user_name;
        private TextView tv_online;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_header = itemView.findViewById(R.id.iv_header);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);
            tv_online = itemView.findViewById(R.id.tv_online);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(FriendListBean.DataBean user);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}