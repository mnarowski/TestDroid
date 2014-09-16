package pl.axxxon.micro.android.ui.helpers;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import pl.axxxon.micro.android.R;
import pl.axxxon.micro.android.ui.activity.ProfileActivity;

/**
 * Created by mnarowski on 31.08.14.
 */
public class ActionBarInitializer {
    private static ActionBarInitializer ourInstance = new ActionBarInitializer();

    public static ActionBarInitializer getInstance() {
        return ourInstance;
    }

    private ActionBarInitializer() {
    }

    public void initFor(SherlockFragmentActivity pProfileActivity) {
        ActionBar actionBar = pProfileActivity.getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(pProfileActivity,R.layout.cursorlistitem_text,pProfileActivity.getResources().getStringArray(R.array.menu_list));
        actionBar.setListNavigationCallbacks(spinnerAdapter, new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                return false;
            }
        });
//        actionBar.setListNavigationCallbacks();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.addTab(actionBar.newTab().setText(pProfileActivity.getString(R.string.first_tab)).setIcon(R.drawable.icon));
//        actionBar.setO
    }
}
