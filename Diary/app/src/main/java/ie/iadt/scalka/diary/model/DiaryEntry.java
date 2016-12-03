package ie.iadt.scalka.diary.model;

import android.content.ContentValues;
import android.graphics.Picture;
import java.util.Date;
import java.util.UUID;

import ie.iadt.scalka.diary.database.DiaryTable;

public class DiaryEntry {

    private UUID uuid;
    private String mId;
    private String id;
    private String mTitle;
    private String mEntry;
    private String mDate;
    private Picture mPicture;
    private boolean mGoodDay;
    // mood, voice note etc....

    //Constructor
    public DiaryEntry(){
        uuid = UUID.randomUUID(); // no set for ID since it is automatically generated
        mDate = new Date().toString();
    }

    // get and set methods
    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.id = mId.toString();
    }

    public String getTitle(){ return mTitle; }
    public void setTitle(String title){ mTitle = title; }

    public boolean goodDay(){ return mGoodDay; }
    public void setGoodDay(boolean goodDay){ mGoodDay = goodDay; }

    public String getDate(){ return mDate; }
    public void setDate(String date){ mDate = date; }

    public String getEntry(){ return mEntry; }
    public void setEntry(String entry){ mEntry = entry; }

    @Override
    public String toString(){
        return mTitle;
    }
    public ContentValues toValues(){
        ContentValues values = new ContentValues(4);
        values.put(DiaryTable.COLUMN_ID, id);
        values.put(DiaryTable.COLUMN_TITLE, mTitle);
        values.put(DiaryTable.COLUMN_DATE, mDate);
        values.put(DiaryTable.COLUMN_ENTRY, mEntry);

        return values;
    }
}
