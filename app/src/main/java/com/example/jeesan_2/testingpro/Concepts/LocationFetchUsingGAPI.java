package com.example.jeesan_2.testingpro.Concepts;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeesan_2.testingpro.CustomUtility.PermissionCheck;
import com.example.jeesan_2.testingpro.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.security.Permission;
import java.security.Permissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kamal on 17/09/2017.
 */

public class LocationFetchUsingGAPI extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final String TAG = "LocationFetchUsingGAPI";
    @BindView(R.id.tv)
    TextView tv;

    GoogleApiClient mGoogleApiClient = null;
    Location mLocation = null;
    LocationRequest mLocationRequest;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        buildGoogleApiClient();
        createLocationRequest();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @OnClick(R.id.btn)
    public void submit() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionCheck.location(LocationFetchUsingGAPI.this))
                dolocationFetchingWork();
        } else
            dolocationFetchingWork();


    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(20000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    private void dolocationFetchingWork() {
        Toast.makeText(this, "Permission Given pls do work", Toast.LENGTH_SHORT).show();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }

//        Toast.makeText(this, "" + isGPSEnabled(), Toast.LENGTH_SHORT).show();
//        if (!isGPSEnabled())
//            enableGPS();
//        else
//            Toast.makeText(this, "GPS already on", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 6) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                dolocationFetchingWork();
            else
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.w(TAG, "onConnected");
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mLocation = LocationServices.FusedLocationApi.getLastLocation(
//                mGoogleApiClient);
//        if (mLocation != null) {
//            tv.setText("Latitude:" + mLocation.getLatitude() + ", Longitude:" + mLocation.getLongitude());
//        }
        if (!isGPSEnabled())
            enableGPS();
        else
            Toast.makeText(this, "GPS already on", Toast.LENGTH_SHORT).show();

        startLocationUpdates();


    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    protected void stopLocationUpdates() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.w(TAG, "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w(TAG, connectionResult.getErrorMessage());
    }

    @Override
    public void onLocationChanged(Location mLocation) {
        if (mLocation != null) {
            tv.setText("Latitude:" + mLocation.getLatitude() + ", Longitude:" + mLocation.getLongitude());
        }
    }

    public void enableGPS() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(mLocationRequest);
            builder.setAlwaysShow(true);

            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi
                    .checkLocationSettings(mGoogleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    final LocationSettingsStates state = result
                            .getLocationSettingsStates();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            // All location settings are satisfied. The client can
                            // initialize location
                            // requests here.
                            Toast.makeText(LocationFetchUsingGAPI.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "SUCCESS");
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            Log.w(TAG, "RESOLUTION_REQUIRED");
                            // Location settings are not satisfied. But could be
                            // fixed by showing the user
                            // a dialog.
                            try {
                                // Show the dialog by calling
                                // startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(LocationFetchUsingGAPI.this, 1000);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }

                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have
                            // no way to fix the
                            // settings so we won't show the dialog.
                            Toast.makeText(LocationFetchUsingGAPI.this, "SETTINGS_CHANGE_UNAVAILABLE", Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "SETTINGS_CHANGE_UNAVAILABLE");
                            break;
                    }
                }
            });
        }
    }

    public boolean isGPSEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
