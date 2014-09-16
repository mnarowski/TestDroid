package pl.axxxon.micro.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import pl.axxxon.micro.android.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mnarowski on 03.09.14.
 */
public class MapFragment extends SupportMapFragment {

    Random mRandom = new Random(System.currentTimeMillis());
    private ArrayList<LatLng> mLocations = new ArrayList<LatLng>();
    private boolean mMarkersAdded = false;

    public MapFragment() {
        super();
    }

    @Override
    public void onResume() {
        super.onResume();
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final GoogleMap map = getMap();

        if(map!=null && !mMarkersAdded){
            mMarkersAdded = true;
            map.setBuildingsEnabled(true);
            for(int i = 0;i<10;i++){

                MarkerOptions markerOptions = new MarkerOptions().title(String.format("Marker %d",i+1));
                LatLng location = new LatLng(mRandom.nextInt(90),mRandom.nextInt(90));
                mLocations.add(location);
                markerOptions.position(location);
                Marker marker=map.addMarker(markerOptions);
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));

            }
            LatLngBounds bounds = getBounds();
            final CameraUpdate update=CameraUpdateFactory.newLatLngBounds(bounds, 50);
            getView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    map.animateCamera(update);
                }
            },3000);
        }
    }

    private LatLngBounds getBounds() {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        for(LatLng point:mLocations) {
            builder.include(point);
        }
        return builder.build();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}