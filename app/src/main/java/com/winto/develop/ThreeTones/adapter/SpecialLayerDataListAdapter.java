package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.bean.LayerDataListBean;
import com.winto.develop.ThreeTones.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class SpecialLayerDataListAdapter extends RecyclerView.Adapter<SpecialLayerDataListAdapter.UserViewHolder> {
    private Context context;
    private List<LayerDataListBean.ResultBean> friendList;
    private SparseBooleanArray mSelectedPositions;
    private OnCbClickListener listener;

    public SpecialLayerDataListAdapter(Context context, List<LayerDataListBean.ResultBean> friendList) {
        this.context = context;
        this.friendList = friendList;
        mSelectedPositions = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_special_table_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.cb_check.setChecked(isItemChecked(position));
        if (isItemChecked(position)) {
            holder.tv_id.setTextColor(ContextCompat.getColor(context, R.color.maincolor));
            holder.tv_name.setTextColor(ContextCompat.getColor(context, R.color.maincolor));
        } else {
            holder.tv_id.setTextColor(ContextCompat.getColor(context, R.color.maintextcolor));
            holder.tv_name.setTextColor(ContextCompat.getColor(context, R.color.maintextcolor));
        }

        LayerDataListBean.ResultBean bean = getItem(position);
        holder.tv_id.setText(bean.getBsm());
        if (TextUtils.isEmpty(bean.getName())) {
            holder.tv_name.setText("无");
        } else {
            holder.tv_name.setText(bean.getName());
        }

        holder.rl_check.setOnClickListener(v -> {
            if (listener != null) {
                if (isItemChecked(position)) {
                    setItemChecked(position, false);
                    holder.cb_check.setChecked(false);
                } else {
                    int count = 0;
                    for (int i = 0; i < mSelectedPositions.size(); i++) {
                        if (mSelectedPositions.get(i)) {
                            count++;
                        }
                    }
                    if (count > 4) {
                        ToastUtil.show(context, "最多同时选择五个");
                        return;
                    }
                    setItemChecked(position, true);
                    holder.cb_check.setChecked(true);
                }
                listener.onCbClick(bean, position, isItemChecked(position));
            }
            notifyItemChanged(position);
        });
    }

    public void refreshChooseItem() {
        mSelectedPositions = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public List<LayerDataListBean.ResultBean> getChooseItemList() {
        List<LayerDataListBean.ResultBean> resultList = new ArrayList<>();
        for (int i = 0; i < friendList.size(); i++) {
            if (mSelectedPositions.get(i)) {
                resultList.add(friendList.get(i));
            }
        }
        return resultList;
    }

    private LayerDataListBean.ResultBean getItem(int position) {
        return friendList.get(position);
    }

    private void setItemChecked(int position, boolean isChecked) {
        mSelectedPositions.put(position, isChecked);
    }

    private boolean isItemChecked(int position) {
        return mSelectedPositions.get(position);
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rl_check;
        private CheckBox cb_check;
        private TextView tv_id;
        private TextView tv_name;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            rl_check = itemView.findViewById(R.id.rl_check);
            cb_check = itemView.findViewById(R.id.cb_check);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }

    public interface OnCbClickListener {
        void onCbClick(LayerDataListBean.ResultBean bean, int position, boolean isCheck);
    }

    public void setOnCbClickListener(OnCbClickListener listener) {
        this.listener = listener;
    }
}