package com.wise.develop.WiseChat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wise.develop.WiseChat.R;
import com.wise.develop.WiseChat.bean.UserListBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    private Context context;
    private List<UserListBean.DataBean> userList;
    private OnAddClickListener listener;

    public UserListAdapter(Context context, List<UserListBean.DataBean> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserListBean.DataBean user = getItem(position);
        holder.tv_user_name.setText(user.getUserName());
        holder.tv_age.setText(String.format("%s岁", user.getAge()));
        if (user.getSex() == 0) {
            holder.tv_sex.setText("女");
        } else {
            holder.tv_sex.setText("男");
        }

        holder.tv_add.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAddClick(user);
            }
        });
    }

    public UserListBean.DataBean getItem(int position) {
        return userList.get(position);
    }

    @Override
    public int getItemCount() {
        return userList.size();
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
        void onAddClick(UserListBean.DataBean user);
    }

    public void setOnAddClickListener(OnAddClickListener listener) {
        this.listener = listener;
    }
}