package br.com.frameworksystem.marvelapp.ui.activities;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.ui.fragments.DeviceListFragment;

/**
 * Created by User on 13/07/2016.
 */
public class BluetoothActivity extends PrincipalActivity implements DeviceListFragment.OnFragmentInteractionListener  {

    private DeviceListFragment mDeviceListFragment;
    private BluetoothAdapter BTAdapter;

    public static int REQUEST_BLUETOOTH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooh);

        BTAdapter = BluetoothAdapter.getDefaultAdapter();

        //inserindo toolbar
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //coloca titulo no topo da tela
        //mesmo comportamento da action bar, mas continua customizavel
        //setSupportActionBar(toolbar);
        // seta de voltar para a home - recria a pilha - volta para a parent a activity
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Phone does not support Bluetooth so let the user know and exit.
        if (BTAdapter == null) {
            new AlertDialog.Builder(this)
                    .setTitle("Not compatible")
                    .setMessage("Your phone does not support Bluetooth")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        if (!BTAdapter.isEnabled()) {
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, REQUEST_BLUETOOTH);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        mDeviceListFragment = DeviceListFragment.newInstance(BTAdapter);
        fragmentManager.beginTransaction().replace(R.id.container, mDeviceListFragment).commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_BLUETOOTH){
            Toast.makeText(this,"Ligado",Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onFragmentInteraction(String id) {

    }}