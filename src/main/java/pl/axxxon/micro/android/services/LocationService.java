package pl.axxxon.micro.android.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by mnarowski on 15.09.14.
 */
public class LocationService extends Service {

    private IBinder mBinder;

    private class LocalBinder extends Binder{
        public Service getService(){
            return LocationService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
