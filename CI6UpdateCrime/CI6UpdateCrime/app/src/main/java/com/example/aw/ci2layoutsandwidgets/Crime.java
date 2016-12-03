package com.example.aw.ci2layoutsandwidgets;

import android.content.ContentValues;

import java.util.Date;
import java.util.UUID;

public class Crime {
    // Instance variables
    private String mId;
    private String mTitle;
    private String mDate;
    private int mSolved;

    // Constructor
    public Crime() {
    }

    public int isSolved() {
        return mSolved;
    }

    public void setSolved(int solved) {
        mSolved = solved;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    // get and set methods
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    public String getId() {
        return mId;
    }

    public  void setID(String id){mId = id;}

    @Override
    public String toString(){
        return mTitle;
    }



    // Creates a ContentValues objects and places the Crimes objects instance variables into a ContentValues object
    public ContentValues toValues() {
        ContentValues values = new ContentValues(4);

        values.put(CrimeTable.CRIME_ID, mId);
        values.put(CrimeTable.COLUMN_TITLE, mTitle);
        values.put(CrimeTable.COLUMN_DATE, mDate);
       // int intSolved = (mSolved) ? 1 : 0;
        values.put(CrimeTable.COLUMN_SOLVED, mSolved);

        return values;
    }

}
