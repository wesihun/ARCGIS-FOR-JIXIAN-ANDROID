package com.winto.develop.ThreeTones.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;

import java.util.List;

public class ReturnedListAdapter extends RecyclerView.Adapter<ReturnedListAdapter.AuditedViewHolder> {
    private Context context;
    private List<String> auditedList;

    public ReturnedListAdapter(Context context, List<String> auditedList) {
        this.context = context;
        this.auditedList = auditedList;
    }

    @NonNull
    @Override
    public AuditedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AuditedViewHolder(LayoutInflater.from(context).inflate(R.layout.item_returned_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AuditedViewHolder holder, int position) {

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
        private TextView tv_delete;

        AuditedViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_person = itemView.findViewById(R.id.tv_person);
            tv_department = itemView.findViewById(R.id.tv_department);
            tv_job = itemView.findViewById(R.id.tv_job);
            tv_purpose = itemView.findViewById(R.id.tv_purpose);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_apply_state = itemView.findViewById(R.id.tv_apply_state);
            tv_delete = itemView.findViewById(R.id.tv_delete);
        }
    }
}
