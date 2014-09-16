package pl.axxxon.micro.android.ui.activity;

import android.app.Activity;
import android.os.Bundle;
//import com.actionbarsherlock.app.PreferenceActivity;
import android.preference.PreferenceActivity;
import pl.axxxon.micro.android.R;

/**
 * Created by mnarowski on 31.08.14.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }
}