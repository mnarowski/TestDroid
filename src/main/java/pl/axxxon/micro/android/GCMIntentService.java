package pl.axxxon.micro.android;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.tagmanager.Container;
import pl.axxxon.micro.android.R;
import pl.axxxon.micro.android.log.Logger;
import pl.axxxon.micro.android.services.AirBopServerUtilities;
import pl.axxxon.micro.android.ui.activity.SplashActivity;

/**
 * Created by mnarowski on 04.09.14.
 */
public class GCMIntentService extends GCMBaseIntentService {

    public static final String SENDER_ID = "367505662713";
    private int mCount = 0;

    public GCMIntentService(boolean withLocation) {
        super(SENDER_ID);
    }
    public GCMIntentService() {
        super(SENDER_ID);
    }
    /**
     * Called after the app has been registered on the GCM service
     * We now have the regID that we can use to register with the
     * AirBop servers.
     */
    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.i(TAG, "Device registered: regId = " + registrationId);
//        displayMessage(context, getString(R.string.gcm_registered));
//Get our data for the server
        AirBopServerUtilities server_data = AirBopServerUtilities.fillDefaults(registrationId);
//server_data.loadCurrentLocation(context);
        server_data.loadDataFromPrefs(context);
// Get rid of the location from the prefs so we requery next time
        AirBopServerUtilities.clearLocationPrefs(context);
        AirBopServerUtilities.register(getApplicationContext()
                , server_data);
    }
    /**
     * Called after the device has been unregisterd from the GCM server.
     * We we are registered on the AirBop servers we should unregister
     * from there as well.
     */
    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "Device unregistered");
//        displayMessage(context, getString(R.string.gcm_unregistered));
//If we are still registered with AirBop it is time to unregister
        if (GCMRegistrar.isRegisteredOnServer(context)) {
            AirBopServerUtilities.unregister(context, registrationId);
        } else {
// This callback results from the call to unregister made on
// ServerUtilities when the registration to the server failed.
            Log.i(TAG, "Ignoring unregister callback");
        }
    }
    /**
     * We have received a push notification from GCM, analyze
     * the intents bundle for the payload.
     */
    @Override
    protected void onMessage(Context context, Intent intent) {
        Log.i(TAG, "Received message");
//        displayMessage(context, "Message Received" );
        String message = null;
        String title = null;
        String url = null;
        if (intent != null) {
//Check the bundle for the pay load body and title
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
//                displayMessage(context, "Message bundle: " + bundle);
                Log.i(TAG, "Message bundle: " + bundle);
                message = bundle.getString("message");
                title = bundle.getString("title");
                url = bundle.getString("url");
            }
        }
// If there was no body just use a standard message
        if (message == null) {
            message = getString(R.string.airbop_message);
        }
        generateNotification(context, title, message, url);
    }
    @Override
    protected void onDeletedMessages(Context context, int total) {
/*
* Called when the GCM servers tells that app that
* pending messages have been deleted because the
* device was idle.
*/
        Log.i(TAG, "Received deleted messages notification");
        String message = getString(R.string.gcm_deleted, total);
//        displayMessage(context, message);
// notifies user
        generateNotification(context, "", message);
    }
    /**
     * Called on registration or unregistration error.
     * Whatever this error is, it is not recoverable
     */
    @Override
    public void onError(Context context, String errorId) {
        Log.i(TAG, "Received error: " + errorId);
//        displayMessage(context, getString(R.string.gcm_error, errorId));
    }
    /**
     * Called on a registration error that could be retried.
     * By default, it does nothing and returns true, but could be
     * overridden to change that behavior and/or display the error.
     */
    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
// log message
        Log.i(TAG, "Received recoverable error: " + errorId);
//        displayMessage(context, getString(R.string.gcm_recoverable_error,
//                errorId));
        return super.onRecoverableError(context, errorId);
    }
    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    private static void generateNotification(Context context
            , String title
            , String message) {
        int icon = R.drawable.ic_launcher;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        if ((title == null) || (title.equals(""))) {
            title = context.getString(R.string.app_name);
        }
        Intent notificationIntent = new Intent(context, SplashActivity.class);
// set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(intent)
                .setSmallIcon(icon)
                .setWhen(when)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);
    }
    private static void generateNotification(Context context
            , String title
            , String message
            , String url) {
        int icon = R.drawable.ic_launcher;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        if ((title == null) || (title.equals(""))) {
            title = context.getString(R.string.app_name);
        }
        Intent notificationIntent = null;
        if ((url == null) || (url.equals(""))) {
//just bring up the app
            notificationIntent = new Intent(context, SplashActivity.class);
        } else {
//Launch the URL
            notificationIntent = new Intent(Intent.ACTION_VIEW);
            notificationIntent.setData(Uri.parse(url));
            notificationIntent.addCategory(Intent.CATEGORY_BROWSABLE);
        }
// set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(intent)
                .setSmallIcon(icon)
                .setWhen(when)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);
    }

}
