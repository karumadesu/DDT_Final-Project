package com.example.courtbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String BOOKINGS_TABLE = "BOOKINGS_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CONTACT_NO = "CONTACT_NO";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {

        super(context, "bookings_list.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + BOOKINGS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CONTACT_NO + " DOUBLE, " + COLUMN_CUSTOMER_EMAIL + " TEXT, " + COLUMN_DATE + " TEXT)";

        db.execSQL(createTableStatement);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addOne(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CONTACT_NO, customerModel.getContact());
        cv.put(COLUMN_CUSTOMER_EMAIL, customerModel.getEmail());
        cv.put(COLUMN_DATE, customerModel.getdate());
        long insert = db.insert(BOOKINGS_TABLE, null, cv);
        if (insert == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean Reserved(CustomerModel customerModel){

        String queryString = "SELECT DATE FROM " + BOOKINGS_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                String selectedDate = cursor.getString(0);

               if(customerModel.getdate().equals(selectedDate)){
                   cursor.close();
                   db.close();
                   return true;
               }
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return false;

    }

    public List<CustomerModel> getAll(){

        List<CustomerModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + BOOKINGS_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                double ContactNo = cursor.getDouble(2);
                String customerEmail = cursor.getString(3);
                String bookingDate = cursor.getString(4);

                CustomerModel newCustomer = new CustomerModel(customerID, customerName, ContactNo, customerEmail, bookingDate);
                returnList.add(newCustomer);
            }
            while(cursor.moveToNext());
        }
        else{
               // edi don't
        }
        cursor.close();
        db.close();
        return returnList;
    }

}
