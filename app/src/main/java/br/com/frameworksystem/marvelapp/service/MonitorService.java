package br.com.frameworksystem.marvelapp.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.ui.activities.EventDetailActivity;
import br.com.frameworksystem.marvelapp.ui.activities.MainActivity;

/**
 * Created by User on 07/07/2016.
 */
public class MonitorService extends IntentService {
    public MonitorService(String name) {
        super(name);
    }

    public MonitorService(){
        super("MonitorService");
    }

    @Override
    protected void onHandleIntent(Intent i) {

        try {
            Thread.sleep(10000);
            Intent intent = new Intent(this, EventDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            Log.i("MSG", "Extra");


//            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//            NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
//                    .setSmallIcon(R.drawable.ic_marvel)
//                    .setContentTitle("Marvel App - Intent Service Notification")
//                    .setContentText("Intent service rodando")
//                    .setAutoCancel(true)
//                    .setSound(defaultSoundUri)
//                    .setContentIntent(pendingIntent);
//
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//            notificationManager.notify(0, notificationBuilder.build());



        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
