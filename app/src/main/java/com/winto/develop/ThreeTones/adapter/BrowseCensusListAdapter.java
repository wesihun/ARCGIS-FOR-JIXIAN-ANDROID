package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.bean.BrowseCensusListBean;
import com.winto.develop.ThreeTones.util.DateUtil;

import java.util.List;

public class BrowseCensusListAdapter extends RecyclerView.Adapter<BrowseCensusListAdapter.ViewHolder> {

    private Context context;
    private List<BrowseCensusListBean.DataBean> browseList;

    public BrowseCensusListAdapter(Context context, List<BrowseCensusListBean.DataBean> browseList) {
        this.context = context;
        this.browseList = browseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_browse_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BrowseCensusListBean.DataBean browse = getItem(position);
        holder.tv_title.setText(DateUtil.formatTime(browse.getCreatetime()));
        holder.tv_info.setText(browse.getLogcontent());
    }

    private BrowseCensusListBean.DataBean getItem(int position) {
        return browseList.get(position);
    }

    @Override
    public int getItemCount() {
        return browseList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_info;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_info = itemView.findViewById(R.id.tv_info);
        }
    }
}
