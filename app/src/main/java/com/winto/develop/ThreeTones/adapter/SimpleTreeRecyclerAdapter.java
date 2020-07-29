package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.bean.Node;
import com.winto.develop.ThreeTones.bean.StatisticalAnalysisMenuListBean;

import java.util.List;

public class SimpleTreeRecyclerAdapter extends TreeRecyclerAdapter<Integer, StatisticalAnalysisMenuListBean> {
    public SimpleTreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node<Integer, StatisticalAnalysisMenuListBean>> datas, int defaultExpandLevel, int iconExpand, int iconNoExpand) {
        super(mTree, context, datas, defaultExpandLevel, iconExpand, iconNoExpand);
    }

    public SimpleTreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node<Integer, StatisticalAnalysisMenuListBean>> datas, int defaultExpandLevel) {
        super(mTree, context, datas, defaultExpandLevel);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_land_cate_list, null));
    }

    @Override
    public void onBindViewHolder(final Node<Integer, StatisticalAnalysisMenuListBean> node, RecyclerView.ViewHolder holder, int position) {

        final ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.cb.setOnClickListener(v -> setChecked(node, viewHolder.cb.isChecked()));

        if (node.isChecked()) {
            viewHolder.cb.setChecked(true);
        } else {
            viewHolder.cb.setChecked(false);
        }

        if (node.getIcon() == -1) {
            viewHolder.icon.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.icon.setImageResource(node.getIcon());
        }

        viewHolder.label.setText(node.getName());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public CheckBox cb;

        public TextView label;

        public ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);

            cb = (CheckBox) itemView
                    .findViewById(R.id.cb_check);
            label = (TextView) itemView
                    .findViewById(R.id.tv_text);
            icon = (ImageView) itemView.findViewById(R.id.icon);

        }

    }
}
