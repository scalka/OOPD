package ie.iadt.scalka.diary;

import android.graphics.Picture;
import java.util.Date;
import java.util.UUID;

import static java.util.Calendar.*;

public class DiaryEntry {
    private UUID mId;
    private String mTitle;
    private String mEntry;
    private Date mDate;
    private Picture mPicture;
    private boolean mGoodDay;
    // mood, voice note etc....

    //Constructor
    public DiaryEntry(){
        mId = UUID.randomUUID(); // no set for ID since it is automatically generated
        mDate = new Date();
    }
    // get and set methods
    public String getTitle(){ return mTitle; }
    public void setTitle(String title){ mTitle = title; }

    public boolean goodDay(){ return mGoodDay; }
    public void setGoodDay(boolean goodDay){ mGoodDay = goodDay; }

    public Date getDate(){ return mDate; }
    public void setDate(Date date){ mDate = date; }

    public String getEntry(){ return mEntry; }
    public void setEntry(String entry){ mEntry = entry; }

    @Override
    public String toString(){
        return mTitle;
    }
}
