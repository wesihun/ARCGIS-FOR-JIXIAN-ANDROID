package com.wise.develop.WiseChat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.bean.FriendApplyListBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ApplyFriendListAdapter extends RecyclerView.Adapter<ApplyFriendListAdapter.UserViewHolder> {
    private Context context;
    private List<FriendApplyListBean.DataBean> applyFriendList;
    private OnAddClickListener listener;

    public ApplyFriendListAdapter(Context context, List<FriendApplyListBean.DataBean> applyFriendList) {
        this.context = context;
        this.applyFriendList = applyFriendList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        FriendApplyListBean.DataBean applyFriend = getItem(position);
        holder.tv_user_name.setText(applyFriend.getUserName());
        if ("0".equals(applyFriend.getAddStatus())) {
            holder.tv_add.setText("同意");
            holder.tv_add.setBackgroundResource(R.drawable.bg_btn_clickable_blue);
            holder.tv_add.setEnabled(true);
        } else {
            holder.tv_add.setText("已添加");
            holder.tv_add.setBackgroundResource(R.drawable.bg_btn_not_clickable);
            holder.tv_add.setEnabled(false);
        }
        holder.tv_age.setText(String.format("%s岁", applyFriend.getAge()));
        if (applyFriend.getSex() == 0) {
            holder.tv_sex.setText("女");
        } else {
            holder.tv_sex.setText("男");
        }

        holder.tv_add.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAddClick(applyFriend);
            }
        });
    }

    public FriendApplyListBean.DataBean getItem(int position) {
        return applyFriendList.get(position);
    }

    @Override
    public int getItemCount() {
        return applyFriendList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_header;
        private TextView tv_user_name;
        private TextView tv_age;
        private TextView tv_sex;
        private TextView tv_add;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_header = itemView.findViewById(R.id.iv_header);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);
            tv_age = itemView.findViewById(R.id.tv_age);
            tv_sex = itemView.findViewById(R.id.tv_sex);
            tv_add = itemView.findViewById(R.id.tv_add);
        }
    }

    public interface OnAddClickListener {
        void onAddClick(FriendApplyListBean.DataBean user);
    }

    public void setOnAddClickListener(OnAddClickListener listener) {
        this.listener = listener;
    }
}