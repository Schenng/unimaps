package com.unimaps.simon.unimaps;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SearchActivity2 extends AppCompatActivity {

    String[] countriesList = new String[0];
    public String[] universitiesList = new String[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        // Access Resources
        final Resources res = getResources();

        String continent = getIntent().getStringExtra("CONTINENT");

        // Create the interpolated resource name to be able to reference by the identifier
        String resourceName = continent.toLowerCase().replace(" ","").concat("Countries");

        // Reference countriesList resource by name
        int resourceIndex = getResources().getIdentifier(resourceName, "array", this.getPackageName());
        countriesList = getResources().getStringArray(resourceIndex);

        // Create the first dropdown with the countriesList
        final Spinner countriesDropdown = findViewById(R.id.countries);
        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countriesList);
        countriesDropdown.setAdapter(countriesAdapter);

        // Add listener to the first dropdown in order to conditionally switch the second one
        countriesDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                // Get the selected country of the first dropdown
                String selectedCountry = countriesDropdown.getSelectedItem().toString();

                // Create the interpolated resource name to be able to reference by the identifier
                String resourceName = selectedCountry.toLowerCase().replace(" ","").concat("Universities");

                // Reference universitiesList resources by name
                int resourceIndex = getResources().getIdentifier(resourceName, "array", SearchActivity2.this.getPackageName());
                universitiesList = getResources().getStringArray(resourceIndex);

                // Create the second dropdown with the universitiesList
                Spinner universitiesDropdown = findViewById(R.id.universities);
                ArrayAdapter<String> universitiesAdapter = new ArrayAdapter<String>(SearchActivity2.this, android.R.layout.simple_spinner_dropdown_item, universitiesList);
                universitiesDropdown.setAdapter(universitiesAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Util
            }
        });

        // Search button
        Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                // Get the selected university of the second dropdown
                String selectedUniversity = countriesDropdown.getSelectedItem().toString();

                // Pass the university name and associated coordinates
                Intent intent = new Intent(SearchActivity2.this, MapsActivity.class);
                intent.putExtra("UNIVERSITY_NAME", selectedUniversity );
                intent.putExtra("LAT", Double.valueOf(1));
                intent.putExtra("LNG", Double.valueOf(1));
                startActivity(intent);
            }
        });

        // Back Button
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity2.this, SearchActivity.class));
            }
        });
    }
}
