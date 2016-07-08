package br.com.frameworksystem.marvelapp.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.ui.activities.CharacterDetailActivity;
import br.com.frameworksystem.marvelapp.ui.activities.EventDetailActivity;
import br.com.frameworksystem.marvelapp.ui.activities.MainActivity;

/**
 * Created by User on 07/07/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService{

    String TAG = "push";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: "+ remoteMessage.getFrom());
        Log.d(TAG, "Notification " + remoteMessage.getNotification().getBody());

        sendNotification(remoteMessage.getNotification().getBody());

    }
    
    private void sendNotification(String messegeBody){
        Intent intent = new Intent(this, CharacterDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Marvel App - Push Notification")
                .setContentText(messegeBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
