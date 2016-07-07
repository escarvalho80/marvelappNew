package br.com.frameworksystem.marvelapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by User on 06/07/2016.
 */
public class MarvelappService extends Service implements IMarvelappService {

    private final MarvelappServiceBinder marvelappServiceBinder = new MarvelappServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return marvelappServiceBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void iniciar() {
        Log.i("InicioFim", "Inicio do processo.");
    }

    @Override
    public void finalizar() {
        Log.i("InicioFim", "Finalizou o processo.");
    }

    public class MarvelappServiceBinder extends Binder {
        public IMarvelappService getService() {
            return MarvelappService.this;
        }
    }
}
