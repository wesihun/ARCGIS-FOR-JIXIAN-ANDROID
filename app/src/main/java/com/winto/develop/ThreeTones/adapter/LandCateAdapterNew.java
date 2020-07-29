package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.winto.develop.ThreeTones.R;

public class LandCateAdapterNew extends BaseAdapter {
    private Context context;
    private int position;

    public LandCateAdapterNew(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        UserViewHolder holder;
        if (convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_land_cate_list, null);
            holder = new UserViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (UserViewHolder) convertView.getTag();
        }

        return convertView;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return 50;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    static class UserViewHolder {

        UserViewHolder(View itemView) {

        }
    }
}