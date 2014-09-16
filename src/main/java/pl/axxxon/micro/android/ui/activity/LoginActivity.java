package pl.axxxon.micro.android.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import pl.axxxon.micro.android.R;
import pl.axxxon.micro.android.http.task.LoginTask;
import pl.axxxon.micro.android.ui.dialog.DialogFactory;
import pl.axxxon.micro.android.ui.helpers.ViewHolder;

/**
 * Created by mnarowski on 30.08.14.
 */
public class LoginActivity extends Activity {
    private LoginTask mLoginTask;
    private Dialog mDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void handleLoginClick(View v){
        final EditText email = (EditText) ViewHolder.get(R.id.email_input,this);
        final EditText password = (EditText) ViewHolder.get(R.id.password_input,this);
        Dialog dialog= DialogFactory.login(this);
        mLoginTask=new LoginTask(this);
        mDialog=dialog;
        dialog.show();
        mLoginTask.execute(email.getText().toString(), password.getText().toString());
    }

    public void releaseTask(){
        mLoginTask = null;
    }

    @Override
    public void onBackPressed() {
        if(mLoginTask!=null && !mLoginTask.isCancelled()){
            mLoginTask.cancel(true);
        }
        super.onBackPressed();
    }

    public void loggedIn() {
        mDialog.dismiss();
        Intent intent = new Intent(this,ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition( android.R.anim.slide_in_left, android.R.anim.slide_out_right );
    }
}