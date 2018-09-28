package com.unimaps.simon.unimaps;

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

        switch (continent) {
            case "Asia":
                countriesList = res.getStringArray(R.array.asiaCountries);
                break;

            case "Africa":
                countriesList = res.getStringArray(R.array.africaCountries);
                break;

            case "North America":
                countriesList = res.getStringArray(R.array.northAmericaCountries);
                break;

            case "Europe":
                countriesList = res.getStringArray(R.array.europeCountries);
                break;
        }

        final Spinner countriesDropdown = findViewById(R.id.countries);
        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countriesList);
        countriesDropdown.setAdapter(countriesAdapter);

        countriesDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCountry = countriesDropdown.getSelectedItem().toString();
                Log.d("LOGS", countriesDropdown.getSelectedItem().toString());

                switch (selectedCountry) {
                    case "Japan":
                        universitiesList = res.getStringArray(R.array.japanUniversities);
                        Log.d("LOGS", universitiesList[0]);
                        break;

                    case "India":
                        universitiesList = res.getStringArray(R.array.indiaUniversities);
                        Log.d("LOGS", universitiesList[0]);
                        break;
                }
                Spinner universitiesDropdown = findViewById(R.id.universities);
                ArrayAdapter<String> universitiesAdapter = new ArrayAdapter<String>(SearchActivity2.this, android.R.layout.simple_spinner_dropdown_item, universitiesList);
                universitiesDropdown.setAdapter(universitiesAdapter);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Disable universities spinner
            }

        });

    }
}
