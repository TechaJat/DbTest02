package com.example.dbtest02.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnswerDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dbtest02.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AnswerContract.AnswerEntry.TABLE_NAME + " (" +
                    AnswerContract.AnswerEntry._ID + " INTEGER PRIMARY KEY," +
                    AnswerContract.AnswerEntry.COLUMN_NAME_DEBUG + " INTEGER," +
                    AnswerContract.AnswerEntry.COLUMN_NAME_Q1 + " INTEGER," +
                    AnswerContract.AnswerEntry.COLUMN_NAME_Q2 + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AnswerContract.AnswerEntry.TABLE_NAME;

    public AnswerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
