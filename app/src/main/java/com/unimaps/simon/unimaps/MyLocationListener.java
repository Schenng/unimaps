package com.unimaps.simon.unimaps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class MyLocationListener implements LocationListener {

    public double lng;
    public double lat;

    @Override
    public void onLocationChanged(Location location) {
        Log.d("LOGS", "LocationListener - onLocationChanged");
        lng = location.getLongitude();
        lat = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }
}
