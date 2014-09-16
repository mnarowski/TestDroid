package pl.axxxon.micro.android.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import pl.axxxon.micro.android.R;
import pl.axxxon.micro.android.ui.activity.LoginActivity;
import pl.axxxon.micro.android.ui.helpers.ViewHolder;

/**
 * Created by mnarowski on 31.08.14.
 */
public class DialogFactory {
    public static Dialog login(Context pLoginActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(pLoginActivity);
//        builder.setTitle("Loading...");
        builder.setCancelable(false);
//        final View view =ViewHolder.get(R.layout.loading_placeholder,pLoginActivity);
        LayoutInflater inflater= (LayoutInflater) pLoginActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        builder.setView(inflater.inflate(R.layout.loading_placeholder,new FrameLayout(pLoginActivity)));
//        builder.setIcon(R.drawable.ic_launcher);
        return builder.create();
//        return null;
    }
}
