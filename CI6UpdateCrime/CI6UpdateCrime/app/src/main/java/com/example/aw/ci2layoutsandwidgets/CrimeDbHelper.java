package com.example.aw.ci2layoutsandwidgets;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * When we instantiate and use CrimeDbHelper in the Model class the methods we use will come from
 * the inherited SQLiteOpenHelper class.
 */
public class CrimeDbHelper extends SQLiteOpenHelper {

    public static final String tag = "CrimeDbHelper";

    public CrimeDbHelper(Context context) {
        super(context, CrimeTable.DB_NAME, null, CrimeTable.VERSION);
        Log.i(tag,"Constructor");

    }

    // onCreate() is called if the table does not exist.
    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(CrimeTable.TABLE_CREATE);
        Log.d(tag,CrimeTable.TABLE_CREATE);
    }

    // onUpgrade() is called if the table version has changed.
    // NOTE: In our case droppping the tabel and re-creating it is fine.
    // However, if you have real content in here dropping the table is not the best thing to do.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CrimeTable.TABLE_CRIMES);
        onCreate(db);
    }



//    public long insertCrimes()
//    {
//        ContentValues cv;
//        cv = new ContentValues();
//
//        for (int i = 0; i < 100; i++) {
//            cv.put(CrimeTable.COLUMN_TITLE, "Crime Number : " + i);
//
//            String date = new SimpleDateFormat("yyyy.MM.dd").format(Calendar
//                    .getInstance().getTime());
//
//            cv.put(CrimeTable.COLUMN_DATE, String.valueOf(Date.valueOf(date)));
//
//            cv.put(CrimeTable.COLUMN_SOLVED, i % 2 == 0);
//
//        }
//
//        return getWritableDatabase().insert(CrimeTable.TABLE_CRIMES, null, cv);
//    }
}

