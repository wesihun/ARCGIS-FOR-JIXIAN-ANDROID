package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.bean.TownListBean;

import java.util.List;

public class TownListAdapter extends RecyclerView.Adapter<TownListAdapter.UserViewHolder> {
    private Context context;
    private List<TownListBean> townList;
    private OnItemClickListener listener;
    private int position = -1;

    public TownListAdapter(Context context, List<TownListBean> townList) {
        this.context = context;
        this.townList = townList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_town_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        TownListBean town = getItem(position);
        holder.tv_town_name.setText(town.getName());

        if (this.position == position) {
            holder.tv_town_name.setTextColor(ContextCompat.getColor(context, R.color.maincolor));
        } else {
            holder.tv_town_name.setTextColor(ContextCompat.getColor(context, R.color.maintextcolor));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(town, position);
                }
            }
        });
    }

    public void setClickColor(int position) {
        this.position = position;
        notifyDataSetChanged();
    }

    public TownListBean getItem(int position) {
        return townList.get(position);
    }

    @Override
    public int getItemCount() {
        return townList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_town_name;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_town_name = itemView.findViewById(R.id.tv_town_name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TownListBean user, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}