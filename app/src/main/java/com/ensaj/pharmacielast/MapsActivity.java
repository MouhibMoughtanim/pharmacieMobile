package com.ensaj.pharmacielast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ensaj.pharmacielast.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Double longitudePharmacie;
    private Double latitudePharmacie;
    private String nomPharmacie;
    private FusedLocationProviderClient fusedLocationClient;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nomPharmacie = getIntent().getStringExtra("nom");
        latitudePharmacie = Double.valueOf(getIntent().getStringExtra("latitude"));
        longitudePharmacie = Double.valueOf(getIntent().getStringExtra("longitude"));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Check if the app has the necessary location permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            // Permission is already granted, display the current location on the map
            displayCurrentLocation();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            // If the permission request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted, display the current location on the map

                    displayCurrentLocation();

            } else {
                // Permission was denied, show a message explaining why the permission is necessary
                showRequestPermissionRationale();
            }
        }
    }


    @SuppressLint("MissingPermission")
    private void displayCurrentLocation() {
        try {

            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {


                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        // Display the current location on the map
                        System.out.println("curent hal3ar");
                         LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                         mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));

                        LatLng pharmacieLocation = new LatLng(latitudePharmacie, longitudePharmacie);
                        mMap.addMarker(new MarkerOptions().position(pharmacieLocation).title(nomPharmacie));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(pharmacieLocation));
                    }
                }
            });
        } catch (SecurityException e) {
            // If the app doesn't have the necessary location permission, show a message explaining why the permission is necessary
            showRequestPermissionRationale();
        }
    }

    private void showRequestPermissionRationale() {
        new AlertDialog.Builder(this)
                .setMessage("This app needs location access to function properly. Please grant location access.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // If the user agrees to grant the permission, open the app settings
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // If the user cancels the permission request, show a message
                        Toast.makeText(MapsActivity.this, "Location access is required to display the current location on the map.", Toast.LENGTH_LONG).show();
                    }
                })
                .create().show();
    }
} // Got last