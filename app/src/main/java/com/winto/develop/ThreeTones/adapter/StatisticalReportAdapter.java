package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.bean.LandInfoChartBean;

import java.util.List;

public class StatisticalReportAdapter extends RecyclerView.Adapter<StatisticalReportAdapter.ViewHolder> {

    private Context context;
    private List<LandInfoChartBean> chartInfoList;

    public StatisticalReportAdapter(Context context, List<LandInfoChartBean> chartInfoList) {
        this.context = context;
        this.chartInfoList = chartInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_statistical_report_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LandInfoChartBean bean = getItem(position);
        holder.tv_name.setText(bean.getDlmc());
        holder.tv_area.setText(String.valueOf(bean.getArea()));
    }

    public LandInfoChartBean getItem(int position) {
        return chartInfoList.get(position);
    }

    @Override
    public int getItemCount() {
        return chartInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_area;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_area = itemView.findViewById(R.id.tv_area);
        }
    }
}
