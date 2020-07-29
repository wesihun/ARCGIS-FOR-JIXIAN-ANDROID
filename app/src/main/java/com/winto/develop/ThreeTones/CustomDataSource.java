package com.winto.develop.ThreeTones;

import com.esri.arcgisruntime.location.LocationDataSource;

public class CustomDataSource extends LocationDataSource {
    @Override
    protected void onStart() {
        onStartCompleted(null);
    }

    @Override
    protected void onStop() {

    }

    public void UpdateLocation(LocationDataSource.Location location){
        this.updateLocation(location);
    }
}
