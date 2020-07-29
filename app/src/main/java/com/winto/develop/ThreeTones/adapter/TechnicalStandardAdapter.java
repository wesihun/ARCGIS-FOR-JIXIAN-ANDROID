package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.bean.TechnicalStandardListBean;
import com.winto.develop.ThreeTones.util.DateUtil;

import java.util.List;

public class TechnicalStandardAdapter extends RecyclerView.Adapter<TechnicalStandardAdapter.UserViewHolder> {
    private Context context;
    private List<TechnicalStandardListBean.DataBean> friendList;
    private OnItemClickListener listener;

    public TechnicalStandardAdapter(Context context, List<TechnicalStandardListBean.DataBean> friendList) {
        this.context = context;
        this.friendList = friendList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_technical_standard_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        TechnicalStandardListBean.DataBean bean = getItem(position);
        String url = bean.getUrl();
        if (!TextUtils.isEmpty(url)) {
            switch (url.substring(url.lastIndexOf("."))) {
                case ".doc":
                case ".docx":
                    holder.iv_image.setImageResource(R.drawable.ic_word);
                    break;
                case ".xls":
                case ".xlsx":
                    holder.iv_image.setImageResource(R.drawable.ic_excal);
                    break;
                case ".pdf":
                    holder.iv_image.setImageResource(R.drawable.ic_pdf);
                    break;
                case ".jpg":
                case ".png":
                case ".jpeg":
                    holder.iv_image.setImageResource(R.drawable.ic_image);
                    break;
                default:
                    holder.iv_image.setImageResource(R.drawable.ic_download_default);
                    break;
            }
        } else {
            holder.iv_image.setImageResource(R.drawable.ic_download_default);
        }

        holder.tv_name.setText(bean.getResourcename());
        holder.tv_info.setText(String.format("创建部门：%s", bean.getSender()));
        holder.tv_time.setText(String.format("创建时间：%s", DateUtil.formatTime(bean.getCreatetime())));
        holder.tv_download.setText("下载");

        holder.tv_download.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(bean);
            }
        });
    }

    public TechnicalStandardListBean.DataBean getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_image;
        private TextView tv_name;
        private TextView tv_info;
        private TextView tv_time;
        private TextView tv_download;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_info = itemView.findViewById(R.id.tv_info);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_download = itemView.findViewById(R.id.tv_download);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TechnicalStandardListBean.DataBean bean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}