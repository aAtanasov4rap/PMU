package com.example.autoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "autoapp3.db";

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE service(id	INTEGER primary key autoincrement, oil BOOLEAN, filter BOOLEAN, mileage INTEGER, price FLOAT)");
        db.execSQL("CREATE TABLE fuel(id INTEGER primary key autoincrement, total_price FLOAT, price_per_liter FLOAT)");
        db.execSQL("CREATE TABLE location(id INTEGER primary key autoincrement, longitude FLOAT, latitude FLOAT)");
//        db.execSQL("CREATE TABLE tires(id INTEGER primary key autoincrement, price FLOAT, mileage INTEGER, note TEXT, date DATE)");
//        db.execSQL("CREATE TABLE location(id INTEGER primary key autoincrement, address TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists service");
        db.execSQL("drop table if exists fuel");
    }

    public Boolean insertService(Boolean oil, Boolean filter, Integer mileage, double price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("oil", oil);
        contentValues.put("filter", filter);
        contentValues.put("mileage", mileage);
        contentValues.put("price", price);
        long result = db.insert("service", null, contentValues);
        if(result == -1)return false;
        else return true;
    }

    public Boolean insertFuel(double total_price, double price_per_liter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("total_price", total_price);
        contentValues.put("price_per_liter", price_per_liter);
        long result = db.insert("fuel", null, contentValues);
        if(result == -1)return false;
        else return true;
    }

    public Boolean insertLocation(double longitude, double latitude){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("longitude", longitude);
        contentValues.put("latitude", latitude);
        long result = db.insert("location", null, contentValues);
        if(result == -1)return false;
        else return true;
    }

    public List<String> getAllFilterValues() {
        List<String> filterValues = new ArrayList<>();
//        filterValues.add("test1");
//        filterValues.add("test2");
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"filter"};

        try (Cursor cursor = db.query("service", columns, null, null, null, null, "mileage DESC")) {
            if (cursor.moveToFirst()) {
                do {
                    int filterValue = cursor.getInt(cursor.getColumnIndex("filter"));
                    if(filterValue == 1){
                        filterValues.add("Сменени");
                    }
                    else{
                        filterValues.add("Не сменени");
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
        }
        return filterValues;
    }

    public List<String> getAllOilValues() {
        List<String> oilValues = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"oil"};

        try (Cursor cursor = db.query("service", columns, null, null, null, null, "mileage DESC")) {
            if (cursor.moveToFirst()) {
                do {
                    int oilValue = cursor.getInt(cursor.getColumnIndex("oil"));
                    if(oilValue == 1){
                        oilValues.add("Сменено");
                    }
                    else{
                        oilValues.add("Не сменено");
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
        }
        return oilValues;
    }

    public List<String> getAllPriceValues() {
        List<String> priceValues = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"price"};

        // We are using try-with-resources statement to ensure that the Cursor is closed automatically
        try (Cursor cursor = db.query("service", columns, null, null, null, null, "mileage DESC")) {
            if (cursor.moveToFirst()) {
                do {
                    float priceValue = cursor.getFloat(cursor.getColumnIndex("price"));
                    priceValues.add(String.valueOf(priceValue)+"лв.");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
        }
        return priceValues;
    }

    public List<String> getAllMileageValues() {
        List<String> mileageValues = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"mileage"};

        // We are using try-with-resources statement to ensure that the Cursor is closed automatically
        try (Cursor cursor = db.query("service", columns, null, null, null, null, "mileage DESC")) {
            if (cursor.moveToFirst()) {
                do {
                    float mileageValue = cursor.getFloat(cursor.getColumnIndex("mileage"));
                    mileageValues.add(String.valueOf(mileageValue)+"км.");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
        }
        return mileageValues;
    }

    public List<String> getAllTotalPriceValues() {
        List<String> totalPriceValues = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"total_price"};

        try (Cursor cursor = db.query("fuel", columns, null, null, null, null, "id DESC")) {
            if (cursor.moveToFirst()) {
                do {
                    float totalPrice = cursor.getFloat(cursor.getColumnIndex("total_price"));
                    totalPriceValues.add(String.valueOf(totalPrice)+"лв.");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
        }
        return totalPriceValues;
    }

    public List<String> getAllPricePerLiterValues() {
        List<String> totalPricePerLiterValues = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"price_per_liter"};

        try (Cursor cursor = db.query("fuel", columns, null, null, null, null, "id DESC")) {
            if (cursor.moveToFirst()) {
                do {
                    float totalPrice = cursor.getFloat(cursor.getColumnIndex("price_per_liter"));
                    totalPricePerLiterValues.add(String.valueOf(totalPrice)+"лв.");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
        }
        return totalPricePerLiterValues;
    }

    public String getLastLongitude() {
        SQLiteDatabase db = this.getReadableDatabase();
        String LastLongitude = null;
        Cursor cursor = db.query("location", new String[]{"longitude"}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            LastLongitude = cursor.getString(0);
        }
        else{
            LastLongitude = "няма запис";
        }
        cursor.close();
        db.close();

        return LastLongitude;
    }

    public String getLastLatitude() {
        SQLiteDatabase db = this.getReadableDatabase();
        String LastLatitude = null;
        Cursor cursor = db.query("location", new String[]{"latitude"}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            LastLatitude = cursor.getString(0);
        }
        else{
            LastLatitude = "няма запис";
        }
        cursor.close();
        db.close();

        return LastLatitude;
    }


}

