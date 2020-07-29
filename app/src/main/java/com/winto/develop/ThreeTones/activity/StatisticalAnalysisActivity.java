package com.winto.develop.ThreeTones.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.Gson;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.adapter.StatisticalAnalysisCateListAdapter;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.Node;
import com.winto.develop.ThreeTones.bean.StatisticalAnalysisBean;
import com.winto.develop.ThreeTones.bean.StatisticalAnalysisMenuListBean;
import com.winto.develop.ThreeTones.bean.TownListBean;
import com.winto.develop.ThreeTones.dialog.ChooseTownPopup;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.DateUtil;
import com.winto.develop.ThreeTones.util.StatusBarHelper;
import com.winto.develop.ThreeTones.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2020/5/20 0020.
 * class note:统计分析
 */
public class StatisticalAnalysisActivity extends BaseActivity {

    private ImageView iv_back;
    private ImageView iv_menu;
    private RelativeLayout rl_title;
    private TextView tv_search_area;
    private PieChart pc_area;
    private PieChart pc_count;
    private LineChart lc_chart;
    private ChooseTownPopup popup;
    private View right_drawer;
    private DrawerLayout dl_layout;
    private ListView lv_land_cate_list;
    private EditText et_input;
    private TextView tv_search;
    private StatisticalAnalysisCateListAdapter statisticalAnalysisCateListAdapter;
    private List<Node<Integer,StatisticalAnalysisMenuListBean>> nodeList;

    private String chooseName;
    private String jsonTree;
    private TextView tv_area;
    private TextView tv_count;
    private TextView tv_change;

    @Override
    protected void initView() {
        iv_back = findViewById(R.id.iv_back);
        iv_menu = findViewById(R.id.iv_menu);
        pc_area = findViewById(R.id.pc_area);
        pc_count = findViewById(R.id.pc_count);
        lc_chart = findViewById(R.id.lc_chart);
        rl_title = findViewById(R.id.rl_title);
        tv_area = findViewById(R.id.tv_area);
        tv_count = findViewById(R.id.tv_count);
        tv_change = findViewById(R.id.tv_change);
        tv_search_area = findViewById(R.id.tv_search_area);
        right_drawer = findViewById(R.id.right_drawer);
        dl_layout = findViewById(R.id.dl_layout);
        lv_land_cate_list = right_drawer.findViewById(R.id.lv_land_cate_list);
        et_input = right_drawer.findViewById(R.id.et_input);
        tv_search = right_drawer.findViewById(R.id.tv_search);


        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl_title.getLayoutParams();
        params.setMargins(0, StatusBarHelper.getStatusBarHeight(), 0, 0);
        rl_title.setLayoutParams(params);
    }

    @Override
    protected void initData() {
        pc_count.setNoDataText("暂无数据");
        pc_area.setNoDataText("暂无数据");
        lc_chart.setNoDataText("暂无数据");

        getMenu();
    }

    @Override
    protected void initAdapter() {
        nodeList = new ArrayList<>();
        statisticalAnalysisCateListAdapter = new StatisticalAnalysisCateListAdapter(this, nodeList, -1, R.drawable.ic_special_folder, R.drawable.ic_special_folder);
        lv_land_cate_list.setAdapter(statisticalAnalysisCateListAdapter);
    }

    public void getNodeParent(Node<Integer,StatisticalAnalysisMenuListBean> node) {
        Node<Integer,StatisticalAnalysisMenuListBean> parent = node.getParent();
        if (parent != null) {
            node.setChecked(true);
            parent.setExpand(true);
            getNodeParent(parent);
        }
    }

    @Override
    protected void initClick() {
        iv_back.setOnClickListener(v -> finish());

        tv_search.setOnClickListener(v -> {
            String key = et_input.getText().toString().trim();
            if (TextUtils.isEmpty(key)) {
                return;
            }
            for (int i = 0; i < nodeList.size(); i++) {
                Node<Integer,StatisticalAnalysisMenuListBean> n = nodeList.get(i);
                if (n.getName().contains(key)) {
                    getNodeParent(n);
                } else {
                    n.setExpand(false);
                }
            }
            statisticalAnalysisCateListAdapter.notifyDataSetChanged();
        });

        statisticalAnalysisCateListAdapter.setOnTreeNodeClickListener((node, position) -> {
            if (node.isLeaf()) {
                jsonTree = new Gson().toJson(node.getBean());
                Log.e("TAG", "onClick: "+jsonTree);
                dl_layout.closeDrawer(right_drawer);

                chooseName = node.getBean().getMenuename();

                tv_area.setText(chooseName + "面积");
                tv_count.setText(chooseName + "数量");
                tv_change.setText(chooseName + "数量变化");
                getAnalysisTotalArea();
                getAnalysisTotalRecord();
                statisticalAnalysisCateListAdapter.setLeafNodeChecked(node);
            } else {
                statisticalAnalysisCateListAdapter.expandOrCollapse(position);
            }
        });

        iv_menu.setOnClickListener(v -> {
            boolean isOpen = dl_layout.isDrawerOpen(right_drawer);
            if (isOpen) {
                dl_layout.closeDrawer(right_drawer);
            } else {
                dl_layout.openDrawer(right_drawer);
            }
        });

        tv_search_area.setOnClickListener(v -> {
            if (popup == null) {
                popup = new ChooseTownPopup(context, tv_search_area);
            }
            popup.show();
            popup.setOnButtonClickListener(new ChooseTownPopup.OnButtonClickListener() {
                @Override
                public void onClearBtnClick() {
                    popup.dismiss();
                }

                @Override
                public void onConfirmBtnClick(TownListBean town, TownListBean.SubAdministrationsBean village, String list, String proviceCode) {
                    tv_search_area.setText(list);
                    popup.dismiss();
                }
            });
        });

        lv_land_cate_list.setOnItemClickListener((parent, view, position, id) -> {
            dl_layout.closeDrawer(right_drawer);
        });
    }

    private void getMenu() {
        HttpAction.getInstance().getAnalysisMenu().subscribe(new BaseObserver<List<StatisticalAnalysisMenuListBean>>() {
            @Override
            public void onSuccess(List<StatisticalAnalysisMenuListBean> bean) {
                if (bean == null || bean.size() == 0) {
                    return;
                }
                recursionCateView(bean);
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private void recursionCateView(List<StatisticalAnalysisMenuListBean> cateList) {

        for (int i = 0; i < cateList.size(); i++) {
            StatisticalAnalysisMenuListBean bean = cateList.get(i);
            nodeList.add(new Node<>(bean.getId(), bean.getParentid(), bean.getMenuename(), bean));
            recursionCateView(cateList.get(i).getSubAnalysisMenue());
        }

        statisticalAnalysisCateListAdapter.addDataAll(nodeList, -1);
    }

    private void getAnalysisTotalArea() {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonTree", jsonTree);
        HttpAction.getInstance().getAnalysisTotalArea(params).subscribe(new BaseObserver<StatisticalAnalysisBean>() {
            @Override
            public void onSuccess(StatisticalAnalysisBean bean) {
                createAreaChart(bean);
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, "暂无数据");
            }
        });
    }

    private void getAnalysisTotalRecord() {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonTree", jsonTree);
        HttpAction.getInstance().getAnalysisTotalRecord(params).subscribe(new BaseObserver<StatisticalAnalysisBean>() {
            @Override
            public void onSuccess(StatisticalAnalysisBean bean) {
                createCountChart(bean);
                createLineChart(bean);
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, "暂无数据");
            }
        });
    }

    private void createAreaChart(StatisticalAnalysisBean bean) {
        List<PieEntry> strings = new ArrayList<>();

        strings.add(new PieEntry((float) bean.getResult(), chooseName + "面积"));
        PieDataSet dataSet = new PieDataSet(strings, "");
        dataSet.setColors(Color.parseColor("#fb6c6c"));

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        pieData.setValueTextSize(10);
        pieData.setValueTextColor(ContextCompat.getColor(context, R.color.white));
        Description description = new Description();
        description.setText("");
        pc_area.setDrawSliceText(false);

        pc_area.setDrawHoleEnabled(true);
        pc_area.setHoleRadius(60f);  //半径
        pc_area.setDescription(description);

        pc_area.setRotationEnabled(false);
        pc_area.setData(pieData);
        pc_area.setNoDataText("暂无数据");
        pc_area.invalidate();
        Legend legend = pc_area.getLegend();//设置比例图
        legend.setWordWrapEnabled(true);
    }

    private void createCountChart(StatisticalAnalysisBean bean) {
        List<PieEntry> strings = new ArrayList<>();

        strings.add(new PieEntry((float) bean.getResult(), chooseName + "数量"));
        PieDataSet dataSet = new PieDataSet(strings, "");
        dataSet.setColors(Color.parseColor("#fcd55f"));

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        pieData.setValueTextSize(10);
        pieData.setValueTextColor(ContextCompat.getColor(context, R.color.white));
        Description description = new Description();
        description.setText("");
        pc_count.setDrawSliceText(false);

        pc_count.setDrawHoleEnabled(true);
        pc_count.setHoleRadius(60f);  //半径
        pc_count.setDescription(description);

        pc_count.setRotationEnabled(false);
        pc_count.setData(pieData);
        pc_count.setNoDataText("暂无数据");
        pc_count.invalidate();
        Legend legend = pc_count.getLegend();//设置比例图
        legend.setWordWrapEnabled(true);
    }

    private void createLineChart(StatisticalAnalysisBean bean) {
        List<Integer> list = new ArrayList<>();
        list.add((int) bean.getResult());
        for (int i = 0; i < 9; i++) {
            list.add(0);
        }
        //显示边界
        lc_chart.setDrawBorders(false);
        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            entries.add(new Entry(i, (float) list.get(i)));
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        //线颜色
        lineDataSet.setColor(Color.parseColor("#F15A4A"));
        //线宽度
        lineDataSet.setLineWidth(1.6f);
        //不显示圆点
        lineDataSet.setDrawCircles(false);
        //线条平滑
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        //设置折线图填充
//        lineDataSet.setDrawFilled(true);
        LineData data = new LineData(lineDataSet);
        //无数据时显示的文字
        lc_chart.setNoDataText("暂无数据");
        //折线图不显示数值
        data.setDrawValues(false);
        //得到X轴
        XAxis xAxis = lc_chart.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(1f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(list.size(), false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum((float) list.size());
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
//        xAxis.setLabelRotationAngle(45);
        //设置X轴值为字符串
        int previewYear = DateUtil.getPreYear();
        int[] yearArray = new int[list.size()];
        for (int i = 0; i < yearArray.length; i++) {
            yearArray[i] = previewYear + i;
        }
        xAxis.setValueFormatter((value, axis) -> {
            int index = (int) value;
            if (index < 0 || index >= yearArray.length) {
                return "";
            } else {
                return yearArray[index] + "";
            }
        });
        //得到Y轴
        YAxis yAxis = lc_chart.getAxisLeft();
        YAxis rightYAxis = lc_chart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setDrawGridLines(false);
        //设置Y轴坐标之间的最小间隔
        yAxis.setGranularity(1);
        //设置y轴的刻度数量
        yAxis.setLabelCount(yearArray.length, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);
        //+1:y轴多一个单位长度，为了好看
//        yAxis.setAxisMaximum(Collections.max(list));

        //y轴
        yAxis.setValueFormatter((value, axis) -> {
            int IValue = (int) value;
            return String.valueOf(IValue);
        });
        //图例：得到Lengend
        Legend legend = lc_chart.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);
        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        lc_chart.setDescription(description);
        lc_chart.setScaleEnabled(false);
        //折线图点的标记
//        MyMarkerView mv = new MyMarkerView(this);
//        lc_chart.setMarker(mv);
        //设置数据
        lc_chart.setData(data);
        //图标刷新
        lc_chart.invalidate();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_statistical_analysis;
    }
}