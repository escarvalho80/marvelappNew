package br.com.frameworksystem.marvelapp.ui.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.frameworksystem.marvelapp.R;

/**
 * Created by User on 11/07/2016.
 */
public class MapsFragment extends Fragment {

    private GoogleMap googleMap;

    public static Fragment newInstancia() {

        MapsFragment fragment = new MapsFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mapfragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MapsFragment.this.googleMap = googleMap;
                setup();
            }
        });

    }

    private void setup() {

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        if (
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == 
                        PackageManager.PERMISSION_GRANTED && 
                        ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == 
                                PackageManager.PERMISSION_GRANTED) {

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            
        }

        UiSettings settings = googleMap.getUiSettings();
        settings.setMyLocationButtonEnabled(true);
        settings.setZoomControlsEnabled(true);
        settings.setCompassEnabled(true);
       
        markerLocation();

    }

    private void markerLocation() {

        LatLng latLng = new LatLng(-19.9178713, -43.9603116);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng).title("Framework System").snippet("Rua Rio de Janeiro, 1278 - Centro - Belo Horizonte/MG");

//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pic));

        googleMap.addMarker(markerOptions);

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15); // 0 número 15 é o zoom
        googleMap.moveCamera(update);

    }
}
