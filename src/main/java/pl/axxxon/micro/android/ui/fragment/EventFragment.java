package pl.axxxon.micro.android.ui.fragment;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import com.actionbarsherlock.app.ListFragment;
import android.widget.ListView;
import pl.axxxon.micro.android.R;

/**
 * Created by mnarowski on 30.08.14.
 */
public class EventFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.events_fragment, container,false);
//        AdapterMock.bind(view, getActivity());
        return view;
    }
}