package com.example.aw.ci2layoutsandwidgets;

/**
 * Created by user10 on 12/11/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrimeDataSource {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;

    public CrimeDataSource(Context context) {
        this.mContext = context;
        mDbHelper = new CrimeDbHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void open() {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Crime createCrime(Crime crime) {
        ContentValues values = crime.toValues();
        mDatabase.insert(CrimeTable.TABLE_CRIMES, null, values);
        return crime;
    }

    public long getDataItemsCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, CrimeTable.TABLE_CRIMES);
    }

    public void seedDatabase(List<Crime> dataItemList) {
        long numItems = getDataItemsCount();
        if (numItems == 0) {
            for (Crime crime :
                    dataItemList) {
                try {
                    createCrime(crime);
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Crime> getAllCrimes() {
        List<Crime> crimes = new ArrayList<>();
        Cursor cursor = mDatabase.query(CrimeTable.TABLE_CRIMES, CrimeTable.ALL_COLUMNS,
                null, null, null, null, null);

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



        }
        return crimes;
    }
}
