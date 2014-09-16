package pl.axxxon.micro.android.http.task;

import android.os.AsyncTask;
import pl.axxxon.micro.android.ui.activity.LoginActivity;

/**
 * Created by mnarowski on 31.08.14.
 */
public class LoginTask extends AsyncTask<String,Void,Boolean>{
    private final LoginActivity mActivity;

    public LoginTask(LoginActivity pLoginActivity) {
        mActivity = pLoginActivity;
    }

    @Override
    protected Boolean doInBackground(String... pStrings) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (pStrings[0].equals("admin") && pStrings[1].equals("admin1")){
            this.mActivity.loggedIn();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        mActivity.releaseTask();
        super.onPostExecute(result);
    }
}
