package com.winto.develop.ThreeTones.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.data.Feature;
import com.esri.arcgisruntime.data.FeatureQueryResult;
import com.esri.arcgisruntime.data.QueryParameters;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.layers.ArcGISMapImageLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.BackgroundGrid;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.TextSymbol;
import com.winto.develop.ThreeTones.CustomDataSource;
import com.winto.develop.ThreeTones.R;
import com.winto.develop.ThreeTones.activity.LandInfoActivity;
import com.winto.develop.ThreeTones.activity.SpecialInvestigationActivity;
import com.winto.develop.ThreeTones.activity.StatisticalAnalysisActivity;
import com.winto.develop.ThreeTones.activity.WordShareActivity;
import com.winto.develop.ThreeTones.base.BaseFragment;
import com.winto.develop.ThreeTones.constant.ConstantData;
import com.winto.develop.ThreeTones.util.DensityUtil;
import com.winto.develop.ThreeTones.util.StatusBarHelper;
import com.winto.develop.ThreeTones.util.ToastUtil;

public class HomeFragment extends BaseFragment {
    private String[] permissionList = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    private MapView mv_map;
    private View include_fun;
    private ImageView iv_position;
    private ImageView iv_compass;
    private TextView tv_zygx;
    private TextView tv_zxdc;
    private TextView tv_tjfx;
    private TextView tv_wdgx;

    private LocationDisplay mLocationDisplay;
    private GraphicsOverlay overlayLandInfo;
    private CustomDataSource dataSource;//定义一个DataSource的成员变量

    @Override
    protected void initView() {
        mv_map = findViewById(R.id.mv_map);
        include_fun = findViewById(R.id.include_fun);
        iv_position = findViewById(R.id.iv_position);
        iv_compass = findViewById(R.id.iv_compass);
        tv_zygx = findViewById(R.id.tv_zygx);
        tv_zxdc = findViewById(R.id.tv_zxdc);
        tv_tjfx = findViewById(R.id.tv_tjfx);
        tv_wdgx = findViewById(R.id.tv_wdgx);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) include_fun.getLayoutParams();
        params.topMargin = StatusBarHelper.getStatusBarHeight() + 50;
        include_fun.setLayoutParams(params);

        initMapView();
    }

    private void initMapView() {
        overlayLandInfo = new GraphicsOverlay();
        BackgroundGrid backgroundGrid = new BackgroundGrid();
        backgroundGrid.setColor(0xf6f6f6f6);
        backgroundGrid.setGridLineWidth(0);
        mv_map.setBackgroundGrid(backgroundGrid);
        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud4449636536,none,NKMFA0PL4S0DRJE15166");
        mv_map.setAttributionTextVisible(false);
        ArcGISMap arcGISMap = new ArcGISMap();
        arcGISMap.setBackgroundColor(Color.parseColor("#DBE5F0"));
        ArcGISMapImageLayer layer = new ArcGISMapImageLayer(ConstantData.ADMINISTRATIVE_MAP);
        arcGISMap.getOperationalLayers().add(layer);
        mv_map.setMap(arcGISMap);

        mLocationDisplay = mv_map.getLocationDisplay();
//        mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.RECENTER);
        mLocationDisplay.startAsync();
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initClick() {
        include_fun.setOnClickListener(v -> ToastUtil.show(context, "功能研发中"));

        iv_position.setOnClickListener(v -> {
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

        iv_compass.setOnClickListener(v -> mv_map.setViewpointRotationAsync(0));

        tv_zygx.setOnClickListener(view -> toClass(context, LandInfoActivity.class));

        tv_zxdc.setOnClickListener(v -> toClass(context, SpecialInvestigationActivity.class));

        tv_tjfx.setOnClickListener(v -> toClass(context, StatisticalAnalysisActivity.class));

        tv_wdgx.setOnClickListener(v -> toClass(context, WordShareActivity.class));

        mv_map.setOnTouchListener(new DefaultMapViewOnTouchListener(context, mv_map) {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                android.graphics.Point screenPoint = new android.graphics.Point(Math.round(e.getX()), Math.round(e.getY()));
                final com.esri.arcgisruntime.geometry.Point clickPoint = mMapView.screenToLocation(screenPoint);
                final com.esri.arcgisruntime.geometry.Point textPoint = mMapView.screenToLocation(screenPoint);
                Log.e("TAG", "onSingleTapConfirmed: " + clickPoint.getX() + "---" + clickPoint.getY());
                QueryParameters query = new QueryParameters();
                query.setGeometry(clickPoint);// 设置空间几何对象
                final ServiceFeatureTable mainServiceFeatureTable = new ServiceFeatureTable(ConstantData.FEATURE_MAP);
                final ListenableFuture<FeatureQueryResult> featureQueryResult = mainServiceFeatureTable.queryFeaturesAsync(query, ServiceFeatureTable.QueryFeatureFields.LOAD_ALL);//不设置load_all的话，会默认获取三个字段，若加载全部数据，需设置为load_all

                featureQueryResult.addDoneListener(() -> {
                    try {
                        FeatureQueryResult featureResul = featureQueryResult.get();
                        for (Object element : featureResul) {
                            if (element instanceof Feature) {
                                GraphicsOverlay graphicsOverlay_1 = new GraphicsOverlay();

                                //添加图片
                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_pos);
                                BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                                PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(bitmapDrawable);
                                pictureMarkerSymbol.setOffsetY(DensityUtil.px2dip(context, 57));
                                Graphic imgGraphic = new Graphic(clickPoint, pictureMarkerSymbol);
                                graphicsOverlay_1.getGraphics().add(imgGraphic);

                                //添加文字
                                FeatureQueryResult result = featureQueryResult.get();
                                if (result.iterator().hasNext()) {
                                    String name = (String) result.iterator().next().getAttributes().get("xzqmc");
                                    Log.e("TAG", "onSingleTapConfirmed: " + name);
                                    TextSymbol textSymbol = new TextSymbol(12, name, Color.BLACK,
                                            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM);
                                    textSymbol.setOffsetX(DensityUtil.px2dip(context, 46));
                                    Graphic graphic = new Graphic(textPoint, textSymbol);
                                    graphicsOverlay_1.getGraphics().add(graphic);
                                }

                                //更新图层
                                mMapView.getGraphicsOverlays().clear();
                                mMapView.getGraphicsOverlays().add(graphicsOverlay_1);
                                /*Feature feature;
                                    Iterator<Feature> iterator = result.iterator();
                                Log.e("TAG", "onSingleTapConfirmed: "+result.getFields().get(0).getName() +"====" +result.getFields().get(0).getAlias() );
                                while (iterator.hasNext()) {
                                    feature = iterator.next();
                                    Map<String, Object> attributes = feature.getAttributes();
                                    for (String string : attributes.keySet()) {
                                        Log.e("打印字段与对应字段数据", string + "=" + attributes.get(string));
                                    }
                                    TextSymbol textSymbol = new TextSymbol(12, (String) attributes.get("xzqmc"), Color.BLACK,
                                            TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.BOTTOM);
                                    textSymbol.setOffsetX(DensityUtil.px2dip(context, 46));
                                    Graphic graphic = new Graphic(textPoint, textSymbol);
//                                    graphicsOverlay_1.getGraphics().clear();
                                    graphicsOverlay_1.getGraphics().add(graphic);
                                }*/
                            }
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });

                return true;
            }
        });
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

    private void addLocationImage(com.esri.arcgisruntime.geometry.Point centerPoint) {
        //在定位的位置添加icon
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_pos);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(bitmapDrawable);
        pictureMarkerSymbol.setOffsetY(DensityUtil.px2dip(context, 57));

        Graphic imgGraphic = new Graphic(centerPoint, pictureMarkerSymbol);
        GraphicsOverlay graphicsOverlay_1 = new GraphicsOverlay();
        graphicsOverlay_1.getGraphics().add(imgGraphic);
        mv_map.getGraphicsOverlays().clear();
        mv_map.getGraphicsOverlays().add(graphicsOverlay_1);
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission(permissionList[0]) || checkPermission(permissionList[1])) {
                ActivityCompat.requestPermissions((Activity) context, permissionList, 1);
            }
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }
}