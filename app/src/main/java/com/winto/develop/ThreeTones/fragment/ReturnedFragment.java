package com.winto.develop.ThreeTones.fragment;

import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.adapter.ReturnedListAdapter;
import com.winto.develop.ThreeTones.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:已审核
 */

public class ReturnedFragment extends BaseFragment {

    private RelativeLayout rl_title;
    private RecyclerView rv_list;

    private ReturnedListAdapter adapter;
    private List<String> auditedList;

    @Override
    protected void initView() {
        rl_title = findViewById(R.id.rl_title);
        rv_list = findViewById(R.id.rv_list);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAdapter() {
        auditedList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            auditedList.add("");
        }
        adapter = new ReturnedListAdapter(context, auditedList);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        rv_list.setAdapter(adapter);
    }

    @Override
    protected void initClick() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_returned;
    }
}