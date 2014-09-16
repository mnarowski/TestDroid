package pl.axxxon.micro.android.ui.fragment;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import com.actionbarsherlock.app.Fragment;
import android.widget.ListView;
import pl.axxxon.micro.android.R;
import pl.axxxon.micro.android.log.Logger;

/**
 * Created by mnarowski on 30.08.14.
 */
public class BoardFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.board_fragment, container,false);
        AdapterMock.bind((ListView) view,getActivity());
        return view;
    }
}