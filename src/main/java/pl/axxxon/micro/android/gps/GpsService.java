package pl.axxxon.micro.android.gps;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import pl.axxxon.micro.android.log.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnarowski on 16.09.14.
 */
public class GpsService extends Service implements LocationListener {
    private IBinder mBinder = new GpsBinder();
    ;
    private List<GpsServiceListener> mListeners = new ArrayList<GpsServiceListener>();
    private Location mLastLocation;

    public static interface GpsServiceListener {
        void onNewGpsLocation(Location location);
        void onGpsEnabled();
        void onGpsDisabled();
        Context getGpsContext();
    }

    public GpsService() {
        super();
    }

    public class GpsBinder extends Binder{
        public GpsService getService(){
            return GpsService.this;
        }
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }



    public void initLocationUpdates(Context pContext) {
        LocationManager locationManager = (LocationManager) pContext.getSystemService(LOCATION_SERVICE);
        LocationProvider provider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,1f,this);
        Location last=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        mLastLocation = last;
        ;
    }

    public void addListener(GpsServiceListener listener){
        mListeners.add(listener);
        initLocationUpdates(listener.getGpsContext());
        listener.onNewGpsLocation(mLastLocation);
    }

    public void removeListener(GpsServiceListener listener){
        mListeners.remove(listener);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onLocationChanged(Location location) {
        for (GpsServiceListener listener:mListeners){
            listener.onNewGpsLocation(location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Logger.d("GPS",String.valueOf(status));
    }

    @Override
    public void onProviderEnabled(String provider) {
        for (GpsServiceListener listener:mListeners){
            listener.onGpsEnabled();
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        for (GpsServiceListener listener:mListeners){
            listener.onGpsDisabled();
        }
    }

    @Override
    public void onDestroy() {
        mListeners.clear();
        super.onDestroy();
        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        locationManager.removeUpdates(this);
    }
}
