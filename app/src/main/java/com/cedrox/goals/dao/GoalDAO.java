package com.cedrox.goals.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cedrox.goals.contracts.GoalContract;
import com.cedrox.goals.entity.Goal;
import com.cedrox.goals.sqlhelpers.GoalDbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edelarosaraymun on 1/10/16.
 */
public class GoalDAO {
    public void insert(Goal goal, Context context) {
        GoalDbHelper goalDbHelper = new GoalDbHelper(context);
        // Gets the data repository in write mode
        SQLiteDatabase db = goalDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(GoalContract.GoalEntry.COLUMN_NAME_ENTRY_ID, goal.getId());
        values.put(GoalContract.GoalEntry.COLUMN_NAME_DESCRIPTION, goal.getDescription());
        values.put(GoalContract.GoalEntry.COLUMN_NAME_STATUS, goal.getStatus());
        values.put(GoalContract.GoalEntry.COLUMN_NAME_CREATIONDATE, goal.getCreationDate().toString());
        values.put(GoalContract.GoalEntry.COLUMN_NAME_EXPECTEDDATE, goal.getExpectedDate().toString());


        long newRowId;
        newRowId = db.insert(
                GoalContract.GoalEntry.TABLE_NAME,
                GoalContract.GoalEntry.COLUMN_NAME_FINALDATE,
                values);
    }

    public void remove(String description, Context context) {
        GoalDbHelper goalDbHelper = new GoalDbHelper(context);
        // Gets the data repository in write mode
        SQLiteDatabase db = goalDbHelper.getWritableDatabase();

        // Define 'where' part of query.
        String selection = GoalContract.GoalEntry.COLUMN_NAME_DESCRIPTION + " LIKE ?";

        // Specify arguments in placeholder order.
        String[] selectionArgs = {String.valueOf(description)};

        // Issue SQL statement.
        db.delete(GoalContract.GoalEntry.TABLE_NAME, selection, selectionArgs);
    }

    public List<Goal> readAll(Context context) {
        GoalDbHelper goalDbHelper = new GoalDbHelper(context);
        // Gets the data repository in write mode
        SQLiteDatabase db = goalDbHelper.getWritableDatabase();

        String[] projection = {
                GoalContract.GoalEntry.COLUMN_NAME_ENTRY_ID,
                GoalContract.GoalEntry.COLUMN_NAME_DESCRIPTION,
                GoalContract.GoalEntry.COLUMN_NAME_STATUS,
                GoalContract.GoalEntry.COLUMN_NAME_CREATIONDATE,
                GoalContract.GoalEntry.COLUMN_NAME_EXPECTEDDATE,
        };

        Cursor c = db.query(
                GoalContract.GoalEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        List<Goal> goalList = new ArrayList<>();
        c.moveToFirst();

        while (c.isAfterLast() == false) {
            Goal goal = new Goal(c.getInt(c.getColumnIndex(GoalContract.GoalEntry.COLUMN_NAME_ENTRY_ID)), c.getString(c.getColumnIndex(GoalContract.GoalEntry.COLUMN_NAME_DESCRIPTION)), c.getInt(c.getColumnIndex(GoalContract.GoalEntry.COLUMN_NAME_STATUS)), c.getString(c.getColumnIndex(GoalContract.GoalEntry.COLUMN_NAME_CREATIONDATE)), c.getString(c.getColumnIndex(GoalContract.GoalEntry.COLUMN_NAME_EXPECTEDDATE)));
            goalList.add(goal);
            c.moveToNext();
        }
        ;
        return goalList;
    }
}
