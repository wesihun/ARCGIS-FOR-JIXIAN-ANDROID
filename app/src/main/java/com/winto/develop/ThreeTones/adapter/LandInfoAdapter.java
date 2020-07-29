package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.winto.develop.ThreeTones.R;

import java.util.List;

public class LandInfoAdapter extends BaseAdapter {
    private Context context;
    private List<String> friendList;
    private int position;

    public LandInfoAdapter(Context context, List<String> friendList) {
        this.context = context;
        this.friendList = friendList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        UserViewHolder holder;
        if (convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_town_list, null);
            holder = new UserViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (UserViewHolder) convertView.getTag();
        }

        String str = getItem(position);

        holder.tv_town_name.setText(str);

        return convertView;
    }

    public String getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return friendList.size();
    }

    static class UserViewHolder {
        private TextView tv_town_name;

        UserViewHolder(View itemView) {
            tv_town_name = itemView.findViewById(R.id.tv_town_name);
        }
    }
}