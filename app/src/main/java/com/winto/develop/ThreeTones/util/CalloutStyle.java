package com.winto.develop.ThreeTones.util;

import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.esri.arcgisruntime.mapping.view.Callout;
import com.winto.develop.ThreeTones.MainApplication;
import com.winto.develop.ThreeTones.R;

public class CalloutStyle {

    public static Callout.Style getCalloutStyle() {
        Callout.Style style = new Callout.Style(MainApplication.getContext());
        style.setMaxWidth(300); //设置最大宽度
        style.setMaxHeight(200);  //设置最大高度
        style.setMinWidth(200);  //设置最小宽度
        style.setMinHeight(100);  //设置最小高度
        style.setBorderWidth(2); //设置边框宽度
        style.setBorderColor(ContextCompat.getColor(MainApplication.getContext(), R.color.maincolor)); //设置边框颜色
        style.setBackgroundColor(Color.WHITE); //设置背景颜色
        style.setCornerRadius(8); //设置圆角半径
        //style.setLeaderLength(50); //设置指示性长度
        //style.setLeaderWidth(5); //设置指示性宽度
        style.setLeaderPosition(Callout.Style.LeaderPosition.LOWER_MIDDLE); //设置指示性位置
        return style;
    }
}
