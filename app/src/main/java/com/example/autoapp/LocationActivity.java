package com.example.autoapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity implements LocationListener {


    Button location_button;
    TextView location_text_lon, location_text_lat;
    LocationManager locationManager;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        DB = new DatabaseHelper(this);
        location_text_lon = (TextView) findViewById(R.id.text_location_lon);
        location_text_lat = (TextView) findViewById(R.id.text_location_lat);
        location_button = (Button) findViewById(R.id.location_button);
        location_text_lon.setText(DB.getLastLongitude());
        location_text_lat.setText(DB.getLastLatitude());

        if (ContextCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(LocationActivity.this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 100);
        }
        if (ContextCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(LocationActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        location_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });
    }

    private void getLocation(){
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, LocationActivity.this);
        }catch (Exception e){
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        location_text_lon.setText(String.valueOf(location.getLongitude()));
        location_text_lat.setText(String.valueOf(location.getLatitude()));
        Boolean insertLocation = DB.insertLocation(Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude()));
        if(insertLocation == false){
            Toast.makeText(this, "Невалидни данни", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Запазена локация", Toast.LENGTH_SHORT).show();
        }
    }

}