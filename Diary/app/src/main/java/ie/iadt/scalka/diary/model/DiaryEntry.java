package ie.iadt.scalka.diary.model;

import android.content.ContentValues;
import android.graphics.Picture;
import java.text.DateFormat;
import java.util.Date;
import ie.iadt.scalka.diary.database.DiaryTable;

/*model class for a DiaryEntry*/
public class DiaryEntry {

    private String mId;
    private String mTitle;
    private String mEntry;
    private String mDate;
    private int mGoodday;

    //Constructor
    public DiaryEntry(){
       //setting a date
        Date date = new Date();
        mDate = DateFormat.getDateTimeInstance().format(date);
        //setting an id
        setId(Integer.toString((int)(Math.random())));
    }
    // get and set methods
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle(){ return mTitle; }

    public void setTitle(String title){ mTitle = title; }

    public String getDate(){ return mDate; }

    public void setDate(String date){ mDate = date; }

    public String getEntry(){ return mEntry; }

    public void setEntry(String entry){ mEntry = entry; }

    public int getGoodday() {
        return mGoodday;
    }

    public void setGoodday(int goodday) {
        mGoodday = goodday;
    }

    public String getPhotoFilename(){
            return "IMG_" + getId().toString() + ".jpg";
    }

    //This class is used to store a set of values
    public ContentValues toValues(){
        ContentValues values = new ContentValues(5);
        values.put(DiaryTable.COLUMN_ID, mId);
        values.put(DiaryTable.COLUMN_TITLE, mTitle);
        values.put(DiaryTable.COLUMN_DATE, mDate);
        values.put(DiaryTable.COLUMN_ENTRY, mEntry);
        values.put(DiaryTable.COLUMN_GOODDAY, mGoodday);
        return values;
    }
}
