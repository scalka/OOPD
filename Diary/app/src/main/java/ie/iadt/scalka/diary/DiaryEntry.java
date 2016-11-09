package ie.iadt.scalka.diary;

import android.graphics.Picture;
import android.util.Log;

import java.util.Calendar;
import java.util.UUID;

import static java.util.Calendar.*;

public class DiaryEntry {
    private UUID mId;
    private String mTitle;
    private String mEntry;
    private String mDate;
    private Picture mPicture;
    // mood, voice note etc....

    //Constructor
    public DiaryEntry(){
        mId = UUID.randomUUID(); // no set for ID since it is automatically generated
        mDate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        Log.i("DiaryEntry", mDate);
    }
    // get and set methods
    public String getTitle(){ return mTitle; };
    public void setTitle(String title){ mTitle = title; };


}
