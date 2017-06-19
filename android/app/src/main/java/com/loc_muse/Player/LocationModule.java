package com.facebook.react.modules.player;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.app.Service;
import android.os.Bundle;

public class LocationModule extends ReactContextBaseJavaModule implements LocationListener {

    private static final long DISTANCE_UPDATES = 20;
    private static final long TIME_UPDATES = 1000 * 30;

    private LocationManager locationManager;


    public LocationModule(ReactApplicationContext reactContext) {
        super(reactContext);
        
        this.locationManager = (LocationManager) reactContext.getSystemService(Service.LOCATION_SERVICE);
    }

    //Obligatoire
    @Override
    public String getName() {
        return "Location";
    }

    @ReactMethod
    public void getLocation(Callback callback){   
        if(this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            this.locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                TIME_UPDATES,
                DISTANCE_UPDATES, 
                this
            );
            Location location = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                callback.invoke(latitude, longitude);
            }
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        // call when gps become enable
    }

    @Override
    public void onProviderDisabled(String provider) {
        // call when gps become disable
    }
    
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude=location.getLatitude();
        double longitude=location.getLongitude();
    }
}