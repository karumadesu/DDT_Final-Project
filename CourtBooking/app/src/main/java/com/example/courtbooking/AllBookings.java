package com.example.courtbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AllBookings extends AppCompatActivity {

    TextView textView;
    ListView lv_AllBookings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bookings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView = findViewById(R.id.textView);
        lv_AllBookings = findViewById(R.id.lv_AllBookings);

        DatabaseHelper databaseHelper = new DatabaseHelper(AllBookings.this);
        List<CustomerModel> allRecords = databaseHelper.getAll();

        ArrayAdapter arrayAdapter = new ArrayAdapter<CustomerModel>(AllBookings.this, android.R.layout.simple_list_item_1, allRecords);
        lv_AllBookings.setAdapter(arrayAdapter);
    }

}