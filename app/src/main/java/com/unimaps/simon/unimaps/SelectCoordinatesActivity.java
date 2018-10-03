package com.unimaps.simon.unimaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SelectCoordinatesActivity extends AppCompatActivity {

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

                Intent intent = new Intent(SelectCoordinatesActivity.this, MapsActivity.class);
                intent.putExtra("LNG", lng );
                intent.putExtra("LAT", lat);
                startActivity(intent);
            }
        });

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SelectCoordinatesActivity.this, MainActivity.class));
            }
        });

    }
}