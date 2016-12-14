package ie.iadt.scalka.diary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*A helper class to manage database creation and version management*/

public class DiaryDbHelper extends SQLiteOpenHelper{
    private static final String tag = "DiaryDbHelper";

    public DiaryDbHelper(Context context) {
        super(context, DiaryTable.DB_NAME, null, DiaryTable.VERSION);
        Log.i(tag, "DiaryDbHelper Constructor");
    }

    // onCreate called when db does not exist
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DiaryTable.TABLE_CREATE);
        Log.d(tag, DiaryTable.TABLE_CREATE);
    }

    //onUpgrade called when the version of table is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DiaryTable.TABLE_ENTRIES);
        onCreate(db);
    }
}
