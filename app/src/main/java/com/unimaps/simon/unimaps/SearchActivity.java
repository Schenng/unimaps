package com.unimaps.simon.unimaps;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Access Resources
        Resources res = getResources();

        // Get the list of continents
        String[] continents = res.getStringArray(R.array.continents);

        // Generate the dropdown
        final Spinner dropdown = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, continents);
        dropdown.setAdapter(adapter);

        // Button handler
        Button ok = findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener(){

            // Pass continent name
            public void onClick(View v){
                String continent = dropdown.getSelectedItem().toString();
                Intent intent = new Intent(SearchActivity.this, SearchActivityExtended.class);
                intent.putExtra("CONTINENT", continent );
                startActivity(intent);
            }
        });

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, MainActivity.class));
            }
        });
    }
}
