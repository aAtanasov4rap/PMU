package com.example.autoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddFuelActivity extends AppCompatActivity {

    Button insert_fuel;
    EditText total_price, price_per_liter;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_fuel);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        insert_fuel = (Button) findViewById(R.id.insert_fuel);
        total_price = (EditText) findViewById(R.id.total_price);
        price_per_liter = (EditText) findViewById(R.id.price_per_liter);
        DB = new DatabaseHelper(this);

        insert_fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String total_price_str = total_price.getText().toString();
                String price_per_liter_str = price_per_liter.getText().toString();

                if(total_price_str.equals("")||price_per_liter_str.equals("")){
                    Toast.makeText(AddFuelActivity.this, "Моля попълнете всички полета", Toast.LENGTH_SHORT).show();
                }
                else{
                    Double total_price_db = Double.valueOf(total_price_str);
                    Double price_per_liter_db = Double.valueOf(price_per_liter_str);
                    Boolean checkFuel = DB.insertFuel(total_price_db, price_per_liter_db);
                    if(checkFuel == false){
                        Toast.makeText(AddFuelActivity.this, "Невалидни данни", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), DisplayFuelsActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}