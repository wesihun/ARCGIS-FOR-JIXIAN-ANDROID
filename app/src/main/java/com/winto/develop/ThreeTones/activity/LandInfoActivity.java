package com.winto.develop.ThreeTones.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.data.Feature;
import com.esri.arcgisruntime.data.FeatureQueryResult;
import com.esri.arcgisruntime.data.QueryParameters;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.geometry.Geometry;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.teaanddogdog.mpandroidchartutil.PieChartFixCover;
import com.winto.develop.ThreeTones.MainApplication;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.adapter.LandCateListAdapter;
import com.winto.develop.ThreeTones.adapter.LandInfoAdapter;
import com.winto.develop.ThreeTones.adapter.MyAxisValueFormatter;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.LandCateBeanNew;
import com.winto.develop.ThreeTones.bean.LandInfoChartBean;
import com.winto.develop.ThreeTones.bean.Node;
import com.winto.develop.ThreeTones.bean.TownListBean;
import com.winto.develop.ThreeTones.constant.ConstantData;
import com.winto.develop.ThreeTones.dialog.ChooseTownPopup;
import com.winto.develop.ThreeTones.dialog.WaitUI;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.CalloutStyle;
import com.winto.develop.ThreeTones.util.DataUtil;
import com.winto.develop.ThreeTones.util.DensityUtil;
import com.winto.develop.ThreeTones.util.StatusBarHelper;
import com.winto.develop.ThreeTones.util.ToastUtil;
import com.winto.develop.ThreeTones.wight.AutoNextLineLinearlayout;
import com.winto.develop.ThreeTones.wight.HorizontalLineView;
import com.winto.develop.ThreeTones.wight.ScaleView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:资源共享
 */

public class LandInfoActivity extends BaseActivity {
    private String[] permissionList = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private String[] colorArray = {"#1cd7ff", "#fcd55f", "#fc615f", "#f2ab01", "#41fad1", "#fcc9c7", "#0391fc", "#1cd7ff", "#394d5c", "#ffff00", "#d17d62", "#c64747", "#1d2088", "#f3302e", "#87c89d", "#fc615f", "#ffb400"};

    private MapView mv_map;
    private ScaleView sv_scale;
    private ImageView iv_back;
    private HorizontalLineView hlv_bar;
    private ImageView iv_exit_full_screen;
    private View include_pie_chart;
    private View include_bar_chart;
    private TextView tv_search_area;
    private View include_fun;
    private ImageView iv_compass;
    private ImageView iv_location;
    private ImageView iv_ruler;
    private ImageView iv_eye2;
    private ImageView iv_full_screen;
    private ImageView iv_tj;
    private View view_land_cate;
    private ImageView iv_top;
    private EditText et_input;
    private TextView tv_search;
    private ListView lv_land_cate_list;
    private RelativeLayout rl_land_cate;
    private BottomSheetBehavior landCateBehavior;
    private boolean isShowRuler = false;

    private LandCateListAdapter landCateListAdapter;
    private LandInfoAdapter landInfoAdapter;
    private List<Node<Integer, LandCateBeanNew>> nodeList;

    private ChooseTownPopup popup;
    private boolean isShowBar = false;
    private boolean isConvenientShowBar = false;
    private List<String> landInfoListBean;

    private LocationDisplay mLocationDisplay;
    private GraphicsOverlay overlayLandInfo;
    private boolean isFullScreen = false;
    private Callout callout;
    private View landInfoPop;

    private FrameLayout fl_container;

    private TextView tv_land_name;
    private ListView lv_land_info_list;

    private String proviceCode = "000000";
    private String proviceName = "集贤县";
    private String landCateName;
    private JSONArray jsonArray;
    private boolean isChooseLeafNode = false;
    private BarChart bc_chart;
    private ImageView iv_cancel_bar;
    private PieChartFixCover pc_chart;
    private ImageView iv_cancel_pie;
    private TextView tv_pie_title;
    private AutoNextLineLinearlayout anl_layout;
    private List<LandInfoChartBean> chartInfoList;

    @Override
    protected void initView() {
        fl_container = findViewById(R.id.fl_container);
        iv_back = findViewById(R.id.iv_back);
        sv_scale = findViewById(R.id.sv_scale);
        iv_exit_full_screen = findViewById(R.id.iv_exit_full_screen);
        include_pie_chart = findViewById(R.id.include_pie_chart);
        pc_chart = include_pie_chart.findViewById(R.id.pie_chart);
        iv_cancel_pie = include_pie_chart.findViewById(R.id.iv_cancel_pie);
        tv_pie_title = include_pie_chart.findViewById(R.id.tv_pie_title);
        anl_layout = include_pie_chart.findViewById(R.id.anl_layout);
        include_bar_chart = findViewById(R.id.include_bar_chart);
        bc_chart = include_bar_chart.findViewById(R.id.bc_chart);
        iv_cancel_bar = include_bar_chart.findViewById(R.id.iv_cancel_bar);
        tv_search_area = findViewById(R.id.tv_search_area);
        ConstraintLayout cl_top = findViewById(R.id.cl_top);
        include_fun = findViewById(R.id.include_fun);
        iv_location = include_fun.findViewById(R.id.iv_location);
        iv_ruler = include_fun.findViewById(R.id.iv_ruler);
        iv_eye2 = include_fun.findViewById(R.id.iv_eye2);
        iv_full_screen = include_fun.findViewById(R.id.iv_full_screen);
        iv_tj = include_fun.findViewById(R.id.iv_tj);
        iv_compass = findViewById(R.id.iv_compass);
        iv_location = findViewById(R.id.iv_location);
        view_land_cate = findViewById(R.id.view_land_cate);
        iv_top = view_land_cate.findViewById(R.id.iv_top);
        et_input = view_land_cate.findViewById(R.id.et_input);
        tv_search = view_land_cate.findViewById(R.id.tv_search);
        hlv_bar = view_land_cate.findViewById(R.id.hlv_bar);
        lv_land_cate_list = view_land_cate.findViewById(R.id.lv_land_cate_list);
        rl_land_cate = findViewById(R.id.rl_land_cate);
        landCateBehavior = BottomSheetBehavior.from(rl_land_cate);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) cl_top.getLayoutParams();
        params.topMargin = StatusBarHelper.getStatusBarHeight();
        cl_top.setLayoutParams(params);

        mv_map = MainApplication.getContext().getMap();
        fl_container.addView(mv_map);
        initMapView();
        initCallOut();
    }

    private void initMapView() {
        overlayLandInfo = new GraphicsOverlay();
        mLocationDisplay = mv_map.getLocationDisplay();
//        mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.RECENTER);
        mLocationDisplay.startAsync();
    }

    @Override
    protected void initData() {
        getMenu();
    }

    @Override
    protected void initAdapter() {
        /*
         * 土地分类信息列表
         * */
        nodeList = new ArrayList<>();
        landCateListAdapter = new LandCateListAdapter(this, nodeList, 1, R.drawable.ic_remove, R.drawable.ic_add);
        lv_land_cate_list.setAdapter(landCateListAdapter);

        landInfoListBean = new ArrayList<>();
        landInfoAdapter = new LandInfoAdapter(context, landInfoListBean);
        lv_land_info_list.setAdapter(landInfoAdapter);
    }

    public void getNodeParent(Node<Integer, LandCateBeanNew> node) {
        Node<Integer, LandCateBeanNew> parent = node.getParent();
        if (parent != null) {
            node.setChecked(true);
            parent.setExpand(true);
            getNodeParent(parent);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initClick() {
        iv_exit_full_screen.setOnClickListener(v -> {
            exitFullScreen();
            isFullScreen = false;
        });

        iv_full_screen.setOnClickListener(v -> {
            if (isFullScreen) {
                isFullScreen = false;
                exitFullScreen();
            } else {
                isFullScreen = true;
                enterFullScreen();
            }
        });

        iv_tj.setOnClickListener(v -> {
            if (chartInfoList == null || chartInfoList.size() == 0) {
                ToastUtil.show(context, "请选择地类");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable("chartInfoList", (Serializable) chartInfoList);
            bundle.putString("name", proviceName + landCateName + "各地类报表（亩）");
            toClass(context, StatisticalReportActivity.class, bundle);
        });

        iv_location.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkPermission(permissionList[0]) || checkPermission(permissionList[1])) {
                    initPermission();
                } else {
                    startLocation();
                }
            } else {
                startLocation();
            }
        });

        iv_ruler.setOnClickListener(v -> {
            if (isShowRuler) {
                sv_scale.setVisibility(View.GONE);
                isShowRuler = false;
            } else {
                sv_scale.setVisibility(View.VISIBLE);
                isShowRuler = true;
            }
        });

        view_land_cate.setOnClickListener(view -> {
        });

        iv_back.setOnClickListener(view -> finish());

        iv_compass.setOnClickListener(v -> mv_map.setViewpointRotationAsync(0));

        mv_map.setOnTouchListener(new DefaultMapViewOnTouchListener(context, mv_map) {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                setSelectedArea(e);
                return true;
            }
        });

        tv_search.setOnClickListener(v -> {
            String key = et_input.getText().toString().trim();
            if (TextUtils.isEmpty(key)) {
                return;
            }
            for (int i = 0; i < nodeList.size(); i++) {
                Node<Integer, LandCateBeanNew> n = nodeList.get(i);
                if (n.getName().contains(key)) {
                    getNodeParent(n);
                } else {
                    n.setExpand(false);
                }
            }
            landCateListAdapter.notifyDataSetChanged();
        });

        iv_top.setOnClickListener(v -> {
            CoordinatorLayout.LayoutParams params1 = (CoordinatorLayout.LayoutParams) rl_land_cate.getLayoutParams();
            if (rl_land_cate.getHeight() > DensityUtil.getScreenHeight(context) - DensityUtil.dip2px(context, 50)) {
                params1.height = DensityUtil.getScreenHeight(context) - DensityUtil.dip2px(context, 50);
                rl_land_cate.setLayoutParams(params1);
            }
            if (landCateBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                landCateBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else if (landCateBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                landCateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        //土地分类
        landCateBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    if (isShowBar) {
                        showBar();
                    }
                    isShowBar = false;
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    CoordinatorLayout.LayoutParams params1 = (CoordinatorLayout.LayoutParams) rl_land_cate.getLayoutParams();
                    if (bottomSheet.getHeight() > DensityUtil.getScreenHeight(context) - DensityUtil.dip2px(context, 50)) {
                        params1.height = DensityUtil.getScreenHeight(context) - DensityUtil.dip2px(context, 50);
                        bottomSheet.setLayoutParams(params1);
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                /*float distance;
                if (slideOffset > 0) {//在peekHeight位置以上 滑动(向上、向下) slideOffset bottomSheet.getHeight() 是展开后的高度的比例
                    distance = 0;
                } else {//在peekHeight位置以下 滑动(向上、向下)  slideOffset 是PeekHeight的高度的比例
                    distance = landCateBehavior.getPeekHeight() * slideOffset;

                }
                if (distance < 0) {
                    if (Math.abs(distance) < 520 - DensityUtil.dip2px(context, 40)) {
                        iv_compass.setTranslationY(-distance);
                    }
                    Log.e("TAG", "onSlide: " + distance);
                }*/
                hlv_bar.setBeanSize((int) (getResources().getDimensionPixelOffset(R.dimen.dp4) * slideOffset));
            }
        });

        tv_search_area.setOnClickListener(view -> {
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
                public void onConfirmBtnClick(TownListBean town, TownListBean.SubAdministrationsBean village, String proviceName, String proviceCode) {
                    tv_search_area.setText(proviceName);
                    popup.dismiss();
                    LandInfoActivity.this.proviceCode = proviceCode;
                    LandInfoActivity.this.proviceName = proviceName;
                    if (jsonArray != null && jsonArray.length() > 0) {
                        showBar();
                    }
/*
                    String bsm = town.getTreeCode().substring(0, 9);
                    QueryParameters query = new QueryParameters();
                    // 类似sql语句的查询 where语句
                    query.setWhereClause("XZQDM = '" + bsm + "'");
                    query.setReturnGeometry(true);
                    // 指定查询的目标图层
                    // 构建查询任务对象QueryTask
                    final ServiceFeatureTable mainServiceFeatureTable = new ServiceFeatureTable(ConstantData.FEATURE_MAP);
                    final ListenableFuture<FeatureQueryResult> featureQueryResult = mainServiceFeatureTable.queryFeaturesAsync(query, ServiceFeatureTable.QueryFeatureFields.LOAD_ALL);//不设置load_all的话，会默认获取三个字段，若加载全部数据，需设置为load_all
                    featureQueryResult.addDoneListener(() -> markSelectedArea(featureQueryResult));*/

                }
            });
        });

        iv_eye2.setOnClickListener(v -> {
            if (isConvenientShowBar) {
                hiddenBar();
                isConvenientShowBar = false;
            } else {
                if (jsonArray == null || jsonArray.length() == 0) {
                    ToastUtil.show(context, "请选择土地类别");
                    return;
                }
                showBar();
                isConvenientShowBar = true;
            }
        });

        iv_cancel_pie.setOnClickListener(v -> {
            include_pie_chart.setVisibility(View.INVISIBLE);
            if (include_bar_chart.getVisibility() == View.INVISIBLE) {
                isConvenientShowBar = false;
            }
        });

        iv_cancel_bar.setOnClickListener(v -> {
            include_bar_chart.setVisibility(View.INVISIBLE);
            if (include_pie_chart.getVisibility() == View.INVISIBLE) {
                isConvenientShowBar = false;
            }
        });

        landCateListAdapter.setOnTreeNodeClickListener((node, position) -> {
            jsonArray = new JSONArray();
            landCateName = node.getName();
            tv_pie_title.setText(String.format("%s比例图", node.getName()));
            LandCateBeanNew cate = node.getBean();
            if (node.getLevel() == 1) {
                for (int i = 0; i < cate.getSubMenue().size(); i++) {
                    LandCateBeanNew subCate = cate.getSubMenue().get(i);
                    for (int l = 0; l < subCate.getSubMenue().size(); l++) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("menueid", subCate.getSubMenue().get(l).getMenueid());
                            jsonObject.put("menuename", subCate.getSubMenue().get(l).getMenuename());
                            jsonObject.put("firstcategoryCode", subCate.getSubMenue().get(l).getFirstcategory());
                            jsonObject.put("secondcategoryCode", subCate.getSubMenue().get(l).getSecondcategory());
                            jsonObject.put("secondcategoryName", subCate.getSubMenue().get(l).getMenuename());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        jsonArray.put(jsonObject);
                    }
                }
                isChooseLeafNode = false;
            } else if (node.getLevel() == 2) {
                for (int l = 0; l < cate.getSubMenue().size(); l++) {
                    LandCateBeanNew subCate = cate.getSubMenue().get(l);
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("menueid", subCate.getMenueid());
                        jsonObject.put("menuename", subCate.getMenuename());
                        jsonObject.put("firstcategoryCode", subCate.getFirstcategory());
                        jsonObject.put("secondcategoryCode", subCate.getSecondcategory());
                        jsonObject.put("secondcategoryName", subCate.getMenuename());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(jsonObject);
                }
                isChooseLeafNode = false;
            } else if (node.getLevel() == 3) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("menueid", cate.getMenueid());
                    jsonObject.put("menuename", cate.getMenuename());
                    jsonObject.put("firstcategoryCode", cate.getFirstcategory());
                    jsonObject.put("secondcategoryCode", cate.getSecondcategory());
                    jsonObject.put("secondcategoryName", cate.getMenuename());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonArray.put(jsonObject);
                isChooseLeafNode = true;

            }
            landCateListAdapter.setLeafNodeChecked(node);
            isShowBar = true;
            landCateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        });

        mv_map.addMapScaleChangedListener(mapScaleChangedEvent -> sv_scale.refreshScaleView(mv_map));
    }

    private void startLocation() {

        com.esri.arcgisruntime.geometry.Point point = mLocationDisplay.getMapLocation();

        Log.e("TAG", "startLocation: " + point.toString());
        mv_map.setViewpointAsync(new Viewpoint(point, 3000), 1.0f);
        if (mv_map.getMapScale() == 3000) {
            addLocationImage(point);
        } else {
            mv_map.addMapScaleChangedListener(mapScaleChangedEvent -> {
                if (mapScaleChangedEvent.getSource().getMapScale() == 3000) {
                    addLocationImage(point);
                }
            });
        }
    }

    private void setSelectedArea(MotionEvent e) {
        mv_map.getGraphicsOverlays().clear();
        Point screenPoint = new Point(Math.round(e.getX()), Math.round(e.getY()));
        final com.esri.arcgisruntime.geometry.Point clickPoint = mv_map.screenToLocation(screenPoint);
        QueryParameters query = new QueryParameters();
        // 设置空间几何对象
        query.setGeometry(clickPoint);
        final ServiceFeatureTable table = new ServiceFeatureTable(ConstantData.FEATURE_MAP);
        //设置为load_all 加载全部数据
        final ListenableFuture<FeatureQueryResult> future = table.queryFeaturesAsync(query, ServiceFeatureTable.QueryFeatureFields.LOAD_ALL);

        mv_map.setViewpointAsync(new Viewpoint(clickPoint, mv_map.getMapScale()), 0.4f);
        future.addDoneListener(() -> markSelectedArea(future, clickPoint));
    }

    private void initCallOut() {
        callout = mv_map.getCallout();
        //自定义的布局
        landInfoPop = LayoutInflater.from(context).inflate(R.layout.layout_land_info_pop, null);
        callout.setStyle(CalloutStyle.getCalloutStyle());
        tv_land_name = landInfoPop.findViewById(R.id.tv_land_name);
        ImageView iv_land_info_cancel = landInfoPop.findViewById(R.id.iv_land_info_cancel);
        lv_land_info_list = landInfoPop.findViewById(R.id.lv_land_info_list);
        iv_land_info_cancel.setOnClickListener(v -> {
            callout.dismiss();
            mv_map.getGraphicsOverlays().remove(overlayLandInfo);
        });
    }

    private void markSelectedArea(ListenableFuture<FeatureQueryResult> featureQueryResult) {
        try {
            FeatureQueryResult featureResult = featureQueryResult.get();
            for (Object element : featureResult) {
                if (element instanceof Feature) {
                    Feature mFeatureGraphic = (Feature) element;
                    Geometry geometry = mFeatureGraphic.getGeometry();
//                    SimpleLineSymbol lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.DASH, Color.GREEN, 1);
//                    SimpleFillSymbol fillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, Color.GREEN, lineSymbol);
//                    Graphic fillGraphic = new Graphic(geometry, fillSymbol);
//                    overlayLandInfo = new GraphicsOverlay();
//                    overlayLandInfo.getGraphics().add(fillGraphic);
                    mv_map.setViewpointAsync(new Viewpoint(geometry.getExtent().getCenter(), 6000), 0.8f);
                    mv_map.getGraphicsOverlays().add(overlayLandInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void markSelectedArea(ListenableFuture<FeatureQueryResult> featureQueryResult, com.esri.arcgisruntime.geometry.Point clickPoint) {
        try {
            landInfoListBean.clear();
            FeatureQueryResult featureResult = featureQueryResult.get();
            for (Object element : featureResult) {
                if (element instanceof Feature) {
                    Feature mFeatureGraphic = (Feature) element;
                    Geometry geometry = mFeatureGraphic.getGeometry();
                    SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.DIAMOND, Color.RED, 0);
                    SimpleLineSymbol lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.DASH, Color.GREEN, 0);
                    SimpleFillSymbol fillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, Color.RED, lineSymbol);
                    Graphic pointGraphic = new Graphic(clickPoint, pointSymbol);
                    Graphic fillGraphic = new Graphic(geometry, fillSymbol);
                    overlayLandInfo = new GraphicsOverlay();
                    overlayLandInfo.getGraphics().add(pointGraphic);
                    overlayLandInfo.getGraphics().add(fillGraphic);

                    mv_map.getGraphicsOverlays().add(overlayLandInfo);

                    landInfoListBean.addAll(DataUtil.getLandInfoList(featureQueryResult.get()));
                    landInfoAdapter.notifyDataSetChanged();
                    tv_land_name.setText((String) featureQueryResult.get().iterator().next().getAttributes().get("dlmc"));
                    callout.show(landInfoPop, clickPoint);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getMenu() {
        HttpAction.getInstance().getMenu().subscribe(new BaseObserver<List<LandCateBeanNew>>() {
            @Override
            public void onSuccess(List<LandCateBeanNew> bean) {
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

    private void recursionCateView(List<LandCateBeanNew> cateList) {

        for (int i = 0; i < cateList.size(); i++) {
            nodeList.add(new Node<>(cateList.get(i).getMenueid(), cateList.get(i).getParentmenueid(), cateList.get(i).getMenuename(), cateList.get(i)));
            recursionCateView(cateList.get(i).getSubMenue());
        }

        landCateListAdapter.addDataAll(nodeList, 1);
    }

    private void createBarChart(List<LandInfoChartBean> chartInfoList) {
        bc_chart.setDrawBarShadow(false);
        bc_chart.setDrawValueAboveBar(true);
        bc_chart.getDescription().setEnabled(false);
        bc_chart.setMaxVisibleValueCount(chartInfoList.size());
        bc_chart.setPinchZoom(false);
        bc_chart.setDrawGridBackground(false);
        bc_chart.setBorderColor(ContextCompat.getColor(context, R.color.secondcolor));

        //自定义坐标轴适配器，配置在X轴，xAxis.setValueFormatter(xAxisFormatter);
        final String[] values = new String[chartInfoList.size()];
        ArrayList<BarEntry> yValueList = new ArrayList<>();
        for (int i = 0; i < chartInfoList.size(); i++) {
            String dlmc = chartInfoList.get(i).getDlmc();
            if (dlmc.length() > 4) {
                dlmc = dlmc.substring(0, 4) + "...";
            }
            values[i] = dlmc;
            yValueList.add(new BarEntry(i, (float) chartInfoList.get(i).getArea()));
        }

        IAxisValueFormatter formatter = (value, axis) -> {
            int index = (int) value;
            if (index < 0 || index >= values.length) {
                return "";
            } else {
                return values[index];
            }
        };

        XAxis xAxis = bc_chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(chartInfoList.size(), false);
        xAxis.setLabelRotationAngle(-40);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(8);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);

        //自定义坐标轴适配器，配置在Y轴。leftAxis.setValueFormatter(custom);
        IAxisValueFormatter custom = new MyAxisValueFormatter();
        //获取到图形左边的Y轴
        YAxis leftAxis = bc_chart.getAxisLeft();
        leftAxis.setLabelCount(6, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f);

        //获取到图形右边的Y轴，并设置为不显示
        bc_chart.getAxisRight().setEnabled(false);

        //图例设置
        Legend legend = bc_chart.getLegend();
        legend.setEnabled(false);

        BarDataSet set1 = new BarDataSet(yValueList, null);
        set1.setDrawIcons(false);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(9f);
        data.setBarWidth(0.6f);

        bc_chart.setData(data);
//        barChart.setScaleXEnabled(true);
        bc_chart.animateY(800);
        bc_chart.invalidate();
        bc_chart.setNoDataText("暂无数据");
        bc_chart.getData().notifyDataChanged();
        bc_chart.notifyDataSetChanged();
    }

    private void createPieChart(List<LandInfoChartBean> chartInfoList) {
        anl_layout.removeAllViews();
        Legend legend = pc_chart.getLegend();//设置图例
        legend.setEnabled(false);
        legend.setTextSize(8);
//        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setWordWrapEnabled(true);
        List<PieEntry> strings = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();
        for (int i = 0; i < chartInfoList.size(); i++) {
            String dlmc = chartInfoList.get(i).getDlmc();
            strings.add(new PieEntry((float) chartInfoList.get(i).getArea(), dlmc));
            if (i > colorArray.length - 1) {
                colors.add(Color.parseColor(colorArray[i - colorArray.length]));
            } else {
                colors.add(Color.parseColor(colorArray[i]));
            }
            View view = LayoutInflater.from(context).inflate(R.layout.item_lenged_list, null);
            ImageView imageView = view.findViewById(R.id.iv_color);
            TextView textView = view.findViewById(R.id.tv_name);
            imageView.setBackgroundColor(Color.parseColor(colorArray[i]));
            textView.setText(dlmc);
            anl_layout.addView(view);
        }
        PieDataSet dataSet = new PieDataSet(strings, "");
        dataSet.setColors(colors);
        dataSet.setValueLinePart1OffsetPercentage(80f);
        dataSet.setValueLinePart1Length(0.3f);
        dataSet.setValueLinePart2Length(0.4f);
        dataSet.setValueLineWidth(0.5f);
        dataSet.setValueLineColor(ContextCompat.getColor(context, R.color.secondtextcolor));
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(10);
        pieData.setValueTextColor(ContextCompat.getColor(context, R.color.maintextcolor));
        Description description = new Description();
        description.setText("");
        pc_chart.setDrawSliceText(false);
        pc_chart.setDrawHoleEnabled(true);
        pc_chart.setHoleRadius(60f);  //半径
        pc_chart.setDescription(description);

        pc_chart.setRotationEnabled(true);
        pc_chart.setData(pieData);
        pc_chart.setUsePercentValues(true);
        pc_chart.setNoDataText("暂无数据");
        pc_chart.setExtraOffsets(0, 3, 0, 3);
        pc_chart.invalidate();
    }

    @Override
    public void onBackPressed() {
        if (landCateBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            landCateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            finish();
        }
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission(permissionList[0]) || checkPermission(permissionList[1])) {
                ActivityCompat.requestPermissions(this, permissionList, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                if (shouldShowRequestPermissionRationale(permissions[0])) {
                    // 循环申请权限
                    initPermission();
                } else {
                    ToastUtil.show(context, "请给与app相应权限，否则将无法正常使用");
                }
            } else {
                startLocation();
            }
        }
    }

    private void enterFullScreen() {
        include_fun.setVisibility(View.INVISIBLE);
        include_fun.setAnimation(AnimationUtils.makeOutAnimation(context, true));

        iv_compass.setVisibility(View.INVISIBLE);
        iv_compass.setAnimation(AnimationUtils.makeOutAnimation(context, true));

        iv_exit_full_screen.setVisibility(View.VISIBLE);
        iv_exit_full_screen.setAnimation(AnimationUtils.makeInAnimation(context, false));

        landCateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void exitFullScreen() {
        include_fun.setVisibility(View.VISIBLE);
        include_fun.setAnimation(AnimationUtils.makeInAnimation(context, false));

        iv_compass.setVisibility(View.VISIBLE);
        iv_compass.setAnimation(AnimationUtils.makeInAnimation(context, false));

        iv_exit_full_screen.setVisibility(View.INVISIBLE);
        iv_exit_full_screen.setAnimation(AnimationUtils.makeOutAnimation(context, true));
    }

    private void addLocationImage(com.esri.arcgisruntime.geometry.Point centerPoint) {
        //在定位的位置添加icon
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_pos);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(bitmapDrawable);
        pictureMarkerSymbol.setOffsetY(DensityUtil.px2dip(context, 57));

        Graphic imgGraphic = new Graphic(centerPoint, pictureMarkerSymbol);
        GraphicsOverlay graphicsOverlay_1 = new GraphicsOverlay();
        graphicsOverlay_1.getGraphics().add(imgGraphic);
        mv_map.getGraphicsOverlays().add(graphicsOverlay_1);
    }

    private void showBar() {
        WaitUI.show(context);
        isConvenientShowBar = true;
        /*
         * 如果选择的是叶子节点 则仅展示柱状图
         */
        Map<String, Object> params = new HashMap<>();
        params.put("jsonMenue", jsonArray);
        params.put("proviceCode", proviceCode);
        HttpAction.getInstance().getSecondCategoryCode(params).subscribe(new BaseObserver<List<LandInfoChartBean>>() {
            @Override
            public void onSuccess(List<LandInfoChartBean> charInfoList) {
                WaitUI.cancel();
                chartInfoList = charInfoList;
                if (isChooseLeafNode) {
                    createBarChart(charInfoList);
                    include_pie_chart.setVisibility(View.GONE);
                    include_bar_chart.setVisibility(View.VISIBLE);
                } else {
                    createBarChart(charInfoList);
                    createPieChart(charInfoList);
                    include_pie_chart.setVisibility(View.VISIBLE);
                    include_bar_chart.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(String message) {
                WaitUI.cancel();
                ToastUtil.show(context, message);
            }
        });

    }

    private void hiddenBar() {
        include_pie_chart.setVisibility(View.INVISIBLE);
        include_bar_chart.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        callout.dismiss();
        mv_map.getGraphicsOverlays().clear();
        fl_container.removeView(mv_map);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_land_info;
    }
}