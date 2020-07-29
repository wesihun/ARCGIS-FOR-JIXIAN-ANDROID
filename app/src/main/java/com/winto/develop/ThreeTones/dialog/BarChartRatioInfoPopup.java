package com.winto.develop.ThreeTones.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.winto.develop.ThreeTones.R;

public class BarChartRatioInfoPopup extends PopupWindow {
    private Context context;
    private View parent;
    private View view;

    public BarChartRatioInfoPopup(Context context, View parent) {
        this.context = context;
        this.parent = parent;
        initPopup();
        initView();
        initAdapter();
        initClick();
    }

    private void initPopup() {
        view = View.inflate(context, R.layout.layout_land_info_bar_chart, null);
        setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
    }

    private void initView() {
    }

    private void initAdapter() {
    }

    private void initClick() {

    }

    public void show() {
        showAsDropDown(parent, 0, 0, Gravity.CENTER);
        update();
    }
}
