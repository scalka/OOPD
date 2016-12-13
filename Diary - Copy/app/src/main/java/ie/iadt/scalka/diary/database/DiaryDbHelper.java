package ie.iadt.scalka.diary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DiaryDbHelper extends SQLiteOpenHelper{
    public static final String tag = "DiaryDbHelper";

    public DiaryDbHelper(Context context) {
        super(context, DiaryTable.DB_NAME, null, DiaryTable.VERSION);
        Log.i(tag, "Constructor");
    }
    // onCreate called when db does not exist
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DiaryTable.TABLE_CREATE);
        Log.d(tag, DiaryTable.TABLE_CREATE);
    }
    //upUpgrade called when the vesion of table is changed
    //in real content dropping the table isnt the best option
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DiaryTable.TABLE_ENTRIES);
        onCreate(db);
    }
}
