package com.example.courtbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    Button btn_Submit, ViewAll, MoreInfo;
    EditText CustomerName, Contact_No, CustomerEmail;
    CalendarView Calendar;
    TextView DatePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Book a Court");
        btn_Submit = findViewById(R.id.btn_Submit);
        CustomerName = findViewById(R.id.CustomerName);
        Contact_No = findViewById(R.id.Contact_No);
        CustomerEmail = findViewById(R.id.CustomerEmail);
        Calendar = findViewById(R.id.Calendar);
        DatePreview = findViewById(R.id.DatePreview);
        ViewAll = findViewById(R.id.ViewAll);
        MoreInfo = findViewById(R.id.MoreInfo);

        Calendar.setMinDate((new Date().getTime()));


        Calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String datestring = (i1 + 1) + "/" + i2 + "/" + i;
                DatePreview.setText(datestring);
            }
        });

        btn_Submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                CustomerModel customerModel;

                try{
                    customerModel = new CustomerModel(-1, CustomerName.getText().toString(), Double.parseDouble(String.valueOf(Contact_No.getText())), CustomerEmail.getText().toString(), DatePreview.getText().toString());

                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error creating reservation", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "error", 0, "error", "error");
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

               boolean dateExists = databaseHelper.Reserved(customerModel);

                if(!dateExists){

                    databaseHelper.addOne(customerModel);

                    Toast.makeText(MainActivity.this, "Booking Placed! Thank you for your reservation.", Toast.LENGTH_SHORT).show();
                    openDialog();
                }
                else{
                    Toast.makeText(MainActivity.this, "Date is Taken! Please choose a different time.", Toast.LENGTH_LONG).show();
                }




            }
          });
        ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAllBookings();
            }
        });

        MoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMoreInformation();
            }
        });

    }
    public void openAllBookings(){
        Intent intent = new Intent(this, AllBookings.class);
        startActivity(intent);
    }
    public void openMoreInformation(){
        Intent intent = new Intent(this, MoreInformation.class);
        startActivity(intent);
    }

    public void openDialog(){
        confirmDialog confirmdialog = new confirmDialog();
        confirmdialog.show(getSupportFragmentManager(), "confirm booking");
    }


}