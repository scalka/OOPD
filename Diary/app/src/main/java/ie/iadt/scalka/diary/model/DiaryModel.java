package ie.iadt.scalka.diary.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import ie.iadt.scalka.diary.R;
import ie.iadt.scalka.diary.database.DiaryDbHelper;
import ie.iadt.scalka.diary.database.DiaryTable;
import ie.iadt.scalka.diary.list_fragment.DiaryListFragment;


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
            de.setGoodday(cursor.getInt(
                    cursor.getColumnIndex(DiaryTable.COLUMN_GOODDAY)
            ));
            diaryEntries.add(de);
        }
        cursor.close();
        return diaryEntries;
    };

    public DiaryEntry getDiaryEntry(String id){
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM myentry WHERE id=?", new String[] {id+""});
        DiaryEntry de = new DiaryEntry();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
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
            de.setGoodday(cursor.getInt(
                    cursor.getColumnIndex(DiaryTable.COLUMN_GOODDAY)
            ));
        }
        return de;
    }
    //populate the database
   public void seedDatabse(){
        DiaryEntry de = new DiaryEntry();

        for(int i=0; i<20; i++){
            de.setId(Integer.toString(i));
            de.setTitle("Entry title " + i);
            Date date = new Date();
            de.setDate(DateFormat.getDateTimeInstance().format(date).toString());
            de.setEntry(mAppContext.getString(R.string.lorem_ipsum));
            de.setGoodday(1);
            try {
                createEntry(de);
            } catch (SQLiteException e){
                e.printStackTrace();
            }
        }
    }

    public void addEntry(DiaryEntry de){
        getmDiaryEntry().add(de);
    }

    public boolean deleteEntry(String rowId){
        Log.d("diarymodel deleteentry", "deleeeeeting entry");
        return mDatabase.delete(DiaryTable.TABLE_ENTRIES, DiaryTable.COLUMN_ID + "=" + rowId, null) > 0;
    }

    public DiaryEntry createEntry(DiaryEntry de){
        ContentValues values = de.toValues();
        mDatabase.insert(DiaryTable.TABLE_ENTRIES, null, values);
        return de;
    }
    public void updateDiaryEntry(DiaryEntry de){
        ContentValues values = de.toValues();
        int result = mDatabase.update(DiaryTable.TABLE_ENTRIES, values, "id = ?", new String[] {de.getId()});
    }
    //this function returns objects that point to the right locations and it verifies if there is an external storage to write to
    public File getPhotoFile(DiaryEntry de){
        File externalFilesDir = mAppContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null){
            return new File (externalFilesDir, de.getPhotoFilename());
        }
        return new File (externalFilesDir, de.getPhotoFilename());
    }
}
