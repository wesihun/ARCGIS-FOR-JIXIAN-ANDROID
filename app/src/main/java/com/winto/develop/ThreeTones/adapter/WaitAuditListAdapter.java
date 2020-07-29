package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.bean.ManageListBean;
import com.winto.develop.ThreeTones.util.DateUtil;

import java.util.List;

public class WaitAuditListAdapter extends RecyclerView.Adapter<WaitAuditListAdapter.AuditedViewHolder> {
    private Context context;
    private List<ManageListBean.DataBean> auditedList;
    private OnBtnClickListener listener;

    public WaitAuditListAdapter(Context context, List<ManageListBean.DataBean> auditedList) {
        this.context = context;
        this.auditedList = auditedList;
    }

    @NonNull
    @Override
    public AuditedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AuditedViewHolder(LayoutInflater.from(context).inflate(R.layout.item_wait_audit_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AuditedViewHolder holder, int position) {
        ManageListBean.DataBean manage = getItem(position);
        holder.tv_person.setText(String.format("申请人：%s", manage.getName()));
        holder.tv_department.setText(String.format("申请部门：%s", manage.getDepname()));
        holder.tv_job.setText(String.format("职位：%s", manage.getPostname()));
        holder.tv_purpose.setText(String.format("申请用途：%s", TextUtils.isEmpty(manage.getApplyreason()) ? "未填写申请用途" : manage.getApplyreason()));
        holder.tv_phone.setText(String.format("电话：%s", manage.getPhone()));
        holder.tv_time.setText(String.format("时间：%s", DateUtil.formatTime(manage.getApplytime())));

        switch (manage.getStates()) {
            case 0:
                holder.tv_examine_state.setVisibility(View.VISIBLE);
                holder.tv_examine_state.setText("审核");
                holder.tv_examine_state.setTextColor(ContextCompat.getColor(context, R.color.white));
                holder.tv_examine_state.setBackgroundResource(R.drawable.bg_btn_blue_corner);
                break;
            case 1:
                holder.tv_examine_state.setVisibility(View.VISIBLE);
                holder.tv_examine_state.setText("已审核");
                holder.tv_examine_state.setTextColor(ContextCompat.getColor(context, R.color.orange));
                holder.tv_examine_state.setBackgroundResource(R.drawable.bg_btn_solid_orange_corner);
                break;
            case -1:
                holder.tv_examine_state.setVisibility(View.GONE);
                holder.tv_examine_state.setText("删除");
                holder.tv_examine_state.setTextColor(ContextCompat.getColor(context, R.color.orange));
                holder.tv_examine_state.setBackgroundResource(R.drawable.bg_btn_solid_orange_corner);
                break;
        }

        holder.tv_examine_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(manage);
                }
            }
        });
    }

    private ManageListBean.DataBean getItem(int position) {
        return auditedList.get(position);
    }

    @Override
    public int getItemCount() {
        return auditedList.size();
    }

    static class AuditedViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_person;
        private TextView tv_department;
        private TextView tv_job;
        private TextView tv_purpose;
        private TextView tv_phone;
        private TextView tv_time;
        private TextView tv_apply_state;
        private TextView tv_examine_state;

        AuditedViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_person = itemView.findViewById(R.id.tv_person);
            tv_department = itemView.findViewById(R.id.tv_department);
            tv_job = itemView.findViewById(R.id.tv_job);
            tv_purpose = itemView.findViewById(R.id.tv_purpose);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_apply_state = itemView.findViewById(R.id.tv_apply_state);
            tv_examine_state = itemView.findViewById(R.id.tv_examine_state);
        }
    }

    public interface OnBtnClickListener {
        void onClick(ManageListBean.DataBean manage);
    }

    public void setOnBtnClickListener(OnBtnClickListener listener) {
        this.listener = listener;
    }
}
