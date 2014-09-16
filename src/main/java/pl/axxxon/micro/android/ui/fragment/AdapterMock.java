package pl.axxxon.micro.android.ui.fragment;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import pl.axxxon.micro.android.log.Logger;

/**
 * Created by mnarowski on 31.08.14.
 */
public class AdapterMock {
    private final static String[] ITEMS = {"Marcin","Narowski","Good","Place","Demotywatory","Jhson","Malibu"};
    private static final String TAG = AdapterMock.class.getSimpleName();

    public static void bind(ListView pView, Context pContext) {
        final ListAdapter adapter = new ArrayAdapter<String>(pContext,android.R.layout.simple_list_item_1,ITEMS);
        pView.setAdapter(adapter);
        int count = pView.getCount();
        Logger.d(TAG, String.valueOf(count));
    }
}
