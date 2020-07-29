package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.bean.NoticeListBean;
import com.winto.develop.ThreeTones.util.DateUtil;

import java.util.List;

public class NoticeListAdapter extends RecyclerView.Adapter<NoticeListAdapter.NoticeViewHolder> {
    private Context context;
    private List<NoticeListBean.DataBean> noticeList;
    private OnItemClickListener listener;

    public NoticeListAdapter(Context context, List<NoticeListBean.DataBean> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoticeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notice_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        NoticeListBean.DataBean notice = getItem(position);
        holder.tv_notice_name.setText(notice.getTitle());
        holder.tv_time.setText(DateUtil.formatTime(notice.getCreatetime()));
        holder.tv_info.setText(Html.fromHtml(notice.getContent()));
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(notice, position);
            }
        });
    }

    public NoticeListBean.DataBean getItem(int position) {
        return noticeList.get(position);
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    static class NoticeViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_notice_name;
        private TextView tv_time;
        private TextView tv_info;

        NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_notice_name = itemView.findViewById(R.id.tv_notice_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_info = itemView.findViewById(R.id.tv_info);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(NoticeListBean.DataBean notice, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}