package pl.axxxon.micro.android.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import pl.axxxon.micro.android.R;
import pl.axxxon.micro.android.ui.fragment.*;
import pl.axxxon.micro.android.ui.helpers.ActionBarInitializer;

/**
 * Created by mnarowski on 30.08.14.
 */
public class ProfileActivity extends SherlockFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageLoaderConfiguration configuration= new ImageLoaderConfiguration.Builder(this).threadPoolSize(3).build();
        ImageLoader.getInstance().init(configuration);

        setContentView(R.layout.profile);
        ActionBarInitializer.getInstance().initFor(this);
        final FragmentTabHost fragmentTabHost= (FragmentTabHost) findViewById(R.id.fragment_tab_host);
        fragmentTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("board").setIndicator(getString(R.string.tab_home)), BoardFragment.class,new Bundle());
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("friends").setIndicator(getString(R.string.tab_friends)), FriendsFragment.class,new Bundle());
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("messages").setIndicator(getString(R.string.tab_messages)), MessagesFragment.class,new Bundle());
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("event").setIndicator(getString(R.string.tab_event)), EventFragment.class,new Bundle());
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("map").setIndicator("Map"), MapFragment.class,new Bundle());
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("comments").setIndicator("Comments"),CommentsFragment.class,new Bundle());
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("content").setIndicator("Content"),ContentFragment.class,new Bundle());
//        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("add").setIndicator("Add"))
    }

    public void publishEvent(View view) {
        
    }

    public void captureImageStart(View view) {

    }
}