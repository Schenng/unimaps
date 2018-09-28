package com.unimaps.simon.unimaps;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.Map;

public class SearchActivity2 extends AppCompatActivity {

    String[] countriesList = new String[0];
    public String[] universitiesList = new String[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        // Allows me to access the resources
        final Resources res = getResources();

        String continent = getIntent().getStringExtra("CONTINENT");

        // Create the interpolated resource name
        String resourceName = continent.toLowerCase().replace(" ","").concat("Countries");

        // Reference resources by name
        final String packageName = this.getPackageName();
        int resourceIndex = getResources().getIdentifier(resourceName, "array", packageName);
        Log.d("LOGS", "OUTER INDEX:" + resourceIndex);
        Log.d("LOGS", "PACKAGE NAME:" + packageName);
        countriesList = getResources().getStringArray(resourceIndex);

        // Create the first dropdown
        final Spinner countriesDropdown = findViewById(R.id.countries);
        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countriesList);
        countriesDropdown.setAdapter(countriesAdapter);

        // Add the listener for the first dropdown in order to conditionally switch the second one
        countriesDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCountry = countriesDropdown.getSelectedItem().toString();
                Log.d("LOGS", countriesDropdown.getSelectedItem().toString());

                // Create the interpolated resource name ** HACK **
                String resourceName = selectedCountry.toLowerCase().replace(" ","").concat("Universities");

                // Reference resources by name
                Log.d("LOGS", "INNER RES:" + resourceName);
                int resourceIndex = getResources().getIdentifier(resourceName, "array", SearchActivity2.this.getPackageName());
                Log.d("LOGS", "INNER INDEX:" + resourceIndex);
                Log.d("LOGS", "INNER PACKAGE NAME:" + SearchActivity2.this.getPackageName());
                universitiesList = getResources().getStringArray(resourceIndex);

                // Create and set the second dropdown
                Spinner universitiesDropdown = findViewById(R.id.universities);
                ArrayAdapter<String> universitiesAdapter = new ArrayAdapter<String>(SearchActivity2.this, android.R.layout.simple_spinner_dropdown_item, universitiesList);
                universitiesDropdown.setAdapter(universitiesAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Util class needed for override
            }
        });
    }
}
