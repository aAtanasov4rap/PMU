package com.example.autoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddServiceActivity extends AppCompatActivity {

    Button add_button;
    CheckBox oil_check_box, filter_check_box;
    EditText km_number, price_number;


    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_service);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        add_button = (Button) findViewById(R.id.add_button);
        km_number = (EditText) findViewById(R.id.km_number);
        price_number = (EditText) findViewById(R.id.price_number);
        oil_check_box = (CheckBox) findViewById(R.id.oil_check_box);
        filter_check_box = (CheckBox) findViewById(R.id.filter_check_box);
        DB = new DatabaseHelper(this);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String km_str = km_number.getText().toString();
                String price_str = price_number.getText().toString();
                Boolean oil = oil_check_box.isChecked();
                Boolean filter = filter_check_box.isChecked();

                if(km_str.equals("")||price_str.equals("")){
                    Toast.makeText(AddServiceActivity.this, "Моля попълнете всички полета", Toast.LENGTH_SHORT).show();
                }
                else{
                    Integer km = Integer.valueOf(km_str);
                    Double price = Double.valueOf(price_str);
                    Boolean checkService = DB.insertService(oil, filter, km, price);
                    if(checkService == false){
                        Toast.makeText(AddServiceActivity.this, "Невалидни данни", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), DisplayServicesActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}