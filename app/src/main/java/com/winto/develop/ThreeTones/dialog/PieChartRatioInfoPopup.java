package com.winto.develop.ThreeTones.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.adapter.TownListAdapter;

import java.util.ArrayList;
import java.util.List;

public class PieChartRatioInfoPopup extends PopupWindow {
    private Context context;
    private View parent;
    private View view;

    public PieChartRatioInfoPopup(Context context, View parent) {
        this.context = context;
        this.parent = parent;
        initPopup();
        initView();
        initClick();
    }

    private void initPopup() {
        view = View.inflate(context, R.layout.popup_pie_chart, null);
        setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
    }

    private void initView() {

    }


    private void initClick() {

    }

    public void show() {
        showAsDropDown(parent, 20, 0, Gravity.START);
        update();
    }
}
