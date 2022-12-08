package com.example.convertisseur2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseManagement extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Database";
    private static final int DATABASE_VERSION = 1;
    private static final String COLUMN_ID = "";
    private static final String TABLE_NAME = "my_currency_and_rate";
    private static final String COLUMN_CURRENCY = "currency";
    private static final String COLUMN_RATE = "rate";

    SQLiteDatabase myDB;

    public DataBaseManagement(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (\n" +
                "    " + COLUMN_ID + " String NOT NULL,\n " +
                "    " + COLUMN_CURRENCY + " String NOT NULL,\n " +
                "    " + COLUMN_RATE + " String NOT NULL\n " +
                ");";
        sqLiteDatabase.execSQL(sql); // creat the tables

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        String sql = ("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL(sql);
        this.onCreate(sqLiteDatabase); // recreat the table

    }

    // add info to the database
    boolean addCurrencyAndRate(String currency, String rate){
        myDB = getWritableDatabase(); // writable data base
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CURRENCY,currency);
        cv.put(COLUMN_RATE, rate);
       return  myDB.insert(TABLE_NAME, null, cv) != 1; // must return -1
    }

    // get info from database
    Cursor getRateAndCurrency(){
        myDB = getReadableDatabase();
        return myDB.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    boolean updateRateAndCurrency(String id, String currency, String rate){

        myDB = getWritableDatabase(); // writable data base
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CURRENCY,currency);
        cv.put(COLUMN_RATE, rate);
        return myDB.update(TABLE_NAME, cv, COLUMN_CURRENCY+"=", new String[]{String.valueOf(id)}) > 0;

    }

    public Cursor viewDataFromDataBase(){
        myDB = this.getReadableDatabase();
        String query = "SELECT FROM" + TABLE_NAME;
        Cursor cursor = myDB.rawQuery(query,null);
        return cursor;

    }
    boolean deleteRateAndCurrency(String id){
        myDB = getWritableDatabase(); // writable data base
        return myDB.delete(TABLE_NAME, COLUMN_ID + "=?",new String[]{String.valueOf(id)}) > 0;
    }

}
