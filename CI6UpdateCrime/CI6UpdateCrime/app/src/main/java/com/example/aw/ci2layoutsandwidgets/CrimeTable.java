package com.example.aw.ci2layoutsandwidgets;

/**
 * This Class defines the Table mycrime. If you have more than one Table you usually will have
 * a class for each table. If you database code is more complex than my example consider creating
 * seperate packages in your project and import the packages as required.
 */
public class CrimeTable {

    // Define the database name
    public static final String DB_NAME = "mycrime.db";

    // Start with version 1 - if a live app needs ot change the table strucutre the version is
    // changed and onUpgrade() in the DBHelper will be called to change the table structure
    public static final int VERSION = 1;

    // define the name of your table.
    public static final String TABLE_CRIMES = "mycrime";

    // Define the columns in the table.
    public static final String CRIME_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_SOLVED = "solved";

    public static final String[] ALL_COLUMNS =
            {CRIME_ID, COLUMN_TITLE, COLUMN_DATE,
                    COLUMN_SOLVED};

    //SQL to create table - Make sure you look up the data types that SQLite supports
    //There's not too many data types, so choose wisely
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_CRIMES + " (" +
                    CRIME_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_SOLVED + " INTEGER" + ")";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_CRIMES;
}
