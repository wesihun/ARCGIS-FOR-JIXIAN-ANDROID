package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.bean.SpecialMenuUpdateListBean;

import java.util.List;

public class SpecialCateAdapter2 extends RecyclerView.Adapter<SpecialCateAdapter2.UserViewHolder> {
    private Context context;
    private List<SpecialMenuUpdateListBean.SubSpecialMenueBeanX> friendList;
    private OnItemClickListener listener;
    private int position = -1;

    public SpecialCateAdapter2(Context context, List<SpecialMenuUpdateListBean.SubSpecialMenueBeanX> friendList) {
        this.context = context;
        this.friendList = friendList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_special_cate_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        SpecialMenuUpdateListBean.SubSpecialMenueBeanX friend = getItem(position);
        holder.tv_cate_name.setText(friend.getMenuename());

        if (friend.getSubSpecialMenue()==null||friend.getSubSpecialMenue().size()==0){
            holder.iv_image.setImageResource(R.drawable.ic_special_word);
        } else {
            holder.iv_image.setImageResource(R.drawable.ic_special_folder);
        }

        if (this.position == position) {
            holder.tv_cate_name.setTextColor(ContextCompat.getColor(context, R.color.maincolor));
        } else {
            holder.tv_cate_name.setTextColor(ContextCompat.getColor(context, R.color.maintextcolor));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(friend, position);
                }
            }
        });
    }

    public void setClickColor(int position) {
        this.position = position;
        notifyDataSetChanged();
    }

    public SpecialMenuUpdateListBean.SubSpecialMenueBeanX getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_image;
        private TextView tv_cate_name;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_cate_name = itemView.findViewById(R.id.tv_special_name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SpecialMenuUpdateListBean.SubSpecialMenueBeanX user, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}