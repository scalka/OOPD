package ie.iadt.scalka.diary.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

import ie.iadt.scalka.diary.database.DiaryDbHelper;
import ie.iadt.scalka.diary.database.DiaryTable;


public class DiaryModel {
    // List<E> is an interface - defines methods for redaing, deleting etc from a list
    // ArrayList is one of the implementations for the List interface - this is used instead of a Databse for the moment
    //arraylist - gone; data is from database
    //private static ArrayList<DiaryEntry> mDiaryEntry;
    //s for static
    private SQLiteDatabase mDatabase;
    private SQLiteOpenHelper mDbHelper;

    private static DiaryModel sDiaryModel;
    private Context mAppContext;
    //constractor can not be called outside of class
    // this and get method below control the number of instances that exist for this class
    //singleton design pattern
    private DiaryModel(Context appContext){
        mAppContext = appContext;
        //mDiaryEntry = new ArrayList<>();
        mDbHelper = new DiaryDbHelper(appContext);
        mDatabase = mDbHelper.getWritableDatabase();
        seedDatabse();
    }
    public void open(){
        mDatabase = mDbHelper.getReadableDatabase();
    }
    public void close(){
        mDbHelper.close();
    }
    //this get method checks to see if DiaryMOdel is null - if it is it instantiates it
    // otherwise it returns the instance that exists
    public static DiaryModel get(Context c){
        if(sDiaryModel == null){
            sDiaryModel = new DiaryModel(c.getApplicationContext());
        }
        return sDiaryModel;
    }

    //returns the Array list of entries in the database
    public ArrayList<DiaryEntry> getmDiaryEntry(){
        ArrayList<DiaryEntry> diaryEntries = new ArrayList<>();
        // check the class DiaryTablefor a definition of these constants table_diaruy and all columns
        //query() is part of the sqlitedatabase class that seeds a query to the database
        Cursor cursor = mDatabase.query(DiaryTable.TABLE_ENTRIES, DiaryTable.ALL_COLUMNS,
                null, null, null, null, null);
        //cursor is like a list of rows from db
        while (cursor.moveToNext()){
            DiaryEntry de = new DiaryEntry();
            de.setId(cursor.getString(
                    cursor.getColumnIndex(DiaryTable.COLUMN_ID)
            ));
            de.setTitle(cursor.getString(
                    cursor.getColumnIndex(DiaryTable.COLUMN_TITLE)
            ));
            de.setDate(cursor.getString(
                    cursor.getColumnIndex(DiaryTable.COLUMN_DATE)
            ));
            de.setEntry(cursor.getString(
                    cursor.getColumnIndex(DiaryTable.COLUMN_ENTRY)
            ));
            diaryEntries.add(de);
        }
        cursor.close();
        return diaryEntries;
    };
    //populate the database
    public void seedDatabse(){
        DiaryEntry de = new DiaryEntry();

        for(int i=0; i<20; i++){
            de.setId(Integer.toString(i));
            de.setTitle("wwwwEntry title " + i);
            Date date = new Date();
            de.setDate(date.toString());
            de.setEntry("ghfhghh");
            try {
                createEntry(de);
            } catch (SQLiteException e){
                e.printStackTrace();
            }

            //mDiaryEntry.add(de);
        }
    }
    public DiaryEntry createEntry(DiaryEntry de){
        ContentValues values = de.toValues();
        mDatabase.insert(DiaryTable.TABLE_ENTRIES, null, values);
        return de;
    }


}
