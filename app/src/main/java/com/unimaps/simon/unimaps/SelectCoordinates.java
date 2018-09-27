package com.unimaps.simon.unimaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SelectCoordinates extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_coordinates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button coordinates = findViewById(R.id.search);
        coordinates.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                EditText lngText = findViewById(R.id.longitude);
                double lng = Double.parseDouble(lngText.getText().toString());

                EditText latText = findViewById(R.id.latitude);
                double lat = Double.parseDouble(latText.getText().toString());

                Intent intent = new Intent(SelectCoordinates.this, MapsActivity.class);
                intent.putExtra("LNG", lng );
                intent.putExtra("LAT", lat);
                startActivity(intent);
            }
        });
    }
}