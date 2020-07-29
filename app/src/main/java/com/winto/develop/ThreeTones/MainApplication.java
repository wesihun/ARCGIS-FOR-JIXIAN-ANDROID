package com.winto.develop.ThreeTones;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.layers.ArcGISMapImageLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.BackgroundGrid;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.winto.develop.ThreeTones.constant.ConstantData;

import java.util.HashSet;
import java.util.Set;

public class MainApplication extends Application {
    private static MainApplication application;
    private SharedPreferences preferences = null;

    public final static String TOKEN = "token";
    public final static String SESSION = "session";
    public final static String USER_NAME = "userName";
    public final static String USER_UID = "userId";
    public final static String DEP_ID = "depId";
    public final static String DEP_NAME = "depName";
    public final static String ROLE_NAME = "roleName";

    public MapView mv_map;
    public MapView mv_map1;
    private ArcGISMap arcGISMap1;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        preferences = getSharedPreferences("ThreeTones", MODE_PRIVATE);
        initMap();
    }

    private void initMap() {
        mv_map = new MapView(application);
        BackgroundGrid backgroundGrid = new BackgroundGrid();
        backgroundGrid.setColor(0xf6f6f6f6);
        backgroundGrid.setGridLineWidth(0);
        mv_map.setBackgroundGrid(backgroundGrid);

        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud4449636536,none,NKMFA0PL4S0DRJE15166");
        mv_map.setAttributionTextVisible(false);
        ArcGISMap arcGISMap = new ArcGISMap();
        ArcGISMapImageLayer layer = new ArcGISMapImageLayer(ConstantData.DYNAMIC_MAP);
        arcGISMap.getOperationalLayers().add(layer);
        arcGISMap.setBackgroundColor(Color.parseColor("#DBE5F0"));
        mv_map.setMap(arcGISMap);

        mv_map1 = new MapView(application);
        BackgroundGrid backgroundGrid1 = new BackgroundGrid();
        backgroundGrid1.setColor(0xf6f6f6f6);
        backgroundGrid1.setGridLineWidth(0);
        mv_map1.setBackgroundGrid(backgroundGrid1);

        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud4449636536,none,NKMFA0PL4S0DRJE15166");
        mv_map1.setAttributionTextVisible(false);
        arcGISMap1 = new ArcGISMap();
        ArcGISMapImageLayer layer1 = new ArcGISMapImageLayer(ConstantData.ADMINISTRATIVE_MAP);
        arcGISMap1.getOperationalLayers().add(layer1);
        arcGISMap1.setBackgroundColor(Color.parseColor("#DBE5F0"));
        mv_map1.setMap(arcGISMap1);
    }

    public ArcGISMap getArcGISMap() {
        if (arcGISMap1.getOperationalLayers().size() >= 2) {
            arcGISMap1.getOperationalLayers().remove(1);
        }
        return arcGISMap1;
    }

    public MapView getMap() {
        return mv_map;
    }

    public MapView getMap1() {
        return mv_map1;
    }

    public static MainApplication getContext() {
        return application;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public String getToken() {
        return preferences.getString(TOKEN, "");
    }

    public void setToken(@NonNull String token) {
        preferences.edit().putString(TOKEN, token).apply();
    }

    public void setSession(HashSet<String> cookies) {
        preferences.edit().putStringSet(SESSION, cookies).apply();
    }

    public Set<String> getSession() {
        return preferences.getStringSet(SESSION, null);
    }

    public String getUserName() {
        return preferences.getString(USER_NAME, "");
    }

    public void setUserName(@NonNull String userName) {
        preferences.edit().putString(USER_NAME, userName).apply();
    }

    public int getUserUId() {
        return preferences.getInt(USER_UID, 0);
    }

    public void setUserUId(int userId) {
        preferences.edit().putInt(USER_UID, userId).apply();
    }

    public int getDepId() {
        return preferences.getInt(DEP_ID, 0);
    }

    public void setDepId(int depId) {
        preferences.edit().putInt(DEP_ID, depId).apply();
    }

    public String getDepName() {
        return preferences.getString(DEP_NAME, "");
    }

    public void setDepName(String depName) {
        preferences.edit().putString(DEP_NAME, depName).apply();
    }

    public String getRoleName() {
        return preferences.getString(ROLE_NAME, "");
    }

    public void setRoleName(String roleName) {
        preferences.edit().putString(ROLE_NAME, roleName).apply();
    }
}
