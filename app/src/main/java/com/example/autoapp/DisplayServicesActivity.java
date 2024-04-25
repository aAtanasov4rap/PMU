package com.example.autoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class DisplayServicesActivity extends AppCompatActivity {

    DatabaseHelper DB;
//    String filterList[] = {"Filter1", "Filter2", "Filter3"};
//    String oilList[] = {"Oil1", "Oil2", "Oil3"};
//    String KMList[] = {"102000км", "112000км.", "130000км."};
//    String PriceList[] = {"50лв.", "250лв.", "100лв."};

    String filterList[];
    String oilList[];
    String KMList[];
    String PriceList[];

    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_services);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        DB = new DatabaseHelper(this);
        filterList = DB.getAllFilterValues().toArray(new String[0]);
        oilList = DB.getAllOilValues().toArray(new String[0]);
        KMList = DB.getAllMileageValues().toArray(new String[0]);
        PriceList = DB.getAllPriceValues().toArray(new String[0]);

        listView = (ListView) findViewById(R.id.customListView);
        ServiceAdapter serviceAdapter = new ServiceAdapter(getApplicationContext(), filterList, oilList, KMList, PriceList);
        listView.setAdapter(serviceAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}