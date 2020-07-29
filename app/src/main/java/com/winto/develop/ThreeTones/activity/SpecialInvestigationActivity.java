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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.data.Feature;
import com.esri.arcgisruntime.data.FeatureQueryResult;
import com.esri.arcgisruntime.data.QueryParameters;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.geometry.Geometry;
import com.esri.arcgisruntime.layers.ArcGISMapImageLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedEvent;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedListener;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.winto.develop.ThreeTones.CustomDataSource;
import com.winto.develop.ThreeTones.MainApplication;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.adapter.SpecialCateAdapter1;
import com.winto.develop.ThreeTones.adapter.SpecialCateAdapter2;
import com.winto.develop.ThreeTones.adapter.SpecialCateAdapter3;
import com.winto.develop.ThreeTones.adapter.SpecialLayerDataListAdapter;
import com.winto.develop.ThreeTones.base.BaseActivity;
import com.winto.develop.ThreeTones.base.BaseObserver;
import com.winto.develop.ThreeTones.bean.LayerDataListBean;
import com.winto.develop.ThreeTones.bean.SpecialMenuUpdateListBean;
import com.winto.develop.ThreeTones.constant.ConstantData;
import com.winto.develop.ThreeTones.dialog.SearchDialog;
import com.winto.develop.ThreeTones.dialog.SpecialCharInfoPopup;
import com.winto.develop.ThreeTones.dialog.WaitUI;
import com.winto.develop.ThreeTones.http.HttpAction;
import com.winto.develop.ThreeTones.util.DensityUtil;
import com.winto.develop.ThreeTones.util.StatusBarHelper;
import com.winto.develop.ThreeTones.util.ToastUtil;
import com.winto.develop.ThreeTones.wight.HorizontalLineView;
import com.winto.develop.ThreeTones.wight.ScaleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:专项调查
 */

public class SpecialInvestigationActivity extends BaseActivity {

    private String[] permissionList = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    private View view_top;
    private MapView mv_map;
    private ImageView iv_back;
    private HorizontalLineView hlv_bar;
    private ImageView iv_exit_full_screen;
    private View include_table_info;
    private ImageView iv_cancel;
    private ImageView iv_bar_char;
    private EditText et_search;
    private TextView tv_cancel_search;
    private RecyclerView rv_table_list;

    private ConstraintLayout cl_top;
    private View include_fun;
    private ImageView iv_compass;
    private ImageView iv_location;
    private ImageView iv_ruler;
    private ImageView iv_full_screen;
    private View view_land_cate;
    private ProgressBar pb_loading;
    private LinearLayout ll_container;
    private RelativeLayout rl_expand_land_cate;
    private RelativeLayout rl_search;
    private RecyclerView rv_special_cate1;
    private RecyclerView rv_special_cate2;
    private RecyclerView rv_special_cate3;
    private RelativeLayout rl_land_cate;
    private BottomSheetBehavior landCateBehavior;
    private ScaleView sv_scale;

    private CustomDataSource dataSource;//定义一个DataSource的成员变量

    private boolean isShowBar = false;

    private LocationDisplay mLocationDisplay;
    private GraphicsOverlay overlayLandInfo;
    private boolean isFullScreen = false;

    private FrameLayout fl_container;

    private float downY;

    private SpecialLayerDataListAdapter specialLayerDataListAdapter;
    private List<LayerDataListBean.ResultBean> specialLayerDataList;
    private List<LayerDataListBean.ResultBean> allSpecialLayerDataList;
    private boolean isShowRuler = false;
    private String areaType;

    @Override
    protected void initView() {
        view_top = findViewById(R.id.view_top);
        fl_container = findViewById(R.id.fl_container);
        iv_back = findViewById(R.id.iv_back);
        iv_exit_full_screen = findViewById(R.id.iv_exit_full_screen);
        include_table_info = findViewById(R.id.include_table_info);
        iv_cancel = include_table_info.findViewById(R.id.iv_cancel);
        iv_bar_char = include_table_info.findViewById(R.id.iv_bar_char);
        et_search = include_table_info.findViewById(R.id.et_input);
        tv_cancel_search = include_table_info.findViewById(R.id.tv_cancel_search);
        rv_table_list = include_table_info.findViewById(R.id.rv_table_list);
        cl_top = findViewById(R.id.cl_top);

        include_fun = findViewById(R.id.include_fun);
        iv_location = include_fun.findViewById(R.id.iv_location);
        iv_ruler = include_fun.findViewById(R.id.iv_ruler);
        iv_full_screen = include_fun.findViewById(R.id.iv_full_screen);

        iv_compass = findViewById(R.id.iv_compass);
        iv_location = findViewById(R.id.iv_location);
        view_land_cate = findViewById(R.id.view_land_cate);
        rl_expand_land_cate = findViewById(R.id.rl_expand_land_cate);
        rl_search = view_land_cate.findViewById(R.id.rl_search);
        pb_loading = view_land_cate.findViewById(R.id.pb_loading);
        ll_container = view_land_cate.findViewById(R.id.ll_container);
        hlv_bar = view_land_cate.findViewById(R.id.hlv_bar);
        rv_special_cate1 = view_land_cate.findViewById(R.id.rv_special_cate1);
        rv_special_cate2 = view_land_cate.findViewById(R.id.rv_special_cate2);
        rv_special_cate3 = view_land_cate.findViewById(R.id.rv_special_cate3);
        rl_land_cate = findViewById(R.id.rl_land_cate);
        sv_scale = findViewById(R.id.sv_scale);
        landCateBehavior = BottomSheetBehavior.from(rl_land_cate);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) cl_top.getLayoutParams();
        params.topMargin = StatusBarHelper.getStatusBarHeight();
        cl_top.setLayoutParams(params);

        mv_map = MainApplication.getContext().getMap1();

        fl_container.addView(mv_map);
        initMapView();

        ll_container.setVisibility(View.GONE);
        pb_loading.setVisibility(View.VISIBLE);
    }

    private void initMapView() {
        overlayLandInfo = new GraphicsOverlay();

        mLocationDisplay = mv_map.getLocationDisplay();
//        mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.RECENTER);
        mLocationDisplay.startAsync();
    }

    @Override
    protected void initData() {
        getSpecialMenu();
    }

    private SpecialCateAdapter1 adapter1;
    private SpecialCateAdapter2 adapter2;
    private SpecialCateAdapter3 adapter3;

    private List<SpecialMenuUpdateListBean.SubSpecialMenueBeanX> specialMenuList2;
    private List<SpecialMenuUpdateListBean.SubSpecialMenueBeanX.SubSpecialMenueBean> specialMenuList3;

    @Override
    protected void initAdapter() {
        specialMenuList = new ArrayList<>();
        adapter1 = new SpecialCateAdapter1(context, specialMenuList);
        rv_special_cate1.setLayoutManager(new LinearLayoutManager(context));
        rv_special_cate1.setAdapter(adapter1);

        specialMenuList2 = new ArrayList<>();
        adapter2 = new SpecialCateAdapter2(context, specialMenuList2);
        rv_special_cate2.setLayoutManager(new LinearLayoutManager(context));
        rv_special_cate2.setAdapter(adapter2);

        specialMenuList3 = new ArrayList<>();
        adapter3 = new SpecialCateAdapter3(context, specialMenuList3);
        rv_special_cate3.setLayoutManager(new LinearLayoutManager(context));
        rv_special_cate3.setAdapter(adapter3);

        specialLayerDataList = new ArrayList<>();
        allSpecialLayerDataList = new ArrayList<>();
        specialLayerDataListAdapter = new SpecialLayerDataListAdapter(context, specialLayerDataList);
        rv_table_list.setLayoutManager(new LinearLayoutManager(context));
        rv_table_list.setAdapter(specialLayerDataListAdapter);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initClick() {
        tv_cancel_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setText("");
                hideSoftInput();
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                specialLayerDataList.clear();
                String key = et_search.getText().toString().trim();
                if (TextUtils.isEmpty(key)) {
                    specialLayerDataList.addAll(allSpecialLayerDataList);
                    tv_cancel_search.setVisibility(View.GONE);
                } else {
                    tv_cancel_search.setVisibility(View.VISIBLE);
                    for (int i = 0; i < allSpecialLayerDataList.size(); i++) {
                        LayerDataListBean.ResultBean layer = allSpecialLayerDataList.get(i);
                        if (layer.getName().contains(key) || layer.getBsm().contains(key)) {
                            specialLayerDataList.add(layer);
                        }
                    }
                }
                specialLayerDataListAdapter.refreshChooseItem();
                specialLayerDataListAdapter.notifyDataSetChanged();
                graphicsOverlayMap.clear();
                mv_map.getGraphicsOverlays().clear();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
//                landCateBehavior.setHideable(true);
//                setSelectedArea(e);
                return true;
            }
        });

        rl_search.setOnClickListener(v -> new SearchDialog(context).show());

        //土地分类
        landCateBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    if (isShowBar) {
                        getLayerData();
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
                    if (landCateBehavior.getPeekHeight() - DensityUtil.dip2px(context, 35) > -distance) {
                        include_table_info.setTranslationY(-distance - (include_table_info.getHeight() / 2 + DensityUtil.dip2px(context, 35)));
                    }
                }

                Log.e("TAG", "onSlide: " + (-distance));
                hlv_bar.setBeanSize((int) (getResources().getDimensionPixelOffset(R.dimen.dp4) * slideOffset));*/
            }
        });

        rl_expand_land_cate.setOnTouchListener((v, event) -> {
            float y = event.getY();
            if (landCateBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //将按下时的坐标存储
                        downY = y;
                        break;
                    case MotionEvent.ACTION_UP:
                        //获取到距离差
                        float dy = y - downY;
                        //防止是按下也判断
                        if (Math.abs(dy) > 5 && getOrientation(dy) == 't') {
                            //通过距离差判断方向
                            landCateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        } else {
                            landCateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }
                        break;
                }
            }
            return true;
        });

        iv_cancel.setOnClickListener(v -> include_table_info.setVisibility(View.GONE));

        iv_bar_char.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 弹出顶部的popwin
                showSpecialCharInfoPopup();
            }
        });

        mv_map.addMapScaleChangedListener(new MapScaleChangedListener() {
            @Override
            public void mapScaleChanged(MapScaleChangedEvent mapScaleChangedEvent) {
                sv_scale.refreshScaleView(mv_map);
            }
        });

        adapter1.setOnItemClickListener((user, position) -> {
            adapter1.setClickColor(position);
            adapter2.setClickColor(-1);
            setSpecialCate2(user.getSubSpecialMenue());
        });

        adapter2.setOnItemClickListener((user, position) -> {
            adapter2.setClickColor(position);
            adapter3.setClickColor(-1);
            if (user.getSubSpecialMenue() == null || user.getSubSpecialMenue().size() == 0) {
                //TODO 弹出图表信息
                isShowBar = true;
                menuJsonStr = new Gson().toJson(user);
                areaType = user.getType();
                ArcGISMap arcGISMap = MainApplication.getContext().getArcGISMap();
                servicePath = ConstantData.MAP_IP + user.getServerpath();
                ArcGISMapImageLayer layer2 = new ArcGISMapImageLayer(servicePath);
                arcGISMap.getOperationalLayers().add(layer2);

                mv_map.setMap(arcGISMap);
                mv_map.getGraphicsOverlays().clear();
                landCateBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                specialLayerDataListAdapter.refreshChooseItem();

                if ("polyline".equals(areaType)) {
                    iv_bar_char.setVisibility(View.GONE);
                } else {
                    iv_bar_char.setVisibility(View.VISIBLE);
                }
            } else {
                setSpecialCate3(user.getSubSpecialMenue());
            }
        });

        adapter3.setOnItemClickListener((user, position) -> {
            adapter3.setClickColor(position);
            //TODO 弹出图表信息
//            isShowBar = true;
//            menuJsonStr = new Gson().toJson(user);
            landCateBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        });

        specialLayerDataListAdapter.setOnCbClickListener((bean, position, isCheck) -> {
            String bsm = bean.getBsm();
            QueryParameters query = new QueryParameters();
            // 类似sql语句的查询 where语句
            query.setWhereClause("BSM = '" + bsm + "'");
            query.setReturnGeometry(true);
            // 指定查询的目标图层
            String queryUrl = servicePath + "/0";
            // 构建查询任务对象QueryTask
            final ServiceFeatureTable mainServiceFeatureTable = new ServiceFeatureTable(queryUrl);
            final ListenableFuture<FeatureQueryResult> featureQueryResult = mainServiceFeatureTable.queryFeaturesAsync(query, ServiceFeatureTable.QueryFeatureFields.LOAD_ALL);//不设置load_all的话，会默认获取三个字段，若加载全部数据，需设置为load_all
            featureQueryResult.addDoneListener(() -> markSelectedArea(featureQueryResult, position, isCheck));
        });
    }

    Map<Integer, GraphicsOverlay> graphicsOverlayMap = new HashMap<>();

    private void markSelectedArea(ListenableFuture<FeatureQueryResult> featureQueryResult, int position, boolean isCheck) {
        try {
            if (isCheck) {
                FeatureQueryResult featureResult = featureQueryResult.get();

                for (Object element : featureResult) {
                    Log.e("TAG", "markSelectedArea: " + element);
                    if (element instanceof Feature) {
                        Feature mFeatureGraphic = (Feature) element;
                        Log.e("TAG", "markSelectedArea: " + mFeatureGraphic);
                        Geometry geometry = mFeatureGraphic.getGeometry();
                        SimpleLineSymbol lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.GREEN, 2);
                        SimpleFillSymbol fillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, Color.GREEN, null);
                        Graphic fillGraphic;
                        if ("polyline".equals(areaType)) {
                            fillGraphic = new Graphic(geometry, lineSymbol);
                        } else {
                            fillGraphic = new Graphic(geometry, fillSymbol);
                        }
                        overlayLandInfo = new GraphicsOverlay();
                        overlayLandInfo.getGraphics().add(fillGraphic);
                        mv_map.setViewpointAsync(new Viewpoint(geometry.getExtent().getCenter(), 6000), 0.8f);
                        mv_map.getGraphicsOverlays().add(overlayLandInfo);
                        graphicsOverlayMap.put(position, overlayLandInfo);
                    }
                }
            } else {
                mv_map.getGraphicsOverlays().remove(graphicsOverlayMap.remove(position));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String servicePath;
    private SpecialCharInfoPopup specialCharInfoPopup;

    private void showSpecialCharInfoPopup() {
        if (specialCharInfoPopup == null) {
            specialCharInfoPopup = new SpecialCharInfoPopup(context, view_top);
        }
        if (specialLayerDataListAdapter.getChooseItemList().size() == 0) {
//            ToastUtil.show(context,"");
            return;
        }
        specialCharInfoPopup.show(specialLayerDataList, specialLayerDataListAdapter.getChooseItemList());
    }

    private String menuJsonStr;

    private void getLayerData() {
        WaitUI.show(context);
        Map<String, Object> params = new HashMap<>();
        params.put("jsonTree", menuJsonStr);
        HttpAction.getInstance().getLayerData(params).subscribe(new BaseObserver<LayerDataListBean>() {
            @Override
            public void onSuccess(LayerDataListBean bean) {
                WaitUI.cancel();
                allSpecialLayerDataList.clear();
                allSpecialLayerDataList.addAll(bean.getResult());
                showTableInfo(bean);
            }

            @Override
            public void onError(String message) {
                WaitUI.cancel();
                ToastUtil.show(context, message);
            }
        });
    }

    private void showTableInfo(LayerDataListBean bean) {
        specialLayerDataList.clear();
        specialLayerDataList.addAll(bean.getResult());
        specialLayerDataListAdapter.notifyDataSetChanged();
        include_table_info.setVisibility(View.VISIBLE);
    }

    private void setSpecialCate3(List<SpecialMenuUpdateListBean.SubSpecialMenueBeanX.SubSpecialMenueBean> subSpecialMenue) {
        rv_special_cate3.setVisibility(View.VISIBLE);
        specialMenuList3.clear();
        specialMenuList3.addAll(subSpecialMenue);
        adapter3.notifyDataSetChanged();

        rv_special_cate1.setNestedScrollingEnabled(false);
        rv_special_cate2.setNestedScrollingEnabled(false);
        rv_special_cate3.setNestedScrollingEnabled(true);
    }

    private void setSpecialCate2(List<SpecialMenuUpdateListBean.SubSpecialMenueBeanX> subSpecialMenu) {
        rv_special_cate3.setVisibility(View.GONE);
        specialMenuList2.clear();
        specialMenuList2.addAll(subSpecialMenu);
        adapter2.notifyDataSetChanged();

        rv_special_cate1.setNestedScrollingEnabled(false);
        rv_special_cate2.setNestedScrollingEnabled(true);
        rv_special_cate3.setNestedScrollingEnabled(false);
    }

    private int getOrientation(float dy) {
        return dy > 0 ? 'b' : 't';
    }

    private void startLocation() {
        com.esri.arcgisruntime.geometry.Point point = mLocationDisplay.getMapLocation();
//        mv_map.setViewpointCenterAsync(centerPoint, 3000);
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
        final ServiceFeatureTable mainServiceFeatureTable = new ServiceFeatureTable(ConstantData.FEATURE_MAP);
        final ListenableFuture<FeatureQueryResult> featureQueryResult = mainServiceFeatureTable.queryFeaturesAsync(query, ServiceFeatureTable.QueryFeatureFields.LOAD_ALL);//不设置load_all的话，会默认获取三个字段，若加载全部数据，需设置为load_all

        mv_map.setViewpointAsync(new Viewpoint(clickPoint, mv_map.getMapScale()), 0.4f);
        featureQueryResult.addDoneListener(() -> markSelectedArea(featureQueryResult, clickPoint));
    }

    private void markSelectedArea(ListenableFuture<FeatureQueryResult> featureQueryResult, com.esri.arcgisruntime.geometry.Point clickPoint) {
        try {

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
//                    landInfoAdapter.notifyDataSetChanged();
//                    tv_land_name.setText((String) featureQueryResult.get().iterator().next().getAttributes().get("dlmc"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        landCateBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private void exitFullScreen() {
        include_fun.setVisibility(View.VISIBLE);
        include_fun.setAnimation(AnimationUtils.makeInAnimation(context, false));

        iv_compass.setVisibility(View.VISIBLE);
        iv_compass.setAnimation(AnimationUtils.makeInAnimation(context, false));

        iv_exit_full_screen.setVisibility(View.INVISIBLE);
        iv_exit_full_screen.setAnimation(AnimationUtils.makeOutAnimation(context, true));
        landCateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
//        mv_map.getGraphicsOverlays().clear();
        mv_map.getGraphicsOverlays().add(graphicsOverlay_1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mv_map.getGraphicsOverlays().clear();
        fl_container.removeView(mv_map);
    }

    List<SpecialMenuUpdateListBean> specialMenuList;

    private void getSpecialMenu() {
        HttpAction.getInstance().getSpecialMenu().subscribe(new BaseObserver<List<SpecialMenuUpdateListBean>>() {
            @Override
            public void onSuccess(List<SpecialMenuUpdateListBean> beanList) {
                if (beanList != null && beanList.size() > 0) {
                    specialMenuList.addAll(beanList);
                }
                getSpecialMenuUpdate();
            }

            @Override
            public void onError(String message) {
                getSpecialMenuUpdate();
            }
        });
    }

    private void getSpecialMenuUpdate() {
        HttpAction.getInstance().getSpecialMenuUpdate().subscribe(new BaseObserver<List<SpecialMenuUpdateListBean>>() {
            @Override
            public void onSuccess(List<SpecialMenuUpdateListBean> beanList) {
                ll_container.setVisibility(View.VISIBLE);
                pb_loading.setVisibility(View.GONE);
                if (beanList != null && beanList.size() > 0) {
                    specialMenuList.addAll(beanList);
                }
                adapter1.notifyDataSetChanged();

                if (specialMenuList == null || specialMenuList.size() == 0) {
                    return;
                }
                setSpecialCate2(specialMenuList.get(0).getSubSpecialMenue());
            }

            @Override
            public void onError(String message) {
                ll_container.setVisibility(View.VISIBLE);
                pb_loading.setVisibility(View.GONE);
                adapter1.notifyDataSetChanged();
                if (specialMenuList == null || specialMenuList.size() == 0) {
                    return;
                }
                setSpecialCate2(specialMenuList.get(0).getSubSpecialMenue());
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_special_investigation;
    }
}