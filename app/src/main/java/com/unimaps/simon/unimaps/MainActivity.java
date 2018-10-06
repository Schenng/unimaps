package com.unimaps.simon.unimaps;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public MyLocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the Location Services
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();

        // Check if location services are enabled for the app
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.d("LOGS", "Location allowed");
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0 , locationListener);
        } else {
            Log.d("LOGS", "Location Disallowed - Prompt Permissions Dialog");
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        // Current Location Button
        Button currentLocation = findViewById(R.id.currentLocation);
        currentLocation.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                Log.d("LOGS", "LNG:" + locationListener.getLng() + "LAT:" + locationListener.getLat());
                intent.putExtra("LNG", locationListener.getLng());
                intent.putExtra("LAT", locationListener.getLat());
                startActivity(intent);
            }
        });

        // Select Coordinates Button
        Button coordinates = findViewById(R.id.selectCoordinates);
        coordinates.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d("LOGS", "Select Coordinates Button Clicked");
                startActivity(new Intent(MainActivity.this, SelectCoordinatesActivity.class));
            }
        });

        // Search Button
        Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d("LOGS", "Search Button Clicked");
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        //Quit Button
        Button quit = (Button) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // Handles the permission step for location
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("LOGS", "Permission Granted");
                } else {
                    Log.d("LOGS", "Permission Rejected");
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
                return;
            }
        }
    }
}
