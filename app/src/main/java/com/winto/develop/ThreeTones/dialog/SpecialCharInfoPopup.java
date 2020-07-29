package com.winto.develop.ThreeTones.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.bean.LayerDataListBean;

import java.util.ArrayList;
import java.util.List;

public class SpecialCharInfoPopup extends PopupWindow {

    private Context context;
    private View parent;
    private View view;

    private PieChart pc_char1;
    private PieChart pc_char2;
    private ImageView iv_bottom;

    public SpecialCharInfoPopup(Context context, View parent) {
        this.context = context;
        this.parent = parent;
        initPopup();
        initView();
        initClick();
    }

    private void initPopup() {
        view = View.inflate(context, R.layout.popup_special_char_info, null);
        pc_char1 = view.findViewById(R.id.pc_char1);
        pc_char2 = view.findViewById(R.id.pc_char2);
        iv_bottom = view.findViewById(R.id.iv_bottom);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setAnimationStyle(R.style.popwin_anim);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
    }

    private void initView() {

    }

    private void initClick() {
        iv_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show(List<LayerDataListBean.ResultBean> specialLayerDataList, List<LayerDataListBean.ResultBean> chooseItemList) {
        showAsDropDown(parent, 0, 0, Gravity.BOTTOM);
        update();
        initPieChar1(specialLayerDataList, chooseItemList);
        initPieChar2(chooseItemList);
    }

    private void initPieChar1(List<LayerDataListBean.ResultBean> specialLayerDataList, List<LayerDataListBean.ResultBean> chooseItemList) {
        float totalArea = 0;
        float chooseArea = 0;
        for (int i = 0; i < specialLayerDataList.size(); i++) {
            totalArea += specialLayerDataList.get(i).getArea();
        }
        for (int i = 0; i < chooseItemList.size(); i++) {
            chooseArea += chooseItemList.get(i).getArea();
        }

        List<PieEntry> strings = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        strings.add(new PieEntry(totalArea - chooseArea, "剩余面积"));
        strings.add(new PieEntry(chooseArea, "选中面积"));
        colors.add(Color.parseColor("#FAD03E"));
        colors.add(Color.parseColor("#F9AB15"));

        PieDataSet dataSet = new PieDataSet(strings, "");
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        pieData.setValueTextSize(10);
        pieData.setValueTextColor(ContextCompat.getColor(context, R.color.white));
        Description description = new Description();
        description.setText("");
        pc_char1.setDrawSliceText(false);
        pc_char1.setDescription(description);
        pc_char1.setHoleRadius(0f);
        pc_char1.setRotationEnabled(false);
        pc_char1.setVerticalFadingEdgeEnabled(true);
        pc_char1.setTransparentCircleRadius(0f);
        pc_char1.setData(pieData);
        pc_char1.invalidate();
        Legend legend = pc_char1.getLegend();//设置比例图
        legend.setWordWrapEnabled(true);
        legend.setXOffset(15f);
    }

    private void initPieChar2(List<LayerDataListBean.ResultBean> chooseItemList) {
        List<PieEntry> strings = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();
        for (int i = 0; i < chooseItemList.size(); i++) {
            strings.add(new PieEntry((float) chooseItemList.get(i).getArea(), chooseItemList.get(i).getBsm()));

        }
        colors.add(Color.parseColor("#fb6c6c"));
        colors.add(Color.parseColor("#30fcb2"));
        colors.add(Color.parseColor("#fda168"));
        colors.add(Color.parseColor("#57c1fe"));
        colors.add(Color.parseColor("#fae23e"));
        PieDataSet dataSet = new PieDataSet(strings, "");
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        pieData.setValueTextSize(10);
        pieData.setValueTextColor(ContextCompat.getColor(context, R.color.white));
        Description description = new Description();
        description.setText("");
        pc_char2.setDrawSliceText(false);
        pc_char2.setDescription(description);
        pc_char2.setHoleRadius(0f);
        pc_char2.setTransparentCircleRadius(0f);
        pc_char2.setRotationEnabled(false);
        pc_char2.setData(pieData);
        if (chooseItemList.size() > 1) {
            pc_char2.setExtraOffsets(0f, 0f, 0f, -7f);
        }
//        pc_char2.setMinimumHeight();
        pc_char2.invalidate();
        Legend legend = pc_char2.getLegend();//设置比例图
        legend.setXOffset(15f);
        legend.setWordWrapEnabled(true);
    }
}