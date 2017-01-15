package com.example.aw.ci2layoutsandwidgets;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

//* This Class is a Singleton..it can only be instantiated once
// This is achieved by having the constructor and a get() method that returns the only
// instance of the class**/


public class CrimeModel {


    // NOTE: The arrayList is gone...we now get the content from the database.

    //Database variables the openHelper and the database object itself.
    private SQLiteDatabase mDatabase;
    private SQLiteOpenHelper mDbHelper;

    private static CrimeModel sCrimeModel;
    private Context mAppContext;

    // Singleton Design Pattern - The constructor is private
    private CrimeModel(Context appContext) {
        mAppContext = appContext;

        // instantiate the DB Helper. onCreate() will be called if the table needs to be set up.
        mDbHelper = new CrimeDbHelper(appContext);
        // getWritableDatabase() returns a SQLiteDatabase object. Use this object to insert(), query()
        mDatabase = mDbHelper.getWritableDatabase();

        // seed the list of crimes. Comment this out once the database has content in it.
      //   seedDatabase();
    }

    public void open(){
        mDatabase = mDbHelper.getReadableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }

    // get() method part of implementing the Singleton
    public static CrimeModel get(Context c) {
        if (sCrimeModel == null) {
            sCrimeModel
                    = new CrimeModel(c.getApplicationContext());
        }
        return sCrimeModel;
    }



    // returns a list of all the Crimes in the database. (replaces the temporary ArrayList in the last Lab
    public ArrayList<Crime> getCrimes() {
        ArrayList<Crime> crimes = new ArrayList<>();

        // Check the class CrimeTable for a definition of these Constants TABLE_CRIMES & ALL_COLUMNS
        // query() is part of the SQLiteDatabase Class that sends a query to the database.
        Cursor cursor = mDatabase.query(CrimeTable.TABLE_CRIMES, CrimeTable.ALL_COLUMNS,
                null, null, null, null, null);

        // The Cursor class is like the ResultSet class in JDBC last year ...its a list of rows from the database.
        while (cursor.moveToNext()) {
            Crime crime = new Crime();
            crime.setID(cursor.getString(
                    cursor.getColumnIndex(CrimeTable.CRIME_ID)));
            crime.setTitle(cursor.getString(
                    cursor.getColumnIndex(CrimeTable.COLUMN_TITLE)));
            crime.setDate(cursor.getString(
                    cursor.getColumnIndex(CrimeTable.COLUMN_DATE)));
            crime.setSolved(cursor.getInt(
                    cursor.getColumnIndex(CrimeTable.COLUMN_SOLVED)));


            crimes.add(crime);

        }
        cursor.close();
        return crimes;
    }


    // Hard code getCrime first to test Fragment Code.
    public Crime getCrime(String id){

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM mycrime WHERE id=?", new String[] {id+""});


        Crime crime = null;
        if(cursor.getCount() > 0) {

            cursor.moveToFirst();
             crime = new Crime();
            crime.setID(cursor.getString(
                    cursor.getColumnIndex(CrimeTable.CRIME_ID)));
            crime.setTitle(cursor.getString(
                    cursor.getColumnIndex(CrimeTable.COLUMN_TITLE)));
            crime.setDate(cursor.getString(
                    cursor.getColumnIndex(CrimeTable.COLUMN_DATE)));
            crime.setSolved(cursor.getInt(
                    cursor.getColumnIndex(CrimeTable.COLUMN_SOLVED)));


        }

        return crime;

    }

    //This method is used simply to populate the Database with Crimes...so we can demonstrate our list..not part of a real app.
    public void seedDatabase() {
        Crime   c = new Crime();
        // Populate the list with boring Crimes or the moment
        for (int i = 10; i < 20; i++) {
            c.setID(Integer.toString(i));
            c.setTitle("Crime Number " + i);
            c.setSolved(i % 2); // every other one

            c.setDate("Today");

            try {
                createCrime(c);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
    }

    // This method accepts a Crime object and converts it to a ContentValues object
    // the contentValues object is a set of Name Value pairs that is used to pass content into the database - ie. when insert() or Uupdate() are called
    // The method then calls the SQLiteDatabase method insert to insert the ContentValues object into the DB
    public Crime createCrime(Crime crime) {
        ContentValues values = crime.toValues();
        mDatabase.insert(CrimeTable.TABLE_CRIMES, null, values);
        return crime;
    }

    public void updateCrime(Crime crime){
        ContentValues values = crime.toValues();
        int result = mDatabase.update(CrimeTable.TABLE_CRIMES, values, "id = ?", new String[] {crime.getId()});
    }


    // this method communicates with the DB to see how many Crimes are in the DB table
    public long getCrimesCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, CrimeTable.TABLE_CRIMES);
    }

}

