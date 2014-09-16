package pl.axxxon.micro.android.http.task;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import pl.axxxon.micro.android.R;
import pl.axxxon.micro.android.api.ContentApiCall;
import pl.axxxon.micro.android.api.ICallback;
import pl.axxxon.micro.android.entity.profile.Profile;
import pl.axxxon.micro.android.ui.dialog.DialogFactory;

/**
 * Created by mnarowski on 05.09.14.
 */
public class ProfileLoader extends AsyncTaskLoader<Profile[]> implements ICallback<Profile[]> {

    private final ContentApiCall mCall;
    private Profile[] mData;
    private Dialog mDialog;

    public ProfileLoader(Context context) {
        super(context);
        mCall = new ContentApiCall(context.getString(R.string.content_uri),this);
    }

    @Override
    protected void onStartLoading() {
        if(mData!=null && mData.length > 0){
            deliverResult(mData);
            return;
        }
        forceLoad();
    }

    @Override
    public Profile[] loadInBackground() {
        mCall.execute();
        if(mData == null){
            mData = new Profile[0];
        }
        return mData;
    }






    @Override
    public void send(Profile[] params) {
        mData = params;
    }
}
