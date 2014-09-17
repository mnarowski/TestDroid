package pl.axxxon.micro.android.ui.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;
import pl.axxxon.micro.android.R;
import pl.axxxon.micro.android.gps.GpsService;
import pl.axxxon.micro.android.gps.GpsTask;
import pl.axxxon.micro.android.gps.IGPSHandler;

/**
 * Created by mnarowski on 15.09.14.
 */
public class GPSActivity extends Activity implements GpsService.GpsServiceListener, IGPSHandler {
    private TextView mGpsStatus;
    private TextView mGpsLocation;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ((GpsService.GpsBinder) service).getService().addListener(GPSActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        mGpsStatus = (TextView)findViewById(R.id.gps_status);
        mGpsLocation = (TextView)findViewById(R.id.gps_location);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(GPSActivity.this,GpsService.class),mConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
    }

    @Override
    public void handleAddress(final String city) {
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               Toast.makeText(GPSActivity.this, String.format("City:%s", city), Toast.LENGTH_LONG).show();
           }
       });
    }

    @Override
    public void onNewGpsLocation(Location location) {
        Toast.makeText(this,"New location",Toast.LENGTH_LONG).show();
        if(location!=null){
            new GpsTask(this,this).execute(new Location[]{location});
        }
    }

    @Override
    public void onGpsEnabled() {
        Toast.makeText(this,"Gps on",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGpsDisabled() {
        Toast.makeText(this,"Gps of",Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getGpsContext() {
        return GPSActivity.this;
    }
}