package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.bean.Node;
import com.winto.develop.ThreeTones.bean.StatisticalAnalysisMenuListBean;
import com.winto.develop.ThreeTones.listener.OnTreeNodeClickListener;

import java.util.List;

public class StatisticalAnalysisCateListAdapter extends TreeListViewAdapter<Integer, StatisticalAnalysisMenuListBean> {
    private OnTreeNodeClickListener<Integer, StatisticalAnalysisMenuListBean> onTreeNodeClickListener;

    public StatisticalAnalysisCateListAdapter(Context context, List<Node<Integer, StatisticalAnalysisMenuListBean>> dataList, int defaultExpandLevel, int iconExpand, int iconNoExpand) {
        super(context, dataList, defaultExpandLevel, iconExpand, iconNoExpand);
    }

    @Override
    public View getConvertView(final Node<Integer, StatisticalAnalysisMenuListBean> node, int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_statistical_cate_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.cb_check.setOnClickListener(v -> setChecked(node, viewHolder.cb_check.isChecked()));

        if (node.isExpand()) {
            viewHolder.tv_text.setTextColor(ContextCompat.getColor(mContext, R.color.maincolor));
            expandNode(node, true);
        } else {
            viewHolder.tv_text.setTextColor(ContextCompat.getColor(mContext, R.color.maintextcolor));
            expandNode(node, false);
        }
        if (node.isLeaf()) {
            if (node.isChecked()) {
                viewHolder.tv_text.setTextColor(ContextCompat.getColor(mContext, R.color.maincolor));
            } else {
                viewHolder.tv_text.setTextColor(ContextCompat.getColor(mContext, R.color.maintextcolor));
            }
        }

        if (node.getIcon() == -1) {
            viewHolder.iv_icon.setImageResource(R.drawable.ic_special_word);
        } else {
            viewHolder.iv_icon.setImageResource(node.getIcon());
        }

        viewHolder.itemView.setOnClickListener(v -> {
            if (onTreeNodeClickListener != null) {
                onTreeNodeClickListener.onClick(node, position);
            }
        });

        viewHolder.tv_text.setText(node.getName());
        return convertView;
    }

    private static final class ViewHolder {
        View itemView;
        ImageView iv_icon;
        CheckBox cb_check;
        TextView tv_text;

        ViewHolder(View itemView) {
            this.itemView = itemView;
            iv_icon = itemView.findViewById(R.id.iv_icon);
            cb_check = itemView.findViewById(R.id.cb_check);
            tv_text = itemView.findViewById(R.id.tv_text);
        }
    }

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener<Integer, StatisticalAnalysisMenuListBean> onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }
}
