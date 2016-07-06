package br.com.frameworksystem.marvelapp.ui.activities;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.com.frameworksystem.marvelapp.broadcast.NetworkBroadcast;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by User on 01/07/2016.
 */
public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(networkBroadcast, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkBroadcast);
    }

    private NetworkBroadcast networkBroadcast = new NetworkBroadcast() {
        @Override
        public void onConnected(boolean isConnected) {
            if (isConnected) {
                Toast.makeText(PrincipalActivity.this, "Internet voltou", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PrincipalActivity.this, "Internet caiu", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
