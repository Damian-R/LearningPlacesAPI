package com.example.damia.learningplacesapi.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.damia.learningplacesapi.R;
import com.example.damia.learningplacesapi.fragments.MainFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapsActivity extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {

    private GoogleApiClient mGoogleApiClient;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.container);
        if(mainFragment == null){
            mainFragment = MainFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container, mainFragment).commit();
        }

    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    public void startLocationServices(){
        Log.v("Maps", "Location services started");

        try {
            LocationRequest req = LocationRequest.create().setPriority(LocationRequest.PRIORITY_LOW_POWER);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, req, this);
        }catch (SecurityException exception){
            Log.v("Maps", exception.toString());
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 1:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationServices();
                } else {
                    // error message
                }
            }
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v("Maps", "Location changed");
        Log.v("Maps", "Long: " + location.getLongitude() + " | Lat: " + location.getLatitude());
        mainFragment.setUserMarker(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.v("Maps", "Connected");

        if(ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Log.v("Maps", "Requesting permission for location");
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
        } else {
            Log.v("Maps", "Permission already granted");
            startLocationServices();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.v("Maps", "Connected suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.v("Maps", "Connection failed");
    }



}
