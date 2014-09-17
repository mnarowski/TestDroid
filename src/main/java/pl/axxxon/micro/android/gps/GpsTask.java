package pl.axxxon.micro.android.gps;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

/**
 * Created by mnarowski on 17.09.14.
 */
public class GpsTask extends AsyncTask<Location,Void,Void>{
    private final IGPSHandler mHandler;
    private final Context mContext;

    public GpsTask(IGPSHandler gpsActivity,Context context) {
        mHandler = gpsActivity;
        mContext = context;
    }

    @Override
    protected Void doInBackground(Location... params) {
        Location location = params[0];
        Geocoder geocoder = new Geocoder(mContext);
        List<Address> addresses =null;
        try {
            addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(addresses!=null && addresses.size() > 0){
            Address address = addresses.get(0);
            String city=address.getLocality();
            mHandler.handleAddress(city);
            String street=address.getThoroughfare();
            mHandler.handleAddress(String.format("%s/%s", city, street));
        }

        return null;
    }
}
