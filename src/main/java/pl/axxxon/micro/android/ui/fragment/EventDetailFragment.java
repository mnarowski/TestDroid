package pl.axxxon.micro.android.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import pl.axxxon.micro.android.R;

/**
 * Created by mnarowski on 30.08.14.
 */
public class EventDetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_detail_fragment, container, false);
    }
}