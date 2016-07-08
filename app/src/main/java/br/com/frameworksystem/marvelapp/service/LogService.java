package br.com.frameworksystem.marvelapp.service;

import android.app.IntentService;
import android.content.Intent;

import java.util.Timer;

/**
 * Created by User on 07/07/2016.
 */
public class LogService extends IntentService {
    public LogService() {
        super("LogService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String string = intent.getStringExtra(INPUT_SERVICE);


    }
}
