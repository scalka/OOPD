package ie.iadt.scalka.diary.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;


public class DiaryModel {
    // List<E> is an interface - defines methods for redaing, deleting etc from a list
    // ArrayList is one of the implementations for the List interface - this is used instead of a Databse for the moment
    private static ArrayList<DiaryEntry> mDiaryEntry;
    //s for static
    private static DiaryModel sDiaryModel;
    private Context mAppContext;
    //constractor can not be called outside of class
    // this and get method below control the number of instances that exist for this class
    //singleton design pattern
    private DiaryModel(Context appContext){
        mAppContext = appContext;
        mDiaryEntry = new ArrayList<>();
        seedDatabse();
    }
    //this get method checks to see if DiaryMOdel is null - if it is it instantiates it
    // otherwise it returns the instance that exists
    public static DiaryModel get(Context c){
        if(sDiaryModel == null){
            sDiaryModel = new DiaryModel(c.getApplicationContext());
        }
        return sDiaryModel;
    }
    //returns the Array list of entries
    public ArrayList<DiaryEntry> getmDiaryEntry(){
        return mDiaryEntry;
    };
    //populate the database
    public void seedDatabse(){
        for(int i=0; i<100; i++){
            DiaryEntry de = new DiaryEntry();
            de.setTitle("Entry title " + i);
            de.setGoodDay(i%2 == 0); // every other one
            Date date = new Date();
            de.setDate(date);

            mDiaryEntry.add(de);
        }
    }



}
