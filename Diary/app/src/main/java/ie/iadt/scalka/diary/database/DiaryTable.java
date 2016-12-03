package ie.iadt.scalka.diary.database;
/* This class defines the Table myentry*/
public class DiaryTable {
    //define db name
    public static final String DB_NAME = "myentry.db";
    //version changes with onUprgade method
    public static final int VERSION = 1;
    //name of the table
    public static final String TABLE_ENTRIES = "myentry";
    //definde the columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_ENTRY = "entry";

    public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_TITLE, COLUMN_DATE, COLUMN_ENTRY};

    // SQL to create table
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_ENTRIES + " (" +
                    COLUMN_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_ENTRY + " TEXT" + ")";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_ENTRIES;
}
