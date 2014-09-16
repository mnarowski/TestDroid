package pl.axxxon.micro.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import com.google.android.gcm.GCMRegistrar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pl.axxxon.micro.android.R;
import pl.axxxon.micro.android.database.Comment;
import pl.axxxon.micro.android.gps.GpsService;
import pl.axxxon.micro.android.gson.Member;
import pl.axxxon.micro.android.gson.Parliment;
import pl.axxxon.micro.android.log.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by mnarowski on 30.08.14.
 */
public class SplashActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Gson gson = new Gson();
        try {
            InputStream assetInputStream = getAssets().open("test.json");

            JsonObject commentList = gson.fromJson(new InputStreamReader(assetInputStream), JsonObject.class);
            JsonElement list = commentList.get("locals");
            Comment[] comments = gson.fromJson(list, Comment[].class);
            for (Comment c : comments) {
                Logger.d("Comments from json", c.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parliment parliment = new Parliment();
        for (int i = 0; i < 30; i++) {
            Member member = new Member();
            member.setAge(i);
            member.setName(String.format("Name of %d", i));
            member.setSurename(String.format("Surename of %d", i));
            parliment.addMember(member);
        }

        String serialized = gson.toJson(parliment);
        Logger.d("Serialized parliment", serialized);

        Parliment parliment1 = gson.fromJson(serialized, Parliment.class);
        RegisterWithGCM();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, GPSActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        };
        Timer timer = new Timer(false);
        timer.schedule(task, 4000);
    }

    private void RegisterWithGCM() {
        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
        final String regId = GCMRegistrar.getRegistrationId(this);
        if (regId.equals("")) {
            GCMRegistrar.register(this, getString(R.string.senderId)); // Note: get the sender id from configuration.
        } else {

            Log.v("Registration", "Already registered, regId: " + regId);

        }
    }
}