package com.josenaves.pills.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Create tables on device.
 * Here are all the DDL for tables.
 */
public class PillsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "pills.db";

    public static final String TRUE = "1";
    public static final String FALSE = "0";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String TIMESTAMP_TYPE = " TIMESTAMP";
    private static final String BLOB_TYPE = " BLOB";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String NOT_NULL = " NOT NULL";
    private static final String AUTO_INCREMENT = " AUTOINCREMENT";

    public static final String PRIMARY_KEY = " PRIMARY KEY";

    private static final String SQL_CREATE_PHRASE =
        "CREATE TABLE "  + PhraseContract.PhraseEntry.TABLE_NAME + " (" +
                PhraseContract.PhraseEntry._ID + INTEGER_TYPE + PRIMARY_KEY + NOT_NULL + COMMA_SEP +
                PhraseContract.PhraseEntry.COLUMN_NAME_PHRASE + TEXT_TYPE + COMMA_SEP +
                PhraseContract.PhraseEntry.COLUMN_NAME_AUTHOR + TEXT_TYPE + COMMA_SEP +
                PhraseContract.PhraseEntry.COLUMN_NAME_VIEWS + INTEGER_TYPE + COMMA_SEP +
                PhraseContract.PhraseEntry.COLUMN_NAME_SHARED + INTEGER_TYPE + COMMA_SEP +
                PhraseContract.PhraseEntry.COLUMN_NAME_FAVORITE + INTEGER_TYPE +
                ")";

    private static PillsDbHelper instance = null;

    public static synchronized PillsDbHelper getInstance(final Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (instance == null) {
            instance = new PillsDbHelper(context);
        }
        return instance;
    }

    private PillsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PHRASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as we are in version 1
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as we are in version 1
    }
}
