package br.com.frameworksystem.marvelapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import br.com.frameworksystem.marvelapp.ui.activities.MainActivity;

/**
 * Created by User on 05/07/2016.
 */
public class BootBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //start activity
        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

}
