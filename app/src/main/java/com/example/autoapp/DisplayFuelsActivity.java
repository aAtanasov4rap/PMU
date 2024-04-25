package com.example.autoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DisplayFuelsActivity extends AppCompatActivity {

    String TotalList[];
    String PPLList[];

    ListView listView;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_fuels);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        DB = new DatabaseHelper(this);
        TotalList = DB.getAllTotalPriceValues().toArray(new String[0]);
        PPLList = DB.getAllPricePerLiterValues().toArray(new String[0]);

        listView = (ListView) findViewById(R.id.customFuelListView);
        FuelAdapter fuelAdapter = new FuelAdapter(getApplicationContext(), TotalList, PPLList);
        listView.setAdapter(fuelAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}