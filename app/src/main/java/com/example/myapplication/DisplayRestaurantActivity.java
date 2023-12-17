package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class DisplayRestaurantActivity extends AppCompatActivity {
    dbHelper db;
    ArrayList<String> review_id;
    ArrayList<String> res_name;
    ArrayList<String> res_rating;
    ArrayList<String> adresseR;
    ArrayList<String> avis;
    RestaurantAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_restaurant);

        db = new dbHelper(this);
        review_id = new ArrayList<>();
        res_name = new ArrayList<>();
        res_rating = new ArrayList<>();
        adresseR = new ArrayList<>();
        avis = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.d("ActivityLifecycle", "onCreate - before storeDataInArrays");
        storeDataInArrays();
        Log.d("ActivityLifecycle", "onCreate - after storeDataInArrays");

        adapter = new RestaurantAdapter(this, review_id, res_name, adresseR, res_rating, avis);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent != null) {
            String nomRestaurant = intent.getStringExtra("nomRestaurant");
            String adresseRestaurant = intent.getStringExtra("adresseRestaurant");
            String avisRestaurant = intent.getStringExtra("avisRestaurant");
            float ratingRestaurant = intent.getFloatExtra("ratingRestaurant", 0.0f);

            if (nomRestaurant != null && adresseRestaurant != null && avisRestaurant != null) {
                db.addRestaurant(nomRestaurant, adresseRestaurant, avisRestaurant, ratingRestaurant);
                res_name.add(nomRestaurant);
                adapter.notifyDataSetChanged();
            }
        }
    }

    void storeDataInArrays() {
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){
            Log.d("DatabaseData", "No data found in the database.");
        } else {
            Log.d("DatabaseData", "Data found in the database. Count: " + cursor.getCount());
            while (cursor.moveToNext()){
                // Add logs to show the data being loaded
                Log.d("DatabaseData", "Review ID: " + cursor.getString(0));
                review_id.add(cursor.getString(0));
                res_name.add(cursor.getString(1));
                res_rating.add(cursor.getString(4));
                adresseR.add(cursor.getString(2));
                avis.add(cursor.getString(3));
            }
        }
        cursor.close();
    }
}