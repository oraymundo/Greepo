package com.cedrox.goals.sqlhelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cedrox.goals.contracts.GoalContract;
import com.cedrox.goals.entity.Goal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edelarosaraymun on 1/10/16.
 */
public class GoalDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GoalsReader.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String BOOL_TYPE = " BOOL";
    private static final String DATE_TYPE = " DATE";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + GoalContract.GoalEntry.TABLE_NAME + " (" +
                    GoalContract.GoalEntry._ID + " INTEGER PRIMARY KEY," +
                    GoalContract.GoalEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    GoalContract.GoalEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    GoalContract.GoalEntry.COLUMN_NAME_STATUS + INT_TYPE + COMMA_SEP +
                    GoalContract.GoalEntry.COLUMN_NAME_CREATIONDATE + TEXT_TYPE + COMMA_SEP +
                    GoalContract.GoalEntry.COLUMN_NAME_FINALDATE + TEXT_TYPE + COMMA_SEP  +
                    GoalContract.GoalEntry.COLUMN_NAME_EXPECTEDDATE + TEXT_TYPE   +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + GoalContract.GoalEntry.TABLE_NAME;

    public GoalDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}
